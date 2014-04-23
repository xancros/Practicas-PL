package P3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/*hacer ejemplo expuesto en las transparencias 49-53*/
public class AnalizadorLexico {
	static int proximoToken=-1;
	public static ArrayList<Token> listaDeTokens = new ArrayList<Token>();
	public static ArrayList<String> tokens(String ruta){
		
		
		ArrayList<String> lista = new ArrayList<String>();
		File texto = new File(ruta);
		BufferedReader entrada;
		String lineaLeida;
		try {
			entrada = new BufferedReader(new FileReader(texto));
			while(entrada.ready()){
				lineaLeida = entrada.readLine();
				lista.add(lineaLeida);
			}
		
		
		} catch (FileNotFoundException e) {
			System.out.println("Fallo en la apertura del fichero.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("la ruta es una cadena normal");
		}
		return lista;
	}
	
	
	public static void main(String[] args) {
		
		//AnalizadorLexico analizador = new AnalizadorLexico();
		//imprimirListaTokens(analizador.obtenerLsitaDeTokens("./src/movimientos.txt"));
		
	}

	public ArrayList<Token> obtenerListaDeTokens(String ruta){
		ArrayList<String> listaTokens = tokens(ruta);//obteiene una lista de strings, cada string es una linea del texto
		imprimirLista(listaTokens);//lista con las lineas del fichero
		//ArrayList<Token> listaFinal = new ArrayList<Token>();
		
		Iterator<String> it = listaTokens.iterator();
		while(it.hasNext()){//iterar linea a linea la lineas del fichero
			String cadena = it.next();
			String bf = "";
			
			int estado = 0;//estado inicial
			char caracter = 0;
			boolean salir=false;
			while(salir==false){//modo de salir del bucle=estado5
				switch(estado){
					case 0:{//primer estado, empezar por una letra
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							salir = true;;
							break;
						}
						if(esLetra(caracter)){//caso de que empieze por una letra
							bf+=cadena.charAt(0);
							estado = 1;
							cadena = cadena.substring(1);
							break;
						}
						if(esDel(caracter)){//caso de que sea un espacio en blanco o un tabulador
							cadena = cadena.substring(1);
							estado = 0;
							bf = "";
							break;
						}
						if(esDel1(caracter)){//si es = + ; < ) }
							Token t = new Token();
							if(esOp(caracter)){
								t.componenteLexico="OP";
							}else{
								t.componenteLexico="DEL";
							}
							t.lexema=""+caracter;
							listaDeTokens.add(t);
							cadena = cadena.substring(1);
							estado = 0;
							break;
						}
						if(esDel2(caracter)){//si es ( { ;
							Token t = new Token();
							t.componenteLexico="DEL";
							t.lexema=""+caracter;
							listaDeTokens.add(t);
							cadena = cadena.substring(1);
							estado = 0;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 5;
							break;
						}
						break;
						
					}
					case 1:{//estado en el que pueden venir letras o numero, si son solo letras se comprobara con palabras reservadas
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							estado = 5;
							break;
						}
						if(esLetra(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 1;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esDel1(caracter) || esDel2(caracter)){//si es = + ; < ) }	( { ;
							bf+=caracter;
							estado = 2;
							break;
						}
						if(esDel(caracter)){//si es un espacio o un tabulador
							bf+=caracter;
							estado = 2;
							break;
						}
						break;
					}
					case 2:{//comprobar si lo el buffer contiene una palabara reservada o es un identificador
						String palabraAnterior = bf.substring(0, bf.length()-1);//sin coger el delimitador encontrado
						//char ultimoCaracter = bf.charAt(bf.length()-1);
						if(esPalabraReservada(palabraAnterior)){
							Token t = new Token();
							t.componenteLexico="PR";
							t.lexema=""+palabraAnterior;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							
						}else{
							Token t = new Token();
							t.componenteLexico="ID";
							t.lexema=""+palabraAnterior;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							
						}
						break;
					}
					case 3:{//lectura de numero y letras, para encontrar identificadores
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							estado = 5;
							break;
						}
						if(esDel(caracter)){// si es \t o espacio
							estado = 4;
							break;
						}
						if(esLetra(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esDel1(caracter) || esDel2(caracter)){//si es = + ; < ) }	( { ;
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 4;
							break;
						}
					}
					case 4:{//guardar el identificador leido letra a letra en el buffer
						if(esDel(caracter) || esDel1(caracter) || esDel2(caracter)){//si es \t espacio  = + ; < ) }	( { ;
							Token t = new Token();
							t.componenteLexico="ID";
							t.lexema=""+bf;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							break;
						}
					}
					case 5:{
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							int numero = Integer.parseInt(bf);
							Token t = new Token();
							t.componenteLexico="NUM";
							t.lexema=""+numero;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							salir = true;;
							break;
						}
						if(esNumero(caracter)){//si es un numero, concatenar y seguir viendo si hay mas numeros
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 5;
							break;
						}
						if(esDel(caracter) || esDel1(caracter) || esDel2(caracter)){//si viene algun caracter delimitador u operacional
							int numero = Integer.parseInt(bf);
							Token t = new Token();
							t.componenteLexico="NUM";
							t.lexema=""+numero;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							break;
						}
					}
				}
				
			}
		}
		return listaDeTokens;
	}
	
	
	public ArrayList<Token> obtenerListaDeTokensCadena(String cadena){
		listaDeTokens= new ArrayList<Token>();
		//ArrayList<String> listaTokens = tokens(ruta);//obteiene una lista de strings, cada string es una linea del texto
		//imprimirLista(listaTokens);//lista con las lineas del fichero
		ArrayList<Token> listaFinal = new ArrayList<Token>();
		
		//Iterator<String> it = listaTokens.iterator();
		//while(it.hasNext()){//iterar linea a linea la lineas del fichero
			//String cadena = it.next();//String cadena = it.next();
			String bf = "";
			
			int estado = 0;//estado inicial
			char caracter = 0;
			boolean salir=false;
			while(salir==false){//modo de salir del bucle=estado5
				switch(estado){
					case 0:{//primer estado, empezar por una letra
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							salir = true;;
							break;
						}
						if(esLetra(caracter)){//caso de que empieze por una letra
							bf+=cadena.charAt(0);
							estado = 1;
							cadena = cadena.substring(1);
							break;
						}
						if(esDel(caracter)){//caso de que sea un espacio en blanco o un tabulador
							cadena = cadena.substring(1);
							estado = 0;
							bf = "";
							break;
						}
						if(esDel1(caracter)){//si es = + ; < ) }
							Token t = new Token();
							if(esOp(caracter)){
								t.componenteLexico="OP";
							}else{
								t.componenteLexico="DEL";
							}
							t.lexema=""+caracter;
							listaDeTokens.add(t);
							cadena = cadena.substring(1);
							estado = 0;
							break;
						}
						if(esDel2(caracter)){//si es ( { ;
							Token t = new Token();
							t.componenteLexico="DEL";
							t.lexema=""+caracter;
							listaDeTokens.add(t);
							cadena = cadena.substring(1);
							estado = 0;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 5;
							break;
						}
						break;
						
					}
					case 1:{//estado en el que pueden venir letras o numero, si son solo letras se comprobara con palabras reservadas
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							estado = 5;
							break;
						}
						if(esLetra(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 1;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esDel1(caracter) || esDel2(caracter)){//si es = + ; < ) }	( { ;
							bf+=caracter;
							estado = 2;
							break;
						}
						if(esDel(caracter)){//si es un espacio o un tabulador
							bf+=caracter;
							estado = 2;
							break;
						}
						break;
					}
					case 2:{//comprobar si lo el buffer contiene una palabara reservada o es un identificador
						String palabraAnterior = bf.substring(0, bf.length()-1);//sin coger el delimitador encontrado
						//char ultimoCaracter = bf.charAt(bf.length()-1);
						if(esPalabraReservada(palabraAnterior)){
							Token t = new Token();
							t.componenteLexico="PR";
							t.lexema=""+palabraAnterior;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							
						}else{
							Token t = new Token();
							t.componenteLexico="ID";
							t.lexema=""+palabraAnterior;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							
						}
						break;
					}
					case 3:{//lectura de numero y letras, para encontrar identificadores
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							estado = 5;
							break;
						}
						if(esDel(caracter)){// si es \t o espacio
							estado = 4;
							break;
						}
						if(esLetra(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esNumero(caracter)){
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 3;
							break;
						}
						if(esDel1(caracter) || esDel2(caracter)){//si es = + ; < ) }	( { ;
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 4;
							break;
						}
					}
					case 4:{//guardar el identificador leido letra a letra en el buffer
						if(esDel(caracter) || esDel1(caracter) || esDel2(caracter)){//si es \t espacio  = + ; < ) }	( { ;
							Token t = new Token();
							t.componenteLexico="ID";
							t.lexema=""+bf;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							break;
						}
					}
					case 5:{
						try{
							caracter = cadena.charAt(0);
						}catch(Exception e){
							int numero = Integer.parseInt(bf);
							Token t = new Token();
							t.componenteLexico="NUM";
							t.lexema=""+numero;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							salir = true;;
							break;
						}
						if(esNumero(caracter)){//si es un numero, concatenar y seguir viendo si hay mas numeros
							bf+=caracter;
							cadena = cadena.substring(1);
							estado = 5;
							break;
						}
						if(esDel(caracter) || esDel1(caracter) || esDel2(caracter)){//si viene algun caracter delimitador u operacional
							int numero = Integer.parseInt(bf);
							Token t = new Token();
							t.componenteLexico="NUM";
							t.lexema=""+numero;
							listaDeTokens.add(t);
							bf="";
							estado = 0;
							break;
						}
					}
				}
				
			
		}
		return listaDeTokens;
	}
	
	
	public void imprimirListaTokens(ArrayList<Token> listaFinal) {
		
		Iterator<Token> it = listaFinal.iterator();
		
		while(it.hasNext()){
			Token t = it.next();
			System.out.println("<"+t.lexema+","+t.componenteLexico+">");
		}
		
	}


	private static void imprimirLista(ArrayList<String> listaTokens) {
		System.out.println("La lista contiene:");
		int n = listaTokens.size();
		for(int i=0; i<n;i++){
			System.out.println(listaTokens.get(i));
		}
		
	}
	private static boolean esNumero(char c){
		if (Character.isDigit(c)){
			return true;
		}return false;
	}
	
	private static boolean esLetra(char c){
		if((c>='a'&& c<='z')||(c>='A' && c<='Z')){
			return true;
		}else{
			return false;
		}
	}
	private static boolean esDel(char c){
		if(c == ' ' || c == '	'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean esDel1(char c){
		if(c=='{' || c=='=' || c=='+' || c==';' || c=='(' | c=='<'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean esDel2(char c){
		if(c==')' || c=='}'){
			return true;
		}else{
			return false;
		}
	}
	private static boolean esOp(char c){
		if(c == '=' || c == '+' || c == '<'){
			return true;
		}
		else
			return false;
	}
	private static boolean esPalabraReservada(String s){
		if(s.equals("do") || s.endsWith("while")){
			return true;
		}else{
			return false;
		}
	}


	public Token tokenSiguiente() {
		proximoToken++;//aumentar el valor statico para acceder al sigueinte la proxima vez
		Token t = listaDeTokens.get(proximoToken);//retornar el proximo token
		
		return t;
	}
	
	public Token tokenActual() {
		Token t = listaDeTokens.get(proximoToken);//retornar el proximo token
		return t;
	}

}
