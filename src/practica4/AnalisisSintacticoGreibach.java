package practica4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import excepciones.ErrorSintactico;
import base.*;

public class AnalisisSintacticoGreibach extends base.AnalizadorSintactico {
	
    public AnalisisSintacticoGreibach(ArrayList<Vocabulary> input) throws ErrorSintactico{
		super(input);
		if(analizar())
			System.out.println("Se ha reconocido la cadena");
		else
			System.err.println("Error al reconocer la cadena");
	}
	protected void generarGramatica(){
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
	
	/*protected boolean analizar() throws ErrorSintactico{
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
	}*/
	protected void derivar() throws ErrorSintactico{
		//TO-DO
		Vocabulary in = inicio.pop();
		Productions p = g.buscarProduccion(entrada.get(0), in);
		if(p==null)
			throw new excepciones.ErrorSintactico();
		for(Vocabulary c : p.getConsecuente()){
			inicio.add(c);
		}
	}
	
	
	
	/**
	 * @param args
	 * @throws ErrorSintactico 
	 */
	public static void main(String[] args) throws ErrorSintactico {
		// TODO Apéndice de método generado automáticamente
		ArrayList<Vocabulary> cadena = new ArrayList<Vocabulary>();
		cadena.add(new Vocabulary("a"));
		cadena.add(new Vocabulary("b"));
		cadena.add(new Vocabulary("b"));
		cadena.add(new Vocabulary("a"));
		cadena.add(new Vocabulary("c"));
		AnalisisSintacticoGreibach an = new AnalisisSintacticoGreibach (cadena);
	}

}
