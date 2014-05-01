package base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Grammar {
	private Collection<NonTerminals> vn;
	private Collection<Terminals> vt;
	private Collection<Productions> p;
	private NonTerminals s;
	private final Terminals lambda=new Terminals("λ");
	private final  Terminals dolar=new Terminals("$");
	private final  Terminals punto=new Terminals(".");
	private NonTerminals s2;
	public ArrayList<Vocabulary> conjuntoTNT = new ArrayList<Vocabulary>();
	
	public NonTerminals getS2() {
		return s2;
	}
	public void setS2(NonTerminals s2) {
		this.s2 = s2;
	}
	public Terminals getPunto() {
		return punto;
	}
	public Grammar(Collection<NonTerminals> vn, Collection<Terminals> vt,
			Collection<Productions> p, NonTerminals s) {
		super();
		this.vn = vn;
		this.vt = vt;
		this.p = p;
		this.s = s;
	}
	public Productions buscarProduccion(Vocabulary t,Vocabulary s){
		for (Productions find: p){
			if(find.getAntecedente().getVocabulario().equals(s.getVocabulario())){
				for (Vocabulary cn:find.getConsecuente()){
					if(cn.getVocabulario().equals(t.getVocabulario()))
						return find;
				}
			}
		}
		
		return null;
	}
	public Collection<NonTerminals> getVn() {
		return vn;
	}

	public void setVn(Collection<NonTerminals> vn) {
		this.vn = vn;
	}

	public Collection<Terminals> getVt() {
		return vt;
	}

	public void setVt(Collection<Terminals> vt) {
		this.vt = vt;
	}

	public Collection<Productions> getP() {
		return p;
	}

	public void setP(Collection<Productions> p) {
		this.p = p;
	}

	public NonTerminals getS() {
		return s;
	}

	public void setS(NonTerminals s) {
		this.s = s;
	}

	public String toString(){
		String s = new String();

		s += "Simbolos no terminales\n";

		s += ("( ");
		for (NonTerminals itNT : vn) {

			s += (itNT.toString());
		}
		s += (" }\n");

		s += ("\nSimbolos terminales\n");

		s += ("( ");
		for (Terminals itT : vt) {
			s += (itT.toString());

		}

		s += (" }\n");

		s += ("\nProducciones\n");

		for (Productions itP : p) {
			s += (itP.toString() + "\n");
		}

		return s;
	}
	public  Terminals getLambda() {
		return lambda;
	}
	public Terminals getDolar() {
		return dolar;
	}

}
