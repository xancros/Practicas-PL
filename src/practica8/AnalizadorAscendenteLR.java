package practica8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import excepciones.ErrorSintactico;
import base.*;
public class AnalizadorAscendenteLR extends AnalizadorSintactico {
	protected List<Tabla> tabla;
	protected List<Productions> producciones;
	protected Grammar g=generarGramatica2();
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
		producciones=new ArrayList<Productions>();
		producciones.add(p1);
		producciones.add(p2);
		producciones.add(p3);
		producciones.add(p4);
		producciones.add(p5);
		producciones.add(p6);
		generarTabla();
	return g;
		
		
	}
	
	@Override
	protected void derivar() throws ErrorSintactico {
		// TODO Auto-generated method stub
		
	}
	private void generarTabla(){
		tabla=new LinkedList<Tabla>();
		//ESTADO 0
		tabla.add(new Tabla(0,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(0,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(0,l.get(6),new Desplazamiento(null,1)));
		tabla.add(new Tabla(0,l.get(7),new Desplazamiento(null,2)));
		tabla.add(new Tabla(0,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 1
		tabla.add(new Tabla(1,l.get(1),new Desplazamiento('D',6)));
		tabla.add(new Tabla(1,l.get(5),new Desplazamiento(null,-1)));
		//ESTADO 2
		tabla.add(new Tabla(2,l.get(1),new Reduccion('R',2)));
		tabla.add(new Tabla(2,l.get(2),new Desplazamiento('D',7)));
		tabla.add(new Tabla(2,l.get(4),new Reduccion('R',2)));
		tabla.add(new Tabla(2,l.get(5),new Reduccion('R',2)));
		//ESTADO 3
		tabla.add(new Tabla(3,l.get(1),new Reduccion('R',5)));
		tabla.add(new Tabla(3,l.get(2),new Reduccion('R',5)));
		tabla.add(new Tabla(3,l.get(4),new Reduccion('R',5)));
		tabla.add(new Tabla(3,l.get(5),new Desplazamiento(null,4)));
		//ESTADO 4
		tabla.add(new Tabla(3,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(3,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(3,l.get(6),new Desplazamiento(null,8)));
		tabla.add(new Tabla(3,l.get(7),new Desplazamiento(null,2)));
		tabla.add(new Tabla(3,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 5
		tabla.add(new Tabla(5,l.get(1),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(2),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(4),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(5),new Reduccion('R',6)));
		//ESTADO 6
		tabla.add(new Tabla(6,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(6,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(6,l.get(7),new Desplazamiento(null,9)));
		tabla.add(new Tabla(6,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 7
		tabla.add(new Tabla(7,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(7,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(7,l.get(8),new Desplazamiento(null,10)));
		//ESTADO 8
		tabla.add(new Tabla(8,l.get(1),new Desplazamiento('D',6)));
		tabla.add(new Tabla(8,l.get(4),new Desplazamiento('D',11)));
		//ESTADO 9
		tabla.add(new Tabla(9,l.get(1),new Reduccion('R',1)));
		tabla.add(new Tabla(9,l.get(2),new Desplazamiento('D',7)));
		tabla.add(new Tabla(9,l.get(4),new Reduccion('R',1)));
		tabla.add(new Tabla(9,l.get(5),new Reduccion('R',1)));
		//ESTADO 10
		tabla.add(new Tabla(10,l.get(1),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(2),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(4),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(5),new Reduccion('R',3)));
		//ESTADO 11
		tabla.add(new Tabla(11,l.get(1),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(2),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(4),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(5),new Reduccion('R',5)));
		
		
		
		
	}
	@Override
	public boolean analizar(){
		
		
		return false;
	}
	public void m(){
		for(Productions p:producciones){
			
			System.out.println(p.toString());
		}
		System.out.println("-----------------------");
		for(Vocabulary p:l){
			
			System.out.println(p.toString());
		}
		System.out.println("..............................");
		for(Tabla t:tabla){
			System.out.print(t.fila+"->"+t.columna+"->");
			if(t.op.letra!=null)
				System.out.print(t.op.letra+"");
			System.out.println(t.op.numero);
		}
			
	}
	public static void main(String args[]){
		AnalizadorAscendenteLR a = new AnalizadorAscendenteLR(2);
		a.m();
	}
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		
	}
}
