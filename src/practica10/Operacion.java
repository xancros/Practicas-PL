package practica10;
import java.util.Stack;
import base.Productions;
import base.Vocabulary;
import java.util.List;
public abstract class Operacion {
	public Character letra;
	public int numero;
	protected Operacion(Character l,int n){
		letra=l;
		numero=n;
	}
	public abstract int accion(Stack<Vocabulary> pila, List<Productions> prod,Vocabulary e);
}
