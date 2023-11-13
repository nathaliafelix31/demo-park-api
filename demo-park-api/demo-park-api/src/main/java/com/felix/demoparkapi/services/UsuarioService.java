package com.felix.demoparkapi.services;


import com.felix.demoparkapi.models.Usuario;
import com.felix.demoparkapi.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional//(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado!")
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
       if(!novaSenha.equals(confirmaSenha)){
           throw new RuntimeException("Nova senha não confere com confirmação de senha.");
       }

        Usuario user = buscarPorId(id);
       if(!user.getPassword().equals(senhaAtual)){
         throw new RuntimeException("Sua senha nao confere.");
       }
        user.setPassword(novaSenha);
        return user;
    }

    @Transactional
    public List<Usuario> buscarTodos() {
        return (List<Usuario>) usuarioRepository.findAll();
        //return usuarioRepository.findAll();
    }
}


