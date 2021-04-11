package io.github.vagnereix.desafioUrbana.model.service;

import io.github.vagnereix.desafioUrbana.model.entity.Cartao;
import io.github.vagnereix.desafioUrbana.model.entity.Usuario;
import io.github.vagnereix.desafioUrbana.model.entity.enums.TipoCartao;
import io.github.vagnereix.desafioUrbana.model.repository.CartaoRepository;
import io.github.vagnereix.desafioUrbana.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    @PersistenceContext
    private EntityManager manager;

    private final UsuarioRepository usuarioRepository;
    private final CartaoRepository cartaoRepository;

    public List<Cartao> findByIdUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        return usuario.getCartoes();
    }


    public void deletarCartao(Long numero) {
        cartaoRepository.findById(numero).map(cartao -> {
            cartaoRepository.delete(cartao);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão inexistente!"));

    }

    public void alterarStatus(Long numero) {
        cartaoRepository.findById(numero).map(cartao -> {
            cartao.setStatus(!cartao.isStatus());
            return cartaoRepository.save(cartao);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão inexistente!"));
    }

    public List<Cartao> buscar() {
        return cartaoRepository.findAll();
    }

    public TipoCartao[] getTipos() {
        return TipoCartao.values();
    }
}
