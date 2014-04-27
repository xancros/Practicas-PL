package practica92;

import java.util.ArrayList;
import java.util.Collection;




import base.Productions;
import base.Terminals;

public class Item {
	Productions p;
	Collection<Terminals> simbolosAnticipacion;
	
	Item(){
		p=new Productions();
		simbolosAnticipacion=new ArrayList<Terminals>();
		
	}
}
