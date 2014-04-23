package practica7;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import excepciones.ErrorSintactico;
import base.*;
public class AnalizadorRecursivoPaseo extends AnalizadorSintactico {

	
	protected AnalizadorRecursivoPaseo(ArrayList<Vocabulary> input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				NonTerminals S = new NonTerminals("PASEO");
				NonTerminals P = new NonTerminals("PASO");
				NonTerminals E = new NonTerminals("EXP");
				
				Terminals casa = new Terminals("casa");
				Terminals giro = new Terminals("giro");
				Terminals id = new Terminals("id");
				Terminals pinta = new Terminals("pinta");
				Terminals avanza = new Terminals("avanza");
				Terminals dolar = new Terminals("$");
				Terminals lambda = new Terminals("λ");
				Terminals n = new Terminals("n");
				Terminals igual = new Terminals("=");
				Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
				
				cp1.add(P);
				cp1.add(S);
				//cp1.add(T);
				Productions p1 = new Productions("PASEO",cp1);
				
				Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
				cp2.add(id);
				cp2.add(igual);
				cp2.add(E);
				Productions p2 = new Productions("PASO",cp2);
				
				Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
				
				cp3.add(lambda);
				Productions p3 = new Productions("PASEO",cp3);
				
		Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
				
				cp4.add(giro);
				cp4.add(E);
				Productions p4 = new Productions("PASO",cp4);
				
		Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();
				cp5.add(pinta);
				cp5.add(E);
				
				Productions p5 = new Productions("PASO",cp5);
				
		Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
				
				cp6.add(casa);
				
				Productions p6 = new Productions("PASO",cp6);
				
		Collection<Vocabulary> cp7 = new LinkedList<Vocabulary>();
				
				cp7.add(n);

				Productions p7 = new Productions("EXP",cp7);
				
		Collection<Vocabulary> cp8 = new LinkedList<Vocabulary>();
				
				cp8.add(id);
				Productions p8 = new Productions("EXP",cp8);
				Collection<Vocabulary> cp9 = new LinkedList<Vocabulary>();
				cp5.add(avanza);
				cp5.add(E);
				
				Productions p9 = new Productions("PASO",cp9);
				Collection<Terminals> t = new HashSet<Terminals>();
				t.add(casa);
				t.add(giro);
				t.add(id);
				t.add(avanza);
				t.add(pinta);
				t.add(n);
				t.add(igual);
				Collection<NonTerminals> nt = new HashSet<NonTerminals>();
				nt.add(S);
				nt.add(E);
				nt.add(P);
				
				Collection<Productions> p = new HashSet<Productions>();
				p.add(p7);
				p.add(p8);
				p.add(p2);
				p.add(p5);
				p.add(p9);
				p.add(p4);
				p.add(p6);
				p.add(p3);
				p.add(p1);
				//System.out.println(p.toString());
				g = new Grammar(nt,t,p,S);
		
	}

	@Override
	protected void derivar() throws ErrorSintactico {
		// TODO Auto-generated method stub
		
	}
	private void m(){
		for(Productions p:g.getP()){
			System.out.print(p.getAntecedente()+"->");
			for(Vocabulary c:p.getConsecuente())
			System.out.print(c+" ");
			System.out.println();
		}
	}
	public static void main(String[] args){
		ArrayList<Vocabulary> l = new ArrayList<Vocabulary>();
		AnalizadorRecursivoPaseo a = new AnalizadorRecursivoPaseo(l);
		a.m();
	}
	protected boolean analizar(){
		try{
			Paseo();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	void Paseo(){
		
	}
	void Paso(){
		
	}
	void Exp(){
		
	}
	boolean esID(){
		return false;
	}
	boolean Match(){
		
		return false;
	}
}
