package p7;

import P1.G;
import P1.P;
import P1.VN;
import P1.VT;
import P3.AnalizadorLexico;
import P3.Token;


public class AnalizadorRercursivoPaseo {

	final static VT lambda = new VT("lambda");
	
	//G=(Vn,Vt,P,S)
	static G gramatica = new G();
	
	//Vn=(PASEO,PASO,EXP)
	static VN PASEO = new VN("paseo");
	static VN PASO = new VN("paso");
	static VN EXP = new VN("exp");
	
	//Vt=(casa,giro,avanza,pinta,id,n,=)
	static VT casa = new VT("casa");
	static VT giro = new VT("giro");
	static VT avanza = new VT("avanza");
	static VT pinta = new VT("pinta");
	static VT id = new VT("id");
	static VT n = new VT("n");
	static VT igual = new VT("=");
	
	static AnalizadorLexico analizadorLexico = new AnalizadorLexico();
	
	AnalizadorRercursivoPaseo(){
		gramatica.vn.add(PASEO);
		gramatica.vn.add(PASO);
		gramatica.vn.add(EXP);
		
		
		
		gramatica.vt.add(casa);
		gramatica.vt.add(giro);
		gramatica.vt.add(avanza);
		gramatica.vt.add(pinta);
		gramatica.vt.add(id);
		gramatica.vt.add(n);
		gramatica.vt.add(igual);
		
		
		//Estado inicial S=PASEO
		gramatica.simboloInicial=PASEO;
		
		
		//Producciones
		
		//PASEO => PASO PASEO
		P p1 = new P();
		p1.antecedente=PASEO;
		p1.consecuente.add(PASEO);
		p1.consecuente.add(PASO);
		gramatica.p.add(p1);
		
		//PASEO => λ
		P p2 = new P();
		p2.antecedente=PASEO;
		p2.consecuente.add(lambda);
		gramatica.p.add(p2);
		
		//PASO => casa
		P p3 = new P();
		p3.antecedente=PASO;
		p3.consecuente.add(casa);
		gramatica.p.add(p3);
		
		//PASO => giro EXP
		P p4 = new P();
		p4.antecedente=PASO;
		p4.consecuente.add(EXP);
		p4.consecuente.add(giro);
		gramatica.p.add(p4);
		
		//PASO => avanza EXP
		P p5 = new P();
		p5.antecedente=PASO;
		p5.consecuente.add(EXP);
		p5.consecuente.add(avanza);
		gramatica.p.add(p5);
		
		//PASO => pinta EXP
		P p6 = new P();
		p6.antecedente=PASO;
		p6.consecuente.add(EXP);
		p6.consecuente.add(pinta);
		gramatica.p.add(p6);
		
		//PASO => id = EXP
		P p7 = new P();
		p7.antecedente=PASO;
		p7.consecuente.add(EXP);
		p7.consecuente.add(igual);
		p7.consecuente.add(id);
		gramatica.p.add(p7);
		
		//EXP => id
		P p8 = new P();
		p8.antecedente=EXP;
		p8.consecuente.add(id);
		gramatica.p.add(p8);
		
		//EXP => n
		P p9 = new P();
		p9.antecedente=EXP;
		p9.consecuente.add(n);
		gramatica.p.add(p9);
	}
	
