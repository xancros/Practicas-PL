package practica10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.*;

//falta los buffers que controla las lineas que se leen del fichero
//y tambien falta terminar los metodos y tokens
public class Analisis {
	
	/*private static final String TK_ID = "TK_ID";
	private static final String TK_LLAV_ABR = "TK_LLAV_ABR";
	private static final String TK_LLAV_CER = "TK_LLAV_CER";
	private static final String TK_FIN_SENT = "TK_FIN_SENT";
	private static final String TK_ASIG = "TK_ASIG";
	private static final String TK_MAS = "TK_MAS";
	private static final String TK_MENOR = "TK_MENOR";
	private static final String TK_PAR_ABR = "TK_PAR_ABR";
	private static final String TK_PAR_CER = "TK_PAR_CER";
	private static final String TK_CTE_NUM = "TK_CTE_NUM";
	private static final String TK_PR = "TK_PR";*/
	private static final String NOTERMINAL = "noterminal";
	private static final String flecha = "->";
	private static final String terminal = "terminal";
	private static final String salto = "cr";
	
	private int inicio,delantero,fin;
	private StringBuffer subBuffer1; // actualmente lo usamos hay que usar otra cosa
	private StringBuffer subBuffer2;
	private String buffer;
	/**
	 * @param args
	 */
	
	private static Pattern N;
	private static Pattern L;
	private static Pattern del;
	private static Pattern del1;
	private static Pattern del2;
	private static Pattern comentario;
	private static Pattern palabrasReservadas;
	private static Matcher matchL;
	private static Matcher matchLanguage;
	private static Matcher matchDel;
	private static Matcher matchDel1;
	private static Matcher matchDel2;
	private static Matcher matchPalRes;
	private static Matcher matchComentario;
	private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
	private String palabra;
	private String val1=new String();
	boolean memoria;
	char c;
	private Collection<String> tokencitos;
	String ruta="./src/practica10/producciones.txt";
	protected class Token{
		protected String lexema;
		protected String token;
		Token(String lex,String tk){
			lexema=lex;
			token=tk;
		}
		
		public String getLexema() {
			return lexema;
		}

