package com.felix.demoparkapi.services;


import com.felix.demoparkapi.exception.EntityNotFoundException;
import com.felix.demoparkapi.exception.UsernameUniqueViolationException;
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
        try {
            return usuarioRepository.save(usuario);
        }catch(org.springframework.dao.DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional//(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário não encontrado!", id))
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
       if(!novaSenha.equals(confirmaSenha)){
           throw new RuntimeException("Nova senha não confere com confirmação de senha.");
       }

        Usuario user = buscarPorId(id);
       if(!user.getPassword().equals(senhaAtual)){
         throw new RuntimeException("Sua senha não confere.");
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


