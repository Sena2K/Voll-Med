package med.voll.api.domain.cliente;

import med.voll.api.domain.endereco.Endereco;

public record detalhamentoClientes(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {


    public detalhamentoClientes(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCPF(), cliente.getTelefone(), cliente.getEndereco());
    }




}
