package paraRemover;

public enum ETipo {
	INT64("int64"),FLOAT_64("float64"),STRING("string"), BOOL("bool");

	public String descricao;
	
	ETipo(String string) {
		this.descricao = string;
	} 
	
	public static ETipo getTipoByString(String tipo) {
		var values = values();
		for(int i=0;i<values.length; i++) {
			if (values[i].descricao.contains(tipo)) {
				return values[i];
			}
		}
		return FLOAT_64;
	}
}
