package practica8;

import base.Vocabulary;

public class Tabla {
	Vocabulary columna;
	int fila;
	Operacion op;
	
	public Tabla(int f,Vocabulary c,Operacion o){
		fila=f;
		columna=c;
		op=o;
	}
}
