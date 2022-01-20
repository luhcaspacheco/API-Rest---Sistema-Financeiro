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
import br.com.sistemafinanceiro.modelo.Despesas;
import br.com.sistemafinanceiro.repository.DespesasRepository;


@RestController
public class DespesasController {

	@Autowired
	private DespesasRepository despesasRepository;

	// LISTAR TODOS
	@RequestMapping(value = "/despesas", method = RequestMethod.GET)
	public List<Despesas> Listar() {
		return despesasRepository.findAll();
	}

	// LISTAR POR ID
	@RequestMapping(value = "/despesas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Despesas> GetById(@PathVariable(value = "id") long id) {
		Optional<Despesas> despesas = despesasRepository.findById(id);
		if (despesas.isPresent())
			return new ResponseEntity<Despesas>(despesas.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// DELETAR POR ID
	@RequestMapping(value = "/despesas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Despesas> despesa = despesasRepository.findById(id);
		if (despesa.isPresent()) {
			despesasRepository.delete(despesa.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// CADASTRO DE DESPESA
	@RequestMapping(value = "/despesa", method = RequestMethod.POST)
	public Despesas Post(@Valid @RequestBody Despesas despesa) {
		return despesasRepository.save(despesa);
	}

	// UPDATE POR ID
	@RequestMapping(value = "/despesas/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Despesas> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Despesas newDespesa) {
		Optional<Despesas> oldDespesa = despesasRepository.findById(id);
		if (oldDespesa.isPresent()) {
			Despesas despesa = oldDespesa.get();
			despesa.setDescricao(newDespesa.getDescricao());
			despesa.setValor(newDespesa.getValor());
			despesa.setData(newDespesa.getData());
			despesasRepository.save(despesa);
			return new ResponseEntity<Despesas>(despesa, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
