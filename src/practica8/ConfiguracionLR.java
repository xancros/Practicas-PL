package practica8;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import base.NonTerminals;
import base.Productions;
import base.Vocabulary;

public class ConfiguracionLR {
	protected List<Tabla> tabla;
	protected List<Productions> producciones;
	public ConfiguracionLR(List<Productions> producciones){
		this.producciones=producciones;
	}
	protected void generarTabla(List<Vocabulary> l){
		tabla=new LinkedList<Tabla>();
		//ESTADO 0
		tabla.add(new Tabla(0,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(0,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(0,l.get(6),new Desplazamiento(null,1)));
		tabla.add(new Tabla(0,l.get(7),new Desplazamiento(null,2)));
		tabla.add(new Tabla(0,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 1
		tabla.add(new Tabla(1,l.get(1),new Desplazamiento('D',6)));
		tabla.add(new Tabla(1,l.get(5),new Desplazamiento('A',-1)));
		//ESTADO 2
		tabla.add(new Tabla(2,l.get(1),new Reduccion('R',2)));
		tabla.add(new Tabla(2,l.get(2),new Desplazamiento('D',7)));
		tabla.add(new Tabla(2,l.get(4),new Reduccion('R',2)));
		tabla.add(new Tabla(2,l.get(5),new Reduccion('R',2)));
		//ESTADO 3
		tabla.add(new Tabla(3,l.get(1),new Reduccion('R',4)));
		tabla.add(new Tabla(3,l.get(2),new Reduccion('R',4)));
		tabla.add(new Tabla(3,l.get(4),new Reduccion('R',4)));
		tabla.add(new Tabla(3,l.get(5),new Reduccion('R',4)));
		//ESTADO 4
		tabla.add(new Tabla(4,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(4,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(4,l.get(6),new Desplazamiento(null,8)));
		tabla.add(new Tabla(4,l.get(7),new Desplazamiento(null,2)));
		tabla.add(new Tabla(4,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 5
		tabla.add(new Tabla(5,l.get(1),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(2),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(4),new Reduccion('R',6)));
		tabla.add(new Tabla(5,l.get(5),new Reduccion('R',6)));
		//ESTADO 6
		tabla.add(new Tabla(6,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(6,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(6,l.get(7),new Desplazamiento(null,9)));
		tabla.add(new Tabla(6,l.get(8),new Desplazamiento(null,3)));
		//ESTADO 7
		tabla.add(new Tabla(7,l.get(0),new Desplazamiento('D',5)));
		tabla.add(new Tabla(7,l.get(3),new Desplazamiento('D',4)));
		tabla.add(new Tabla(7,l.get(8),new Desplazamiento(null,10)));
		//ESTADO 8
		tabla.add(new Tabla(8,l.get(1),new Desplazamiento('D',6)));
		tabla.add(new Tabla(8,l.get(4),new Desplazamiento('D',11)));
		//ESTADO 9
		tabla.add(new Tabla(9,l.get(1),new Reduccion('R',1)));
		tabla.add(new Tabla(9,l.get(2),new Desplazamiento('D',7)));
		tabla.add(new Tabla(9,l.get(4),new Reduccion('R',1)));
		tabla.add(new Tabla(9,l.get(5),new Reduccion('R',1)));
		//ESTADO 10
		tabla.add(new Tabla(10,l.get(1),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(2),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(4),new Reduccion('R',3)));
		tabla.add(new Tabla(10,l.get(5),new Reduccion('R',3)));
		//ESTADO 11
		tabla.add(new Tabla(11,l.get(1),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(2),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(4),new Reduccion('R',5)));
		tabla.add(new Tabla(11,l.get(5),new Reduccion('R',5)));
		
		
		
		
	}
	protected int analisisDecision(Vocabulary elem,Stack<Vocabulary> pila){
		int e;
		if(elem instanceof NonTerminals)
			e=Integer.parseInt(pila.get(pila.size()-2).getVocabulario());
		else
			e = Integer.parseInt(pila.peek().getVocabulario());
		for(Tabla t:tabla){
			if(t.fila==e && t.columna.getVocabulario().equals(elem.getVocabulario())){
				
				return t.op.accion(pila, producciones, elem);
			}
		}
		return -1;
	}
}
