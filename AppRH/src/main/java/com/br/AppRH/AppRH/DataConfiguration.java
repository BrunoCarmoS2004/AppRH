/*package com.br.AppRH.AppRH;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//MOSTRAR QUE É UMA CLASSE DE CONFIGURAÇÃO
@Configuration
public class DataConfiguration {
    //CONFIGURAR O BANCO DE DADOS
    //Bean para injeção de dependencia importantesa
    @Bean
    public DataSource dataSource(){
        //Criar um objeto de coneção
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //setou o nome do driver do banco == MYSQL
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //Setar a url do banco
        dataSource.setUrl("jdbc:mysql://localhost:3306/apprh?useTimezone=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    //criando o JPA
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}
*/
