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
		this.editor = editor;
		Lexico lexico = new Lexico(code.replaceAll(" ", ""));
		try {

			while (Objects.nonNull(lexico.nextToken())) {  // Só falta o lexico ler espaço em branco, AAAAAAAAA
//				tokens.add(new Pair())
			}

		} catch (LexicalError e) {
			System.out.println(e);

			if ("simbolo inválido".equals(e.getMessage())) {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - "
						+ editor.getText().charAt(e.getPosition()) + " " + e.getMessage());
			} else {
				messageArea.setText("Erro na linha " + getLinha(e.getPosition()) + " - " + e.getMessage());
			}
		}

	}

	private String getLinha(int position) {
		int linhasEncontradas = 0;
		int areaCodigoLength = editor.getText().length();

		for (int i = 0; i < position && i < areaCodigoLength; i++) {
			if (editor.getText().charAt(i) == '\n') {
				linhasEncontradas++;
			}
		}
		return String.valueOf(linhasEncontradas + 1);
	}
}
