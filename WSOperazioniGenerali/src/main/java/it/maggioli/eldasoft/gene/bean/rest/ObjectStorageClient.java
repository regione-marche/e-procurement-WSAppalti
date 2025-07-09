package it.maggioli.eldasoft.gene.bean.rest;

import com.fasterxml.jackson.core.util.JacksonFeature;
import it.maggioli.eldasoft.gene.bean.ObjectStorageConfiguration;
import it.maggioli.eldasoft.gene.model.UploadInfo;
import it.maggioli.eldasoft.gene.util.ListUtils;
import it.maggioli.eldasoft.gene.util.Pair;
import it.maggioli.eldasoft.gene.util.exception.ObjectStorageException;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Client per contattare l'ObjectStorage-ms.
 */
@Slf4j
@Component
public final class ObjectStorageClient {

    private static final String BUCKET_REF_HEADER =  "bucket_ref";
    private static final String UUID_HEADER = "uuid";
    private static final String TENANT_HEADER = "X-TENANT-ID";

    private final Client client;
    private final ObjectStorageConfiguration obs_conf;

    public ObjectStorageClient(
        final ObjectStorageConfiguration obs_conf
        , final JSONClientConfigurer json_configurer
    ) {
        this.obs_conf = obs_conf;
        client = ClientBuilder.newClient(
                    new ClientConfig()
                        .register(MultiPartFeature.class)
                        .register(JacksonFeature.class)
                        .register(json_configurer)
                )
        ;
    }

    public boolean is_configured() {
        return StringUtils.isNotEmpty(obs_conf.url())
                && StringUtils.isNotEmpty(obs_conf.tenant());
    }

    /**
     * Inizializza la cartella dove fare l'upload creandoci un segnaposto (restituisce, bucket_ref e bucket_uuid)
     * @return
     */
    private UploadInfo initialize() {
        return inner_call(
            "GET"
            , "/v1/upload/init"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        );
    }

    public UploadInfo full_upload(final AllegatoComunicazioneType allegato_type) {
        return full_upload(allegato_type.getFile());
    }
    /**
     * Richiamare sempre, ma, fa quello che deve solo se è stato abilitato l'upload su bucket.
     *
     * @param to_upload
     * @return
     */
    public UploadInfo full_upload(final byte[] to_upload) {
        if (obs_conf.enabled() && to_upload != null && to_upload.length > 0) {
            val info = initialize();
            upload(info.uuid(), to_upload);
            confirm(info.uuid());
            return info;
        } else
            return new UploadInfo(null, null);
    }

    /**
     * Si prega di usare full_upload(...) x usi esterni alla classe.
     * @param bucket_uuid
     * @param to_upload
     */
    @SneakyThrows
    private void upload(final String bucket_uuid, final byte[] to_upload) {
        try (final FormDataMultiPart part = new FormDataMultiPart()) {
            final Entity<MultiPart> entity = Entity.entity(
                    part.bodyPart(
                        new StreamDataBodyPart(
                            "file"
                            , new ByteArrayInputStream(to_upload)
                            , "someFile.bin"
                            , MediaType.APPLICATION_OCTET_STREAM_TYPE
                        )
                    )
                    , MediaType.MULTIPART_FORM_DATA_TYPE
            );

            inner_call(
                "POST"
                , "/v1/upload/"
                , Optional.of(entity)
                , ListUtils.of(
                    Pair.of(UUID_HEADER, bucket_uuid)
                    , Pair.of(TENANT_HEADER, obs_conf.tenant())
                    , Pair.of("COUNTER", 0) // Diamo per assunto che facciamo l'upload di un solo chunk
                )
            );

        }
    }

    /**
     * Cancella il segnaposto dalla cartella contenente i chunk.
     *
     * @param bucket_uuid
     * @return
     */
    private String confirm(final String bucket_uuid) {
        return inner_call(
            "GET"
            , "/v1/upload/confirmUpload"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(UUID_HEADER, bucket_uuid)
                , Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        );
    }

    /**
     * Effettua la download dei chunk dal bucket.
     * Attualmente restituisce un byte[], ma è possibile farsi restituire uno stream di dati.
     *
     * @param bucket_ref
     * @param bucket_uuid
     * @return
     */
    @SneakyThrows
    public byte[] download(final String bucket_ref, final String bucket_uuid) {
        if (!StringUtils.isNotEmpty(bucket_ref))
            throw new ObjectStorageException("Per effettuare il download di un documento 'bucket_ref' è un campo obbligatorio");
        if (!StringUtils.isNotEmpty(bucket_uuid))
            throw new ObjectStorageException("Per effettuare il download di un documento 'bucket_uuid' è un campo obbligatorio");

        final InputStream stream = inner_call(
                "POST"
                , "/v1/download/"
                , Optional.empty()
                , ListUtils.of(
                    Pair.of(UUID_HEADER, bucket_uuid + "/0")    // Nel nostro caso abbiamo un solo chunk
                    , Pair.of(BUCKET_REF_HEADER, bucket_ref)
                    , Pair.of(TENANT_HEADER, obs_conf.tenant())
                )
        );
        try (val bao = new ByteArrayOutputStream()) {
            var nRead = -1;
            val data = new byte[16384];
            while ((nRead = stream.read(data, 0, data.length)) != -1)
                bao.write(data, 0, nRead);
            return bao.toByteArray();
        }
    }

