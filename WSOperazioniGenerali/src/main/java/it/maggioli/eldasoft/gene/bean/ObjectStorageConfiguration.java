package it.maggioli.eldasoft.gene.bean;

import it.maggioli.eldasoft.gene.util.exception.ObjectStorageException;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Accessors(fluent = true)
// Sarebbe meglio @ConfigurationProperties, ma, non c'è in questa versione
@Component
public class ObjectStorageConfiguration {

    private final String tenant;
    private final String url;
    private final boolean enabled;

    public ObjectStorageConfiguration(
        @Value("${objectStorage.tenantId:}") final Optional<String> tenant,
        @Value("${objectStorage.url:}") final Optional<String> url,
        @Value("${objectStorage.enable:false}") final boolean enabled
    ) {
        this.enabled = enabled;
        if (enabled) {
            if (!tenant.isPresent())
                throw new ObjectStorageException("Per abilitare la nuova gestione con i bucket, è necessario inserire l'environment 'objectStorage.tenantId'");
            if (!url.isPresent())
                throw new ObjectStorageException("Per abilitare la nuova gestione con i bucket, è necessario inserire l'environment 'objectStorage.url'");
        }
        this.tenant = tenant.orElse(null);
        this.url = url.orElse(null);
    }

}
