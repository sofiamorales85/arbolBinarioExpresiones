package arbolBinarioExpresion;

/**
 * @author JennyMorales 7690-08-6790
 */
public class ArbolBinExp {
	NodoArbol raiz;

	public ArbolBinExp() {
		raiz = null;
	}

	public ArbolBinExp(String cadena) {
		raiz = creaArboBinExp(cadena);
	}

	public void reiniciarArbol() {
		raiz = null;
	}

	public void creaNodo(char dato) {
		raiz = new NodoArbol(dato);
	}

	public NodoArbol creaSubArbol(NodoArbol datoDerecho, NodoArbol datoIzquierdo, NodoArbol operador) {
		operador.setSubArbolIzquierdo(datoIzquierdo);
		operador.setSubArbolDerecho(datoDerecho);
		return operador;
	}

	// Para verificar si el arbol esta vacio
	public boolean arbolVacio() {
		if (raiz == null) {
			return true;
		} else {
			return false;
		}
	}

	// Metodo para el recorrido preorden RID
	private String preorden(NodoArbol subArbol, String c) {
		String cadena = "";
		if (subArbol != null) {
			cadena = c + subArbol.getDatoNodo().toString() + "\n" + preorden(subArbol.getSubArbolIzquierdo(), c)
					+ preorden(subArbol.getSubArbolDerecho(), c);
		}
		return cadena;
	}

	// Metodo para el recorrido inorden IRD
	private String inorden(NodoArbol subArbol, String c) {
		String cadena;
		cadena = "";
		if (subArbol != null) {
			cadena = c + inorden(subArbol.getSubArbolIzquierdo(), c) + subArbol.getDatoNodo().toString() + "\n"
					+ inorden(subArbol.getSubArbolDerecho(), c);
		}
		return cadena;
	}

	// Metodo para recorrido posorden IDR
	private String posorden(NodoArbol subArbol, String c) {
		String cadena;
		cadena = "";
		if (subArbol != null) {
			cadena = c + posorden(subArbol.getSubArbolIzquierdo(), c) + posorden(subArbol.getSubArbolDerecho(), c)
					+ subArbol.getDatoNodo().toString() + "\n";
		}
		return cadena;
	}

	public String toString(int a) {
		String cadena = "";
		switch (a) {
		case 0:
			cadena = preorden(raiz, cadena);
			break;
		case 1:
			cadena = inorden(raiz, cadena);
			break;
		case 2:
			cadena = posorden(raiz, cadena);
			break;
		}
		return cadena;
	}

	private int prioridad(char c) {
		int p = 100;
		switch (c) {
		case '^':
			p = 30;
			break;
		case '*':
		case '/':
			p = 20;
		case '+':
		case '-':
			p = 10;
			break;
		default:
			p = 0;
		}
		return p;
	}

	private boolean esOperador(char operador) {
		boolean resultado;
		switch (operador) {
		case '(':
		case ')':
		case '^':
		case '*':
		case '/':
		case '+':
		case '-':
			resultado = true;
			break;
		default:
			resultado = false;
		}
		return resultado;
	}

	private NodoArbol creaArboBinExp(String cadena) {
		// Pilas de arboles de expresiones para las operaciones
		PilaArbolExpresion pilaOperadores;
		PilaArbolExpresion pilaExpresiones;

		NodoArbol charIndividualValor;
		NodoArbol op1;
		NodoArbol op2;
		NodoArbol op;

		pilaOperadores = new PilaArbolExpresion();
		pilaExpresiones = new PilaArbolExpresion();
		// Sirve para identifiar si es un operador o un numero
		char caracterEvaluado;

		for (int i = 0; i < cadena.length(); i++) {
			caracterEvaluado = cadena.charAt(i);
			charIndividualValor = new NodoArbol(caracterEvaluado);
			if (!esOperador(caracterEvaluado)) {// Es un número
				pilaExpresiones.push(charIndividualValor);
			} else { // Es un operador
				switch (caracterEvaluado) {
				case '(':
					pilaOperadores.push(charIndividualValor);
					break;
				case ')':
					while (!pilaOperadores.esVacia() && !pilaOperadores.topePila().getDatoNodo().equals('(')) {// Buscamos 
																												// la
																												// posicion
																												// en la
																												// pila
						op2 = pilaExpresiones.pop();
						op1 = pilaExpresiones.pop();
						op = pilaOperadores.pop();
						op = creaSubArbol(op1, op2, op);
						pilaExpresiones.push(op);
					}
					pilaOperadores.pop();
					break;
				default:
					while (!pilaOperadores.esVacia() && prioridad(caracterEvaluado) <= prioridad(
							pilaOperadores.topePila().getDatoNodo().toString().charAt(0))) {
						op2 = pilaExpresiones.pop();
						op1 = pilaExpresiones.pop();
						op = pilaOperadores.pop();
						op = creaSubArbol(op1, op2, op);
						pilaExpresiones.push(op);
					}
					pilaOperadores.push(charIndividualValor);// Insertar el caracter que estoy comparando
				}
			}
			while (!pilaOperadores.esVacia()) {// Todavía existen elemetos en la pila
				op2 = pilaExpresiones.pop();
				op1 = pilaExpresiones.pop();
				op = pilaOperadores.pop();
				op = creaSubArbol(op1, op2, op);
				pilaExpresiones.push(op);
			}
		}
		op = pilaExpresiones.pop();
		return op;
	}

	public double evaluaExpresion() {
		return evalua(raiz);
	}

	private double evalua(NodoArbol subArbol) {
		double count = 0;
		if (!esOperador(subArbol.getDatoNodo().toString().charAt(0))) {
			return Double.parseDouble(subArbol.getDatoNodo().toString());
		} else {
			switch (subArbol.getDatoNodo().toString().charAt(0)) {
			case '^':
				count = count
						+ Math.pow(evalua(subArbol.getSubArbolIzquierdo()), evalua(subArbol.getSubArbolDerecho()));//pow es la elevación 
				break;
			case '*':
				count = count + evalua(subArbol.getSubArbolIzquierdo()) * evalua(subArbol.getSubArbolDerecho());
				break;
			case '/':
				count = count + evalua(subArbol.getSubArbolIzquierdo()) / evalua(subArbol.getSubArbolDerecho());
				break;
			case '+':
				count = count + evalua(subArbol.getSubArbolIzquierdo()) + evalua(subArbol.getSubArbolDerecho());
				break;
			case '-':
				count = count + evalua(subArbol.getSubArbolIzquierdo()) - evalua(subArbol.getSubArbolDerecho());
				break;
			}
		}
		return count;
	}
}
