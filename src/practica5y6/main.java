package practica5y6;

import java.util.ArrayList;
import java.util.List;

import base.NonTerminals;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<base.Vocabulary> l = new ArrayList<base.Vocabulary>();
		//λ
		  // create an empty array list with an initial capacity
	     
		AnalizadorSintacticoLLgenerico a = new AnalizadorSintacticoLLgenerico(l);
		//a.mostrarIniciales(new base.Terminals("+"));
		//a.mostrarTabla();
		a.mostrarTabla();
		base.Grammar g = a.getGramatica();
		//for(base.NonTerminals nt:g.getVn()){
		/*for(base.Productions p:g.getP()){
				System.out.println("Produccion "+p.toString());
				a.mostrarDirectores(p);
				NonTerminals nt = p.getAntecedente();
			if(nt.getVocabulario().equals("T")){
				System.out.println("NO terminal "+nt);
				//a.mostrarSeguidores(nt);
				//a.mostrarIniciales(nt);
				
			}
		}
		*/
	}

}
