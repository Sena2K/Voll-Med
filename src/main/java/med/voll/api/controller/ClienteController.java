package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.cliente.ClienteRepository;
import med.voll.api.cliente.DadosAtualizacaoCliente;
import med.voll.api.cliente.DadosCadastroClientes;
import med.voll.api.cliente.DadosListagemClientes;
import med.voll.api.medico.DadosListagemMedico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteRepository repository; //Auto injeção do spring, já que damos "extends" num metodo do proprio spring

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroClientes cliente){
        System.out.println(cliente);
    }

    @GetMapping
    public Page<DadosListagemClientes> listar(Pageable pageable){
        return repository.findByAtivoTrue(pageable).map(DadosListagemClientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DadosAtualizacaoCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInfomacoes(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(Long id){
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
    }

}
