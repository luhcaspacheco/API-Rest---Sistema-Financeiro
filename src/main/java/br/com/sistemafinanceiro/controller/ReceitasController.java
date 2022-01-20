package br.com.sistemafinanceiro.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.sistemafinanceiro.modelo.Receitas;
import br.com.sistemafinanceiro.repository.ReceitasRepository;

@RestController
public class ReceitasController {

	@Autowired
	private ReceitasRepository receitasRepository;

	// LISTAR TODOS
	@RequestMapping(value = "/receitas", method = RequestMethod.GET)
	public List<Receitas> Listar() {
		return receitasRepository.findAll();
	}

	// LISTAR POR ID
	@RequestMapping(value = "/receitas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Receitas> GetById(@PathVariable(value = "id") long id) {
		Optional<Receitas> receita = receitasRepository.findById(id);
		if (receita.isPresent())
			return new ResponseEntity<Receitas>(receita.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// DELETAR POR ID
	@RequestMapping(value = "/receitas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Receitas> receita = receitasRepository.findById(id);
		if (receita.isPresent()) {
			receitasRepository.delete(receita.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// CADASTRO DE RECEITA
	@RequestMapping(value = "/receita", method = RequestMethod.POST)
	public Receitas Post(@Valid @RequestBody Receitas receita) {
		return receitasRepository.save(receita);
	}

	// UPDATE POR ID
	@RequestMapping(value = "/receitas/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Receitas> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Receitas newReceita) {
		Optional<Receitas> oldReceita = receitasRepository.findById(id);
		if (oldReceita.isPresent()) {
			Receitas receita = oldReceita.get();
			receita.setDescricao(newReceita.getDescricao());
			receita.setValor(newReceita.getValor());
			receita.setData(newReceita.getData());
			receitasRepository.save(receita);
			return new ResponseEntity<Receitas>(receita, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
