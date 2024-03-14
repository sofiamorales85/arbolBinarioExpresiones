package arbolBinarioExpresion;

/**
 * @author JennyMorales 7690-08-6790
 */
public class NodoArbol {
	private NodoArbol subArbolDerecho;
	private NodoArbol subArbolIzquierdo;
	private Object datoNodo;
	
	
	public NodoArbol(Object datoNodo) {
		this.datoNodo = datoNodo;
		this.subArbolDerecho = null;
		this.subArbolIzquierdo = null;
	}


	public NodoArbol(char caracterEvaluado) {
		// TODO Auto-generated constructor stub
	}

	public NodoArbol getSubArbolDerecho() {
		return subArbolDerecho;
	}


	public void setSubArbolDerecho(NodoArbol subArbolDerecho) {
		this.subArbolDerecho = subArbolDerecho;
	}


	public NodoArbol getSubArbolIzquierdo() {
		return subArbolIzquierdo;
	}


	public void setSubArbolIzquierdo(NodoArbol subArbolIzquierdo) {
		this.subArbolIzquierdo = subArbolIzquierdo;
	}


	public Object getDatoNodo() {
		return datoNodo;
	}


	public void setDatoNodo(String datoNodo) {
		this.datoNodo = datoNodo;
	}	
}
