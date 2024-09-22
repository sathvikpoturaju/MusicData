package com.sathvik.MusicData;

import com.sathvik.MusicData.config.AppConfig;
import com.sathvik.MusicData.repository.AppConfigParamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(MusicDataApplication.class, args);
	}

	@Bean
	public AppConfig appConfig(AppConfigParamRepository appConfigParamRepository) {
		return new AppConfig(appConfigParamRepository);
	}
}