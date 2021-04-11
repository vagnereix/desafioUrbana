package io.github.vagnereix.desafioUrbana.model.controller;

import io.github.vagnereix.desafioUrbana.model.controller.exception.UsuarioCadastradoException;
import io.github.vagnereix.desafioUrbana.model.entity.Cartao;
import io.github.vagnereix.desafioUrbana.model.entity.Usuario;
import io.github.vagnereix.desafioUrbana.model.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try {
            usuarioService.salvar(usuario);
        } catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarCartao(@PathVariable int id, @RequestBody @Valid Cartao cartao) {
        usuarioService.salvarCartao(id, cartao);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public List<Usuario> buscar(){
        return this.usuarioService.buscarUsuarios();
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public Usuario buscarPorID(@PathVariable int id){
        return usuarioService.buscarUsuarioUnico(id);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        this.usuarioService.deletarUsuario(id);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Usuario usuario) {
        this.usuarioService.atualizarUsuario(id, usuario);
    }

    @RequestMapping(value = "/usuario/email/{email}", method = RequestMethod.GET)
    public Usuario buscarPorEmail(@PathVariable String email){
        return usuarioService.buscarUsuarioEmail(email);
    }

}
