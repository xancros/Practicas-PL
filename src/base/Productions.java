package base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Productions {
	private NonTerminals antecedente;
	private Collection<Vocabulary> consecuente;
	

	public Productions (String ant,Collection<Vocabulary> con){
		antecedente= new NonTerminals(ant);
		consecuente=con;
	}
	
	public NonTerminals getAntecedente() {
		return antecedente;
	}

	public void setAntecedente(NonTerminals antecedente) {
		this.antecedente = antecedente;
	}

	public Collection<Vocabulary> getConsecuente() {
		return consecuente;
	}

	public void setConsecuente(Collection<Vocabulary> consecuente) {
		this.consecuente = consecuente;
	}
	public Vocabulary getFirstElement(){
		Vocabulary e=null;
		for(Vocabulary element : consecuente)
		{
			e=element;
			break;
		}
		return e;
	}
	public String toString(){
		//Iterator<Vocabulary> it = consecuente.iterator();
		String c = new String();
		//while (it.hasNext()){
		for (Vocabulary vo: consecuente){
		//	c=(c+(it.next().toString()));
			c=(c+vo.toString());
		}
		return new String(antecedente.toString()+"->"+c);
	}
}
