package com.felix.demoparkapi.repositories;


import com.felix.demoparkapi.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

}