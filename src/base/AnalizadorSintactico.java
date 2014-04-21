package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import excepciones.ErrorSintactico;

public abstract class AnalizadorSintactico {
	protected Grammar g;
	protected List<Vocabulary> entrada;
	protected Stack<Vocabulary> inicio;
	protected AnalizadorSintactico(ArrayList<Vocabulary> input){
		inicio=new Stack<Vocabulary>();
		generarGramatica();
		entrada=input;
		inicio.add(g.getS());
	}
	protected abstract void generarGramatica();
	protected boolean analizar() throws ErrorSintactico{
		while(!entrada.isEmpty() && !inicio.isEmpty()){
			if(inicio.get(0) instanceof NonTerminals || firstElementIsNonTerminal()){
				derivar();
			}
			if(primerElementoIgual()){
				casar();
			}else{
				//lanzar excepcion
				throw new excepciones.ErrorSintactico();
			}
			
		}
		return entrada.isEmpty() && inicio.isEmpty();
	}
	protected abstract void derivar() throws ErrorSintactico;
	protected void casar(){
		//TO-DO
		Vocabulary in = inicio.get(0);
		Vocabulary en = entrada.get(0);
		entrada.remove(0);
		inicio.remove(0);
		System.out.println("Se reconoce: "+in.getVocabulario()+" , "+en.getVocabulario());
		mostrar();
	}
	protected void mostrar(){
		System.out.print("Configuracion:(");
		for(int i=0;i<inicio.size();i++)
			System.out.print(inicio.get(i));
		System.out.print("),(");
		for(int i=0;i<entrada.size();i++)
			System.out.print(entrada.get(i));
		System.out.println(")");
	}
	protected boolean primerElementoIgual(){
		String a =inicio.get(0).getVocabulario();
		String b = entrada.get(0).getVocabulario();
		return (a).equals(b); 
}
	protected boolean firstElementIsNonTerminal(){
		return g.getVn().contains(inicio.peek().getVocabulario());
	}
}
