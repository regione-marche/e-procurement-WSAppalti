package it.maggiolil.eldasoft;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDBTest {

    @Autowired
    private DataSource dataSource;

    @BeforeAll
    protected void initDB() throws SQLException {	//Non potendolo fare static, questo metodo viene chiamato ad ogni @Test
        if (!new File("src/test/resources/database.mv.db").exists()) {
            System.out.println("START - INIT");

            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS tab2\n" +
                        "(\n" +
                        "    tab2cod VARCHAR(5) ,\n" +
                        "    tab2tip VARCHAR(5) ,\n" +
                        "    tab2d1 VARCHAR(200) ,\n" +
                        "    tab2d2 VARCHAR(200) ,\n" +
                        "    tab2mod VARCHAR(1) ,\n" +
                        "    tab2nord NUMERIC(8,2),\n" +
                        "    tab2arc VARCHAR(1) ,\n" +
                        "    CONSTRAINT tab2_pkey PRIMARY KEY (tab2cod, tab2tip)\n" +
                        ")");
                statement.execute("INSERT INTO tab2(\n" +
                        "\ttab2cod, tab2tip, tab2d1, tab2d2, tab2mod, tab2nord, tab2arc)\n" +
                        "\tVALUES ('1107', '1', 'DEFPER', 'Permesso di Costruire', NULL, NULL, NULL);\n" +
                        "INSERT INTO tab2(\n" +
                        "\ttab2cod, tab2tip, tab2d1, tab2d2, tab2mod, tab2nord, tab2arc)\n" +
                        "\tVALUES ('1107', '2', 'DEFVAL', 'Valutazione Preventiva', NULL, NULL, NULL);");
                statement.execute("CREATE TABLE IF NOT EXISTS tab1\n" +
                        "(\n" +
                        "    tab1cod VARCHAR(5) ,\n" +
                        "    tab1tip NUMERIC(7,0) NOT NULL,\n" +
                        "    tab1desc VARCHAR(200) ,\n" +
                        "    tab1mod VARCHAR(1) ,\n" +
                        "    tab1nord NUMERIC(8,2),\n" +
                        "    tab1arc VARCHAR(1) ,\n" +
                        "    CONSTRAINT tab1_pkey PRIMARY KEY (tab1cod, tab1tip)\n" +
                        ")\n");
                statement.execute("INSERT INTO public.tab1(\n" +
                        "\ttab1cod, tab1tip, tab1desc, tab1mod, tab1nord, tab1arc)\n" +
                        "\tVALUES ('1007', '1', 'Permesso di Costruire', NULL, 0, NULL);");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("END - INIT");
        }
    }

}
