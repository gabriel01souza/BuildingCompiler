package parte04;

public enum ETipo {
	INT64("int64"),FLOAT_64("float64"),STRING("string"), BOOL("bool");

	public String descricao;
	
	ETipo(String string) {
		this.descricao = string;
	} 
}
