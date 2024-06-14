package med.voll.api.controller;

import med.voll.api.cliente.DadosCadastroClientes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroClientes cliente){
        System.out.println(cliente);
    }
}
