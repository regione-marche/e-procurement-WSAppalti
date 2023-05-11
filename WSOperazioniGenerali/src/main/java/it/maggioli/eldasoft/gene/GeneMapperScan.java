package it.maggioli.eldasoft.gene;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@MapperScan("it.maggioli.eldasoft.gene.db.mapper")
public class GeneMapperScan {
}
