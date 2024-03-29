package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
		repository.save(new Paciente(dados));
	}
	@GetMapping		//generic define TIPO , n pode retornar nada alem do tipo <aqui>    // filtro  de qtd
	public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemPaciente::new); //:: method reference referencia new que é a dto DadosLIstagemPaciente, que pega todos os dados da entidade.
	}
	@PutMapping
	@Transactional
	public void atualizar (@RequestBody @Valid DadosAtualizacaoPaciente dados){
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}

	@DeleteMapping("/{id}")
	public void excluir (@PathVariable Long id){
		var paciente = repository.getReferenceById(id);
		paciente.excluir();
	}
}