	public static void main(String[] args) {
		
		
		
		
		
		
		gramatica.vn.add(PASEO);
		gramatica.vn.add(PASO);
		gramatica.vn.add(EXP);
		
		
		
		gramatica.vt.add(casa);
		gramatica.vt.add(giro);
		gramatica.vt.add(avanza);
		gramatica.vt.add(pinta);
		gramatica.vt.add(id);
		gramatica.vt.add(n);
		gramatica.vt.add(igual);
		
		
		//Estado inicial S=PASEO
		gramatica.simboloInicial=PASEO;
		
		
		//Producciones
		
		//PASEO => PASO PASEO
		P p1 = new P();
		p1.antecedente=PASEO;
		p1.consecuente.add(PASEO);
		p1.consecuente.add(PASO);
		gramatica.p.add(p1);
		
		//PASEO => λ
		P p2 = new P();
		p2.antecedente=PASEO;
		p2.consecuente.add(lambda);
		gramatica.p.add(p2);
		
		//PASO => casa
		P p3 = new P();
		p3.antecedente=PASO;
		p3.consecuente.add(casa);
		gramatica.p.add(p3);
		
		//PASO => giro EXP
		P p4 = new P();
		p4.antecedente=PASO;
		p4.consecuente.add(EXP);
		p4.consecuente.add(giro);
		gramatica.p.add(p4);
		
		//PASO => avanza EXP
		P p5 = new P();
		p5.antecedente=PASO;
		p5.consecuente.add(EXP);
		p5.consecuente.add(avanza);
		gramatica.p.add(p5);
		
		//PASO => pinta EXP
		P p6 = new P();
		p6.antecedente=PASO;
		p6.consecuente.add(EXP);
		p6.consecuente.add(pinta);
		gramatica.p.add(p6);
		
		//PASO => id = EXP
		P p7 = new P();
		p7.antecedente=PASO;
		p7.consecuente.add(EXP);
		p7.consecuente.add(igual);
		p7.consecuente.add(id);
		gramatica.p.add(p7);
		
		//EXP => id
		P p8 = new P();
		p8.antecedente=EXP;
		p8.consecuente.add(id);
		gramatica.p.add(p8);
		
		//EXP => n
		P p9 = new P();
		p9.antecedente=EXP;
		p9.consecuente.add(n);
		gramatica.p.add(p9);
		
		
		analizadorLexico = new AnalizadorLexico();
		analizadorLexico.obtenerListaDeTokens("./src/movimientos.txt");
		
		analizadorLexico.imprimirListaTokens(analizadorLexico.listaDeTokens);
		
		
		//Probar funcionamiento de analiza y ver si transita bien y pide los tokes de forma adecuada
		analiza();//arranca el proceso de
		
		Ventana ventana = new Ventana("");
		ventana.setVisible(true);
		

	}
	
	
	public static boolean analiza(){//cambio el retorno de void a boolean, util para pintar
		try{
			Paseo();
		}catch(Exception e){
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	public static void Paseo(){
		try{
			Token t = analizadorLexico.tokenSiguiente();////////////////
			if( esCasaGiroAvanzaPintaID(t)){
				Paso();
				Paseo();
			}
			else{
				System.out.println("Operacion invalida segun LOGO: "+t.lexema);
			}
		}catch(Exception e){
			System.out.println("la cadena ha sido procesada y no hay mas elementos.");
		}
	}
	


	public static void Paso(){
		try{
			Token t = analizadorLexico.tokenActual();////////////////
			if(esCasa(t)){
				Match(casa);
			}
			if(esGiro(t)){
				Match(giro);
				Exp();
			}
			if(esAvanza(t)){
				Match(avanza);
				Exp();
			}
			if(esPinta(t)){
				Match(pinta);
				Exp();
			}
			if(esID(t)){
				Match(id);
				Match(igual);
				Exp();
			}
		}catch(Exception e){
			System.out.println("Esperaba pinta,casa,avanza, gira o identificador, o falla dame token");//throw new ErrorSintacticoEnPaso("Esperaba pinta,casa,avanza, gira o identificador");
		}
		
	}
	
	


	


	public static void Exp(){
		try{
			Token anterior = analizadorLexico.tokenActual();
			Token t = analizadorLexico.tokenSiguiente();////////////////
			boolean procesar = false;
			if(esID(t)){
				procesar = Match(id);
			}
			if(esN(t)){
				procesar = Match(n);
			}
			if(procesar == false){
				System.out.println("esperaba id o numero despues de "+anterior.lexema);//throw new ErrorSintacticoExp("esperaba id o numero")
			}
		}catch(Exception e){
			//System.out.println("falla dame token");//throw new ErrorSintacticoExp("esperaba id o numero")
		}
		
	}


	public static boolean esID(Token t){
		if(t.lexema.equals(id.v)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean Match(VT vt){
		try{
			Token t=analizadorLexico.tokenActual();
			return true;
		}catch(Exception e){
			return false;
		}
		/*if(t.lexema.equals(vt.v)){
			return true;
		}else{
			return false;
		}*/
	}
		//id=new Vt("identificador")
		//igual=new Vt(":=")
		//casa=new Vt("c")
	
	private static boolean esCasaGiroAvanzaPintaID(Token t) {
		if(t.lexema.equals(casa.v) || t.lexema.equals(giro.v) || t.lexema.equals(avanza.v) || t.lexema.equals(pinta.v) || t.lexema.equals(id.v)){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean esCasa(Token t) {
		if(t.lexema.equals(casa.v)){
			return true;
		}else{
			return false;
		}
		
	}
	
	private static boolean esN(Token t) {
		try{
			Integer.parseInt(t.lexema);
			return true;
		}catch(Exception e){
			return false;
		}
			
	}
	
	private static boolean esPinta(Token t) {
		if(t.lexema.equals(pinta.v)){
			return true;
		}else{
			return false;
		}
	}


	private static boolean esAvanza(Token t) {
		if(t.lexema.equals(avanza.v)){
			return true;
		}else{
			return false;
		}
	}


	private static boolean esGiro(Token t) {
		if(t.lexema.equals(giro.v)){
			return true;
		}else{
			return false;
		}
	}

}
