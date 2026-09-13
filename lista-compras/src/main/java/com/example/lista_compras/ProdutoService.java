package com.example.lista_compras;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProdutoService {

    // Armazenamento em memória
    private final ConcurrentHashMap<String, Produto> produtos = new ConcurrentHashMap<>();

    public void adicionar(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        produtos.putIfAbsent(nome, new Produto(nome));
    }

    //lista de produtos
    public Collection<Produto> listarTodos() {
        return produtos.values();
    }

    public boolean remover(String nome) {
        return produtos.remove(nome) != null;
    }

    public boolean marcarComoComprado(String nome) {
        Produto p = produtos.get(nome);
        if (p == null) return false;
        p.setComprado(true);
        return true;
    }

    public int total() {
        return produtos.size();
    }

    //função testte
    public void teste() {
        System.out.println("INÍCIO DO TESTE");

        // 1) Adicionar produtos
        adicionar("Arroz");
        adicionar("Feijão");
        adicionar("Macarrão");
        adicionar("Arroz");
        System.out.println("Lista de produtos " + listarTodos());

        marcarComoComprado("Feijão");
        System.out.println("Feijão foi comprado ");
        System.out.println("Lista de produtos " + listarTodos());

        remover("Macarrão");
        System.out.println("Macarrão foi removido!" );

        System.out.println("Total de produtos: " + total());

        System.out.println("FIM DO TESTE ");
    }
}
