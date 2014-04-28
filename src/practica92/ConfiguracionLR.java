package practica92;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import practica8.Desplazamiento;
import practica8.Operacion;
import practica8.Reduccion;


import base.Grammar;
import base.NonTerminals;
import base.Productions;
import base.Terminals;
import base.Vocabulary;

public class ConfiguracionLR {
	protected Grammar G;
	protected ArrayList<Vocabulary> conjuntoTVT;
	
	protected Operacion[][] Tabla;
	protected List<Productions> prod;
	public ConfiguracionLR(ArrayList<Productions> prods,ArrayList<Vocabulary> h){
		//G=g;
		//conjuntoTVT=c;
		NonTerminals S = new NonTerminals("S");
		NonTerminals C = new NonTerminals("C");
		//NonTerminals F = new NonTerminals("F");
		NonTerminals S2 = new NonTerminals("S'");
		
		Terminals c = new Terminals("c");
		Terminals d = new Terminals("d");
		//Terminals id = new Terminals("id");
		//Terminals abierto = new Terminals("(");
		//Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals punto = new Terminals(".");
		conjuntoTVT = new ArrayList<Vocabulary>();
		conjuntoTVT.add(c);
		conjuntoTVT.add(d);
		//conjuntoTVT.add(F);
		//conjuntoTVT.add(id);
		conjuntoTVT.add(S);
		conjuntoTVT.add(C);
		conjuntoTVT.add(dolar);
		//conjuntoTVT.add(abierto);
	//	conjuntoTVT.add(cerrado);
		
		Collection<Vocabulary> cp0 = new ArrayList<Vocabulary>();
		cp0.add(S);
		Productions p0 = new Productions("S'",cp0);
Collection<Vocabulary> cp1 = new ArrayList<Vocabulary>();
		
		//cp1.add(E);
		cp1.add(C);
		cp1.add(C);
		Productions p1 = new Productions("S",cp1);
		
		//Collection<Vocabulary> cp2 = new ArrayList<Vocabulary>();
		//cp2.add(T);
		//Productions p2 = new Productions("E",cp2);
		Collection<Vocabulary> cp3 = new ArrayList<Vocabulary>();
		
		//cp3.add(T);
		cp3.add(c);
		cp3.add(C);
		Productions p3 = new Productions("C",cp3);
		
		//Collection<Vocabulary> cp4 = new ArrayList<Vocabulary>();
		//cp4.add(F);
		//Productions p4 = new Productions("T",cp4);
Collection<Vocabulary> cp5 = new ArrayList<Vocabulary>();
		
		//cp5.add(T);
		cp5.add(d);
		//cp5.add(cerrado);
		Productions p5 = new Productions("C",cp5);
		
		//Collection<Vocabulary> cp6 = new ArrayList<Vocabulary>();
		//cp6.add(id);
		//Productions p6 = new Productions("F",cp6);
		
		/*Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E);
		nt.add(T);
		//nt.add(F);
		
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(por);
		//t.add(id);
		//t.add(abierto);
		//t.add(cerrado);*/
		/*Collection<Productions> p = new ArrayList<Productions>();
		p.add(p1);
		//p.add(p2);
		p.add(p3);
		//p.add(p4);
		p.add(p5);
		//p.add(p6);*/
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(S2);
		nt.add(S);
		nt.add(C);
		//nt.add(F);
		
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(c);
		t.add(d);
		//t.add(id);
		//t.add(abierto);
		//t.add(cerrado);
		Collection<Productions> p = new ArrayList<Productions>();
		p.add(p0);
		p.add(p1);
		//p.add(p2);
		p.add(p3);
		//p.add(p4);
		p.add(p5);
		//p.add(p6);
		
		//System.out.println(p.toString());
		Grammar gr = new Grammar(nt,t,p,S);
		gr.setS2(S2);
		G=gr;
		
		Tabla=generarTabla(gr);
		imprimirTabla(Tabla);
		prod=prods;
	}/*
	public static void main(String[] args){
		
		ConfiguracionLR c = new ConfiguracionLR(null,null);
	}*/
	private void imprimirEstados(Collection<Item> lista, int n){
		System.out.println("-------------------------");
		System.out.println("Estado "+n);
		for(Item it:lista){
			
			System.out.print(it.p.getAntecedente().getVocabulario()+"->");
			for(Vocabulary vp:it.p.getConsecuente())
				System.out.print(vp.getVocabulario());
			System.out.println();
		}
		System.out.println("\n------------------------");
			
	}
	private void imprimirTabla(Operacion[][] tabla){
		System.out.print("\t");
		for(int i=0;i<conjuntoTVT.size();i++){
			System.out.print(conjuntoTVT.get(i).getVocabulario()+"\t");
		}
		System.out.println();
		
		for(int i=0;i<tabla.length;i++){
			System.out.print(i+"\t");
			for(int j=0;j<tabla[i].length;j++){
				if(tabla[i][j]!=null){
					if(tabla[i][j].letra!=null){
						System.out.print(tabla[i][j].letra);
					}
					System.out.print(+tabla[i][j].numero+"\t");
				}else{
					System.out.print("--\t");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
	}
	 private Operacion[][] generarTabla(Grammar G2){
		 Collection<Item> estados = new ArrayList<Item>();
		 //ESTADO 0
		 Collection<Vocabulary> cons= new ArrayList<Vocabulary>();
		 cons.add(G2.getPunto());
		 cons.add(G2.getS());
		 
		 Productions prd = new Productions(G2.getS2().getVocabulario(),cons);
		 Item it = new Item();
		 it.simbolosAnticipacion.add(G.getDolar());
		 it.p=prd;
		 
		 estados.add(it);
		 //-----------------------
		 Operacion op;
		 Collection<Collection<Item>> listaEstados = calculoDeConjuntos(G2,estados);
		 Operacion[][] tabla = new Operacion[listaEstados.size()][conjuntoTVT.size()];
		 int Nestado=-1;
		 for(Collection<Item> estado:listaEstados){
			 Nestado++;
			 imprimirEstados(estado, Nestado);
			 for(Item item: estado){
				 if(puntoAlFinal(item.p)){
					 if(item.p.getAntecedente().getVocabulario().equals(G2.getS2().getVocabulario())){
						 int indiceV = conjuntoTVT.indexOf(G.getDolar());
						 op = new Desplazamiento('A',-1);
						 tabla[Nestado][indiceV]=op;
						 
					 }else{
						for(Terminals t:item.simbolosAnticipacion){
							//simbolos de anticipacion con la reduccion
							int n = buscarProduccionEnGramatica(G2, item.p);
							 int indiceV =conjuntoTVT.indexOf(t);
							 op = new Reduccion('R',n);
							 tabla[Nestado][indiceV]=op;
						}
					 }
				 }else{
					 
					 Vocabulary v = this.dameSiguienteDelPunto(item.p);
					 Collection<Item> ir = ir_a(G2,estado,v);
					 int indiceV= conjuntoTVT.indexOf(v);
					 int indice = this.orden(listaEstados, ir);
					 
						if (v instanceof Terminals) {
							op = new Desplazamiento('D', indice);
							tabla[Nestado][indiceV] = op;
						} else {
							op = new Desplazamiento(null, indice);
							tabla[Nestado][indiceV] = op;
						}
					 
				 }
			 }
			
		 }
		 return tabla;
	 }
	 private int buscarProduccionEnGramatica(Grammar G2,Productions item){
		 int indice=-1;
		 for(Productions p:G2.getP()){
			 indice++;
			 if(item.getAntecedente().getVocabulario().equals(p.getAntecedente().getVocabulario())){
				 if(item.getConsecuente().size()==p.getConsecuente().size()+1){
					 boolean eq=true;
					 ArrayList<Vocabulary> listaP = (ArrayList<Vocabulary>) p.getConsecuente();
					 ArrayList<Vocabulary> listaI = (ArrayList<Vocabulary>) item.getConsecuente();
					 for(int i=0;i<p.getConsecuente().size() && eq;i++){
						 if(listaP.get(i).getVocabulario().equals(listaI.get(i).getVocabulario()))
							 eq=true;
						 else
							 eq=false;
					 }
					 if(eq)
						 return indice;
						 
				 }
			 }
		 }
		 return 0;
	 }
	private Collection<Terminals> siguientes(Grammar G2, NonTerminals nt){
		ArrayList<Terminals> listaSiguientes = new ArrayList<Terminals>();
		if(nt.getVocabulario().equals(G2.getS().getVocabulario()))
			listaSiguientes.add(G2.getDolar());
		for(Productions p:G2.getP()){
		
			List<Vocabulary> v = (List<Vocabulary>)p.getConsecuente();
			if(v.contains(nt)){
				
				List<Vocabulary> listaProd = (List<Vocabulary>)p.getConsecuente();
				if(listaProd.indexOf(nt)==listaProd.size()-1){   //ultimo elemento?
					if(!p.getAntecedente().getVocabulario().equals(nt.getVocabulario()))
						listaSiguientes.addAll(siguientes(G2,p.getAntecedente()));
				}else{
					List<Vocabulary> subLista=listaProd.subList(listaProd.indexOf(nt)+1, listaProd.size());
					if(iniciales(G2,subLista).contains(G2.getLambda())){
						listaSiguientes.addAll(iniciales(G2,subLista));
						listaSiguientes.remove(G2.getLambda());
						listaSiguientes.addAll(siguientes(G2,p.getAntecedente()));
						
					}else{
						listaSiguientes.addAll(iniciales(G2,subLista));
					}
				}
			}
		}
		return listaSiguientes;
	}
	private Collection<Terminals> iniciales(Grammar G2,Collection<Vocabulary> c){
		/*Collection<Terminals> lista = new ArrayList<Terminals>();
		for(Vocabulary v:c){
			if(v instanceof Terminals){
				lista.addAll(iniciales(G2,((Terminals)v)));
				return lista;
			}else{
				lista = iniciales(G2,(NonTerminals)v);
				if(!lista.contains(G2.getLambda())){
					return lista;
				}
			}
		}
		lista.add(G2.getLambda());
		return lista;*/
		Collection<Terminals> ls = new ArrayList<Terminals>();
		if(c.isEmpty()){
			ls.add(G2.getLambda());
			return ls;
		}
		for(Vocabulary v: c){
			if(v instanceof Terminals){
				ls.add((Terminals)v);
				return ls;
			}
			if (v instanceof NonTerminals){
				Collection<Terminals> ini = iniciales(G2, (NonTerminals)v);
				ls.addAll(ini);
			}
		}
		return ls;
		
	}
	private Collection<Terminals> iniciales(Grammar G2,Terminals t){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		conjunto.add(t);
		return conjunto;
	}
	
	private Collection<Terminals> iniciales(Grammar G2,NonTerminals nt){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		for(Productions p:G2.getP())
			if(p.getAntecedente().getVocabulario().equals(nt.getVocabulario())){
				conjunto.addAll(iniciales(G2,p.getConsecuente()));
			}
		return conjunto;
		/*Set<Terminals> conjunto = new HashSet<Terminals>();
		for(Productions p : dameProducciones(G2,nt)){
			//System.out.println("Produccion: "+p.toString());
			conjunto.addAll(iniciales(G2,p.getConsecuente()));
		}
		return conjunto;*/
		
	}
	private List<Productions> dameProducciones(Grammar G2,NonTerminals nt){
		List<Productions> lista = new ArrayList<Productions>();
		for(Productions p : G2.getP()){
			if (p.getAntecedente().getVocabulario().equals(nt.getVocabulario())){
				lista.add(p);
			}
		}
		return lista;
	}
	
	private int orden(Collection<Collection<Item>> lista,Collection<Item> ir){
		
		ArrayList<Vocabulary> cons = new ArrayList<Vocabulary>();
		ArrayList<Vocabulary> cons2 = new ArrayList<Vocabulary>();
		ArrayList<Item> l2 = new ArrayList<Item>();
		for(Item p : ir){l2.add(p);}
		
		int orden=-1;
		for(Collection<Item> c:lista){//ir conparando los collecciones de la lista con el colecction a comparar
			orden++;
			ArrayList<Item> l1 = new ArrayList<Item>();
			for(Item p : c){l1.add(p);}
			
			
			
			if(c.size()==ir.size()){
				int i=0;
				for(i=0;i<l1.size();i++){
					if(l1.get(i).p.getAntecedente().getVocabulario().equals(l2.get(i).p.getAntecedente().getVocabulario())){
						//si antecedente son iguales miramos los consecuentes
						cons=(ArrayList<Vocabulary>)l1.get(i).p.getConsecuente();
						cons2=(ArrayList<Vocabulary>)l2.get(i).p.getConsecuente();
						if(cons.size()==cons2.size()){//si la longitud de los consecuentes son iguales
							for(int m=0;m<cons.size();m++){
								if(cons.get(m).getVocabulario().equals(cons2.get(m).getVocabulario())){
									
								}else{
									//return -1;//si falla la comparacion de un solo V del consecuente
								}
							}
						}else{
							break;
						}
					}else{
						break;//uno de los antecedentes no concuerdan
					}
				}
				if(i==l1.size()){
					ArrayList<Terminals> simb1;
					ArrayList<Terminals> simb2;
					boolean salir=false;
					for(i=0;i<l1.size() && !salir;i++ ){
						simb1=(ArrayList<Terminals>) l1.get(i).simbolosAnticipacion;
						simb2=(ArrayList<Terminals>) l2.get(i).simbolosAnticipacion;
						if(simb1.size()!=simb2.size())
							break;
						for(int j=0;j<simb1.size();j++){
							if(!simb1.get(j).getVocabulario().equals(simb2.get(j).getVocabulario())){
								salir=true;
								break;
							}
							
						}
					}
					if(i==l1.size())
						return orden;
				}
				//return false;//el conjunto de items ya existia
			}else{
				//teinen distintas longitudes
			}
		}
		return -1;
	}

	public  Collection<Collection<Item>> calculoDeConjuntos(Grammar G2,Collection<Item> I){
		Collection<Collection<Item>> estados=new ArrayList<Collection<Item>>();
		Collection<Collection<Item>> subEstados;
		Collection<Collection<Item>> subEstados2;
		int size;
		Collection<Item> E0 = cierre(G2,I);
		estados.add(E0);
		boolean cambia=true;
		while(cambia){
			size=estados.size();
			subEstados=new ArrayList<Collection<Item>>();
			for(Collection<Item> est:estados){
				subEstados2=new ArrayList<Collection<Item>>();
				for(Vocabulary v: conjuntoTVT){//Simbolos de G'
					Collection<Item> ir = ir_a(G2,est,v);
					if(!ir.isEmpty() && !contiene(estados,ir))
						subEstados2.add(ir);
				}
				if(!subEstados2.isEmpty())
					subEstados.addAll(subEstados2);
			}
			if(!subEstados.isEmpty())
				estados.addAll(subEstados);
			if(size==estados.size())
				cambia=false;
		}
		
		return estados;
	}
	
	private boolean contiene(Collection<Collection<Item>> estados,Collection<Item> ir){
		
		for(Collection<Item> c:estados){//ir conparando los collecciones de la lista con el colecction a comparar
			ArrayList<Item> l1 = new ArrayList<Item>();
			for(Item p : c){l1.add(p);}
			ArrayList<Item> l2 = new ArrayList<Item>();
			for(Item p : ir){l2.add(p);}
			
			ArrayList<Vocabulary> cons;
			ArrayList<Vocabulary> cons2;
			if(c.size()==ir.size()){
				int i=0;
				for(i=0;i<l1.size();i++){
					if(l1.get(i).p.getAntecedente().getVocabulario().equals(l2.get(i).p.getAntecedente().getVocabulario())){//si antecedente son iguales miramos los consecuentes
						if(l1.get(i).p.getConsecuente().size()==l2.get(i).p.getConsecuente().size()){//si la longitud de los consecuentes son iguales
							for(int m=0;m<l1.get(i).p.getConsecuente().size();m++){
								cons=(ArrayList<Vocabulary>)l1.get(i).p.getConsecuente();
								cons2=(ArrayList<Vocabulary>)l2.get(i).p.getConsecuente();
								if(cons.get(m).getVocabulario().equals(cons2.get(m).getVocabulario())){
									
								}else{
									return false;//si falla la comparacion de un solo V del consecuente
								}
							}
						}else{
							break;
						}
					}else{
						break;//uno de los antecedentes no concuerdan
					}
				}
				if(i==l1.size()){
					//Miramos los símbolos anticipadores
					boolean salir=false;
					ArrayList<Terminals> i1;
					ArrayList<Terminals> i2;
					for(i=0;i<l1.size() && salir==false;i++){
						i1 = (ArrayList<Terminals>) l1.get(i).simbolosAnticipacion;
						i2 = (ArrayList<Terminals>) l2.get(i).simbolosAnticipacion;
						if(i1.size()!=i2.size())
							break;
						for(int h=0;h<i1.size();h++){
							if(!i1.get(h).getVocabulario().equals(i2.get(h).getVocabulario())){
								salir=true;
								break;
							}
								
						}
							
					}
					if(i==l1.size())
					return true;
				}
				//return false;//el conjunto de items ya existia
			}else{
				//teinen distintas longitudes
			}
		}
		return false;
			/*for(Collection<Productions>subEstado:estados)
					for(Productions p:ir)
						if(contiene(subEstado,p))
							return true;
		return false;*/
	}
	
	
	public Collection<Item> ir_a(Grammar G2,Collection<Item> I, Vocabulary v){
		Collection<Item> conjunto = new ArrayList<Item>();
		
			for(Item item:I){
				if(!puntoAlFinal(item.p)){
					Vocabulary sig= dameSiguienteDelPunto(item.p);
						if(sig.getVocabulario().equals(v.getVocabulario())){
							Item produccion = moverPunto(item);
							if(!conjunto.contains(produccion))
								conjunto.add(produccion);
						}
				}
				
			}
		for(Item item:cierre(G2,conjunto))
			if(!contiene(conjunto,item.p))
				conjunto.add(item);
		return conjunto;
	}
	
	private Item moverPunto(Item item){
		
		
		
		ArrayList<Vocabulary> listaItem = new ArrayList<Vocabulary>();
		listaItem.addAll( item.p.getConsecuente());
		int indice = listaItem.indexOf(G.getPunto());
		listaItem.set(indice, listaItem.get(indice+1));
		listaItem.set(indice+1, G.getPunto());
		Productions prd = new Productions(item.p.getAntecedente().getVocabulario(),listaItem);
		Item i = new Item();
		i.p=prd;
		for(Terminals t:item.simbolosAnticipacion)
			i.simbolosAnticipacion.add(t);
		return i;
		
	}
	/**
	 * 
	 * @param G2 es la gramatica G'
	 * @param I
	 */
	private Collection<Item> cierre(Grammar G2,Collection<Item> I){
		boolean cambia=false;
		//int tamaño;
		Collection<Item>Cierre = new ArrayList<Item>();
		Collection<Item> subConjunto;
		int size;
		Cierre.addAll(I);
		while(!cambia){
			subConjunto=new ArrayList<Item>();
			size=Cierre.size();
			for(Item item:Cierre){
				if(!puntoAlFinal(item.p)){
					Vocabulary v = dameSiguienteDelPunto(item.p);
					for(Productions p: G2.getP())
						if(p.getAntecedente().getVocabulario().equals(v.getVocabulario())){
							Item i = new Item();
							Productions produccion = copiarProduccionConPunto(p);
							i.p=produccion;
							if(!contiene(Cierre,produccion)){
								ArrayList<Vocabulary> subCadena = despuesDelPunto(item.p);
								Collection<Terminals> in = iniciales(G2,subCadena);
								if(in.contains(G.getLambda())){
									in.remove(G.getLambda());
									in.addAll(item.simbolosAnticipacion);
								}
								i.simbolosAnticipacion=in;
								subConjunto.add(i);
							}
								
						}
							
				}
			}
			Cierre.addAll(subConjunto);
			if(Cierre.size()==size)
				cambia=true;
		}
		return Cierre;
	}
	private ArrayList<Vocabulary> despuesDelPunto(Productions p){
		ArrayList<Vocabulary> l = (ArrayList<Vocabulary>) p.getConsecuente();
		int index = l.indexOf(G.getPunto());
		ArrayList<Vocabulary> sublista = new ArrayList<Vocabulary>();
		sublista.addAll( l.subList(index+1, l.size()-1) );
		return sublista; 
	}
	
	private boolean contiene(Collection<Item>d,Productions h){
		Collection<Productions> c = new ArrayList<Productions>();
		for(Item i:d)
			c.add(i.p);
		for(Productions x:c)
			if(x.getAntecedente().getVocabulario().equals(h.getAntecedente().getVocabulario()))
				if(x.getConsecuente().size()==h.getConsecuente().size()){
					boolean iguales=false;
					ArrayList<Vocabulary> listaConj=(ArrayList<Vocabulary>) x.getConsecuente();
					ArrayList<Vocabulary> listaProduccion=(ArrayList<Vocabulary>) h.getConsecuente();
					int i=0;
					for(i=0;i<listaConj.size();i++){
						if(! listaConj.get( i ).getVocabulario().equals( listaProduccion.get( i ).getVocabulario() ) )
							break;
						
					}
					if(i==h.getConsecuente().size())
						return true;
						
				}
		return false;
					
	}
	private Productions copiarProduccionConPunto(Productions p){
		Collection<Vocabulary> cons = new ArrayList<Vocabulary>();
		cons.add(G.getPunto());
		cons.addAll(p.getConsecuente());
		return new Productions(p.getAntecedente().getVocabulario(),cons);
	}
	private boolean puntoAlFinal(Productions p){
		ArrayList<Vocabulary> cons = (ArrayList<Vocabulary>) p.getConsecuente();
		if(cons.get(cons.size()-1).getVocabulario().equals(G.getPunto().getVocabulario()))
			return true;
		return false;
	}
	private Vocabulary dameSiguienteDelPunto(Productions it){
		ArrayList<Vocabulary> lista = (ArrayList<Vocabulary>) it.getConsecuente();
		int indicePunto = lista.indexOf(G.getPunto());
		return lista.get(indicePunto+1);
		
	}

	protected int analisisDecision(Vocabulary elem,Stack<Vocabulary> pila){
		int estado;
		if(elem instanceof NonTerminals)
			estado=Integer.parseInt(pila.get(pila.size()-2).getVocabulario());
		else
			estado = Integer.parseInt(pila.peek().getVocabulario());
		
		 int columna=0;
		 for(Vocabulary c:conjuntoTVT)
			 if(c.getVocabulario().equals(elem.getVocabulario())){
				 columna=conjuntoTVT.indexOf(c);
				 break;
			 }
		 return Tabla[estado][columna].accion(pila, prod, elem);
		/*for(Tabla t:tabla){
			if(t.fila==e && t.columna.getVocabulario().equals(elem.getVocabulario())){
				
				return t.op.accion(pila, producciones, elem);
			}
		}*/
		//return -5;
	}
}
