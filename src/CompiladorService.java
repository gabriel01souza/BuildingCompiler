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

		for (int i = 0; i < lines.length; i++) {
			// SEPARA AS LINHAS AQUI, E CONSEGUIMOS TER O CONTROLE NO "i"
			String line = lines[i];
			Lexico lexico = new Lexico(line);
			try {
				while (Objects.nonNull(lexico.nextToken())) {
					Token token = lexico.nextToken();
					tokens.add(token);
				}
			} catch (LexicalError e) {
				System.out.println("Linha: " + i);
				e.printStackTrace();
			}
			
//			 ESSE CODIGO AQUI FOI TESTE PARA VERIFICAR SE A CLASSE LEXICO CONSEGUE LER APENAS A PALAVRA E NÃO A LINHA INTEIRA.
//			for (int j = 0; j < words.length; j++) {
//				String word = words[j].replace(" ", "");
//				Lexico lexico = new Lexico(word);
//				try {
//					while (Objects.nonNull(lexico.nextToken())) {
//						Token token = lexico.nextToken();
//						tokens.add(token);
//					}
//				} catch (LexicalError e) {
//					System.out.println("Linha: " + i);
//					e.printStackTrace();
//				}
//			}
		}
		System.out.println(tokens);
	}

}
