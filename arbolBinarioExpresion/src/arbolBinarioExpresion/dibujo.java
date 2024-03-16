package arbolBinarioExpresion;
import java.awt.Graphics;
/**
 * @author JennyMorales 7690-08-6790 - CristianMelgar 7690-21-8342
 */
import javax.swing.JPanel;
public class dibujo extends JPanel {
	private ArbolBinExp arbol;
	public static final int Diametro = 30;
	public static final int Radio = Diametro / 2;
	public static final int Ancho = 30;
	
	public void setArbol(ArbolBinExp arbol) {
		this.arbol=arbol;
		repaint();
	}
	@Override
	public void paint(Graphics g) {
	super.paint(g);
	pintar(g, getWidth()/2, 20, arbol.raiz);
	}
	public void pintar(Graphics g, int x, int y, NodoArbol subArbol) {
		if(subArbol != null) {
			int Extra=arbol.nodosCompletos(subArbol)*Ancho/2;
			g.drawOval(x, y, Diametro, Diametro);
			g.drawString(subArbol.getDatoNodo().toString(), x+12, y+18);
			if(subArbol.getSubArbolIzquierdo() != null) {
				g.drawLine(x, y + Radio, x + Radio - Ancho - Extra, y + Ancho);
			}
			if(subArbol.getSubArbolDerecho() != null) {
				g.drawLine(x + Diametro, y + Radio, x + Radio + Ancho + Extra, y + Ancho);
				
			}
			pintar(g, x - Ancho - Extra, y + Ancho, subArbol.getSubArbolIzquierdo());
			pintar(g, x + Ancho + Extra, y + Ancho, subArbol.getSubArbolDerecho());
		}
		
	}
}
