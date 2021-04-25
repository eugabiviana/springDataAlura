package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service //para o spring reconhecer a classe
public class CrudCargoService {
	
	private Boolean system = true; //controla quando o cliente quer sair do sistema.
	private final CargoRepository cargoRepository; //para chamar na classe principal (SpringDataApplication)
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository; //mostrando à classe que o repository é o mesmo
	}
	
	//é a função que mostrará qual a ação o cliente quer executar
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			//ao invés de usar if, usa switch para ser mais rápido e organizado.
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}
	
	//lógica para salvar o registro
	private void salvar(Scanner scanner) {
		System.out.println("Descricao do cargo");
		String descricao = scanner.next(); //pega o valor que o cliente inseriu no console
		Cargo cargo = new Cargo(); //cria o cargo com a descrição inserida
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo); //salva o cargo com a descrição dinâmica passada pelo cliente
		System.out.println("Salvo"); //informa ao cliente que a informação foi salva.
	}
	
	//atualizar registro do cargo
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt(); //pega a informação passada pelo cliente
		System.out.println("Descricao do Cargo");
		String descricao = scanner.next(); //guarda a informação passada e mostra no console
		
		Cargo cargo = new Cargo(); //criando o objeto
		cargo.setId(id); //pega o id informado
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado");
	}
	
	//visualizar um registro na tabela.
	private void visualizar() { //não precisa de parâmetros, pois o método não vai parar.
		Iterable<Cargo> cargos = cargoRepository.findAll(); //retorna todos os registros de cargo.
		cargos.forEach(cargo -> System.out.println(cargo)); //printa todos os cargos
	}
	
	//método para deletar via id.
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado"); //mensagem de confirmação para o cliente.
	}
	
}

//Lembrete: o scanner foi criado na classe principal (SpringDataApplication)!