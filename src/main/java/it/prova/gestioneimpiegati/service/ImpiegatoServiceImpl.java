package it.prova.gestioneimpiegati.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegati.model.Impiegato;
import it.prova.gestioneimpiegati.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

	@Autowired
	private ImpiegatoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> listAllElements() {
		return (List<Impiegato>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Impiegato caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		repository.save(impiegatoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		repository.save(impiegatoInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		repository.delete(impiegatoInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> cercaByCognomeENomeILike(String cognomeTerm, String nomeTerm) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByCognomeAsc(nomeTerm,
				cognomeTerm);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> findByExample(Impiegato example) {
		Specification<Impiegato> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getMatricola()))
				predicates.add(
						cb.like(cb.upper(root.get("matricola")), "%" + example.getMatricola().toUpperCase() + "%"));

			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));

			if (example.getDataDiNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiNascita"), example.getDataDiNascita()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		return repository.findAll(specificationCriteria);
	}

}
