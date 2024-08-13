package com.example.Notas.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidaEmailTest {
    
    @Test
    void caracterArroba(){
        String emailValido = "mateus@senac.com.br";
        String emailInvalido = "mateussenac.com.br";
        String emailNulo = null;

    assertTrue(ValidaEmail.validarCaracterArroba(emailValido), "O email válido deve conter '@'" );
    assertFalse(ValidaEmail.validarCaracterArroba(emailInvalido), "O email inválido não deve conter '@'");
    assertFalse(ValidaEmail.validarCaracterArroba(emailNulo), "O email nulo não deve conter nenhum caracter");
    }
}
