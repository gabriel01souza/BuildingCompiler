package parte04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants {

	String codigoObjeto = "";
	List<String> listaId = new ArrayList<String>();
	String operadorRelacional = "";
	Stack<String> pilhasTipo = new Stack<String>();
	Stack<String> pilhasRotulo = new Stack<String>();
	String[][][] tabelaSimbolos = new String[50][][];

	public void executeAction(int action, Token token) throws SemanticError {
		switch (action) {
		case 100:
			this.regra100();
			break;
		case 101:
			this.regra101();
			break;
		case 108:
			this.regra108();
			break;
		case 129:
			this.regra129(token);
			break;
		default:
			this.regra127(token);
			System.out.println(codigoObjeto);
		}
	}
	
	// OK
	public void regra100() {
		codigoObjeto += ".assembly extern mycolic {} \n " 
				+ ".assembly _codigo_objeto{} \n "
				+ ".module _codigo_objeto.exe \n " + ".class public _UNICA{  \n "
				+ ".method static public void _principal() { \n" + ".entrypoint); \n";
	}

	// OK
	public void regra101() {
		codigoObjeto += "ret\n" + "}\n" + "}";
	}
	
	// NAO
	public void regra102() {
		codigoObjeto += "ret\n" + "}\n" + "}";
	}
	
	// OK
	private void regra108() {
		String tipoDesempilhado = pilhasTipo.pop();
		if (tipoDesempilhado == "int64") {
			codigoObjeto += "conv.i8 \n";
		}
		codigoObjeto += "call void [mscorlib]System.Console::WriteLine("+tipoDesempilhado+")";
	}
	
	// OK
	private void regra117() {
		pilhasTipo.add("bool");
		codigoObjeto += "ldc.i4.1 \n";
	}

	// OK
	private void regra118() {
		pilhasTipo.add("bool");
		codigoObjeto += "ldc.i4.0 \n";
	}

	// PENDENTE
	private void regra122_123_124_125() { // dividir cada uma acao
		String operador1 = pilhasTipo.pop();
		String operador2 = pilhasTipo.pop();
		
		if ("int64".equals(operador1) && "int64".equals(operador2)) {
			pilhasTipo.push("int64");
		} else {
			pilhasTipo.push("float64");
		}
		codigoObjeto += "ldc.i8 -1 \n"
				+ "mul \n";
	}
	
	// OK
	public void regra127(Token token) {
		pilhasTipo.add("int64");
		codigoObjeto += "ldc.i8 " + token.getLexeme() + " \n"
				+ "conv.r8 \n";
	}
	
	// OK
	public void regra128(Token token) {
		pilhasTipo.add("float64");
		codigoObjeto += "ldc.r8 " + token.getLexeme() + " \n";
	}
	
	// OK
	public void regra129(Token token) {
		pilhasTipo.add("string");
		codigoObjeto += "ldstr " + token.getLexeme() + " \n";
	}

	// DUVIDA
	public void regra130(Token token) {
		String operador = pilhasTipo.pop();
		codigoObjeto += "ldc.i8 -1 \n"
				+ "mul \n";
		pilhasTipo.push(operador);
	}

}
