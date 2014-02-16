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
		inicio=new Stack<Vocabulary>();
		generarGramatica();
	}
	public AnalisisSintactico(Stack<Vocabulary> input) throws ErrorSintactico{
		this();
		entrada=input;
		inicio.add(g.getS());
		analizar();
	}
	private void generarGramatica(){
		NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		NonTerminals S = new NonTerminals("E");
		
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
		
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
		
		cp3.add(i);
		Productions p3 = new Productions("E",cp3);
		
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
		p.add(p3);
		//System.out.println(p.toString());
		g = new Grammar(nt,t,p,S);
		//System.out.println(g.getS().getVocabulario());
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
		Vocabulary in = inicio.pop();
		Productions p = g.buscarProduccion(entrada.peek(), in);
		for(Vocabulary c : p.getConsecuente()){
			inicio.push(c);
		}
	}
	private void casar(){
		//TO-DO
		Vocabulary in = inicio.pop();
		Vocabulary en = entrada.pop();
		System.out.println(in.getVocabulario()+" , "+en.getVocabulario());
	}
	
	private boolean primerElementoIgual(){
			return (inicio.peek().getVocabulario()).equals((entrada.peek().getVocabulario())); 
	}
	private boolean firstElementIsNonTerminal(){
		return g.getVn().contains(inicio.peek().getVocabulario());
	}
	
	
	/**
	 * @param args
	 * @throws ErrorSintactico 
	 */
	public static void main(String[] args) throws ErrorSintactico {
		// TODO Apéndice de método generado automáticamente
		Stack<Vocabulary> cadena = new Stack<Vocabulary>();
		//cadena.add(new Vocabulary(")"));
		cadena.add(new Vocabulary("i"));
		cadena.add(new Vocabulary("+"));
		cadena.add(new Vocabulary("i"));
		//cadena.add(new Vocabulary("("));
		AnalisisSintactico an = new AnalisisSintactico (cadena);
	}

}
