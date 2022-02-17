package com.ylv;

import com.ylv.jpa.SimpleJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(value = "com.ylv.modules",repositoryBaseClass = SimpleJpaRepositoryImpl.class)
@SpringBootApplication
public class H2Application {

    public static void main(String []args){
        SpringApplication.run(H2Application.class,args);
    }
}
