package it.prova.gestioneimpiegati.service;

import java.util.List;

import it.prova.gestioneimpiegati.model.Impiegato;

public interface ImpiegatoService {
	public List<Impiegato> listAllElements();

	public Impiegato caricaSingoloElemento(Long id);
	
	public void aggiorna(Impiegato impiegatoInstance);

	public void inserisciNuovo(Impiegato impiegatoInstance);

	public void rimuovi(Long idImpiegato);
	
	public List<Impiegato> findByExample(Impiegato example);
	
	public List<Impiegato> cercaByCognomeENomeILike(String cognomeTerm, String nomeTerm);
}
