import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.swt.custom.StyledText;

import Implementations.Pair.Pair;
import parte02.LexicalError;
import parte02.Lexico;
import parte02.Token;

public class CompiladorService {

	List<Pair<Integer, Token>> tokens = new ArrayList<Pair<Integer, Token>>();
	StyledText editor = null;

	public void compilar(String code, StyledText editor, StyledText messageArea) {
		tokens = new ArrayList<Pair<Integer, Token>>();
		messageArea.setText("");
		this.editor = editor;
		Lexico lexico = new Lexico(code);
		try {
			Token token = lexico.nextToken();

			while (Objects.nonNull(token)) {
				tokens.add(new Pair(getLinha(token.getPosition()), token));
				token = lexico.nextToken();
			}
			compiladoComSucesso(messageArea);
		} catch (LexicalError e) {
			System.out.println(e);
			if ("símbolo inválido".equals(e.getMessage())) {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - "
						+ editor.getText().charAt(e.getPosition()) + " " + e.getMessage());
			} else {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - " + e.getMessage());
			}
		}

	}

	private void compiladoComSucesso(StyledText messageArea) {
		if (tokens.isEmpty()) {
			StringBuilder mensagemFinal = new StringBuilder(String.format("Nenhum token reconhecido!"));
			messageArea.setText(mensagemFinal.toString());
		} else {
			// %15s e %30s é modo de especificar que eles tenham um espaço mínimo de 15 e 30
			// caracteres
			StringBuilder mensagemFinal = new StringBuilder(
					String.format("%-15s %-35s %s\n", "Linha", "Classe", "Lexema"));
			tokens.forEach(token -> {
				mensagemFinal.append(String.format("%-20s %-30s %s\n", token.getFirst(),
						getTipo(token.getSecond().getId()), token.getSecond().getLexeme()));
			});
			mensagemFinal.append("		Programa compilado com sucesso!");
			messageArea.setText(mensagemFinal.toString());
		}
	}

	private String getTipo(int id) {
		// { "bin", "bool", "elif", "else", "endif", "false", "float", "hexa", "if",
		// "input",
		// "int", "main", "output", "repeat", "str", "toBin", "toHexa", "toInt", "true",
		// "until" };
		switch (id) {
		case 3:
			return "constante_int";
		case 4:
			return "constante_float";
		case 5:
			return "constante_bin";
		case 6:
			return "constante_hexa";
		case 7:
			return "constante_str";
		case 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27:
			return "palavra reservada";
		case 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45:
			return "símbolo especial";
		default:
			return "identificador";
		}
	}

	private String getLinha(int position) {
		int tamanhoEditor = editor.getText().length();
		int linhas = 0;

		for (int i = 0; i < position && i < tamanhoEditor; i++) {
			if (editor.getText().charAt(i) == '\n') {
				linhas++;
			}
		}
		return String.valueOf(linhas + 1);
	}
}
