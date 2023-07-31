package com.modu.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncriptTest { 

	//@Test
	public void test() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder(); 
		String password = scpwd.encode("1234");
		System.out.println(password);
		
		
	}
	
	@Test
	   public void test1() {
	      
	      PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	      SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	      config.setPassword("gnrdhiebgnuighiregbhjesghbeuya");
	      config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
	      config.setKeyObtentionIterations("1000");
	      config.setPoolSize("1");
	      config.setProviderName("SunJCE");
	      config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	      config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
	      config.setStringOutputType("base64");
	      encryptor.setConfig(config);
	      String enc=encryptor.encrypt("TmwFwvifjy3zuvwUHWAz");
	      System.out.println(enc);
	      String dec = encryptor.decrypt(enc);
	      System.out.println(dec);
	   }
}