    /**
     * Effettua la restore dei chunk cancellati logicamente sul bucket.
     *
     * @param bucket_ref
     * @param bucket_uuid
     */
    public void logical_restore_folder(final String bucket_ref, final String bucket_uuid) {
        inner_call(
            "POST"
            , "/v1/delete/logical-restore"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(UUID_HEADER, bucket_uuid)
                , Pair.of(BUCKET_REF_HEADER, bucket_ref)
                , Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        );
    }

    /**
     * Sposta i chunk su un'altra cartella destinata alla cancellazione.
     * I file su questa cartella vengono cancellati 1 giorno dopo la loro persistenza.
     *
     * @param bucket_ref
     * @param bucket_uuid
     */
    public void logical_delete_folder(final String bucket_ref, final String bucket_uuid) {
        inner_call(
            "DELETE"
            , "/v1/delete/logical-folder"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(UUID_HEADER, bucket_uuid)
                , Pair.of(BUCKET_REF_HEADER, bucket_ref)
                , Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        );
    }

    public Optional<Long> first_chunk_size(final String bucket_ref, final String bucket_uuid) {
        return Optional.ofNullable(inner_call(
            "GET"
            , "/v1/metadata/size"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(UUID_HEADER, bucket_uuid + "/0")
                , Pair.of(BUCKET_REF_HEADER, bucket_ref)
                , Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        ));
    }


    /**
     * Cancella definitivamente una cartella dal bucket.
     *
     * @param bucket_ref
     * @param bucket_uuid
     */
    public void delete_folder(final String bucket_ref, final String bucket_uuid) {
        inner_call(
            "DELETE"
            , "/v1/delete/folder"
            , Optional.empty()
            , ListUtils.of(
                Pair.of(UUID_HEADER, bucket_uuid)
                , Pair.of(BUCKET_REF_HEADER, bucket_ref)
                , Pair.of(TENANT_HEADER, obs_conf.tenant())
            )
        );
    }

    /**
     * true = gestione bucket abilitata
     * false = gestione bucket non attiva (default)
     * @return
     */
    public boolean is_enabled() {
        return obs_conf.enabled();
    }

    @SafeVarargs
    private final <T> T inner_call(
            final String method
            , final String path
            , final Optional<Entity<?>> entity
            , final List<Pair<String, Object>> headers
            , final T...return_type
    ) {
        val to_return = reified_class(return_type);
        val builder = client.target(obs_conf.url()).path(path).request();
        if (headers != null && !headers.isEmpty())
            headers.forEach(it -> builder.header(it.key(), it.value()));

        val invocation = StringUtils.equals(method, "PUT") || StringUtils.equals(method, "POST")
                ? builder.build(method, entity.orElse(Entity.json("")))
                : builder.build(method);

        return invocation.invoke(to_return);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> reified_class(final T...array) {
        if (array == null) throw new RuntimeException("Don't pass any arguments to this method");
        return (Class<T>) array.getClass().getComponentType();
    }

//    public static void main(final String[] args) {
//        val obj = new ObjectStorageClient(
//                new ObjectStorageConfiguration(
//                    Optional.of("postgres1")
//                    , Optional.of("http://localhost:8050/rest/objectstorage-ms")
//                    , true
//                )
//                , new JSONClientConfigurer()
//        );
//
//        val info = obj.full_upload("Testo molto molto semplice per fare un test iniziale di upload tramite WSAppalti".getBytes());
//        System.out.println("Upload Done " + info);
//        val result = obj.download(info.bucket_ref(), info.uuid());
//        System.out.println("Output from download: " + new String(result));
//
//        obj.logical_delete_folder(info.bucket_ref(), info.uuid());
//
//        System.out.println("Logical delete succeded");
//
//        obj.logical_restore_folder(info.bucket_ref(), info.uuid());
//
//        System.out.println("Logical restored folder");
//
//        System.out.println("Asking size");
//        val size = obj.first_chunk_size(info.bucket_ref(), info.uuid());
//        System.out.println("The size is: " + size);
//
//    }

}
