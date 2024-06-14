package med.voll.api.cliente;

import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroClientes(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
