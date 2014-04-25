package practica8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import excepciones.ErrorSintactico;
import base.*;
public class AnalizadorAscendenteLR extends AnalizadorSintactico {
	protected ConfiguracionLR cfg;
	
	protected Grammar g;
	protected List<Vocabulary> l;
	public AnalizadorAscendenteLR(int i){
		//super();
		
	}
	
	protected Grammar generarGramatica2() {
		// TODO Auto-generated method stub
		NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		
		
		Terminals mas = new Terminals("+");
		Terminals por = new Terminals("*");
		Terminals i = new Terminals("id");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		
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
		cp6.add(i);
		Productions p6 = new Productions("F",cp6);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		nt.add(F);
		
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
		p.add(p4);
		p.add(p5);
		p.add(p6);
		
		//System.out.println(p.toString());
		Grammar g = new Grammar(nt,t,p,E);
		List<Vocabulary> columnas= new LinkedList<Vocabulary>();
		columnas.add(i);
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
		cfg=new ConfiguracionLR(producciones);
		cfg.generarTabla(l);
	return g;
		
		
	}
	protected AnalizadorAscendenteLR (List<Vocabulary> input){
		//super(input);
		
		g=generarGramatica2();
		super.entrada=input;
		inicio=new Stack<Vocabulary>();
		super.inicio.add(g.getDolar());
		super.inicio.add(new Vocabulary("0"));
		
		
	}
	@Override
	protected void derivar() throws ErrorSintactico {
		// TODO Auto-generated method stub
		
	}
	
	@Override
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
	
	public void m(){
		for(Productions p:cfg.producciones){
			
			System.out.println(p.toString());
		}
		System.out.println("-----------------------");
		for(Vocabulary p:l){
			
			System.out.println(p.toString());
		}
		System.out.println("..............................");
		for(Tabla t:cfg.tabla){
			System.out.print(t.fila+"->"+t.columna+"->");
			if(t.op.letra!=null)
				System.out.print(t.op.letra+"");
			System.out.println(t.op.numero);
		}
			
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
		
		AnalizadorAscendenteLR a = new AnalizadorAscendenteLR(l);
		a.analizar();
		/*Stack<String> s = new Stack<String>();
		s.push("G");
		s.push("S");
		System.out.println(s.peek());
		System.out.println(s.get(s.size()-1)+" "+s.get(s.size()-2));*/
		//a.m();
	}
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		
	}
}
