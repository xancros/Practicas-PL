package practica10;

import java.util.ArrayList;
import java.util.List;

import P3.Token;
import base.NonTerminals;
import base.Terminals;
import base.Productions;
import base.Vocabulary;

public class TraductorSemantico {
	Terminals cr = new Terminals("cr");
	Terminals flecha = new Terminals("->");
	Terminals noterminal = new Terminals("noterminal");
	Terminals terminal = new Terminals("terminal");
	Analisis lexico = new Analisis();
	
	/*
	PRODUCCIONES1 ::= PRODUCCION cr PRODUCCIONES
	{PRODUCCIONES1.v=new ArrayList<Produccion>();
	PRODUCCIONES1.v.add(PRODUCCION.v);
	PRODUCCIONES1.v.addAll(PRODUCCIONES.v)}
	*/
	public List<Productions> producciones1(){
		List<Productions> lista = new ArrayList<Productions>();
		try{
			lexico.siguienteToken();
			List<Productions> listaprod = producciones();
			lista.addAll(listaprod);
			match("cr");
			List<Productions> lp = producciones1();
			lista.addAll(lp);
			return lista;
		}catch(Exception e){
			return lista;
		}
	}
	/*
	PRODUCCIONES ::= PRODUCCION
	{PRODUCCIONES.v=new ArrayList<Produccion>();
	PRODUCCIONES.v.add(PRODUCCION.v)}
	*/
	private ArrayList<Productions> producciones(){
		Productions pv = produccion();
		ArrayList<Productions> listapv = new ArrayList<Productions>();
		listapv.add(pv);
		return listapv;
	}
	/*
	PRODUCCION	::= ANTECEDENTE -> CONSECUENTE
	{PRODUCCION.v=new Produccion(ANTECEDENTE.v,
	CONSECUENTE.v)}
	 */
	private Productions produccion(){
		Productions pv = new Productions();
		pv.setAntecedente(antecedente());
		match("->");
		pv.setConsecuente(consecuente1());
		return pv;
	}
	/*
	 ANTECEDENTE ::= notermial {ANTECEDENTE.v=new Vn(noterminal.v)}
	 */
	private NonTerminals antecedente(){
		NonTerminals ant = noTerminal();
		return ant;
	}
	/*
	 CONSECUENTE ::= SIMBOLO
	{CONSECUENTE.v=new ArrayList<V>();
	CONSECUENTE.v.add(SIMBOLO.v)}
	*/
	private ArrayList<Vocabulary> consecuente(){
		ArrayList<Vocabulary> lista = new ArrayList<Vocabulary>();
		try{
			Analisis.Token t = lexico.siguienteToken();
			if(t.lexema.equals("cr"))
				return lista;
			else{
				ArrayList<Vocabulary> lc2 = new ArrayList<Vocabulary>();
				NonTerminals vn = simboloN();
				if (vn != null)
					lista.add(vn);
				else{
					Terminals vt = simboloT();
					lista.add(vt);
				}
				lc2.addAll(consecuente());
				lista.addAll(lc2);
				return lista;
			}
			
		}catch(Exception e){
			return lista;
		}
	}
	/*
	 SIMBOLO::= noterminal {SIMBOLO.v=new Vn(noterminal.v)}
	 */
	private NonTerminals simboloN(){
		NonTerminals vn = noTerminal();
		return vn;
	}
	private NonTerminals noTerminal(){
		NonTerminals vn = new NonTerminals(match("noterminal"));
		return vn;
	}

	/*
	SIMBOLO::= terminal {SIMBOLO.v=new Vt(terminal.v)}
	 */
	private Terminals simboloT(){
		Terminals vt = new Terminals(match("terminal"));
		return vt;
	}
	/*
	 CONSECUENTE1 ::= SIMBOLO CONSECUENTE
	{CONSECUENTE1.v=new ArrayList<V>();
	CONSECUENTE1.v.add(SIMBOLO.v);
	CONSECUENTE1.v.addAll(CONSECUENTE.v) }
	 */
	private ArrayList<Vocabulary> consecuente1(){
		Vocabulary v = simboloN();
		if(v==null)
			v = simboloT();
		ArrayList<Vocabulary> lista = new ArrayList<Vocabulary>();
		lista.add(v);
		ArrayList<Vocabulary> lista2 = consecuente();
		lista.addAll(lista2);
		return lista;
		
	}
	private String match(String s) {
		if(s.equals("terminal")){
			return lexico.dameSiguiente().lexema;
		}
		if(s.equals("noterminal")){
			return lexico.dameSiguiente().lexema;
		}
		if(s.equals("cr")){
			return lexico.dameSiguiente().lexema;
		}
		if(s.equals("->")){
			return lexico.dameSiguiente().lexema;
		}
		return null;
		
	}
	public ArrayList<Productions> traduce() {
		//ArrayList<Token> listaTokens = lexico.analizaLexico("./src/producciones.txt");
		ArrayList<Productions> lista = (ArrayList<Productions>) producciones1();
		return lista;
		
	}
	public void imprimirListaTokens(ArrayList<Analisis.Token> listaFinal){
		for(Analisis.Token tk:listaFinal)
			System.out.println("<"+tk.lexema+","+tk.token+">");
	}
	public void imprimirListaProducciones(ArrayList<Productions> lista){
		for(Productions p:lista){
			System.out.println(p.toString());
		}
	}
	/*CONSECUENTE ::= SIMBOLO
	{CONSECUENTE.v=new ArrayList<V>();
	CONSECUENTE.v.add(SIMBOLO.v)}
		*/
	
}
