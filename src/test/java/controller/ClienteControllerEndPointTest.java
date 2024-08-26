package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Notas.NotasApplication;
import com.example.Notas.dto.ClienteAuthDTO;
import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.dto.EnderecoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = NotasApplication.class)
@AutoConfigureMockMvc
public class ClienteControllerEndPointTest {
    
    @Autowired
    private MockMvc mockMvc;

    private Integer idCliente = 53;

    ClienteDTO novoCliente = new ClienteDTO();
    EnderecoDTO novoEnderecoDTO = new EnderecoDTO();
    ClienteAuthDTO novoClienteAuth = new ClienteAuthDTO();

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        novoCliente = new ClienteDTO();

        novoCliente.setNome("Cliente teste" + idCliente);
        novoCliente.setEmail("teste"+idCliente+"@teste.com.br");

        novoEnderecoDTO.setCep("0000-123");
        novoEnderecoDTO.setNumero(idCliente.toString());
        novoEnderecoDTO.setComplemento("Apartamento" + idCliente.toString());
        novoCliente.setEnderecoDTO(novoEnderecoDTO);

        novoClienteAuth.setUsername(novoCliente.getEmail());
        novoClienteAuth.setPasswordHash("senha" + idCliente);
        novoCliente.setClienteAuth(novoClienteAuth);
    }

    @Test
    void ClienteControllerMapEndPoint() throws Exception {
        this.mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }

    @Test
    void criarCliente() throws Exception {

        String clienteNovoJson = objectMapper.writeValueAsString(novoCliente);

        this.mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteNovoJson))
                .andExpect(status().isOk());
    }
}
