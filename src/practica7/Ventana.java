package practica7;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener{
	private Lienzo l;
	private JButton b1;
	//public String cadena="";
	public static String consola="";
	/**
	* Constructor for objects of class Ventana
	*/
	JTextArea textArea;
	public Ventana(String cadena){
		super();
		JPanel barraHerramientas = new JPanel();
		textArea = new JTextArea(20, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		consola+="<br>"+cadena;
		JLabel labelError=new JLabel("<html>Consola:"+consola+"</html>");//<html>Consola: línea<br>Segunda Línea</html>"
		
		//barraHerramientas.setLayout(new FlowLayout());
		b1 = new JButton("Ejecuta");
		b1.setActionCommand("s");
		b1.addActionListener(this);
		barraHerramientas.add(b1);
		l=new Lienzo(cadena);
		//getContentPane().setLayout(new BorderLayout()); // No hacefalta, por defecto ya es BorderLayout
		getContentPane().add(barraHerramientas, BorderLayout.NORTH);
		getContentPane().add(l, BorderLayout.CENTER);
		getContentPane().add(textArea,BorderLayout.WEST);
		getContentPane().add(labelError,BorderLayout.SOUTH);
		pack();
	}
	public static void main(){
		//Ventana v=new Ventana();
		//v.setVisible(true);
	}

	/*********************************
	* ActionListener implementation
	*********************************/
	public void actionPerformed(ActionEvent e) {
		System.out.println("textArea tiene: "+textArea.getText().toString());
		
		if ("s".equals(e.getActionCommand())) {
			
			//setCurrentTool(sT);
			
			//System.out.println("textArea tiene: "+this.textArea.getText().toString());
			
			String cadenaIntroducida = (String)this.textArea.getText();
			this.setVisible(false);
			Ventana ventana2 = new Ventana(cadenaIntroducida);
			ventana2.setVisible(true);
			}
		
	}
}
