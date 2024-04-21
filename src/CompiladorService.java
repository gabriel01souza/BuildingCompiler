import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import parte02.LexicalError;
import parte02.Lexico;
import parte02.Token;

public class CompiladorService {

	List<Token> tokens = new ArrayList<Token>();

	public void compilar(String code) {
		String[] lines = code.split("\n");
		try {
			Lexico lexico = new Lexico(code);
//			Token token1;
//			token1 = lexico.nextToken();
//			System.out.println(token1);
//			Token token2 = lexico.nextToken();
//			System.out.println(token2);
//			Token token3 = lexico.nextToken();
//			System.out.println(token3);

			for (int i = 0; i < lines.length; i++) {
				String line = lines[i];
				lexico = new Lexico(line);
				String[] numberOfWords = line.split(" ");
				int j = 0; // controle para ao dar o "nextToken"

				Token token = lexico.nextToken();
				while (Objects.nonNull(token) && j < numberOfWords.length) {
					System.out.println(token);
					if ((j + 1) != numberOfWords.length) {
						token = lexico.nextToken();
					}
					j++;
				}
			}

		} catch (LexicalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for (int i = 0; i < lines.length; i++) {
//			// SEPARA AS LINHAS AQUI, E CONSEGUIMOS TER O CONTROLE NO "i"
//			String line = lines[i];
//			Lexico lexico = new Lexico(line.replaceAll(" ", ""));
//			try {
//				while (Objects.nonNull(lexico.nextToken())) {
//					Token token = lexico.nextToken();
//					tokens.add(token);
//				}
//			} catch (LexicalError e) {
//				System.out.println("Linha: " + i);
//				e.printStackTrace();
//			}
//		}
		System.out.println(tokens);
	}

}
