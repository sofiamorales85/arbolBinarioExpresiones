package arbolBinarioExpresion;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.awt.Graphics;

/**
 * @author JennyMorales 7690-08-6790 - CristianMelgar 7690-21-8342
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

	public void creaNodo(String dato) {
		raiz = new NodoArbol(dato);
	}

	public NodoArbol creaSubArbol(NodoArbol datoDerecho, NodoArbol datoIzquierdo, NodoArbol operador) {
		operador.setSubArbolIzquierdo(datoIzquierdo);
		operador.setSubArbolDerecho(datoDerecho);
		return operador;
	}

	// Para verificar si el arbol esta vacio
	/**public boolean arbolVacio() {
		if (raiz == null) {
			return true;
		} else {
			return false;
		}
	}
	*/
	public boolean arbolVacio() {
		return raiz == null;
	}

	public int nodosCompletos(NodoArbol subArbol) {
	    if (subArbol == null) {
	        return 0;
	    } else {
	        if (subArbol.getSubArbolIzquierdo() != null && subArbol.getSubArbolDerecho() != null) {
	            return nodosCompletos(subArbol.getSubArbolIzquierdo()) + nodosCompletos(subArbol.getSubArbolDerecho()) + 1;
	        }
	        return nodosCompletos(subArbol.getSubArbolIzquierdo())+nodosCompletos(subArbol.getSubArbolDerecho()); 
	        }
	}

	// Metodo para el recorrido preorden RID
	private String preOrden(NodoArbol subArbol, String c) {	
		String cadena = "";
		if(subArbol != null) {
			cadena = c + subArbol.getDatoNodo().toString() + " " + preOrden(subArbol.getSubArbolIzquierdo(), c)
				+ preOrden(subArbol.getSubArbolDerecho(), c);
		}
		return cadena;
	}

	// Metodo para el recorrido inorden IRD
	private String inOrden(NodoArbol subArbol, String c) {
		String cadena = "";
		if (subArbol != null) {
			cadena = c + inOrden(subArbol.getSubArbolIzquierdo(), c) + " " + subArbol.getDatoNodo().toString()
					+ inOrden(subArbol.getSubArbolDerecho(), c);
		}
		return cadena;
	}

	// Metodo para recorrido posorden IDR
	private String posOrden(NodoArbol subArbol, String c) {
		String cadena = "";
		if (subArbol != null) {
			cadena = c + posOrden(subArbol.getSubArbolIzquierdo(), c)+ " " + posOrden(subArbol.getSubArbolDerecho(), c)
					+ subArbol.getDatoNodo().toString();
		}
		return cadena;
	}

	public String toString(int a) {
		String cadena = "";
		switch (a) {
		case 0:
			cadena = preOrden(raiz, cadena);
			break;
		case 1:
			cadena = inOrden(raiz, cadena);
			break;
		case 2:
			cadena = posOrden(raiz, cadena);
			break;
		}
		return cadena;
	}

	private static int prioridad(String c) {
		int p = 100;
		switch (c) {
		case "^":
			p = 3;
			break;
		case "*":
		case "/":
			p = 2;
		case "+":
		case "-":
			p = 1;
			break;
		default:
			p = 0; // Operadores no válidos o paréntesis
		}
		return p;
	}
	
	

	private static boolean esNumero(String elemento) {
 		return elemento.matches("\\d+");
 	}
 	 
	//Funcion que verifica si es un operador
	private static boolean esOperador(String operador) {
		 return operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/") || operador.equals("(")  || operador.equals(")") || operador.equals("^");
	}

	private NodoArbol creaArboBinExp(String cadena) {
		// Pilas de arboles de expresiones para las operaciones
		PilaArbolExpresion pilaOperadores;
		PilaArbolExpresion pilaExpresiones;

		NodoArbol charIndividualValor;
		NodoArbol expresionUno;
		NodoArbol expresionDos;
		NodoArbol operador;
		boolean masDigitos = false;

		pilaOperadores = new PilaArbolExpresion();
		pilaExpresiones = new PilaArbolExpresion();
		// Sirve para identificar si es un operador o un numero
		String caracterEvaluado;

		for (int i = 0; i < cadena.length(); i++) {
			caracterEvaluado =  String.valueOf(cadena.charAt(i));
			System.out.println("Caracter evaluado :" + caracterEvaluado );
			
			//Si el primer caracter es negativo
			if (i == 0 && caracterEvaluado.equals("-")) {
				caracterEvaluado = "" + String.valueOf(cadena.charAt(i));
				String digito = String.valueOf(cadena.charAt(i+1));
				caracterEvaluado = caracterEvaluado + digito;		
				System.out.println("Caracter evaluado " + caracterEvaluado);
				i++; //Incrementa en 1 para el siguiente caracter
			}
					
			if(i + 1 < cadena.length()){
				//Si el siguiente caracter es un numero los concatena 
				if(esNumero(String.valueOf(cadena.charAt(i))) && esNumero(String.valueOf(cadena.charAt(i+1)))) {
					String digitoConcat ="" + cadena.charAt(i+1);
					caracterEvaluado =  caracterEvaluado +  digitoConcat;
					System.out.println("Caracter evaluado " + caracterEvaluado);
					i++;
				}
			}
			
			
			charIndividualValor = new NodoArbol(caracterEvaluado);
			if (!esOperador(caracterEvaluado)) {// Es un número
				
				if(!masDigitos) {
					masDigitos = true;
					pilaExpresiones.push(charIndividualValor);
				}else {
					String temporal = pilaExpresiones.pop().getDatoNodo().toString();
					temporal = temporal + caracterEvaluado;
					charIndividualValor = new NodoArbol(temporal);
					pilaExpresiones.push(charIndividualValor);
					
				}
				
			} else { // Es un operador
				masDigitos = false;
				switch (caracterEvaluado) {
				case "(":
					pilaOperadores.push(charIndividualValor);
					break;
				case ")":
					while (!pilaOperadores.esVacia() && !pilaOperadores.topePila().getDatoNodo().equals("(")) {// Buscamos la posicion en la pila
						expresionDos = pilaExpresiones.pop();
						expresionUno = pilaExpresiones.pop();
						operador = pilaOperadores.pop();
						operador = creaSubArbol(expresionDos, expresionUno, operador);
						pilaExpresiones.push(operador);
					}
					pilaOperadores.pop();
					break;
				default:
					while (!pilaOperadores.esVacia() && prioridad(caracterEvaluado) <= prioridad(pilaOperadores.topePila().getDatoNodo())) {
						expresionDos = pilaExpresiones.pop();
						expresionUno = pilaExpresiones.pop();
						operador = pilaOperadores.pop();
						operador = creaSubArbol(expresionDos, expresionUno, operador);
						pilaExpresiones.push(operador);
					}
					pilaOperadores.push(charIndividualValor);// Insertar el caracter que estoy comparando
				}
			}
		}
		while (!pilaOperadores.esVacia()) {// Todavía existen elemetos en la pila
			expresionDos = pilaExpresiones.pop();
			expresionUno = pilaExpresiones.pop();
			operador = pilaOperadores.pop();
			operador = creaSubArbol(expresionDos, expresionUno, operador);
			pilaExpresiones.push(operador);
		}
		operador = pilaExpresiones.pop();
		return operador;
	}

	public double evaluaExpresion() {
		return evalua(raiz);
	}

	//Función para validar caracteres en la expresion ingresada
	 public static boolean contieneCaracteresEspeciales(String expresion) {
	        // Patrón para buscar caracteres especiales
	        Pattern patron = Pattern.compile("[&%$#\"!,:;.\\_{}\\[\\]´¿'?¡|]");
	        Matcher matcher = patron.matcher(expresion);
	        return matcher.find();
	 }
	
	private double evalua(NodoArbol subArbol) {
		if (subArbol == null) {
	        throw new IllegalArgumentException("El nodo de árbol es nulo.");
	    }

		String operador = subArbol.getDatoNodo();
	    double resultado = 0;
		
	    if (!esOperador(operador)) {
	        return Double.parseDouble(subArbol.getDatoNodo().toString());
	    } else {
	        double izquierdo = evalua(subArbol.getSubArbolIzquierdo());
	        double derecho = evalua(subArbol.getSubArbolDerecho());

	        switch (operador) {
	            case "^":
	                resultado = Math.pow(izquierdo, derecho);//pow es la elevación
	                break;
	            case "*":
	                resultado = izquierdo * derecho;
	                break;
	            case "/":
	                if (derecho == 0) {
	                    throw new ArithmeticException("División por cero.");
	                }
	                resultado = izquierdo / derecho;
	                break;
	            case "+":
	                resultado = izquierdo + derecho;
	                break;
	            case "-":
	                resultado = izquierdo - derecho;
	                break;
	            default:
	                throw new IllegalArgumentException("Operador desconocido: " + operador);
	        }
	    }
	    return resultado;
	}
}
