package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@RestController
@RequestMapping("/rest/estudantes")
public class EstudanteRestController {

	@Autowired
	private EstudanteRepository estudanteRepository;

	// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em forma de RESTCONTROLLER (seguindo o padrão RESTFUL)

	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@PostMapping
	public ResponseEntity<Estudante> criar(@Valid @RequestBody Estudante estudante, HttpServletResponse response) {
		Estudante estudanteSalva = estudanteRepository.save(estudante);
		return ResponseEntity.status(HttpStatus.CREATED).body(estudanteSalva);
	}

	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Estudante obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = estudanteRepository.save(obj);
		return ResponseEntity.noContent().build();
	}

	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Estudante>> findAll() {
		List<Estudante> list = estudanteRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estudante> bucarEstudantePorId(@PathVariable("id") long id) {
		Estudante obj = estudanteRepository.findById(id).orElse(new Estudante());
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		estudanteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
