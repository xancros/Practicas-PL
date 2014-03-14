package practica3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.*;

//falta los buffers que controla las lineas que se leen del fichero
//y tambien falta terminar los metodos y tokens
public class Analisis {
	
	private static final String TK_ID = "TK_ID";
	private static final String TK_LLAV_ABR = "TK_LLAV_ABR";
	private static final String TK_LLAV_CER = "TK_LLAV_CER";
	private static final String TK_FIN_SENT = "TK_FIN_SENT";
	private static final String TK_ASIG = "TK_ASIG";
	private static final String TK_MAS = "TK_MAS";
	private static final String TK_MENOR = "TK_MENOR";
	private static final String TK_PAR_ABR = "TK_PAR_ABR";
	private static final String TK_PAR_CER = "TK_PAR_CER";
	private static final String TK_CTE_NUM = "TK_CTE_NUM";
	private static final String TK_PR = "TK_PR";
	private int inicio,delantero,fin;
	private StringBuffer subBuffer1; // actualmente lo usamos hay que usar otra cosa
	private StringBuffer subBuffer2;
	private String buffer;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		Analisis a = new Analisis();
	}
	private static Pattern N;
	private static Pattern L;
	private static Pattern del;
	private static Pattern del1;
	private static Pattern del2;
	private static Pattern palabrasReservadas;
	private static Matcher matchNumber;
	private static Matcher matchLanguage;
	private static Matcher matchDel;
	private static Matcher matchDel1;
	private static Matcher matchDel2;
	private static Matcher matchPalRes;
	private String palabra;
	public Analisis (){
		L = Pattern.compile("[a-zA-Z]+");
		N = Pattern.compile("[0-9]+");
		del = Pattern.compile("\b|\t");
		del1 = Pattern.compile(del.pattern()+"|[(]|"+"|[{]|=|[+]|;|[)]|<|#");
		del2 = Pattern.compile(del.pattern()+N.pattern()+"|"+L.pattern());
		palabrasReservadas = Pattern.compile("for|while|int|char|Integer|Character|float|Float|String|do|switch|case|if|else");
		//subBuffer1 = new StringBuffer("int casa;");
		buffer = new String ("for(int i=0;i<10;i++);");
		delantero = 0;
		inicio = 0;
		fin = buffer.length();
		subBuffer1 = new StringBuffer(buffer.replaceAll("\\s","#"));
		//fin = subBuffer1.length();
		analizador();
	}
	
	
	private boolean noHayCaracteres(){
		return delantero>fin;
	}
	private char leerSiguienteCaracter(){
		//subBuffer1.trimToSize();
		char car =subBuffer1.charAt(delantero);
		
		//char car = buffer.charAt(delantero);
		delantero++;
		return car;
	}
	private int convierteNumero(char c){
		
		return Integer.valueOf(String.valueOf(c));
	}
	private String concatenarCaracter (char c){
		palabra=palabra.concat(String.valueOf(c));
		return palabra;
	}
	private String daLexema(){
		//return subBuffer1.substring(inicio, delantero);
		return palabra;
	}
	private void iniLexema(){
		palabra=new String();
		inicio=delantero;
		
	}
	private boolean diferPRId (String lex){
		//mirar si es palabra reservada o no y devolver el token de lo que es
		matchPalRes=palabrasReservadas.matcher(lex);
		if(matchPalRes.matches()){
			//es una palabra reservada
			this.daToken2(TK_PR, lex);
		}else{
			this.daToken2(TK_ID, lex);
		}
		delantero++;
		return true;
	}
	private void retrocesoPuntero(){
		delantero-=2;
	}
	private String daToken2(String tk, String lex){
		String tok = ("<"+tk+", "+lex+">");
		System.out.println(tok);
		return tok;
		
	}
	private String daToken1(String tk){
		String tok = ("<"+tk+">");
		System.out.println(tok);
		return tok;
	}
	private void analizador(){
		int estado=0;
		int valor=0;
		int digito=0;
		palabra=new String();
		String cadena;
		char caracter = 0;
		while(!noHayCaracteres()){
			switch(estado){
			case 0:{
					iniLexema();
					digito = 0;
					valor = 0;
					caracter = this.leerSiguienteCaracter();
					//Matcher mat = pat.matcher(cadena);
					cadena = String.valueOf(caracter);
					matchLanguage = L.matcher(cadena);
					matchNumber = N.matcher(cadena);
					matchDel = del.matcher(cadena);
					matchDel1 = del1.matcher(cadena);
					matchDel2 = del2.matcher(cadena);
					switch (caracter){
					//case del: estado = 0;break;
					//case 'L': estado = 1;break;
					case '{': estado = 5;break;
					case '}': estado = 6;break;
					case ';': estado= 9;break;
					case '(': estado = 14;break;
					case ')': estado= 15;break;
					case '=' : estado = 7;break;
					case '+' : estado = 8;break;
					case '<' : estado = 10;break;
					//case 'N' : estado = 16;break;
					default:{
						/*
						 * aqui va el c�digo que se implementar� para comprobar si el caracter pertenece a los delimitadores 'del'
						 * a los numeros 'N' o a las letras 'L' con unas sentencias de if-else
						 */
						if (matchDel.matches())
							estado=0;
						else if (matchLanguage.matches())
							estado = 1;
						else if (matchNumber.matches())
							estado = 16;
						
						
					}break;
					}
			}break;
			case 1:{
				concatenarCaracter (caracter);
				caracter= leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				matchLanguage = L.matcher(cadena);
				matchNumber = N.matcher(cadena);
				
				matchDel1 = del1.matcher(cadena);
				
				//sentencias if-else dado que no se puede comprobar con funciones en el switch
				if (matchDel1.matches())
					estado = 2;
				else if (matchNumber.matches())
					estado = 3;
				else if (matchLanguage.matches())
					estado = 1;
				
				/*switch (caracter){
				case del1: estado = 2;break;
				case N: estado = 3;break;
				case L: estado = 1;break;
				
				}*/
				
			}break;
			case 2:{
				retrocesoPuntero();
				diferPRId(daLexema());
				estado = 0;
			}break;
			case 3:{
				//concatenarCaracter (caracter);
				caracter = leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				matchLanguage = L.matcher(cadena);
				matchNumber = N.matcher(cadena);
				
				matchDel1 = del1.matcher(cadena);
				
//sentencias if-else dado que no se puede comprobar con funciones en el switch
				if (matchDel1.matches())
					estado = 4;
				else if (matchNumber.matches())
					estado = 3;
				else if (matchLanguage.matches())
					estado = 3;
				/*switch (caracter){
				case del1: estado = 4;break;
				case N: estado = 3;break;
				case L: estado = 3;break;
				
				}*/
			}break;
			case 4:{
				retrocesoPuntero();
				daToken2 (TK_ID, daLexema());
				estado = 0;
			}break;
			case 5:{
				daToken1 (TK_LLAV_ABR);
				estado = 0;
			}break;
			case 6:{
				daToken1 (TK_LLAV_CER);
				estado =  0;
			}break;
			case 7:{
				caracter = leerSiguienteCaracter();
				//sentencia if
				cadena = String.valueOf(caracter);
				
				matchDel2 = del2.matcher(cadena);
				if (matchDel2.matches())
					estado = 11;
				
				/*switch (caracter){
				case del2: estado=11;break;
				}*/
			}
			case 8:{
				caracter = leerSiguienteCaracter();
				//sentencia if
				
				cadena = String.valueOf(caracter);
				matchDel2 = del2.matcher(cadena);
				if (matchDel2.matches())
					estado = 12;
				/*switch(caracter){
				case del2: estado= 12;
				}*/
			}
			case 9:{
				daToken1 (TK_FIN_SENT);
				estado = 0;
			}break;
			case 10:{
				caracter = leerSiguienteCaracter();
				//sentencia if
				cadena = String.valueOf(caracter);
				matchDel2 = del2.matcher(cadena);
				if (matchDel2.matches())
					estado = 13;
				/*switch(caracter){
				case del2: estado = 13;break;
				}*/
				
			}break;
			case 11:{
				retrocesoPuntero();
				daToken1 (TK_ASIG);
				estado = 0;
			}break;
			case 12:{
				retrocesoPuntero();
				daToken1 (TK_MAS);
				estado = 0;
			}break;
			case 13:{
				retrocesoPuntero();
				daToken1 (TK_MENOR);
				estado = 0;
			}break;
			case 14:{
				daToken1 (TK_PAR_ABR);
				estado =0;
			}break;
			case 15:{
				daToken1 (TK_LLAV_CER);
				estado = 0;
			}break;
			case 16:{
				digito = convierteNumero ( caracter );
				valor = valor * 10 + digito;
				caracter = leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				
				matchNumber = N.matcher(cadena);
				
				matchDel1 = del1.matcher(cadena);
				
				//sentencia if-else
				if (matchNumber.matches())
					estado = 16;
				else if (matchDel1.matches())
					estado = 17;
				/*switch (caracter){
				case 'N': estado = 16;break;
				case del1: estado = 17;break;
				
				}*/
			}break;
			case 17:{
				retrocesoPuntero();
				daToken2 (TK_CTE_NUM, String.valueOf(valor));
				estado = 0;
			}break;
			}
		}
	}
	
}
