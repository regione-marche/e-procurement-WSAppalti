package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.ws.dto.CategoryForOperator;
import it.maggioli.eldasoft.sil.gare.ws.dto.ElencoOperatoriAbilitatiElenco;
import it.maggioli.eldasoft.sil.gare.ws.search.ElencoOperatoriAbilitatiSearch;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ElenchiMapper {

    String IS_PRESENT_ENABLED_LIST_FOR_IMPR =
            "select " +
            "    1 " +
            "FROM " +
            "    iscrizcat " +
            "    LEFT join CAIS on ISCRIZCAT.CODCAT = CAIS.CAISIM " +
            "    LEFT join tab1 CL ON CL.tab1cod = 'A1015' AND CL.tab1tip = iscrizcat.supnumclass " +
            "where " +
            "    cais.caisim IS NOT NULL " +
            "    and iscrizcat.codimp = I.CODIMP " +
            "    and iscrizcat.ngara = ditg.ngara5 " +
            "    and ditg.ngara5 = iscrizcat.ngara " +
//            "    AND iscrizcat.codcat &lt;&gt; '0' " +
            "    <if test='search.classification != null and !search.classification.isEmpty()'>and ${funUpper}(${trimFun}(CL.tab1desc))) = ${funUpper}(#{search.classification})</if> " +
            "    <if test='search.category != null and !search.category.isEmpty()'>AND (${funUpper}(iscrizcat.codcat) LIKE #{search.category} OR ${funUpper}(cais.descat) like #{search.category})</if> ";
    String FROM_IMPRESE_ABILITATE_A_ELENCO =
            "FROM ditg " +
            "    join impr I ON ditg.dittao = I.codimp " +
            "    join tab1 A on A.tab1cod = 'A1075' and (A.tab1tip &gt;= 1 or A.tab1tip &lt;= 10) " +
            "    left join ( " +
            "                select " +
            "                    distinct situazdoci " +
            "                    , ngara " +
            "                    , codimp " +
            "                FROM v_gare_docditta " +
            "                WHERE situazdoci = 3 " +
            "    ) situaz ON ditg.NGARA5 = situaz.ngara and situaz.codimp = I.CODIMP " +
            "    <if test='isRer'> " +
            "       left join ximpr XI ON XI.xcodimp = I.codimp " +
            "       left join tab1 RT ON RT.tab1cod = 'X0001' and RT.tab1tip = XI.XIMPRRATING" +
            "   </if> " +
            "WHERE " +
            "    ((ditg.abilitaz = 1 or ditg.abilitaz = 8) and ditg.abilitaz = A.tab1tip)" +
            "    <if test='search.stato != null'>and (situazdoci <if test='search.stato'> = 3</if><if test='!search.stato'>&lt;&gt; 3 or situazdoci IS NULL</if>)</if> " +
            "    <if test='search.codElenco != null and !search.codElenco.isEmpty()'>AND ${funUpper}(ditg.NGARA5) LIKE #{search.codElenco}</if> " +
            "    <if test='(search.category != null and !search.category.isEmpty()) or (search.classification != null and !search.classification.isEmpty())'>AND 1 IN (" + IS_PRESENT_ENABLED_LIST_FOR_IMPR + ")</if> " +
            "    <if test='search.fiscalCode != null and !search.fiscalCode.isEmpty()'>and ${funUpper}(I.cfimp) like #{search.fiscalCode}</if> " +
            "    <if test='search.city != null and !search.city.isEmpty()'>and ${funUpper}(I.locimp) like #{search.city}</if> " +
            "    <if test='search.province != null and !search.province.isEmpty()'>and I.proimp = #{search.province}</if> " +
            "    <if test='search.rating != null and !search.rating.isEmpty()'>and RT.tab1tip = #{search.rating}</if> " +
            "    <if test='isRer'>and ditg.NGARA5='E00001'</if> " +
            "    <if test='search.businessName != null and !search.businessName.isEmpty()'>and ${funUpper}(I.NOMIMP) like #{search.businessName}</if> ";

    String SELECT_IMPRESE_CON_ABILITAZIONI_A_ELENCO =
            "<script> " +
            "SELECT " +
            "    ${trimFun}(i.nomest)) as businessName " +
            "    , cfimp as fiscalCode " +
            "    , locimp as city " +
            "    , proimp AS province " +
                    //Per qualche assurdo motivo con postgresql se chiamo il campo "stato" come il campo sul dto, mi da un errore di parsing perchè si aspetta un booleano (non è mappato nel @Results)
            "    , case when situaz.situazdoci = 3 then 1 ELSE 0 end as stato " +
            "    , ditg.abilitaz as abilitazione " +
            "    , <choose><when test='isRer'>RT.tab1desc</when><otherwise>''</otherwise></choose> as rating " +
            "    , I.codimp as codimp " +
            "    , <choose><when test='search.category != null and !search.category.isEmpty()'>#{search.category}</when><otherwise>''</otherwise></choose> as codcat " +
            "    , ditg.ngara5 as codElenco " +
            "    , <choose><when test='search.classification != null and !search.classification.isEmpty()'>#{search.classification}</when><otherwise>''</otherwise></choose> as classification " +
            "    , #{trimFun} as trimFun " +
            "    , #{funUpper} as funUpper " +
            FROM_IMPRESE_ABILITATE_A_ELENCO +
            "order by ${trimFun}(I.nomest)) " +
            "</script>";

    String COUNT_IMPRESE_CON_ABILITAZIONE_A_ELENCO_RETURN =
            "<script> " +
            "SELECT " +
            "    COUNT(*) " +
            FROM_IMPRESE_ABILITATE_A_ELENCO +
            "</script>";

    @Select(COUNT_IMPRESE_CON_ABILITAZIONE_A_ELENCO_RETURN)
    long countElencoOperatoriAbilitatiElenchi(
            @Param("search") ElencoOperatoriAbilitatiSearch search
            , @Param("isRer") boolean isRer
            , @Param("trimFun") String trimFun
            , @Param("funUpper") String funUpper
    );

    @Select(SELECT_IMPRESE_CON_ABILITAZIONI_A_ELENCO)
    @Results(id = "elencoOperatoriAbilitatiElenchiResult", value = {
            @Result(property = "businessName", column = "businessName")
            , @Result(property = "fiscalCode", column = "fiscalCode")
            , @Result(property = "city", column = "city")
            , @Result(property = "province", column = "province")
            , @Result(property = "rating", column = "rating")
            , @Result(property = "stato", column = "stato")
            , @Result(property = "abilitazione", column = "abilitazione")
            , @Result(property = "categories", column = "{codimp=codimp,codcat=codcat,codElenco=codElenco,classifica=classification,trimFun=trimFun,funUpper=funUpper}", many = @Many(select = "getCategoriaPerOperatore"))
    })
//    @Result(property = "categoryToConcat", column = "{codimp,codcat}", many = @Many(select = "getCategoriaPerOperatore"))
    Cursor<ElencoOperatoriAbilitatiElenco> getElencoOperatoriAbilitatiElenchi(
            @Param("search") ElencoOperatoriAbilitatiSearch search
            , @Param("isRer") boolean isRer
            , @Param("trimFun") String trimFun
            , @Param("funUpper") String funUpper
            , RowBounds bounds
    );

    String SELECT_CATEGORIE_PER_OPERATORE =
            "<script> " +
            "select " +
            "   cais.caisim as caisim" +
            "   , cais.descat as descat" +
            "   , CL.tab1desc as classifica " +
            "   , ISCRIZCAT.NGARA as codElenco " +
            "FROM iscrizcat " +
            "   LEFT join cais ON iscrizcat.codimp = #{codimp} AND iscrizcat.codcat = cais.caisim " +
            "   left join tab1 CL ON CL.tab1cod = 'A1015' AND CL.tab1tip = iscrizcat.supnumclass " +
            "WHERE cais.caisim IS NOT NULL " +
            "   and iscrizcat.NGARA = #{codElenco} " +
//            "   AND iscrizcat.codcat &lt;&gt; '0' " +
            "   <if test='codcat != null and !codcat.isEmpty()'>and (${funUpper}(cais.caisim) like #{codcat} OR cais.descat like #{codcat})</if> " +
            "   <if test='classifica != null and !classifica.isEmpty()'>and ${funUpper}(${trimFun}(CL.tab1desc))) = ${funUpper}(#{classifica})</if> " +
            "</script>";

    @Select(SELECT_CATEGORIE_PER_OPERATORE)
    @Results(id = "elencoCategoriePerOperatore", value = {
            @Result(property = "classifica", column = "classifica")
            , @Result(property = "caisim", column = "caisim")
            , @Result(property = "descat", column = "descat")
    })
    List<CategoryForOperator> getCategoriaPerOperatore(
            @Param("codimp") String codimp
            , @Param("codcat") String codcat
            , @Param("codElenco") String codElenco
            , @Param("funUpper") String funUpper
            , @Param("trimFun") String trimFun
    );

}
