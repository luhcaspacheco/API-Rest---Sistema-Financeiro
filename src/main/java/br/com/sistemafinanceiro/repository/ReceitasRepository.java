package br.com.sistemafinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import br.com.sistemafinanceiro.modelo.Receitas;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long> { }
