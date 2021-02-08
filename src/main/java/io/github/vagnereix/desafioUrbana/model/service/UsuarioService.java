package io.github.vagnereix.desafioUrbana.model.service;

import io.github.vagnereix.desafioUrbana.model.controller.exception.UsuarioCadastradoException;
import io.github.vagnereix.desafioUrbana.model.entity.Cartao;
import io.github.vagnereix.desafioUrbana.model.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import io.github.vagnereix.desafioUrbana.model.entity.Usuario;
import io.github.vagnereix.desafioUrbana.model.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final CartaoRepository cartaoRepository;

    public Usuario salvarCartao(int id, Cartao cartao){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        cartao.setUsuario(usuario);
        cartao.setNome(usuario.getNome());
        cartaoRepository.save(cartao);
        usuario.addCartao(cartao);
        usuarioRepository.save(usuario);
        return usuario;
    }

    //método usado para buscar Usuario no banco e passar dados para o SecurityConfig
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException ("Login não encontrado!"));
        return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }

    public Usuario salvar(Usuario usuario) {
        boolean exists = usuarioRepository.existsByEmail(usuario.getEmail());
        if(exists){
            throw new UsuarioCadastradoException(usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarUsuarios(){
        return this.usuarioRepository.findAll();
    }

    public void deletarUsuario(Integer id) {
        this.usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public void atualizarUsuario(Integer id, Usuario usuarioAtualizado){
        usuarioRepository.findById(id).map(usuario -> {
            usuarioAtualizado.setId(usuario.getId());
            return usuarioRepository.save(usuarioAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public Usuario buscarUsuarioUnico(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }
}