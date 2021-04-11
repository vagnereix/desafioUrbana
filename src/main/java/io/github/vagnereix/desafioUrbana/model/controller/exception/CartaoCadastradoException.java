package io.github.vagnereix.desafioUrbana.model.controller.exception;

public class CartaoCadastradoException extends RuntimeException {
    public CartaoCadastradoException(Long numero){
        super("Cartão com o número: " + numero + " já existe!");
    }
}
