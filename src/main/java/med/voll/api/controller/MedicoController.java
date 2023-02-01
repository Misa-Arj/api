package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	//autowired , ver o que significa!
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { //observe os valid
		repository.save(new Medico(dados));
		//salve os 'dados' na entidade Medico.
		//transactional para metodos de escrita
	}
//	@GetMapping NAO PAGINADO
//	public List<DadosListagemMedico> listar(){
//		return repository.findAll().stream().map(DadosListagemMedico::new).toList();
		//converter repository que tem <medico> para <dadoslistagemmedico>
//	}

	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemMedico::new);
	} //PAGINADO

}
	