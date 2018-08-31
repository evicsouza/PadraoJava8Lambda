package br.edu.ifpe.java8.ex2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import br.edu.ifpe.java8.entidades.Aluno;

/**
 * Esta classe é responsável pela leitura de todos os alunos de um arquivo de entrada (alunos.txt)
 *  e gerar um relatório 5 Opções de relatórios estão disponíveis: 
 *  
 * 		1 - Com o nome de todos os alunos;
 *		2 - Com o nome e a matrícula de todos os alunos;
 *		3 - Com o nome, a matrícula e a média de todos os alunos;
 *		4 - Com o nome e a média de todos os alunos;
 *		5 - Com o a matrícula e a média de todos os alunos;
 *
 * O problema do código abaixo (entre outros) é que ele viola o princípio OPEN-CLOSED de OO, 
 * que diz que o entidades devem estar aberta para extensões, porém fechadas para mudanças.
 * 
 * Logo, o seu trabalho é basicamente, utilizando programação funcional, modificar o mecanismo 
 * de geração de relatórios para evitar ifs e switchs ao longo do método abaixo.
 * 
 * @author Victor Lira
 *
 */
public class RelatorioAlunos2 {

	private static final String SEPARADOR = "#";

	public static void gerar(TipoRelatorio2 opcao) {
		File arquivoEntrada = new File("alunos.txt");
		File arquivoSaida = new File("relatorio.txt");

		Scanner leitor = null;
		PrintWriter escritor = null;

		try {
			leitor = new Scanner(arquivoEntrada);
			escritor = new PrintWriter(arquivoSaida);

			while (leitor.hasNextLine()) {
				String linha = leitor.nextLine();

				String[] valores = linha.split(SEPARADOR);

				Aluno aluno = new Aluno();
				aluno.setNome(valores[0]);
				aluno.setMatricula(valores[1]);
				aluno.setMedia(Double.parseDouble(valores[2]));

				switch (opcao) {
				case NOME:
					escritor.println(aluno.getNome());
					break;
				case NOME_MATRICULA:
					escritor.println(aluno.getNome() + SEPARADOR + aluno.getMatricula());
					break;
				case NOME_MATRICULA_MEDIA:
					escritor.println(aluno.getNome() + SEPARADOR + aluno.getMatricula() + SEPARADOR + aluno.getMedia());
					break;
				case NOME_MEDIA:
					escritor.println(aluno.getNome() + SEPARADOR + aluno.getMedia());
					break;
				case MATRICULA_MEDIA:
					escritor.println(aluno.getMatricula() + SEPARADOR + aluno.getMedia());
					break;
				}
			}
		} catch (IOException e) {
			/* DO NOTHING */
		} finally {
			if (leitor != null) {
				leitor.close();
			}
			if (escritor != null) {
				escritor.close();
			}
		}
	}
}
