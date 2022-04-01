package dao;

import java.util.Scanner;

public class DAOTeste {

	public static void main(String[] args) {

		DAO dao = new DAO();
		Scanner sc = new Scanner(System.in);
		String nome = "";
		int op = 0;
		int id = 0;
		String sql = "";

		while(op != 5) {
			dao.menu();
			op = sc.nextInt();
			sc.nextLine();
			
			switch(op) {
				case 1:
					sql = "SELECT * FROM pessoas where nome like ?";
					System.out.println("Pesquisar : ");
					String chave = sc.nextLine();
						
					dao.buscar(sql, chave);
					dao.close();
					break;
				case 2:
					sql = "INSERT INTO pessoas(nome) VALUES (?)";
					System.out.println("Nome : ");
					nome = sc.nextLine();
						
					dao.incluir(sql, nome);
					dao.close();
					break;
				case 3:
					sql = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
					
					System.out.println("Novo nome :");
					String novoNome = sc.nextLine();
					System.out.println("informe o Id da pessoa :");
					id = sc.nextInt();
					
					dao.editar(sql, novoNome, id);
					dao.close();
					break;
				case 4:
					sql = "DELETE FROM pessoas WHERE codigo = ?";
					
					System.out.println("informe o Id da pessoa :");
					id = sc.nextInt();
					
					dao.excluir(sql, id);
					dao.close();
					break;
				case 5:
					System.out.println("Sessão encerrada.");
					break;
				default:
					System.out.println("Opcao inválida");
			}
		}
		sc.close();
	}

}
