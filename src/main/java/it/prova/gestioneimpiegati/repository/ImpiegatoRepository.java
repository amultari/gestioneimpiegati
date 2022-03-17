package it.prova.gestioneimpiegati.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneimpiegati.model.Impiegato;

public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>,JpaSpecificationExecutor<Impiegato> {
	List<Impiegato> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByCognomeAsc(String cognomeTerm,
			String nomeTerm);
}
