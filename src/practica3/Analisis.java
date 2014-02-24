package practica3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import base.*;

public class Analisis {
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

	}*/
	private static Set<Integer> N;
	private static Set<Character> L;
	private static Set<Character> del;
	private static Set<Character> del1;
	private static Set<Character> del2;
	public Analisis (){
		
	}
	private boolean hayCaracteres(){
		return false;
	}
	private void analizador(){
		int estado=0;
		int valor=0;
		int digito=0;
		char caracter;
		while(hayCaracteres()){
			switch(estado){
			case 0:{
					iniLexema();
					digito = 0;
					valor = 0;
					caracter = leerSiguienteCaracter();
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
						 * aqui va el código que se implementará para comprobar si el caracter pertenece a los delimitadores 'del'
						 * a los numeros 'N' o a las letras 'L' con unas sentencias de if-else
						 */
					}break;
					}
			}break;
			case 1:{
				concatenarCaracter (caracter)
				caracter= leerSiguienteCaracter();
				//sentencias if-else dado que no se puede comprobar con funciones en el switch
				
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
				concatenarCaracter (caracter);
				caracter = leerSiguienteCaracter();
//sentencias if-else dado que no se puede comprobar con funciones en el switch
				
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
				
				/*switch (caracter){
				case del2: estado=11;break;
				}*/
			}
			case 8:{
				caracter = leerSiguienteCaracter();
				//sentencia if
				
				/*switch(caracter){
				case del2: estado= 12;
				}*/
			}
			case 9:{
				daToken1 (TK_FIN_SENT);
				estado =0;
			}break;
			case 10:{
				caracter = leerSiguienteCaracter();
				//sentencia if
				
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
				//sentencia if-else
				
				/*switch (caracter){
				case 'N': estado = 16;break;
				case del1: estado = 17;break;
				
				}*/
			}break;
			case 17:{
				retrocesoPuntero();
				daToken2 (TK_CTE_NUM, valor);
				estado = 0;
			}break;
			}
		}
	}
	private void leerSiguenteCaracter(){
		
	}
	private int convierteNumero(char c){
		
		return -1;
	}
	private String concatenarCaracter (char c){
		
		return null;
	}
	private void daLexema(){
		
	}
	private void iniLexema(){
		
	}
	private void diferPRId (char c){
		
	}
	private void retrocesoPuntero(){
		
	}
	private void daToken2(){
		
	}
	private void daToken1(){
		
	}
}
