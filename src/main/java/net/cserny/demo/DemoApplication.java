package net.cserny.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class DemoApplication implements CommandLineRunner {;

    @Value("${torrent.webui.baseUrl:hardcoded}")
    private String baseUrl;

    @Value("${spring.data.mongodb.database:hardcoded_db}")
    private String dbName;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("baseUrl: " + baseUrl);
        log.info("dbName: " + dbName);
    }
}
