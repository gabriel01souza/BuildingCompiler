package paraRemover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import parte3.Token;

import objects.Simbolo;

public class Semantico implements Constants {

	String codigoObjeto = "";
	List<String> listaId = new ArrayList<String>();
	String operadorRelacional = "";
	Stack<ETipo> pilhasTipo = new Stack<ETipo>();
	Stack<String> pilhasRotulo = new Stack<String>();
	Map<String, Simbolo> tabelaSimbolos = new HashMap<String, Simbolo>();

	public void executeAction(int action, Token token) throws SemanticError {
		switch (action) {
		case 100:
			this.regra100();
			break;
		case 101:
			this.regra101();
			break;
		case 102:
			this.regra102(token);
			break;
		case 103:
			this.regra103();
			break;
		case 104:
			this.regra104(token);
			break;
		case 105:
			this.regra105(token);
			break;
		case 106:
			this.regra106();
			break;
		case 107:
			this.regra107();
			break;
		case 108:
			this.regra108();
			break;
		case 109:
			this.regra109();
			break;
		case 110:
			this.regra110();
			break;
		case 111:
			this.regra111();
			break;
		case 112:
			this.regra112();
			break;
		case 113:
			this.regra113();
			break;
		case 114:
			this.regra114();
			break;
		case 115:
			this.regra115();
			break;
		case 116:
			this.regra116();
			break;
		case 117:
			this.regra117();
			break;
		case 118:
			this.regra118();
			break;
		case 119:
			this.regra119();
			break;
		case 120:
			this.regra120(token);
			break;
		case 121:
			this.regra121();
			break;
		case 122:
			this.regra122();
			break;
		case 123:
			this.regra123();
			break;
		case 124:
			this.regra124();
			break;
		case 125:
			this.regra125();
			break;
		case 126:
			this.regra126(token);
			break;
		case 127:
			this.regra127(token);
			break;
		case 128:
			this.regra128(token);
			break;
		case 129:
			this.regra129(token);
			break;
		case 130:
			this.regra130(token);
			break;
		default:
			break;
		}
	}

	// OK
	public void regra100() {
		codigoObjeto += ".assembly extern mycolic {} \n " 
				+ ".assembly _codigo_objeto{} \n "
				+ ".module _codigo_objeto.exe \n " 
				+ ".class public _UNICA{  \n "
				+ ".method static public void _principal() { \n" 
				+ ".entrypoint); \n";
	}

	// OK
	public void regra101() {
		codigoObjeto += "ret \n" 
					+ "} \n" 
				+ "}";
		System.out.println(codigoObjeto);
	}

	// VERIFICAR CRIACAO DO SIMBOLO
	public void regra102(Token token) throws SemanticError {
		String lexema = token.getLexeme();

		if (tabelaSimbolos.containsKey(lexema)) {
			throw new SemanticError(lexema + " já declarado");
		}
		ETipo tipo = pilhasTipo.pop();
		tabelaSimbolos.put(lexema, new Simbolo(pilhasTipo.pop(), Boolean.FALSE));
		codigoObjeto += ".locals (" + tipo.descricao + " " + lexema + ") \n";
	}

	// OK
	public void regra103() {
		listaId.clear();
	}

