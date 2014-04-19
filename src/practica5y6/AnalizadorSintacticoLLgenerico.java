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
	private Grammar g;
	
	protected AnalizadorSintacticoLLgenerico(ArrayList<Vocabulary> input) {
		super(input);
		// TODO Auto-generated constructor stub
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
	/*
	 * Collection<Vt> iniciales(Vt) ;
Collection<Vt> iniciales(Vn) ;
Collection<Vt> iniciales(Collection<V>) ;


	 */
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
		
	}
	@Override
	protected void derivar() throws ErrorSintactico {
		// TODO Auto-generated method stub
		
	}

}
