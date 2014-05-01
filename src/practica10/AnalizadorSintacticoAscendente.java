package practica10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import base.Grammar;
import base.NonTerminals;
import base.Productions;
import base.Terminals;
import base.Vocabulary;
import practica8.AnalizadorAscendenteLR;


public class AnalizadorSintacticoAscendente extends AnalizadorAscendenteLR {
	public ArrayList<Productions> listaDeProducciones;
	
	protected AnalizadorSintacticoAscendente(List<Vocabulary> input) {
		super(input);
		
		// TODO Auto-generated constructor stub
	}
	protected ConfiguracionLR cfg;
	
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		/*NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		NonTerminals E2 = new NonTerminals("E'");

		Terminals mas = new Terminals("+");
		Terminals por = new Terminals("*");
		Terminals id = new Terminals("id");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals punto = new Terminals(".");
		
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();

		cp1.add(E);
		cp1.add(mas);
		cp1.add(T);
		Productions p1 = new Productions("E", cp1);

		Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		cp2.add(T);
		Productions p2 = new Productions("E", cp2);
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();

		cp3.add(T);
		cp3.add(por);
		cp3.add(F);
		Productions p3 = new Productions("T", cp3);

		Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
		cp4.add(F);
		Productions p4 = new Productions("T", cp4);
		Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();

		cp5.add(abierto);
		cp5.add(E);
		cp5.add(cerrado);
		Productions p5 = new Productions("F", cp5);

		Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
		cp6.add(id);
		Productions p6 = new Productions("F", cp6);

		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		nt.add(F);

		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(por);
		t.add(id);
		t.add(abierto);
		t.add(cerrado);
		Collection<Productions> p = new ArrayList<Productions>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);

		// System.out.println(p.toString());
		super.g = new Grammar(nt, t, p, E);
		g.setS2(new NonTerminals("E'"));
		List<Vocabulary> columnas = new LinkedList<Vocabulary>();
		columnas.add(id);
		columnas.add(mas);
		columnas.add(por);
		columnas.add(abierto);
		columnas.add(cerrado);
		columnas.add(dolar);
		columnas.add(E);
		columnas.add(T);
		columnas.add(F);
		l = columnas;*/
		Terminals cr = new Terminals("cr");
		Terminals flecha = new Terminals("->");
		Terminals nt = new Terminals("noterminal");
		Terminals t = new Terminals("terminal");
		Terminals dolar = new Terminals("$");

		NonTerminals PRODUCCIONES = new NonTerminals("PRODUCCIONES");
		NonTerminals PRODUCCION = new NonTerminals("PRODUCCION");
		NonTerminals ANTECEDENTE = new NonTerminals("ANTECEDENTE");
		NonTerminals CONSECUENTE = new NonTerminals("CONSECUENTE");
		NonTerminals SIMBOLO = new NonTerminals("SIMBOLO");
		ArrayList<Vocabulary> vnt = new ArrayList<Vocabulary>();
		vnt.add(cr);
		vnt.add(flecha);
		vnt.add(nt);
		vnt.add(t);
		vnt.add(PRODUCCIONES);
		vnt.add(PRODUCCION);
		vnt.add(ANTECEDENTE);
		vnt.add(CONSECUENTE);
		vnt.add(SIMBOLO);
		vnt.add(dolar);

		Collection<Vocabulary> cp1 = new ArrayList<Vocabulary>();
		cp1.add(PRODUCCION);

		cp1.add(cr);
		cp1.add(PRODUCCIONES);
		Productions p1 = new Productions("PRODUCCIONES", cp1);

		Collection<Vocabulary> cp2 = new ArrayList<Vocabulary>();
		cp2.add(PRODUCCION);
		Productions p2 = new Productions("PRODUCCIONES", cp2);
		Collection<Vocabulary> cp3 = new ArrayList<Vocabulary>();

		cp3.add(ANTECEDENTE);
		cp3.add(flecha);
		cp3.add(CONSECUENTE);
		Productions p3 = new Productions("PRODUCCION", cp3);

