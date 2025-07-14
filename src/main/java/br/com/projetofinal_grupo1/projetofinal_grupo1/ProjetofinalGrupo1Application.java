package br.com.projetofinal_grupo1.projetofinal_grupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.projetofinal_grupo1.projetofinal_grupo1.feign")
public class ProjetofinalGrupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetofinalGrupo1Application.class, args);
	}

}
