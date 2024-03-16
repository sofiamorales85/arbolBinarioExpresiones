package arbolBinarioExpresion;

/**
 * @author JennyMorales 7690-08-6790
 */
public class NodoArbol {
	private NodoArbol subArbolDerecho;
	private NodoArbol subArbolIzquierdo;
	private char datoNodo;
	
	
	public NodoArbol(char caracterEvaluado) {
		// TODO Auto-generated constructor stub
		this.datoNodo = caracterEvaluado;
		this.subArbolDerecho = null;
		this.subArbolIzquierdo = null;
	}

	public NodoArbol(String temporal) {
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


	public void setDatoNodo(char caracterEvaluado) {
		this.datoNodo = caracterEvaluado;
	}	
}
