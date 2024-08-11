package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

//Teoricamente nossa entidade é nosso "objeto" no banco de dados, todas as informações do banco de dados
//Devem ser representadas na nossa entidade :)))
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING) // Nosso enum é do tipo String
    private Especialidade especialidade;

    @Embedded // Isso pq endereco é um objeto nosso, logo usamos o embedded
    private Endereco endereco;

    private Boolean ativo; // Alterado de boolean para Boolean

    public Medico(DadosCadastroMedicos dados) {
        this.ativo = true; // Garantir que ativo tem um valor padrão
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        // Campos são opcionais, então se eu enviar algo nulo ele vai substituir, logo nós temos que fazer as verificações pra ver se o nome tá nulo
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefonem() != null) {
            this.telefone = dados.telefonem();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
        if (dados.ativo() != null) { // Verifique se o valor de ativo não é nulo
            this.ativo = dados.ativo();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
