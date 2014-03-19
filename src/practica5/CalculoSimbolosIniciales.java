package practica5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import excepciones.ErrorSintactico;
import practica4.AnalisisSintacticoGreibach;
import base.*;

public class CalculoSimbolosIniciales extends base.AnalizadorSintactico{
	private Collection<Terminals> listaIniciales;
	
	/**
	 * 
	 * @param input Coleccion de elementos de una produccion para obtener sus iniciales
	 * @throws ErrorSintactico
	 */
	public CalculoSimbolosIniciales(ArrayList<Vocabulary>input) throws ErrorSintactico{
		super(input);
		listaIniciales = this.iniciales(input);
		
	}
	public Collection<Terminals> getListaIniciales(){
		return this.listaIniciales;
	}
	protected void derivar(){
		
	}
	
	protected Collection<Terminals> iniciales(Collection<Vocabulary> v){
		Collection<Terminals> lista = new LinkedList<Terminals>();
		for(Vocabulary p:v){
			if(p instanceof Terminals || esLamba(p))
				lista.add((Terminals) p);
			else if (p instanceof NonTerminals)
				lista.addAll(iniciales(getProductions(p)));
		}
		return lista;
	}
	private Collection<Vocabulary> getProductions(Vocabulary p){
		Collection<Vocabulary> lista = new LinkedList<Vocabulary>();
		for(Productions prod:g.getP()){
			if(prod.getAntecedente().getVocabulario().equals(p.getVocabulario())){
				lista.addAll(prod.getConsecuente());
			}
		}
		return lista;
	}
	private boolean esLamba(Vocabulary p){
		return false;
	}
	

	@Override
	protected void generarGramatica() {
		// TODO Auto-generated method stub
		
	}

}