	// VERIFICAR A ENTRADA DO BOOL
	public void regra104(Token token) {
		String lex = token.getLexeme();

		for (String id : listaId) {
			Simbolo simbolo = tabelaSimbolos.get(id);

			switch (simbolo.getTipo()) {
			case STRING:
				codigoObjeto += "ldstr " + id + " \n";
				break;
			case BOOL:
				codigoObjeto += "ldc.i4.1 " + id + " \n";
				break;
			case FLOAT_64:
				codigoObjeto += "ldc.r8 " + id + " \n";
				break;
			case INT64:
				codigoObjeto += "ldc.i8 " + id + " \n";
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + simbolo.getTipo());
			}
			codigoObjeto += "stloc " + id + " \n";
			simbolo.setUsed(Boolean.TRUE);
		}
	}

	// OK
	public void regra105(Token token) {
		listaId.add(token.getLexeme());
	}

	private void regra106() throws SemanticError {
		ETipo tipo = pilhasTipo.pop();
		if (ETipo.INT64.descricao.equals(tipo)) {
			codigoObjeto += "conv.i8 \n";
		}
		adicionaCodigoDUP();

		for (String id : listaId) {
			Simbolo simbolo = tabelaSimbolos.get(id);
			if (Objects.nonNull(simbolo)) {
				codigoObjeto += "stloc " + id + " \n";
				simbolo.setUsed(Boolean.TRUE);
			} else {
				throw new SemanticError(id + " não declarado");
			}
		}
		listaId.clear();
	}

	private void adicionaCodigoDUP() {
		for (int i = 0; i < listaId.size() - 1; i++) {
			codigoObjeto += "dup \n";
		}
	}

	// OK
	private void regra107() throws SemanticError {
		for (String id : listaId) {
			ETipo tipo = tabelaSimbolos.get(id).getTipo();

			if (Objects.nonNull(tipo)) {
				codigoObjeto += "call string [mscorlib]System.Console::ReadLine() \n" + "";
				parseIfNecessery(tipo);
				codigoObjeto += "stloc " + id + " \n";
			} else {
				throw new SemanticError(id + " não declarado");
			}
		}
		listaId.clear();
	}

	private void parseIfNecessery(ETipo tipo) {
		if (!ETipo.STRING.equals(tipo)) {
			codigoObjeto += "call " + tipo + " [mscorlib]System.+" + getClasse(tipo) + "::Parse(string) \n";
		}
	}

	private String getClasse(ETipo tipo) {
		switch (tipo) {
		case INT64 -> {
			return "Int64";
		}
		case FLOAT_64 -> {
			return "Double";
		}
		case STRING -> {
			return "String";
		}
		case BOOL -> {
			return "Boolean";
		}
		}
		throw new RuntimeException("O tipo de variável %s não possui uma classe definida".formatted(tipo));
	}

	// OK
	private void regra108() {
		ETipo tipoDesempilhado = pilhasTipo.pop();
		if (ETipo.INT64.equals(tipoDesempilhado)) {
			codigoObjeto += "conv.i8 \n";
		}
		codigoObjeto += "call void [mscorlib]System.Console::WriteLine(" + tipoDesempilhado + ") \n";
	}

	// OK
	private void regra109() {
		String novoRotulo = " novo_rotulo:";
		pilhasRotulo.add(novoRotulo);
		String novoRotulo2 = " novo_rotulo:";
		codigoObjeto += "brfalse " + novoRotulo2 + " \n";
		pilhasRotulo.add(novoRotulo2);
	}

	// OK
	private void regra110() {
		String rotulo = pilhasRotulo.pop();
		codigoObjeto += rotulo + ": \n";
	}

	// OK
	private void regra111() {
		String rotulo2 = pilhasRotulo.pop();
		String rotulo1 = pilhasRotulo.pop();

		codigoObjeto += "br " + rotulo1 + " \n";
		pilhasRotulo.add(rotulo1);
		codigoObjeto += rotulo2 + ": \n";
	}

	// REVISAR CRIACAO DO ROTULO
	private void regra112() {
		String novoRotulo = " novo_rotulo:";

		codigoObjeto += "brfalse " + novoRotulo + " \n";
		pilhasRotulo.add(novoRotulo);
	}

	// REVISAR CRIACAO DO ROTULO
	private void regra113() {
		String novoRotulo = " novo_rotulo:";

		pilhasRotulo.add(novoRotulo);
	}

	// OK
	private void regra114() {
		String rotulo = pilhasRotulo.pop();
		codigoObjeto += "brfalse " + rotulo + " \n";
	}

	// CONFIRMAR
	private void regra115() {
		pilhasTipo.pop();
		pilhasTipo.pop();
		pilhasTipo.add(ETipo.BOOL);

		codigoObjeto += "ldc.i4.1 \n";
	}

	// CONFIRMAR
	private void regra116() {
		pilhasTipo.pop();
		pilhasTipo.pop();
		pilhasTipo.add(ETipo.BOOL);

		codigoObjeto += "ldc.i4.01 \n";
	}

	// OK
	private void regra117() {
		pilhasTipo.add(ETipo.BOOL);
		codigoObjeto += "ldc.i4.1 \n";
	}

	// OK
	private void regra118() {
		pilhasTipo.add(ETipo.BOOL);
		codigoObjeto += "ldc.i4.0 \n";
	}

	// OK
	private void regra119() {
		pilhasTipo.pop();
		codigoObjeto += "ldc.i4.0 \n";
	}

	// VERIFICACAO
	private void regra120(Token token) {
		operadorRelacional = token.getLexeme();
	}

	// VERIFICAR O OPERADOR RELACIONAL !=
	private void regra121() {
		pilhasTipo.pop();
		pilhasTipo.pop();
		pilhasTipo.add(ETipo.BOOL);

		switch (operadorRelacional) {
		case ">":
			codigoObjeto += "cgt \n";
			break;
		case "<":
			codigoObjeto += "clt \n";
			break;
		case "==":
			codigoObjeto += "ceq \n";
			break;
		case "!=":
			codigoObjeto += "ceq \n";
			codigoObjeto += "ldc.i4.0 \n";
			codigoObjeto += "ceq \n";
			break;
		}
	}

	// OK
	private void regra122() { // dividir cada uma acao
		pop2Push1_Operator();
		codigoObjeto += "add \n";
	}

	// OK
	private void regra123() { // dividir cada uma acao
		pop2Push1_Operator();
		codigoObjeto += "sub \n";
	}

	// OK
	private void regra124() { // dividir cada uma acao
		pop2Push1_Operator();
		codigoObjeto += "mul";
	}

	// OK
	private void regra125() { // dividir cada uma acao
		pop2Push1_Operator();
		codigoObjeto += "div";
	}

	private void pop2Push1_Operator() {
		ETipo operador1 = pilhasTipo.pop();
		ETipo operador2 = pilhasTipo.pop();

		if ("int64".equals(operador1) && "int64".equals(operador2)) {
			pilhasTipo.push(ETipo.INT64);
		} else {
			pilhasTipo.push(ETipo.FLOAT_64);
		}
	}

	// OK
	private void regra126(Token token) throws SemanticError {
		if (tabelaSimbolos.containsKey(token.getLexeme())) {
			codigoObjeto += "ldloc " + token.getLexeme() + " \n";
			ETipo tipo = tabelaSimbolos.get(token.getLexeme()).getTipo();

			if (ETipo.INT64.descricao.equals(tipo)) {
				codigoObjeto += "conv.r8 \n";
			}
			pilhasTipo.add(tipo);
		} else {
			throw new SemanticError(token.getLexeme() + " não declarado");
		}
	}

	// OK
	public void regra127(Token token) {
		pilhasTipo.add(ETipo.INT64);
		codigoObjeto += "ldc.i8 " + token.getLexeme() + " \n" 
					+ "conv.r8 \n";
	}

	// OK
	public void regra128(Token token) {
		pilhasTipo.add(ETipo.FLOAT_64);
		codigoObjeto += "ldc.r8 " + token.getLexeme() + " \n";
	}

	// OK
	public void regra129(Token token) {
		pilhasTipo.add(ETipo.STRING);
		codigoObjeto += "ldstr " + token.getLexeme() + " \n";
	}

	// DUVIDA
	public void regra130(Token token) {
		ETipo operador = pilhasTipo.pop();
		codigoObjeto += "ldc.i8 -1 \n" + "mul \n";
		pilhasTipo.push(operador);
	}

}
