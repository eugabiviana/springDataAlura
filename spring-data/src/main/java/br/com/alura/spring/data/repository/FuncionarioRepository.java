package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
		JpaSpecificationExecutor<Funcionario> {
	List<Funcionario> findByNome(String nome);
	
	//criando queries com JDPL
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true) //informando ap spring data que esse parâmetro é uma native query
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}

//dentro do repository podemos passar a lógica de pesquisa no banco de dados.
//PagingAndSortingRepository - insere a biblioteca de paginação para a aplicação
//JpaSpecificationExecutor - segunda extensão para o repository, é só adicionar uma vírgula. Nela informamos apenas a entidades que iremos trabalhar. Essa classe faz com que a busca passe a ser dinâmica.
