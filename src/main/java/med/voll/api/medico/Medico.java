package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@jakarta.persistence.Table(name="medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	public Medico(DadosCadastroMedico dados) {
		// TODO Auto-generated constructor stub
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());

	}

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //ver o que é isso!!
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded 
	private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {

		if(dados.nome() != null){
			this.nome = dados.nome();
		}
		if(dados.telefone() != null){
			this.telefone = dados.telefone();
		}
		if(dados.endereco() != null){
			this.endereco.atuualizarInfomacoes(dados.endereco());
		}

    }
    //para ele ficar em classe separada, mas o banco de dados considera que faz parte da mesma tabela!
}
