package base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Grammar {
	private Collection<NonTerminals> vn;
	private Collection<Terminals> vt;
	private Collection<Productions> p;
	private NonTerminals s;
	
	public Grammar(Collection<NonTerminals> vn, Collection<Terminals> vt,
			Collection<Productions> p, NonTerminals s) {
		super();
		this.vn = vn;
		this.vt = vt;
		this.p = p;
		this.s = s;
	}
	
	public String toString(){
		String s = new String();

		s += "Simbolos no terminales\n";

		s += ("(");
		for (NonTerminals itNT : vn) {

			s += (itNT.toString() + ",");
		}
		s += ("}\n");

		s += ("\nSimbolos terminales\n");

		s += ("(");
		for (Terminals itT : vt) {
			s += (itT.toString() + ",");

		}

		s += ("}\n");

		s += ("\nProducciones\n");

		for (Productions itP : p) {
			s += (itP.toString() + "\n");
		}

		return s;
	}

}
