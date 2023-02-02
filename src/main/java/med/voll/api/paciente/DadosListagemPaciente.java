package med.voll.api.paciente;

public record DadosListagemPaciente(Long id, String nome, String telefone, String email) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(),paciente.getTelefone(),paciente.getEmail());
    }

    //ISSO É UM DTO PARA METODO GET
}
