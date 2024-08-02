package med.voll.api.cliente;

public record DadosListagemClientes(String id, String nome, String email, String cpf) {

    public DadosListagemClientes(Cliente cliente){
        this(String.valueOf(cliente.getId()), cliente.getNome(), cliente.getEmail(), cliente.getCPF());
    }


}
