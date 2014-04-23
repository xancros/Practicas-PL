package P1;

public class practica1 {

	
	public static void main(String[] args) {
		/*Representar la siguiente gramatica
		 	T->F
			F->(E)
			F->i*/
		G gramatica = new G();
		VN e = new VN("E");
		VN t = new VN("T");
		VT f = new VT("F");
		VT mas = new VT("+");
		VT por = new VT("*");
		
		P produccion1 = new P();
		produccion1.antecedente=e;
		produccion1.consecuente.add(e);
		produccion1.consecuente.add(mas);
		produccion1.consecuente.add(t);
		
		P produccion2 = new P();
		produccion2.antecedente=e;
		produccion2.consecuente.add(t);
		
		P produccion3 = new P();
		produccion3.antecedente=t;
		produccion3.consecuente.add(t);
		produccion3.consecuente.add(por);
		produccion3.consecuente.add(f);
	}

}
