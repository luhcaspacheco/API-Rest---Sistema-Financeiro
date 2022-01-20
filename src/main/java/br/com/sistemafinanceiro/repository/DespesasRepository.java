package br.com.sistemafinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import br.com.sistemafinanceiro.modelo.Despesas;


@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> { }
