package br.com.mateusalves.cliente.service;

import br.com.thiagofellipe.cliente.dto.ClienteResponseDTO;
import br.com.thiagofellipe.cliente.model.Cliente;
import br.com.thiagofellipe.cliente.repository.ClienteRepository;
import br.com.thiagofellipe.cliente.service.ClienteService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void init(){
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();

        when(clienteRepository.save(any())).thenReturn(cliente);
    }

   /* @Test
    public void criarTest(){
        Cliente cliente = Cliente.builder()
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        Cliente clienteSalvo = clienteService.criar(cliente);
        Assertions.assertNotNull(clienteSalvo);
        Assertions.assertEquals("888.888.888.88",clienteSalvo.getCpf());
        Assertions.assertEquals(1L,clienteSalvo.getId());
        Mockito.verify(clienteRepository,Mockito.times(1)).save(cliente);
    }*/

    @Test
    public void listarClientesTest(){

        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        Cliente cliente2 = Cliente.builder()
                .id(2L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        Cliente cliente3 = Cliente.builder()
                .id(3L)
                .nome("Ariel Tintel")
                .cpf("888.888.888.88")
                .build();
        List<Cliente> clienteList = Arrays.asList(cliente, cliente2, cliente3);

        when(clienteRepository.findAll()).thenReturn(clienteList);

        List<ClienteResponseDTO> listaClientes = clienteService.listarClientes();
        Assertions.assertNotNull(listaClientes);
        Assertions.assertEquals(3,listaClientes.size());
        Mockito.verify(clienteRepository,Mockito.times(1)).findAll();
    }


}
