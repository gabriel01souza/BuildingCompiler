import java.util.ArrayList;
import java.util.List;
import parte02.LexicalError;
import parte02.Lexico;
import parte02.Token;

public class CompiladorService {

    List<Token> tokens = new ArrayList<>();
    private boolean inBlockComment = false; // Estado para acompanhar se estamos dentro de um comentário de bloco

    public void compilar(String code) {
        String[] lines = code.replace("\r", "").split("\n");  // Dividir o código em linhas e tratar os retornos de carro
        int cumulativePosition = 0;  // Acumula a posição para ajustar no lexer global

        for (int i = 0; i < lines.length; i++) {
            try {
                String processedLine = inBlockComment ? checkEndOfBlockComment(lines[i], i + 1) : checkBlockComments(lines[i], i + 1);
                if (processedLine.isEmpty() && inBlockComment) continue;  // Ignora linhas vazias dentro de comentários de bloco

                Lexico lexico = new Lexico(processedLine);
                int linePosition = 0;

                Token token = lexico.nextToken();
                while (token != null) {
                    token.setPosition(cumulativePosition + token.getPosition());  // Ajusta a posição global do token
                    tokens.add(token);
                    System.out.println("Linha: " + (i + 1) + " - Token: " + token);
                    linePosition = token.getPosition() + token.getLexeme().length();
                    token = lexico.nextToken();
                }
                System.out.println("--------------------------------------------------------------------------");

            } catch (LexicalError e) {
                System.out.println("Erro léxico na linha " + (i + 1) + " na posição " + (cumulativePosition + e.getPosition()) + ": " + e.getMessage());
            }
            System.out.println("--------------------------------------------------------------------------");

            cumulativePosition += lines[i].length() + 1;  // Incrementa a posição acumulativa pela linha e pelo caractere de nova linha
        
        }
        
    }

    private String checkBlockComments(String line, int lineNumber) throws LexicalError {
        int start = line.indexOf("&-");
        int end = line.lastIndexOf("-&");

        if (start != -1 && end == -1) {
            inBlockComment = true; // Entrou em um comentário de bloco
            return line.substring(0, start);
        }
        if (end != -1) {
            inBlockComment = false; // Fechou o comentário de bloco
            return line.substring(end + 2);
        }
        if (inBlockComment) {
            return ""; // Continua dentro do comentário de bloco
        }
        return line; // Nenhuma ação de comentário de bloco
    }

    private String checkEndOfBlockComment(String line, int lineNumber) throws LexicalError {
        int end = line.indexOf("-&");
        if (end != -1) {
            inBlockComment = false; // Fecha o comentário de bloco
            return line.substring(end + 2);
        }
        return ""; // Continua dentro do comentário de bloco
    }

}
