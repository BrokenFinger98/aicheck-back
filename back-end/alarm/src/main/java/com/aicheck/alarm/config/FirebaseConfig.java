package com.aicheck.alarm.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

	@Value("${firebase.config.path}")
	private String firebaseConfigPath;

	@PostConstruct
	public void initializeFirebase() {
		try {
			InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();

			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		} catch (IOException e) {
			throw new RuntimeException("Firebase 초기화 실패", e);
		}
	}
}