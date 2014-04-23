package p7;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import P3.Token;
public class Lienzo extends JPanel {
	/**
	* Constructor for objects of class Lienzo
	*/
	public static String cadenaAProcesar =" ";
	
	public Lienzo(){
		super();
		setPreferredSize(new Dimension(320,240));
	}
	
	public Lienzo(String cadena){
		super();
		cadenaAProcesar=cadena;
		setPreferredSize(new Dimension(320,240));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		
		
		AnalizadorRercursivoPaseo analizadorSintactico = new AnalizadorRercursivoPaseo();//creamos el analizador sintactico
		//analizadorSintactico.analizadorLexico.obtenerListaDeTokensCadena(cadenaAProcesar);//el lexico obtiene la lista de coquens
		System.out.println("la cadena a procesar es: "+cadenaAProcesar);
		
		
		g.setColor(Color.red);
		g.setColor(Color.red);
		Tortuga t=new Tortuga(g);
		t=new Tortuga(g);
		t.pen(false);
		t.turn(90);
		t.move(240/2);
		t.turn(-90);
		t.move(320/2);
		t.pen(true);
		//estamos en el centro de pintado
		
		if(AnalizadorRercursivoPaseo.analiza()){
			
			ArrayList<Token> lista = analizadorSintactico.analizadorLexico.obtenerListaDeTokensCadena(cadenaAProcesar);
			Iterator<Token> it = lista.iterator();
			while(it.hasNext()){//iteraremos la lista de tokens segun vengan y realizaremos el dibujado del camino
				Token token = it.next();
				if(token.lexema.equals("avanza")){//avanzar
					int numero = Integer.parseInt(it.next().lexema);
					System.out.println("avanza: "+ numero);
					t.move(numero/2);
				}
				if(token.lexema.equals("giro")){//avanzar
					int numero = Integer.parseInt(it.next().lexema);
					t.turn(numero);
				}
				if(token.lexema.equals("pinta")){//avanzar
					//t.pen(true);//pintar
					//t.pen(false);//deshabilitar pintado
				}
			}
		}
		/*g.setColor(Color.red);
		t=new Tortuga(g);
		t.pen(false);
		t.turn(90);
		t.move(240/2);
		t.turn(-90);
		t.move(320/2);
		t.pen(true);
		t.move(50);
		t.turn(90);
		t.move(50);
		g.setColor(Color.black);
		t.turn(-120);
		t.move(50);
		t.turn(90);
		t.move(50);
		*/
	}
	
	public void redraw(){
		this.repaint();
	}
		
}
