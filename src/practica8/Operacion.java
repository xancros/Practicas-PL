package practica8;
import java.util.Stack;
import base.Productions;
import base.Vocabulary;
import java.util.List;
public abstract class Operacion {
	Character letra;
	int numero;
	protected Operacion(Character l,int n){
		letra=l;
		numero=n;
	}
	protected abstract void accion(Stack<Vocabulary> pila, List<Productions> prod);
}
