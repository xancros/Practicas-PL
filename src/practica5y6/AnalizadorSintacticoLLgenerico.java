package practica5y6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.*;
import excepciones.ErrorSintactico;





public class AnalizadorSintacticoLLgenerico extends AnalizadorSintactico{
	
	private class Tabla{
		NonTerminals linea;
		Terminals columna;
		Productions consecuente;
		Tabla(NonTerminals x,Terminals y,Productions xy){
			linea=x;
			columna=y;
			consecuente=xy;
		}
	}
	private List<Tabla> tabla;
	protected AnalizadorSintacticoLLgenerico(ArrayList<Vocabulary> input) {
		super(input);
		rellenarTabla();
		// TODO Auto-generated constructor stub
	}
	
	private void rellenarTabla(){
		tabla = new LinkedList<Tabla>();
		for(Productions p:g.getP()){
			for(Terminals t:directores(p)){
				tabla.add(new Tabla(p.getAntecedente(),t,p));
			}
		}
	}
	private Set<Terminals> siguientes(NonTerminals nt){
		
		Set<Terminals> listaSiguientes = new HashSet<Terminals>();
		if(nt.getVocabulario().equals(g.getS().getVocabulario()))
			listaSiguientes.add(g.getDolar());
		for(Productions p:g.getP()){
			/*
			 * for(NonTerminals ntrs:g.getVn())
		for(Productions prd: g.getP()){
			
			List<Vocabulary> lista = (List<Vocabulary>)prd.getConsecuente();
			if(lista.contains(ntrs)){
				System.out.println(ntrs.getVocabulario()+"  ES CONTENIDO EN: ");
				System.out.println("Produccion:"+prd.toString());
				}
		}
			 */
			List<Vocabulary> v = (List<Vocabulary>)p.getConsecuente();
			if(v.contains(nt)){
				
				List<Vocabulary> listaProd = (List<Vocabulary>)p.getConsecuente();
				if(listaProd.indexOf(nt)==listaProd.size()-1){   //ultimo elemento?
					if(!p.getAntecedente().getVocabulario().equals(nt.getVocabulario()))
						listaSiguientes.addAll(siguientes(p.getAntecedente()));
				}else{
					List<Vocabulary> subLista=listaProd.subList(listaProd.indexOf(nt)+1, listaProd.size());
					if(iniciales(subLista).contains(g.getLambda())){
						listaSiguientes.addAll(iniciales(subLista));
						listaSiguientes.remove(g.getLambda());
						listaSiguientes.addAll(siguientes(p.getAntecedente()));
						
					}else{
						listaSiguientes.addAll(iniciales(subLista));
					}
				}
			}
		}
		return listaSiguientes;
	}
	private Set<Terminals> directores(Productions p){
		Set<Terminals> directores=new HashSet<Terminals>();
		directores.addAll(iniciales(p.getConsecuente()));
		if(directores.contains(g.getLambda())){
			directores.addAll(siguientes(p.getAntecedente()));
			directores.remove(g.getLambda());
		}
		  
		return directores;
	}
	/**
	 * devolverá un booleano indicando si la gramática contienen 
	 * algún símbolo no terminal con producciones 
	 * con recursividad a izquierda directa o indirecta.
	 * @return
	 */
	public boolean esRecursiva(){
		for(Productions p:super.g.getP()){
			if(recursividadDirecta(p)){
				return true;
			}else if (recursividadIndirecta(p)){
					return true;
			}
		}
		
		return false;
	}
	private boolean recursividadDirecta(Productions p){
		
		return (p.getAntecedente().getVocabulario().equals(p.getFirstElement().getVocabulario()));
		
	}
	private boolean recursividadIndirecta(Productions p){
		Vocabulary ultimo=null;
		for(Vocabulary c:p.getConsecuente()){
			ultimo=c;
		}
		return (p.getAntecedente().getVocabulario().equals(ultimo.getVocabulario()));
	}

	/**
	 * 
	 * @param t
	 * @return devuelve directamente un conjunto con el elemento Vt.
	 */
	private Collection<Terminals> iniciales(Terminals t){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		conjunto.add(t);
		return conjunto;
	}
	/**
	 * 
	 * @param nt
	 * @return devuelve la unión de los conjuntos devueltos por todos sus consecuentes.
	 */
	private Collection<Terminals> iniciales(NonTerminals nt){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		for(Productions p : dameProducciones(nt)){
			//System.out.println("Produccion: "+p.toString());
			conjunto.addAll(iniciales(p.getConsecuente()));
		}
		return conjunto;
	}
	/**
	 * 
	 * @param nt
	 * @return Conjunto de las producciones en las que el antecedente es nt
	 */
	private List<Productions> dameProducciones(NonTerminals nt){
		List<Productions> lista = new LinkedList<Productions>();
		for(Productions p : g.getP()){
			if (p.getAntecedente().getVocabulario().equals(nt.getVocabulario())){
				lista.add(p);
			}
		}
		return lista;
	}
	/**
	 * 
	 * @param c
	 * @return llamaremos a los anteriores métodos según el algoritmo comentado en clase.
	 */
	private Collection<Terminals> iniciales(Collection<Vocabulary> c){
		Collection<Terminals> lista = new LinkedList<Terminals>();
		for(Vocabulary v:c){
			if(v instanceof Terminals){
				lista.addAll(iniciales((Terminals)v));
				return lista;
			}else{
				lista = iniciales((NonTerminals)v);
				if(!lista.contains(g.getLambda())){
					return lista;
				}
			}
		}
		lista.add(g.getLambda());
		return lista;
		
	}
	private boolean isLambda(Terminals t){
		return t.getVocabulario().equals("λ");
	}
	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		NonTerminals E = new NonTerminals("E");
		NonTerminals E2 = new NonTerminals("E'");
		NonTerminals T = new NonTerminals("T");
		NonTerminals T2 = new NonTerminals("T'");
		//NonTerminals F = new NonTerminals("F");
		NonTerminals F = new NonTerminals("F");
		
