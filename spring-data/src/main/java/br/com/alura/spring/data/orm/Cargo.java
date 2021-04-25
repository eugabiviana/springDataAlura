package br.com.alura.spring.data.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cargos") //define o nome da tabela e dá a possibilidade de alterar o nome dela por aqui
public class Cargo {
	@Id //sinaliza o id que controla a entidade
	@GeneratedValue(strategy = GenerationType.IDENTITY) //cria a sequência
	private Integer id;
	private String descricao;
	@OneToMany(mappedBy = "cargo") //
	private List<Funcionario> funcionario;
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override //método feito para que os VALORES da lista de cargos apareça na visualização e não o endereço de memória de cargos
	public String toString() {
		return "Cargo [id=" + id + ", descricao=" + descricao + "]";
	}

}