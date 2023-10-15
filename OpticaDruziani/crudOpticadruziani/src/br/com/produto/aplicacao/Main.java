package br.com.produto.aplicacao;

import br.com.opticadruziani.dao.OpticaDao;
import br.com.opticadruziani.model.Produto;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		OpticaDao opticaDao = new OpticaDao();
		Produto produto = new Produto();
		Scanner scan = new Scanner(System.in);
		String acao = "";

		while (!acao.equals("0")) { // Adiciona um loop que continua até a opção "0" ser selecionada
			System.out.println("Selecione uma opção:");
			System.out.println("1 - Criar e salvar produto");
			System.out.println("2 - Atualizar produto");
			System.out.println("3 - Deletar produto");
			System.out.println("0 - Sair");
			scan = new Scanner(System.in);
			acao = scan.nextLine(); // Lê a ação do usuário
			
			
			switch (acao) {
			case "1":
				// criar e salvar produto
				System.out.println("Nome do produto: ");
				String nomeProduto = scan.nextLine();
				produto.setNome(nomeProduto);

				System.out.println("Descrição do produto: ");
				String descricaoProduto = scan.nextLine();
				produto.setDescricao(descricaoProduto);

				System.out.println("Preço do produto: ");
				Float precoProduto = scan.nextFloat();
				produto.setPreco(precoProduto);

				opticaDao.save(produto);
				scan.nextLine();
				break;

			case "2":
				// atualizar produto
				Produto alterProd = new Produto();

				System.out.println("Infomre o ID do produto a ser alterado: ");
				Integer idP1 = scan.nextInt();
				alterProd.setId(idP1);// id atual do produto

				scan = new Scanner(System.in);
				System.out.println("Nome do produto: ");
				String nomeP1 = scan.nextLine();
				alterProd.setNome(nomeP1);

				System.out.println("Descrição do produto: ");
				String descricaoP1 = scan.nextLine();
				alterProd.setDescricao(descricaoP1);

				System.out.println("Preço do produto: ");
				Float precoP1 = scan.nextFloat();
				alterProd.setPreco(precoP1);

				opticaDao.update(alterProd);
				scan.nextLine();
				break;

			case "3":
				Produto deleteIDProd = new Produto();

				System.out.println("Informe o ID do produto que deseja deletar: ");
				Integer IDProd = scan.nextInt();
				deleteIDProd.setId(IDProd);// id atual do produto

				opticaDao.deleteByID(IDProd);
				scan.nextLine();
				break;
				
			default:
				break;
			}
		}

		// visualização de todos os registros do banco de dados
		for (Produto p : opticaDao.getProdutos()) {
			System.out.println("Produto: " + p.getNome());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("");
		}

	}

}
