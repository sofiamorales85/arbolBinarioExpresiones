package arbolBinarioExpresion;

/**
 * @author JennyMorales 7690-08-6790 - CristianMelgar 7690-21-8342
 */
public class NodoArbol {
	
	private NodoArbol subArbolDerecho;
	private NodoArbol subArbolIzquierdo;
	private String datoNodo;
	
	
	public NodoArbol(String caracterEvaluado) {
		// TODO Auto-generated constructor
		this.datoNodo = caracterEvaluado;
		this.subArbolDerecho = null;
		this.subArbolIzquierdo = null;
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


	public String getDatoNodo() {
		return datoNodo;
	}


	public void setDatoNodo(String caracterEvaluado) {
		this.datoNodo = caracterEvaluado;
	}	
}
