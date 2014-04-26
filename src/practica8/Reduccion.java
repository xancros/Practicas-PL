package practica8;

import java.util.List;
import java.util.Stack;

import base.Productions;
import base.Vocabulary;

public class Reduccion extends Operacion {

	public Reduccion(char l, int n) {
		super(l, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int accion(Stack<Vocabulary> pila, List<Productions> prod,Vocabulary e) {
		// TODO Auto-generated method stub
		for(Vocabulary p: prod.get(numero-1).getConsecuente()){
			pila.pop();
			pila.pop();
		}
		pila.push(prod.get(numero-1).getAntecedente());
		return 2;
	}

}
