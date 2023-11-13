package com.felix.demoparkapi.web.controllers;

import com.felix.demoparkapi.models.Usuario;
import com.felix.demoparkapi.services.UsuarioService;
import com.felix.demoparkapi.web.dtos.UsuarioResponseDto;
import com.felix.demoparkapi.web.dtos.UsuarioSenhaDto;
import com.felix.demoparkapi.web.dtos.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        Usuario user = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDto dto){
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(),dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll(){
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
