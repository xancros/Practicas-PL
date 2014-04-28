package P1;
import java.util.ArrayList;


public class P {
	public VN antecedente;
	public ArrayList<V> consecuente;
	public P(){
		this.consecuente = new ArrayList<V>();
	}
	@Override public P clone() {
        try {
            final P result = (P) super.clone();
            return result;
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
	}
	public P copiar() {
		P nuevo = new P();
		nuevo.antecedente=this.antecedente;
		for(int i = 0; i<this.consecuente.size();i++){
			nuevo.consecuente.add(this.consecuente.get(i));
		}
		return nuevo;
	}
}
