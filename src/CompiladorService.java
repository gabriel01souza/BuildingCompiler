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
		lines = limparLinhasVazias(lines);
		Lexico lexico = new Lexico(code);
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			lexico = new Lexico(line);
			String[] numberOfWords = line.split(" ");
			int j = 0; // controle para ao dar o "nextToken"

			try {
				Token token = lexico.nextToken();
				while (Objects.nonNull(token) && j < numberOfWords.length) {
					System.out.println("Linha: " + i + " " + token);
					if ((j + 1) != numberOfWords.length) {
						token = lexico.nextToken();
					}
					j++;
				}

			} catch (LexicalError e) {
				// TODO Auto-generated catch block
				System.out.println("Erro na linha " + i);
				e.printStackTrace();
			}
		}
	}

	public String[] limparLinhasVazias(String[] lines) {
		String[] newLines = new String[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			line = line.replace("\r","");
			int l = line.length();
			if (l > 0) {
				newLines[i] = line;
			} else {
				newLines[i] = "";
			}
		}
		return newLines;
	}

}
