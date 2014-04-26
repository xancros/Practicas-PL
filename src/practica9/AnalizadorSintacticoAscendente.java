package practica9;

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
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		
		
		Terminals mas = new Terminals("+");
		Terminals por = new Terminals("*");
		Terminals id = new Terminals("id");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals punto = new Terminals(" ");
		
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
		
		cp1.add(E);
		cp1.add(mas);
		cp1.add(T);
		Productions p1 = new Productions("E",cp1);
		
		Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		cp2.add(T);
		Productions p2 = new Productions("E",cp2);
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
		
		cp3.add(T);
		cp3.add(por);
		cp3.add(F);
		Productions p3 = new Productions("T",cp3);
		
		Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
		cp4.add(F);
		Productions p4 = new Productions("T",cp4);
Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();
		
		cp5.add(abierto);
		cp5.add(E);
		cp5.add(cerrado);
		Productions p5 = new Productions("F",cp5);
		
		Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
		cp6.add(id);
		Productions p6 = new Productions("F",cp6);
		
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
		
		//System.out.println(p.toString());
		super.g = new Grammar(nt,t,p,E);
		g.setS2(new NonTerminals("E'"));
		List<Vocabulary> columnas= new LinkedList<Vocabulary>();
		columnas.add(id);
		columnas.add(mas);
		columnas.add(por);
		columnas.add(abierto);
		columnas.add(cerrado);
		columnas.add(dolar);
		columnas.add(E);
		columnas.add(T);
		columnas.add(F);
		l=columnas;
		ArrayList<Productions> producciones=new ArrayList<Productions>();
		producciones.add(p1);
		producciones.add(p2);
		producciones.add(p3);
		producciones.add(p4);
		producciones.add(p5);
		producciones.add(p6);
	
	}
	protected AnalizadorSintacticoAscendente(int i){
		super(i);
		
	}
	public static void main(String args[]){
		AnalizadorSintacticoAscendente a = new AnalizadorSintacticoAscendente(2);
		a.generarGramatica();
		ArrayList<Productions> p =(ArrayList<Productions>) a.g.getP();
		for(Productions pd:p){
			System.out.println(pd.toString());
			LinkedList<Vocabulary> con =(LinkedList<Vocabulary>) pd.getConsecuente();
			System.out.println(con.get(0));
		}
			
	}
}
