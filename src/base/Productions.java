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
	
	public String toString(){
		Iterator<Vocabulary> it = consecuente.iterator();
		String c = new String();
		while (it.hasNext()){
			c=(c+(it.next().toString()));
		}
		return new String(antecedente.toString()+"->"+c);
	}
}
