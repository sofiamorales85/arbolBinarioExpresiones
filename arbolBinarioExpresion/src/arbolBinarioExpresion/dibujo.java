package arbolBinarioExpresion;
import java.awt.Graphics;
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
		}
		
	}
}
