package parte_novo4;

public interface ParserConstants
{
    int START_SYMBOL = 46;

    int FIRST_NON_TERMINAL    = 46;
    int FIRST_SEMANTIC_ACTION = 81;

    int[][] PARSER_TABLE =
    {
        { -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1,  1, -1,  1, -1, -1, -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1,  5, -1,  6, -1, -1, -1, -1, -1,  8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1, 18, 18, -1, 18, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 21, 23, -1, -1, -1, -1, 20, 22, -1, 19, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 25, 26, 27, 28, 31, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 44, 44, 44, 44, 44, 44, -1, -1, -1, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, -1, 44, 44, -1, -1, -1, -1, -1, -1, -1, 44, -1, -1, -1, 44, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, 40, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 35, 35, 35, 35, 35, 35, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, 35, 35, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, 35, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 48, 48, 48, 48, 48, 48, -1, -1, -1, -1, -1, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, 48, 48, -1, -1, -1, -1, -1, -1, -1, 48, -1, -1, -1, 51, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 45, -1, 45, -1, 45, -1, 45, 46, 47, -1, -1, -1, -1, -1 },
        { -1, 52, 52, 52, 52, 52, 52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, 52, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 59, 59, 59, 59, 59, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, 59, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 53, -1, 53, -1, 53, -1, 53, 53, 53, -1, 54, 54, 54, 54 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 55, 56, 57, 58 },
        { -1, 63, 63, 63, 63, 63, 63, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, 63, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, 62, -1, -1, 60, -1, 60, -1, 60, -1, 60, 60, 60, -1, 60, 60, 60, 60 },
        { -1, 67, 67, 67, 67, 67, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, 67, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, 64, 65, 66, 64, -1, 64, -1, 64, -1, 64, 64, 64, -1, 64, 64, 64, 64 },
        { -1, 68, 69, 70, 71, 72, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, 76, -1, -1, -1, -1, -1, -1, -1, 74, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, 77, 77, 77, 77, 78, 77, -1, 77, -1, 77, 77, 77, -1, 77, 77, 77, 77 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, 80, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        {  3,  2, -1, -1, -1, -1, -1, -1, -1,  3,  3,  3, -1, -1, -1,  2, -1,  2, -1,  2, -1, -1, -1, -1, -1,  2,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 181,  47,  19,  50, 182 },
        {  51,  34,  80 },
        {  50 },
        {   0 },
        {  52 },
        {  53 },
        {  54 },
        {  55 },
        {  56 },
        {  57 },
        {   0 },
        {  58,  36,  60,  48 },
        { 183,  34, 184,  49 },
        { 183,  35,  61, 185,  34, 184,  49 },
        {  57 },
        {   0 },
        {   2, 186,  59 },
        {  32,  58 },
        {   0 },
        {  17 },
        {  14 },
        {   8 },
        {  15 },
        {   9 },
        {  21 },
        {   3 },
        {   4 },
        {   5 },
        {   6 },
        {  25 },
        {  13 },
        {   7 },
        {  58,  35,  62, 187 },
        {  18,  37,  58, 188,  38 },
        {  20,  37,  65,  38 },
        {  62, 189,  66 },
        {  32,  65 },
        {   0 },
        {  16,  62, 190,  36,  50,  63, 192,  64,  12, 191 },
        { 192,  10,  62, 193,  36,  50,  63 },
        {   0 },
        {  11,  50 },
        {   0 },
        {  26, 194,  50,  27,  62, 195 },
        {  67,  68 },
        {   0 },
        {  39,  67, 196,  68 },
        {  40,  67, 197,  68 },
        {  69 },
        {  25, 198 },
        {  13, 199 },
        {  41,  67, 200 },
        {  70,  71 },
        {   0 },
        {  72, 201,  70, 202 },
        {  42 },
        {  43 },
        {  44 },
        {  45 },
        {  73,  74 },
        {   0 },
        {  28,  73, 203,  74 },
        {  29,  73, 204,  74 },
        {  75,  76 },
        {   0 },
        {  30,  75, 205,  76 },
        {  31,  75, 206,  76 },
        {  77,  78 },
        {   2, 207 },
        {   3, 208 },
        {   4, 209 },
        {   5 },
        {   6 },
        {   7, 210 },
        {  37,  62,  38 },
        {  28,  77 },
        {  29,  77, 211 },
        {   0 },
        {  33,  79 },
        {  22 },
        {  23 },
        {  24 }
    };

    String[] PARSER_ERROR =
    	{
	        "",
	        "esperado EOF",  		//"Era esperado fim de programa",
	        "esperado identificador",   		//"Era esperado id",
	        "esperado constante_int",	//"Era esperado cint",
	        "esperado constante_float", //"Era esperado cfloat",
	        "esperado constante_bin", //"Era esperado cbin",
	        "esperado constante_hexa", //"Era esperado chexa",
	        "esperado constante_str", //"Era esperado cstr",
	        "esperado bin", // "Era esperado bin",
	        "esperado bool", //"Era esperado bool",
	        "esperado elif", //"Era esperado elif",
	        "esperado else", //"Era esperado else",
	        "esperado endif", //"Era esperado endif",
	        "esperado false", //"Era esperado false",
	        "esperado float", //"Era esperado float",
	        "esperado hexa", //"Era esperado hexa",
	        "esperado if", // "Era esperado if",
	        "esperado int", //"Era esperado int",
	        "esperado input", //"Era esperado input",
	        "esperado main", //"Era esperado main",
	        "esperado output", //"Era esperado output",
	        "esperado str", //"Era esperado str",
	        "esperado toInt", //"Era esperado toInt",
	        "esperado toBin", //"Era esperado toBin",
	        "esperado toHexa", //"Era esperado toHexa",
	        "esperado true", //"Era esperado true",
	        "esperado repeat", //"Era esperado repeat",
	        "esperado until", //"Era esperado until",
	        "esperado +", //"Era esperado \"+\"",
	        "esperado -", //"Era esperado \"-\"",
	        "esperado *", //"Era esperado \"*\"",
	        "esperado /", //"Era esperado \"/\"",
	        "esperado ,", //"Era esperado \",\"",
	        "esperado .", //"Era esperado \".\"",
	        "esperado ;", //"Era esperado \";\"",
	        "esperado =", //"Era esperado \"=\"",
	        "esperado :", //"Era esperado \":\"",
	        "esperado (", //"Era esperado \"(\"",
	        "esperado )", //"Era esperado \")\"",
	        "esperado &", //"Era esperado \"&\"",
	        "esperado |", //"Era esperado \"|\"",
	        "esperado !", //"Era esperado \"!\"",
	        "esperado ==", //"Era esperado \"==\"",
	        "esperado !=", //"Era esperado \"!=\"",
	        "esperado <", //"Era esperado \"<\"",
	        "esperado >", //"Era esperado \">\"",
	        "esperado id main",//"<programa> inv�lido",
	        "esperado id main",//"<declaracao_variaveis> inv�lido",
	        "esperado ; =",//"<lista_variaveis1> inv�lido",
	        "esperado id main",//"<lista_variaveis2> inv�lido",
	        "esperado id if input output repeat",  // "<lista_comandos> inv�lido",
	        "esperado id if input output repeat",//"<comando> inv�lido",
	        "esperado id",  //"<atribuicao> inv�lido",
	        "esperado input", //"<entrada_dados> inv�lido",
	        "esperado output",//"<saida_dados> inv�lido",
	        "esperdo if ",//"<cmd_selecao> inv�lido",
	        "esperado repeat",//"<cmd_repeticao> inv�lido",
	        "esperado id", //"<lista_variaveis> inv�lido",
	        "esperado id", //"<lista_id> inv�lido",
	        "esperado , : = )", //"<lista_id1> inv�lido",
	        "esperado bin bool float hexa int str", //"<tipo> inv�lido",
	        "esperado constante_int constante_float constante_bin constante_hexa constante_str false true", //"<valor> inv�lido",
	        "esperado expressão", // "<expressao> inv�lido",
	        "esperado elif else endif", // "<condicoes> inv�lido",
	        "esperado else endif",   //"<else> inv�lido",
	        "esperado expressão", //"<lista_expressoes> inv�lido",
	        "esperado , )", //"<lista_expressoes1> inv�lido",
	        "esperado expressão", //"<elemento> inv�lido",
	        "esperado expressão",//"<expressao_> inv�lido",
	        "esperado expressão", //"<relacional> inv�lido",
	        "esperado expressão", //"<aritmetica> inv�lido",
	        "esperado expressão", //"<relacional_> inv�lido",
	        "esperado == != < >", //"<operador_relacional> inv�lido",
	        "esperado expressão", // "<termo> inv�lido",
	        "esperado expressão", //"<aritmetica_> inv�lido",
	        "esperado expressão", //"<fator> inv�lido",
	        "esperado expressão", //"<termo_> inv�lido",
	        "esperado expressão", //"<membro> inv�lido",
	        "esperado expressão", //"<membro_> inv�lido",
	        "esperado expressão", //"<membro__> inv�lido",
	        "esperado EOF id elif else endif if input output repeat until", //"<comando_> inv�lido"
	    };
}
