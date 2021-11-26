package br.com.wellmmjr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // define ser uma application springboot
@EnableAutoConfiguration //permite que o application content do spring carregue automaticamente baseado nos jar e nas configurações
@ComponentScan // diz ao springboot que deve scannear os pacotes e encontrar arquivos de configuração
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		
//		para quando realizar metodos que registram Users: usar desta maneira a criptografia das senhas:::::
//		
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
//		String return = bCryptPasswordEncoder.encode("my password here");
	}
}
