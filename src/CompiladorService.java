import org.eclipse.swt.custom.StyledText;

import parte_novo4.ParserConstants;
import parte_novo4.LexicalError;
import parte_novo4.Lexico;
import parte_novo4.SemanticError;
import parte_novo4.Semantico;
import parte_novo4.Sintatico;
import parte_novo4.SyntaticError;
import parte_novo4.Token;

public class CompiladorService {

	StyledText editor = null;

	public void compilar(String code, StyledText editor, StyledText messageArea) {
		messageArea.setText("");
		this.editor = editor;

		Lexico lexico = new Lexico();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		lexico.setInput(code);

		try {
			sintatico.parse(lexico, semantico);
			messageArea.setText("programa compilado com sucesso");
		} catch (LexicalError e) {
			if ("símbolo inválido".equals(e.getMessage())) {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - "
						+ editor.getText().charAt(e.getPosition()) + " " + e.getMessage());
			} else {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - " + e.getMessage());
			}
		} catch (SyntaticError e) {
			Token tokenAtual = sintatico.getCurrentToken();
			
			messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - encontrado "
					+ getDescription(tokenAtual) + " " + e.getMessage());
		} catch (SemanticError e) {
			messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - " + e.getMessage());
		}
	}

	private String getDescription(Token tokenAtual) {
		String descricao = ParserConstants.PARSER_ERROR[tokenAtual.getId()];
		if (descricao.contains("str")) {
			return "constante_str";
		} else if (descricao.contains("EOF")) {
			return "EOF";
		}
		return tokenAtual.getLexeme();
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
