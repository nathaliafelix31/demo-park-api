package com.felix.demoparkapi.web.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioSenhaDto {

    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;

}
