package com.example.demo;

import com.example.demo.encryption.Encryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demo", "persondal"})
public class DemoApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);

		String toEncrypt = "Ana are mere";
		System.out.println("Encrypting string:  " + toEncrypt);

		byte[] encrypted = Encryption.encrypt(toEncrypt);
		System.out.println("Encrypted string: " + encrypted);

		System.out.println("Decrypting");
		String decrypted = Encryption.decrypt(encrypted);

		System.out.println("Decrypted text: " + decrypted);

	}

}
