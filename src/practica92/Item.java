package practica92;

import java.util.ArrayList;
import java.util.Collection;




import base.Productions;
import base.Terminals;

public class Item {
	public Productions p;
	public Collection<Terminals> simbolosAnticipacion;
	
	public Item(){
		p=new Productions();
		simbolosAnticipacion=new ArrayList<Terminals>();
		
	}
	public String toString(){
		String l = new String();
		for(Terminals a:simbolosAnticipacion){
			l+=a.toString();
		}
		l+="\n";
		l+=p.toString();
		return l;
	}
}