		Collection<Vocabulary> cp4 = new ArrayList<Vocabulary>();
		cp4.add(nt);
		Productions p4 = new Productions("ANTECEDENTE", cp4);
		Collection<Vocabulary> cp5 = new ArrayList<Vocabulary>();

		cp5.add(nt);
		Productions p5 = new Productions("SIMBOLO", cp5);

		Collection<Vocabulary> cp6 = new ArrayList<Vocabulary>();
		cp6.add(t);
		Productions p6 = new Productions("SIMBOLO", cp6);
		Collection<Vocabulary> cp7 = new ArrayList<Vocabulary>();

		cp7.add(SIMBOLO);
		cp7.add(CONSECUENTE);

		Productions p7 = new Productions("CONSECUENTE", cp7);
		Collection<Vocabulary> cp8 = new ArrayList<Vocabulary>();
		cp8.add(SIMBOLO);
		Productions p8 = new Productions("CONSECUENTE", cp8);
		Collection<NonTerminals> noterminales = new HashSet<NonTerminals>();
		noterminales.add(PRODUCCIONES);
		noterminales.add(PRODUCCION);
		noterminales.add(ANTECEDENTE);
		noterminales.add(CONSECUENTE);
		noterminales.add(SIMBOLO);

		Collection<Terminals> terminales = new HashSet<Terminals>();
		terminales.add(cr);
		terminales.add(flecha);
		terminales.add(nt);
		terminales.add(t);
		ArrayList<Productions> li = new ArrayList<Productions>();
		Collection<Productions> p = new ArrayList<Productions>();
		p.add(p1);

		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
		li.add(p1);
		li.add(p2);
		li.add(p3);
		li.add(p4);
		li.add(p5);
		li.add(p6);
		li.add(p7);
		li.add(p8);

		Grammar g = new Grammar(noterminales, terminales, p, PRODUCCIONES);
		g.conjuntoTNT.addAll(vnt);
		ArrayList<Productions> producciones = new ArrayList<Productions>();
		producciones.add(p1);
		producciones.add(p2);
		producciones.add(p3);
		producciones.add(p4);
		producciones.add(p5);
		producciones.add(p6);
		producciones.add(p7);
		producciones.add(p8);
		cfg = new ConfiguracionLR(producciones, g);
	
	}
	protected AnalizadorSintacticoAscendente(int i){
		super(i);
		
	}
	
	
	public boolean analizar(){
		while(entrada.size()>0){
			int accion = cfg.analisisDecision(entrada.get(0), inicio);
			switch (accion) {
			case 0: {
				System.out.println("Se reconoce: "+entrada.get(0));
				entrada.remove(0);
			}
				break;
			case 2: {
				
				
				
				cfg.analisisDecision(inicio.get(inicio.size()-1), inicio);
			}break;
			case -2: {
				listaDeProducciones = (ArrayList<Productions>)inicio.get(inicio.size()-2).s;
				System.out.println("Cadena reconocida");
				return true;
			}
			
			}
		}
		return false;
	}
	
	
	public static void main(String args[]){
		ArrayList<Vocabulary> l = new ArrayList<Vocabulary>();
		l.add(new Vocabulary("id"));
		l.add(new Vocabulary("+"));
		l.add(new Vocabulary("id"));
		l.add(new Vocabulary("*"));
		l.add(new Vocabulary("("));
		l.add(new Vocabulary("id"));
		l.add(new Vocabulary("+"));
		l.add(new Vocabulary("id"));
		l.add(new Vocabulary(")"));
		l.add(new Vocabulary("$"));
		AnalizadorSintacticoAscendente a = new AnalizadorSintacticoAscendente(l);
		a.generarGramatica();
		//a.analizar();
		/*ArrayList<Productions> p =(ArrayList<Productions>) a.g.getP();
		for(Productions pd:p){
			System.out.println(pd.toString());
			LinkedList<Vocabulary> con =(LinkedList<Vocabulary>) pd.getConsecuente();
			System.out.println(con.get(0));
		}*/
			
	}
}
