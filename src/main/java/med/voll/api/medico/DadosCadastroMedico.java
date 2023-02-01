package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
//ESSA CLASSE É UM DTO
        @NotBlank
        String nome,
        @NotBlank //notblank so para campos strings
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //expressao regular, ver sobre dps
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid //validar esse outro objeto
        DadosEndereco endereco) {

}
