package it.maggioli.eldasoft.sil.lfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@MapperScan("it.maggioli.eldasoft.sil.lfs.db.mapper")
public class LFSMapperScan {
}