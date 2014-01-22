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
		System.out.print("Simbolos no terminales\n");
		Iterator<NonTerminals> itNT = vn.iterator();
		System.out.print("{");
		while(itNT.hasNext()){
			System.out.print(itNT.next().toString());
			if(itNT.hasNext())
				System.out.print(",");
		}
		System.out.print("}\n");
		
		System.out.print("\nSimbolos terminales\n");
		Iterator<Terminals> itT = vt.iterator();
		System.out.print("{");
		while(itT.hasNext()){
			System.out.print(itT.next().toString());
			if(itT.hasNext())
				System.out.print(",");
		}
		System.out.print("}\n");
		System.out.print("\nProducciones\n");
		Iterator<Productions> itP = p.iterator();
		while(itP.hasNext()){
			System.out.println(itP.next().toString());
		}
		/*String objeto = new String();
		objeto.concat("Simbolos no terminales\n");
		Iterator<NonTerminals> itNT = vn.iterator();
		while(itNT.hasNext()){
			objeto.concat(itNT.next().toString()+"\n");
		}
		objeto.concat("Simbolos Terminales\n");
		Iterator<Terminals> itT = vt.iterator();
		while(itT.hasNext()){
			objeto.concat(itT.next().toString()+"\n");
		}
		objeto.concat("Producciones\n");
		Iterator<Productions> itP = p.iterator();
		while(itP.hasNext()){
			objeto.concat(itP.toString()+"\n");
		}
		objeto.concat("Simbolo inicial\n"+s.toString());
		return objeto;*/
		return "";
	}
	
}
