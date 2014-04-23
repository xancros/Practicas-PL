package practica7;
import java.io.IOException;

import practica3.Analisis;
public class AnalizadorLexico extends Analisis  {
	static String ruta="E:\\GitHub\\PracticasEDA\\Practicas-PL\\src\\practica7\\movimientos.txt";
	static int indice=-1;
	
	
	
	
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		AnalizadorLexico a = new AnalizadorLexico();
		a.setRuta(ruta);
		try {
			a.preparacion();
			a.analizador();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
