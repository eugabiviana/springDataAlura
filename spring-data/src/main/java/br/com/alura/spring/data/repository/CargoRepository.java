package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Cargo;

@Repository //para que o Java application leia essa anotação ao rodar o projeto.
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}

//O CrudRepository também é uma interface e já tem pronto todos os métodos que seriam criados manualmente usando apenas JPA.