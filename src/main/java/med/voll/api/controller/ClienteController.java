package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository; //Auto injeção do spring, já que damos "extends" num metodo do proprio spring

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroClientes dados, UriComponentsBuilder uriBuilder){
        var cliente = new Cliente(dados);
        repository.save(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new detalhamentoClientes(cliente));
    }
    @GetMapping
    public  ResponseEntity<Page<DadosListagemClientes>>  listar(Pageable pageable){
        var cliente = repository.findByAtivoTrue(pageable).map(DadosListagemClientes::new);
        return ResponseEntity.ok(cliente);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInfomacoes(dados);
        return ResponseEntity.ok(new detalhamentoClientes(cliente));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(Long id){
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new detalhamentoClientes(cliente));
    }


}
