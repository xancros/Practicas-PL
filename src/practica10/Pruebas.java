package practica10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import base.*;

public class Pruebas {
	
	public static void main(String[] args){
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
		Productions p1 = new Productions(PRODUCCIONES.getVocabulario(),cp1);
		
		Collection<Vocabulary> cp2 = new ArrayList<Vocabulary>();
		cp2.add(PRODUCCION);
		Productions p2 = new Productions("PRODUCCIONES",cp2);
		Collection<Vocabulary> cp3 = new ArrayList<Vocabulary>();
		
		cp3.add(ANTECEDENTE);
		cp3.add(flecha);
		cp3.add(CONSECUENTE);
		Productions p3 = new Productions("PRODUCCION",cp3);
		
		Collection<Vocabulary> cp4 = new ArrayList<Vocabulary>();
		cp4.add(nt);
		Productions p4 = new Productions("ANTECEDENTE",cp4);
Collection<Vocabulary> cp5 = new ArrayList<Vocabulary>();
		
		
		cp5.add(nt);
		Productions p5 = new Productions("SIMBOLO",cp5);
		
		Collection<Vocabulary> cp6 = new ArrayList<Vocabulary>();
		cp6.add(t);
		Productions p6 = new Productions("SIMBOLO",cp6);
Collection<Vocabulary> cp7 = new ArrayList<Vocabulary>();
		
		cp7.add(SIMBOLO);
		cp7.add(CONSECUENTE);
		
		Productions p7 = new Productions("CONSECUENTE",cp7);
		Collection<Vocabulary> cp8 = new ArrayList<Vocabulary>();
		cp8.add(SIMBOLO);
		Productions p8 = new Productions("CONSECUENTE",cp8);
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
		
		Grammar g = new Grammar(noterminales,terminales,p,PRODUCCIONES);
		Analisis lexico = new Analisis();
		lexico.preparacion();try {
			lexico.analizador();
			lexico.imprimir();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Analisis.Token> listaTokens = lexico.listaTokens;
		ArrayList<Vocabulary> cadena = new ArrayList<Vocabulary>();
		System.out.println();
		for(Analisis.Token tok:listaTokens){
			System.out.println(tok.lexema);
			if(Character.isLowerCase(tok.lexema.charAt(0)) && !tok.lexema.equals("cr")){
				//terminal.v = t.lexema
				Object z = new Terminals(tok.lexema);
				Terminals vt = new Terminals(t.getVocabulario());
				vt.s=z;
				cadena.add(vt);
			}
			else if(Character.isUpperCase(tok.lexema.charAt(0))){
				//noterminal.v=t.lexema
				Object z= new NonTerminals(tok.lexema);
				NonTerminals vn = new NonTerminals(nt.getVocabulario());
				vn.s=z;
				cadena.add(vn);
			}
			else if (tok.lexema.equals("cr")){
				cadena.add(cr);
			}else if (tok.lexema.equals("->")){
				cadena.add(flecha);
			}
			else{
				Object z = new Terminals(tok.lexema);
				Terminals vt = new Terminals(t.getVocabulario());
				vt.s=z;
				cadena.add(vt);
			}
		}
		cadena.add(g.getDolar());
		ArrayList<Vocabulary> conjuntoTVT = new ArrayList<Vocabulary>();
		for(Terminals vt: g.getVt())
			conjuntoTVT.add(vt);
		for(NonTerminals nvt:g.getVn())
			conjuntoTVT.add(nvt);
		conjuntoTVT.add(g.getDolar());
		System.out.println(conjuntoTVT.indexOf(g.getDolar()));
		System.out.println("PRUEBA ANALIZADOR SINTACTICO ASCENDENTE");
		AnalizadorSintacticoAscendente sintactico = new AnalizadorSintacticoAscendente(cadena);
		//ConfiguracionLR cfg = new ConfiguracionLR(g,li,vnt);
		sintactico.generarGramatica();
		sintactico.analizar();
		System.out.println("*************************************************");
		System.out.println("Traducción de la cadena introducida:");
		TraductorSemantico semantico = new TraductorSemantico();
		
		semantico.imprimirListaProducciones(sintactico.listaDeProducciones);
		//sintactico.listaDeProducciones
	}
	
}