		public void setLexema(String lexema) {
			this.lexema = lexema;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String toString(){
			String tok = ("<"+token+", "+lexema+">");
			return tok;
		}
	}
	
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	protected List<Token>listaTokens;
	public Analisis (){
		listaTokens=new LinkedList<Token>();
		tokencitos=new LinkedList<String>();
	//	L = Pattern.compile("[a-zA-Z]+");
		N = Pattern.compile("[a-z]+|[-]|[>]|[<]");
		del = Pattern.compile("[(]|[+]|[;]|[)]|[*]");
		//del1 = Pattern.compile(del.pattern()+"|[(]|"+"|[{]|=|[+]|;|[)]|<|#");
		//del2 = Pattern.compile(del.pattern()+"|"+N.pattern()+"|"+L.pattern());
		//palabrasReservadas = Pattern.compile("for|while|int|char|Integer|Character|float|Float|String|do|switch|case|if|else");
		//comentario = Pattern.compile("[//]|[/*]|[*/]");
		//subBuffer1 = new StringBuffer("int casa;");
		//"for(int i=0;i<10;i++);"
		//buffer = new String ("do{id=id+num;}while(id<num);");
		delantero = 0;
		inicio = 0;
		
		//subBuffer1 = new StringBuffer(buffer.replaceAll("\\s","#"));
		//fin = subBuffer1.length();
		
	}
	public String getRuta() {
		return ruta;
	}
	public void preparacion(){
		try{
			//ruta="E:\\GitHub\\PracticasEDA\\Practicas-PL\\src\\p7\\movimientos.txt";
			archivo = new File(ruta);
			fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         cargarFichero();
	         subBuffer1 = new StringBuffer(buffer);
	         fin=subBuffer1.length();

		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	private void cargarFichero(){
		String Caracter;
	     String valor="";
	     try{            
	            while ((Caracter=br.readLine())!=null){
	            	if(!Caracter.startsWith("//")){
	            		subBuffer1 = new StringBuffer(Caracter.replaceAll("\\s","#"));
	            		valor=valor+subBuffer1;  
	            	}
	            }            
	            fin=valor.length();
	            buffer=valor;
 
	     }
	     catch(IOException e){
	         
	     
	     }
	    
	}
	private boolean noHayCaracteres() throws IOException{
		
		
		return fin<=0;
	}
	private char leerSiguienteCaracter(){
		//subBuffer1.trimToSize();
		char car =subBuffer1.charAt(delantero);
		
		//char car = buffer.charAt(delantero);
		delantero++;
		fin--;
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
	/*private boolean diferPRId (String lex){
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
	}*/
	private void retrocesoPuntero(){
		delantero--;
		fin++;
	}
	private String daToken2(String tk, String lex){
		String tok = ("<"+tk+", "+lex+">");
		this.tokencitos.add(tok);
		this.listaTokens.add(new Token(lex,tk));
		//System.out.println(tok);
		return tok;
		
	}
	private String daToken1(String tk){
		String tok = ("<"+tk+">");
		this.tokencitos.add(tok);
		this.listaTokens.add(new Token(String.valueOf(c),tk));
		//System.out.println(tok);
		return tok;
	}
	public void analizador() throws IOException{
		int estado=0;
		int valor=0;
		int digito=0;
		
		boolean notacion=false;
		palabra=new String();
		String cadena;
		char caracter = 0;
		while(!noHayCaracteres()){
			switch(estado){
			case 0:{
					
					iniLexema();
					digito = 0;
					valor = 0;
					val1=" ";
					caracter = this.leerSiguienteCaracter();
					//Matcher mat = pat.matcher(cadena);
					cadena = String.valueOf(caracter);
					if(Character.isUpperCase(caracter)){
						estado=1;
						concatenarCaracter(caracter);
						break;
					}
					if(Character.isLowerCase(caracter)){
						estado=2;
						concatenarCaracter(caracter);
						break;
					}
					if(caracter=='-'){
						estado=3;
						concatenarCaracter(caracter);
						break;
					}
					matchDel1 = del.matcher(cadena);
					if(matchDel1.matches()){
						estado=0;
						concatenarCaracter(caracter);
						this.daToken2(terminal, this.palabra);
						break;
					}
					
			}break;
			case 1:{
				caracter=this.leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				matchDel1 = del.matcher(cadena);
				matchL= N.matcher(cadena);
				if(matchDel1.matches()||matchL.matches()){
					this.daToken2(NOTERMINAL, palabra);
					estado=0;
					this.retrocesoPuntero();
					break;
				}
				this.concatenarCaracter(caracter);
				estado=1;
			}break;
			case 2:{
				caracter=this.leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				matchDel1 = del.matcher(cadena);
				if(Character.isLowerCase(caracter)||matchDel1.matches()){
					this.concatenarCaracter(caracter);
					estado=2;
					
					break;
				}
				this.daToken2(terminal, palabra);
				this.retrocesoPuntero();
				estado=0;
			}break;
			case 3:{
				caracter=this.leerSiguienteCaracter();
				cadena = String.valueOf(caracter);
				if(caracter=='>'){
					this.daToken2(flecha, palabra);
					estado=0;
					break;
				}
				retrocesoPuntero();
				estado=0;
			}break;
			case 4:{
				
			}break;
			
			case 5:{
				
			}break;
			
			}
		}
		//System.out.println("NO HAY MAS CARACTERES");
	}
	
	public void imprimir(){
		for(Token t:listaTokens)
			System.out.print(t.token+",");
	}
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		Analisis a = new Analisis();
		a.preparacion();try {
			a.analizador();
			a.imprimir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
