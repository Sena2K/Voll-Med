package med.voll.api.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "clientes")//Nome da tabela que vamos criar
@Entity(name = "Cliente")//Classe que "contem" esses dados
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean ativo;
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String CPF;
    @Embedded//Isso pq endereco é um objeto nosso, logo usamos o embedded
    private Endereco endereco;

    public Cliente(DadosCadastroClientes dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.CPF = dados.cpf();
        this.endereco = new Endereco(dados.endereco());//Instancia pq é um objeto novo do tipo endereço que estamos passando
    }


    public void atualizarInfomacoes(DadosAtualizacaoCliente dados) {
        //campos sao opcionais, entao se eu enviar algo nulo ele vai substituir, logo nós temos que fazer as verificações pra ver se o nome ta nulo
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
