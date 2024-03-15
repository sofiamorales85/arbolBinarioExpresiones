package arbolBinarioExpresion;

public class PilaArbolExpresion {
	private NodoPila tope;

	public PilaArbolExpresion() {
		this.tope = null;
	}
	
	public boolean esVacia() {
		if(tope == null) {
			return true;
		}else {
			return false;
		}
	}
	
	//Insertando nuevo elemento en la pila
	public void push(NodoArbol elemento) {
		NodoPila nuevo;
		nuevo = new NodoPila(elemento);
		nuevo.setSiguiente(tope);
		tope = nuevo;
	}
	
	public NodoArbol topePila() {
		return tope.getDato();
	}
	
	public void ReiniciarPila() {
		tope = null;
	}
	
	//Eliminando arbol de expresiones
	public NodoArbol pop() {
		NodoArbol temporal = null;
		if(!esVacia()) {
			temporal = tope.getDato();
			tope = tope.getSiguiente();
		}
		return temporal;
	}

}
