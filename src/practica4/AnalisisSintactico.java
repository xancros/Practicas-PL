package practica4;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import base.*;
public class AnalisisSintactico {

	private Grammar g;
	private Stack<Vocabulary> entrada;
	private Stack<Vocabulary> lectura;
	
	private AnalisisSintactico(){
		entrada=new Stack<Vocabulary>();
		lectura=new Stack<Vocabulary>();
		generarGramatica();
	}
	public AnalisisSintactico(String input){
		this();
		entrada.add(new Vocabulary(input));
		lectura.add(g.getS());
	}
	private void generarGramatica(){
		NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		NonTerminals S = new NonTerminals("S");
		
		Terminals mas = new Terminals("+");
		Terminals por = new Terminals("*");
		Terminals i = new Terminals("i");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
		
		cp1.add(E);
		cp1.add(mas);
		cp1.add(T);
		Productions p1 = new Productions("E",cp1);
		
		Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		cp2.add(T);
		Productions p2 = new Productions("E",cp2);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		nt.add(F);
		nt.add(S);
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(por);
		t.add(i);
		t.add(abierto);
		t.add(cerrado);
		Collection<Productions> p = new HashSet<Productions>();
		p.add(p1);
		p.add(p2);
		//System.out.println(p.toString());
		Grammar g = new Grammar(nt,t,p,S);
	}
	
	private boolean analizar(){
		while(!entrada.isEmpty() && !lectura.isEmpty()){
			
		}
		return false;
	}
	private void derivar(){
		//TO-DO
	}
	private void casar(){
		//TO-DO
		
	}
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

	}

}
