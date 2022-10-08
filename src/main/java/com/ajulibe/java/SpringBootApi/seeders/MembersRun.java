package com.ajulibe.java.SpringBootApi.seeders;

import com.ajulibe.java.SpringBootApi.dao.MembersDAOJPAImpl;
import com.ajulibe.java.SpringBootApi.entity.Members;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;


/**
 * Used as a database seeder. This runs immediately after the application is initialized
 */

@Configuration
public class MembersRun {

    String pattern = "yyyy/MM/dd";
    SimpleDateFormat format = new SimpleDateFormat(pattern);

    @Bean
    CommandLineRunner commandLineRunner(MembersDAOJPAImpl repository) {
        return args -> {
            Members firstmember = new Members("GUEST", "GUEST", "GUEST", 0, "(000) 000-0000", format.parse("2012/07/01"), null);

            repository.save(firstmember);
        };


    }

}
