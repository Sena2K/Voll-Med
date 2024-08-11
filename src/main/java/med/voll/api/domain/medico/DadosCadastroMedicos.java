package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedicos(

        @NotBlank(message = "Vai corinthians") String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank  @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull Especialidade especialidade, //Not black é so para strings
        @NotNull @Valid DadosEndereco endereco ) { //Pro string validar a outra dto

}