		Terminals mas = new Terminals("+");
		Terminals asterisco = new Terminals("*");
		Terminals id = new Terminals("id");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals n = new Terminals("n");
		Collection<Vocabulary> cp1 = new LinkedList<Vocabulary>();
		
		cp1.add(T);
		cp1.add(E2);
		//cp1.add(T);
		Productions p1 = new Productions("E",cp1);
		
		Collection<Vocabulary> cp2 = new LinkedList<Vocabulary>();
		cp2.add(mas);
		cp2.add(T);
		cp2.add(E2);
		Productions p2 = new Productions("E'",cp2);
		
		Collection<Vocabulary> cp3 = new LinkedList<Vocabulary>();
		
		cp3.add(lambda);
		Productions p3 = new Productions("E'",cp3);
		
Collection<Vocabulary> cp4 = new LinkedList<Vocabulary>();
		
		cp4.add(F);
		cp4.add(T2);
		Productions p4 = new Productions("T",cp4);
		
Collection<Vocabulary> cp5 = new LinkedList<Vocabulary>();
		cp5.add(asterisco);
		cp5.add(F);
		cp5.add(T2);
		Productions p5 = new Productions("T'",cp5);
		
Collection<Vocabulary> cp6 = new LinkedList<Vocabulary>();
		
		cp6.add(lambda);
		
		Productions p6 = new Productions("T'",cp6);
		
Collection<Vocabulary> cp7 = new LinkedList<Vocabulary>();
		
		cp7.add(abierto);
		cp7.add(E);
		cp7.add(cerrado);
		Productions p7 = new Productions("F",cp7);
		
Collection<Vocabulary> cp8 = new LinkedList<Vocabulary>();
		
		cp8.add(id);
		Productions p8 = new Productions("F",cp8);
Collection<Vocabulary> cp9 = new LinkedList<Vocabulary>();
		
		cp9.add(n);
		Productions p9 = new Productions("F",cp9);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(E2);
		nt.add(T);
		nt.add(T2);
		nt.add(F);
		
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(asterisco);
		t.add(id);
		t.add(abierto);
		t.add(cerrado);
		t.add(n);
		Collection<Productions> p = new HashSet<Productions>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
		p.add(p9);
		//System.out.println(p.toString());
		g = new Grammar(nt,t,p,E);
		//System.out.println(g.getS().getVocabulario());
		
	}
	@Override
	protected void derivar() throws ErrorSintactico {
		// TODO Auto-generated method stub
		//INICIO ES LA PILA DE LOS ELEMENTOS
		//ENTRADA ES LA CADENA DE ENTRADA
		Vocabulary in = inicio.pop();
		Vocabulary en = entrada.get(0);
		Collection<Vocabulary> produccionResultante=produccion(in,en);
		if(produccionResultante==null){
			throw new excepciones.ErrorSintactico();
		}
		for(Vocabulary v:produccionResultante)
			inicio.add(v);
		/*
		 * Vocabulary in = inicio.pop();
			Productions p = g.buscarProduccion(entrada.get(0), in);
			if(p==null)
				throw new excepciones.ErrorSintactico();
			for(Vocabulary c : p.getConsecuente()){
				inicio.add(c);
			}
		 */
	}
private Collection<Vocabulary> produccion(Vocabulary p,Vocabulary en){
		
		for(Tabla t:tabla){
			if(t.linea.getVocabulario().equals(p)&&t.columna.getVocabulario().equals(en)){
				return t.consecuente.getConsecuente();
			}
		}
		return null;
	}
	public void mostrarTabla(){
		System.out.print(" ");
		for(Tabla t:tabla){	
			System.out.println(t.columna+" ==> "+t.linea+" ==> "+t.consecuente);
		}
		
	}
	public void mostrarIniciales(NonTerminals nt){
		for(Terminals t:iniciales(nt)){
			System.out.print(t+"->");
		}
	}
	public void mostrarIniciales(Terminals nt){
		for(Terminals t:iniciales(nt)){
			System.out.print(t+"->");
		}
	}
	public void mostrarSeguidores(NonTerminals nt){
		for(Terminals t:siguientes(nt)){
			System.out.print(t+"->");
		}
	}
	public void mostrarDirectores(Productions p){
		for(Terminals t:directores(p))
			System.out.println(t+" -> ");
	}
	public Grammar getGramatica(){
		return super.g;
	}
}
