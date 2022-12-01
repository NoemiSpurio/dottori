package it.prova.dottori.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.dottori.model.Dottore;

public interface DottoreRepository extends CrudRepository<Dottore, Long>{

	public List<Dottore> findByCodiceDottore(String codiceDottore);
}
