package io.github.vagnereix.desafioUrbana.model.controller;

import io.github.vagnereix.desafioUrbana.model.entity.Cartao;
import io.github.vagnereix.desafioUrbana.model.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoService cartaoService;

    //lista todos os cartões de um usuário
    @GetMapping("{id}")
    public List<Cartao> buscarCartao(@PathVariable int id){
        return this.cartaoService.findByIdUsuario(id);
    }

    @GetMapping
    public List<Cartao> buscar(){
        return this.cartaoService.buscar();
    }

    @DeleteMapping("{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long numero){
        cartaoService.deletarCartao(numero);
    }

    @PutMapping("{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarStatus(@PathVariable Long numero){
        cartaoService.alterarStatus(numero);
    }

}
