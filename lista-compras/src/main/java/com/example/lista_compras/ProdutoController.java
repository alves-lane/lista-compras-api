package com.example.lista_compras;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController// marca classe como controlado REST
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Map <String, String> body){ // recebe json e converte para map
        service.adicionar(body.get("nome"));
    }

    @GetMapping
    public Collection<Produto> listar(){
        return service.listarTodos();
    }

    @DeleteMapping("/{produto}")
    public ResponseEntity<Void> remover(@PathVariable String produto){ // extrai valore da url
        return service.remover(produto)
            ? ResponseEntity.ok().build() //controla codigo http
            : ResponseEntity.notFound().build();

    }

    @PutMapping("/{produto}/{comprado}")
    public ResponseEntity<Void> marcarComprado(@PathVariable String produto){
        return service.marcarComoComprado(produto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/total")
    public Map<String, Integer> total(){
        return Map.of("total", service.total());

    }
}
