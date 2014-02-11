package practica4;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import excepciones.ErrorSintactico;
import base.*;
public class AnalisisSintactico {

	private Grammar g;
	private Stack<Vocabulary> entrada;
	private Stack<Vocabulary> inicio;
	
	private AnalisisSintactico(){
		entrada=new Stack<Vocabulary>();
		inicio=new Stack<Vocabulary>();
		generarGramatica();
	}
	public AnalisisSintactico(String input){
		this();
		entrada.add(new Vocabulary(input));
		inicio.add(g.getS());
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
	
	private boolean analizar() throws ErrorSintactico{
		while(!entrada.isEmpty() && !inicio.isEmpty()){
			if(inicio.peek() instanceof NonTerminals || firstElementIsNonTerminal()){
				derivar();
			}
			if(primerElementoIgual()){
				casar();
			}else{
				//lanzar excepcion
				throw new excepciones.ErrorSintactico();
			}
		}
		return entrada.isEmpty() && inicio.isEmpty();
	}
	private void derivar(){
		//TO-DO
	}
	private void casar(){
		//TO-DO
		Vocabulary in = inicio.pop();
		Vocabulary en = entrada.pop();
		//in.getVocabulario().substring(1,X);
		//en.getVocabulario().substring(1,X);
	}
	
	private boolean primerElementoIgual(){
			return (inicio.peek().getVocabulario().charAt(0))==(entrada.peek().getVocabulario().charAt(0)); 
	}
	private boolean firstElementIsNonTerminal(){
		return g.getVn().contains(inicio.peek().getVocabulario().charAt(0));
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

	}

}
