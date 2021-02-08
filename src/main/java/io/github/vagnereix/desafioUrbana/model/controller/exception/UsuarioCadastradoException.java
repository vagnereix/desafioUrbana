package io.github.vagnereix.desafioUrbana.model.controller.exception;

public class UsuarioCadastradoException extends RuntimeException{
    public UsuarioCadastradoException(String email){
        super("Usuário com o e-mail: " + email + " já existe em nosso sistema!");
    }
}
