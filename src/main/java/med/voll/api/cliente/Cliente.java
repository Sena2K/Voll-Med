package med.voll.api.cliente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

import java.lang.reflect.GenericDeclaration;

@Table(name = "clientes")//Nome da tabela que vamos criar
@Entity(name = "Cliente")//Classe que "contem" esses dados
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String CPF;
    @Embedded//Isso pq endereco é um objeto nosso, logo usamos o embedded
    private Endereco endereco;
}
