package arbolBinarioExpresion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;

public class InterfazArbolBinarioExpresiones extends JFrame {

	private JPanel contentPane;
	private JTextField expresion;
	private JTextField textResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazArbolBinarioExpresiones frame = new InterfazArbolBinarioExpresiones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazArbolBinarioExpresiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		expresion = new JTextField();
		expresion.setBounds(35, 121, 202, 34);
		contentPane.add(expresion);
		expresion.setColumns(10);
		
		JTextArea preOrden = new JTextArea();
		preOrden.setBounds(301, 165, 613, 42);
		contentPane.add(preOrden);
		
		JTextArea inOrden = new JTextArea();
		inOrden.setBounds(301, 252, 613, 42);
		contentPane.add(inOrden);
		
		JTextArea posOrden = new JTextArea();
		posOrden.setBounds(301, 353, 613, 49);
		contentPane.add(posOrden);
		
		JLabel lblNewLabel = new JLabel("Crear árboles binarios de expresión");
		lblNewLabel.setForeground(new Color(51, 102, 51));
		lblNewLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 30));
		lblNewLabel.setBorder(UIManager.getBorder("TitledBorder.border"));
		lblNewLabel.setBounds(250, 29, 571, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pre Orden");
		lblNewLabel_1.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(301, 124, 64, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("In Orden");
		lblNewLabel_2.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(303, 229, 74, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pos Orden");
		lblNewLabel_3.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(301, 319, 85, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Notación Polaca ");
		lblNewLabel_4.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(35, 323, 116, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel mensaje = new JLabel("");
		mensaje.setForeground(new Color(153, 51, 0));
		mensaje.setBackground(new Color(204, 51, 0));
		mensaje.setFont(new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC, 11));
		mensaje.setHorizontalAlignment(SwingConstants.LEFT);
		mensaje.setBounds(35, 88, 368, 34);
		contentPane.add(mensaje);
		
		JButton btnEvauar = new JButton("Evaluar");
		btnEvauar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEvauar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadenaEntrada = expresion.getText();
				validarTexto();
				if(cadenaEntrada.equals("")) {
					mensaje.setText("Debe ingresar una expresión");
				}else {
					System.out.println("Entrada de texto: " + expresion.getText());
					ArbolBinExp arbolBinExpresion = new ArbolBinExp(cadenaEntrada);
					preOrden.setText(arbolBinExpresion.toString(0));
					inOrden.setText(arbolBinExpresion.toString(1));
					posOrden.setText(arbolBinExpresion.toString(2));
					
					double resultado = arbolBinExpresion.evaluaExpresion();
			
					textResultado.setText(String.valueOf(resultado));		
				}
			}
			
			//Función para validar los caracteres en la expresion ingresada
			private void validarTexto() {
				// TODO Auto-generated method stub
				ArbolBinExp arbolBinExpresion = new ArbolBinExp();
				String expresionUsuario = expresion.getText();
		        boolean esInvalido = arbolBinExpresion.contieneCaracteresEspeciales(expresionUsuario);

		        if (esInvalido) {
		        	mensaje.setText("La expresión debe usar operadores válidos");
					preOrden.setText("");
					inOrden.setText("");
					posOrden.setText("");
					expresion.setText("");
					textResultado.setText("");
					
		        } else {
		        	mensaje.setText("");
		        }
			}
		});
		

		btnEvauar.setBounds(35, 165, 85, 21);
		contentPane.add(btnEvauar);
		
		
		JLabel lblResultado = new JLabel("Resultado de evaluación:");
		lblResultado.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		lblResultado.setBounds(35, 258, 187, 24);
		contentPane.add(lblResultado);
		
		textResultado = new JTextField();
		textResultado.setBounds(35, 292, 202, 24);
		contentPane.add(textResultado);
		textResultado.setColumns(10);
		textResultado.setEditable(false);
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preOrden.setText("");
				inOrden.setText("");
				posOrden.setText("");
				expresion.setText("");
				textResultado.setText("");
				mensaje.setText("");
			}
		});
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBounds(137, 165, 85, 21);
		contentPane.add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(891, 34, 85, 21);
		contentPane.add(btnSalir);
		
		
		

	}
}
