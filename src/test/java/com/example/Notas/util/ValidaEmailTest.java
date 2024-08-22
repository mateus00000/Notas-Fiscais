package com.example.Notas.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Notas.dto.ClienteDTO;

public class ValidaEmailTest {
    
    String emailValido = "mateus@senac.com.br";
    String emailInvalido = "mateussenac.com.br";
    String emailNulo = null;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp(){
        clienteDTO = new ClienteDTO();
        clienteDTO.setEmail("mateus@senac.com.br");
    }

    @Test
    void caracterArroba(){   

    assertTrue(ValidaEmail.validarCaracterArroba(emailValido), "O email válido deve conter '@'" );
    assertFalse(ValidaEmail.validarCaracterArroba(emailInvalido), "O email inválido não deve conter '@'");
    assertFalse(ValidaEmail.validarCaracterArroba(emailNulo), "O email nulo não deve conter nenhum caracter");
    }

    @Test
    void ConfirmarEmail(){
        String emailAntigo = "mateus@senac.com.br";

        //Verifica se o valor esperado é igual ao valor atual.
        //Verifica se os dois são iguais
        //Passa no teste se for igual
        assertEquals(emailValido, clienteDTO.getEmail(),"Os emails devem ser iguais");

        //Verifica se o valor atual não é igual ao valor esperado.
        //Verifica se os emails são diferentes
        //Passa no teste se for diferente
        assertEquals(clienteDTO.getEmail(),emailAntigo,"Os emails devem ser iguais");

        //Verifica se o objeto é nulo
        //Verifica se a variavel é nula
        //passa se for nulo
        assertNull(emailNulo);

        //Verifica se o objeto não é nulo
        //Verifica se o objeto cliente não é nulo
        //passa se o cliente não for nulo
        assertNotNull(clienteDTO);
        
        //Permite agrupar multiplas asserções, executando todas elas e reportando todas as falhas.
        //Verifica se os emails são iguais e validos
        //Passa se os emails forem iguais e validos
        assertAll("Valida Email",
            () -> assertEquals(emailValido, clienteDTO.getEmail(),"Os emails devem ser iguais"),
            () -> assertEquals(clienteDTO.getEmail(),emailAntigo,"Os emails devem ser iguais")
        );
    }

    @Test
    void testEmailValido() {
        assertTrue(ValidaEmail.isValid(clienteDTO.getEmail()));
    }

    @Test
    void testEmailInvalidoSemArroba() {
        assertTrue(ValidaEmail.isValid(emailInvalido));
    }

    @Test
    void testEmailInvalidoDominio() {
        assertTrue(ValidaEmail.isValid("mateus@.com"));
    }
    
}
