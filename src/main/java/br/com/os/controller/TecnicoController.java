package br.com.os.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.os.domain.Tecnico;
import br.com.os.dto.TecnicoDto;
import br.com.os.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping
	public ResponseEntity<List<TecnicoDto>> searchAll() {
		ResponseEntity<List<TecnicoDto>> resposta = null;
		resposta = ResponseEntity.ok(tecnicoService.searchAll());
		return resposta;
	}

	@GetMapping({ "/{cpf}", "/" })
	public ResponseEntity<Tecnico> searchByCode(@PathVariable(required = false) String cpf) {
		ResponseEntity<Tecnico> resposta = null;

		resposta = ResponseEntity.ok(tecnicoService.searchByCpf(cpf));
		return resposta;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TecnicoDto> save(@Valid @RequestBody TecnicoDto tecnicoDto) {
		TecnicoDto resposta = null;

		resposta = tecnicoService.save(tecnicoDto);
		return ResponseEntity.ok(resposta);

	}

	@PutMapping({ "/{cpf}", "/" })
	public TecnicoDto update(@PathVariable String cpf, @RequestBody TecnicoDto tecnico) {
		return tecnicoService.update(tecnico, cpf);
	}

	@DeleteMapping({ "/{cpf}", "/" })
	public ResponseEntity<TecnicoDto> deleteByCpf(@PathVariable String cpf) {
		return tecnicoService.deleteByCpf(cpf);
	}

}