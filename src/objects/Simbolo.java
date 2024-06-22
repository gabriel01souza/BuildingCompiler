package objects;

import parte04.ETipo;

public class Simbolo {

	private ETipo tipo;
	private boolean isUsed;

	public Simbolo() {
	}

	public Simbolo(ETipo tipo, boolean isUsed) {
		this.tipo = tipo;
		this.isUsed = isUsed;
	}

//	public String getIdentificador() {
//		return identificador;
//	}
//
//	public void setIdentificador(String identificador) {
//		this.identificador = identificador;
//	}

	public ETipo getTipo() {
		return tipo;
	}

	public void setTipo(ETipo tipo) {
		this.tipo = tipo;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}
