package practica8;

import java.util.List;
import java.util.Stack;

import base.NonTerminals;
import base.Productions;
import base.Terminals;
import base.Vocabulary;

public class Desplazamiento extends Operacion {

	public Desplazamiento(Character l, int n) {
		super(l, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int accion(Stack<Vocabulary> pila, List<Productions> prod,Vocabulary e) {
		// TODO Auto-generated method stub
		
		if(letra!=null){
			if(numero!=-1){
				pila.add(e);
				pila.add(new Vocabulary(String.valueOf(numero)));
				return 0;
			}else
				return -2;
		}else{
			pila.add(new Vocabulary(String.valueOf(numero)));
			return 1;
		}
		
		
		
	}

}
