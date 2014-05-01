package practica10;

import java.util.List;
import java.util.Stack;


import base.NonTerminals;
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
		for(Vocabulary p: prod.get(numero).getConsecuente()){
			pila.pop();
			pila.pop();
		}
		NonTerminals nt = new NonTerminals(prod.get(numero).getAntecedente().getVocabulario());
		pila.push(nt);
		
		return 2;
	}

}
