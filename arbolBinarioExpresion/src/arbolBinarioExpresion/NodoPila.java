package arbolBinarioExpresion;

/**
 * @author JennyMorales 7690-08-6790
 */
public class NodoPila {
	private NodoArbol dato;
	private NodoPila siguiente;
		
	public NodoPila(NodoArbol dato) {
		this.dato = dato;
		this.siguiente = null;
	}

	public NodoArbol getDato() {
		return dato;
	}

	public void setDato(NodoArbol dato) {
		this.dato = dato;
	}

	public NodoPila getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoPila siguiente) {
		this.siguiente = siguiente;
	}
	
	
	
}
