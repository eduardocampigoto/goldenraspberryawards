package com.goldenraspberry.awards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AwardsApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        var context = SpringApplication.run(AwardsApplication.class, args);
        context.getBean(DBInitializer.class).importFile();
    }
}
