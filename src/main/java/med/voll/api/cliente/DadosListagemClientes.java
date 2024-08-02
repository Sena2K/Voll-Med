package med.voll.api.cliente;

public record DadosListagemClientes(String nome, String email, String cpf) {

    public DadosListagemClientes(Cliente cliente){
        this(cliente.getNome(), cliente.getEmail(), cliente.getCPF());
    }
}
