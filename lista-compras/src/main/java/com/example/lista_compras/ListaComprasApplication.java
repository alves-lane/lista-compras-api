package com.example.lista_compras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
@SpringBootApplication // liga spring booy
public class ListaComprasApplication {

	public static void main(String[] args) {

        SpringApplication.run(ListaComprasApplication.class, args);

	}

    //executa função teste
    @Bean
    CommandLineRunner executarTeste(ProdutoService service){ //executa no boot e roda automático
        return args -> service.teste();
    }
}
