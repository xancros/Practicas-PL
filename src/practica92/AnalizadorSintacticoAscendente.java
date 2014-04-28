package practica92;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import base.Grammar;
import base.NonTerminals;
import base.Productions;
import base.Terminals;
import base.Vocabulary;
import practica8.AnalizadorAscendenteLR;

public class AnalizadorSintacticoAscendente extends AnalizadorAscendenteLR {

	protected AnalizadorSintacticoAscendente(List<Vocabulary> input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
	protected ConfiguracionLR cfg;
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		NonTerminals E = new NonTerminals("S");
		NonTerminals T = new NonTerminals("C");
		//NonTerminals F = new NonTerminals("F");
		NonTerminals E2 = new NonTerminals("S'");
		
		Terminals mas = new Terminals("c");
		Terminals por = new Terminals("d");
		//Terminals id = new Terminals("id");
		//Terminals abierto = new Terminals("(");
		//Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals punto = new Terminals(".");
		ArrayList<Vocabulary>conjuntoTVT = new ArrayList<Vocabulary>();
		conjuntoTVT.add(E);
		conjuntoTVT.add(T);
		//conjuntoTVT.add(F);
	//	conjuntoTVT.add(id);
		conjuntoTVT.add(mas);
		conjuntoTVT.add(por);
		conjuntoTVT.add(dolar);
		//conjuntoTVT.add(abierto);
	//	conjuntoTVT.add(cerrado);
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
		
		//cp1.add(E);
		cp1.add(T);
		cp1.add(T);
		Productions p1 = new Productions("S",cp1);
		
		//Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		//cp2.add(T);
		//Productions p2 = new Productions("E",cp2);
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
		
		//cp3.add(T);
		cp3.add(mas);
		cp3.add(T);
		Productions p3 = new Productions("C",cp3);
		
		//Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
		//cp4.add(F);
		//Productions p4 = new Productions("T",cp4);
Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();
		
		//cp5.add(T);
		cp5.add(por);
		//cp5.add(cerrado);
		Productions p5 = new Productions("C",cp5);
		
		//Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
		//cp6.add(id);
		//Productions p6 = new Productions("F",cp6);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		//nt.add(F);
		
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(por);
		//t.add(id);
		//t.add(abierto);
		//t.add(cerrado);
		Collection<Productions> p = new ArrayList<Productions>();
		p.add(p1);
		//p.add(p2);
		p.add(p3);
		//p.add(p4);
		p.add(p5);
		//p.add(p6);
		
		//System.out.println(p.toString());
		super.g = new Grammar(nt,t,p,E);
		g.setS2(new NonTerminals("E'"));
		List<Vocabulary> columnas= new LinkedList<Vocabulary>();
		//columnas.add(id);
		columnas.add(mas);
		columnas.add(por);
		//columnas.add(abierto);
		//columnas.add(cerrado);
		columnas.add(dolar);
		columnas.add(E);
		columnas.add(T);
		//columnas.add(F);
		l=columnas;
		ArrayList<Productions> producciones=new ArrayList<Productions>();
		producciones.add(p1);
		//producciones.add(p2);
		producciones.add(p3);
		//producciones.add(p4);
		producciones.add(p5);
		//producciones.add(p6);
		cfg=new ConfiguracionLR(producciones,conjuntoTVT);
	
	}
	protected AnalizadorSintacticoAscendente(int i){
		super(i);
		
	}
	
	
	public boolean analizar(){
		while(entrada.size()>0){
			int accion = cfg.analisisDecision(entrada.get(0), inicio);
			switch (accion) {
			case 0: {
				entrada.remove(0);
			}
				break;
			case 2: {
				cfg.analisisDecision(inicio.get(inicio.size()-1), inicio);
			}break;
			case -2: {
				System.out.println("Cadena reconocida");
				return true;
			}
			
			}
		}
		return false;
	}
	
	
	public static void main(String args[]){
		ArrayList<Vocabulary> l = new ArrayList<Vocabulary>();
		//cccdcdd
		l.add(new Vocabulary("c"));
		l.add(new Vocabulary("c"));
		l.add(new Vocabulary("c"));
		l.add(new Vocabulary("d"));
		l.add(new Vocabulary("c"));
		l.add(new Vocabulary("d"));
		l.add(new Vocabulary("d"));
		//l.add(new Vocabulary("id"));
		//l.add(new Vocabulary(")"));
		l.add(new Vocabulary("$"));
		AnalizadorSintacticoAscendente a = new AnalizadorSintacticoAscendente(l);
		a.generarGramatica();
		try{
		a.analizar();
		}catch(Exception e){
			System.out.println("No se ha reconocido la cadena");
		}
		/*ArrayList<Productions> p =(ArrayList<Productions>) a.g.getP();
		for(Productions pd:p){
			System.out.println(pd.toString());
			LinkedList<Vocabulary> con =(LinkedList<Vocabulary>) pd.getConsecuente();
			System.out.println(con.get(0));
		}*/
			
	}
}
