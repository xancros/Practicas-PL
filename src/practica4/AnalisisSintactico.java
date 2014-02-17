package practica4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import excepciones.ErrorSintactico;
import base.*;
public class AnalisisSintactico {

	private Grammar g;
	private List<Vocabulary> entrada;
	private Stack<Vocabulary> inicio;
	
	private AnalisisSintactico(){
		inicio=new Stack<Vocabulary>();
		generarGramatica();
	}
    public AnalisisSintactico(ArrayList<Vocabulary> input) throws ErrorSintactico{
		this();
		
		entrada=input;
		inicio.add(g.getS());
		if(analizar())
			System.out.println("Se ha reconocido la cadena");
	}
	private void generarGramatica(){
		NonTerminals E = new NonTerminals("A");
		NonTerminals T = new NonTerminals("B");
		//NonTerminals F = new NonTerminals("F");
		NonTerminals S = new NonTerminals("S");
		
		//Terminals mas = new Terminals("+");
		Terminals por = new Terminals("d");
		Terminals i = new Terminals("b");
		Terminals abierto = new Terminals("a");
		Terminals cerrado = new Terminals("c");
		
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
		
		cp1.add(i);
		cp1.add(E);
		//cp1.add(T);
		Productions p1 = new Productions("S",cp1);
		
		Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		cp2.add(abierto);
		cp2.add(T);
		Productions p2 = new Productions("S",cp2);
		
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
		
		cp3.add(i);
		cp3.add(E);
		cp3.add(E);
		Productions p3 = new Productions("A",cp3);
		
Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
		
		cp4.add(abierto);
		cp4.add(S);
		Productions p4 = new Productions("A",cp4);
		
Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();
		cp5.add(por);
		Productions p5 = new Productions("A",cp5);
		
Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
		
		cp6.add(abierto);
		cp6.add(T);
		cp6.add(T);
		Productions p6 = new Productions("B",cp6);
		
Collection<Vocabulary> cp7 = new LinkedList<Vocabulary>();
		
		cp7.add(i);
		cp7.add(S);
		Productions p7 = new Productions("B",cp7);
		
Collection<Vocabulary> cp8 = new LinkedList<Vocabulary>();
		
		cp8.add(cerrado);
		Productions p8 = new Productions("B",cp8);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		//nt.add(F);
		nt.add(S);
		Collection<Terminals> t = new HashSet<Terminals>();
		//t.add(mas);
		t.add(por);
		t.add(i);
		t.add(abierto);
		t.add(cerrado);
		Collection<Productions> p = new HashSet<Productions>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
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
	private void derivar() throws ErrorSintactico{
		//TO-DO
		Vocabulary in = inicio.pop();
		Productions p = g.buscarProduccion(entrada.get(0), in);
		if(p==null)
			throw new excepciones.ErrorSintactico();
		for(Vocabulary c : p.getConsecuente()){
			inicio.add(c);
		}
	}
	private void casar(){
		//TO-DO
		Vocabulary in = inicio.get(0);
		Vocabulary en = entrada.get(0);
		entrada.remove(0);
		inicio.remove(0);
		System.out.println("Se reconoce: "+in.getVocabulario()+" , "+en.getVocabulario());
		mostrar();
	}
	private void mostrar(){
		System.out.print("Configuracion:(");
		for(int i=0;i<inicio.size();i++)
			System.out.print(inicio.get(i));
		System.out.print("),(");
		for(int i=0;i<entrada.size();i++)
			System.out.print(entrada.get(i));
		System.out.println(")");
	}
	private boolean primerElementoIgual(){
			String a =inicio.get(0).getVocabulario();
			String b = entrada.get(0).getVocabulario();
			return (a).equals(b); 
	}
	private boolean firstElementIsNonTerminal(){
		return g.getVn().contains(inicio.peek().getVocabulario());
	}
	
	
	/**
	 * @param args
	 * @throws ErrorSintactico 
	 */
	public static void main(String[] args) throws ErrorSintactico {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		ArrayList<Vocabulary> cadena = new ArrayList<Vocabulary>();
		cadena.add(new Vocabulary("a"));
		cadena.add(new Vocabulary("b"));
		cadena.add(new Vocabulary("b"));
		cadena.add(new Vocabulary("a"));
		cadena.add(new Vocabulary("c"));
		AnalisisSintactico an = new AnalisisSintactico (cadena);
	}

}
