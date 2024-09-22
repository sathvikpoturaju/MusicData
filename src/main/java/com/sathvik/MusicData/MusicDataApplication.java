package com.sathvik.MusicData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MusicDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(MusicDataApplication.class, args);
	}
}