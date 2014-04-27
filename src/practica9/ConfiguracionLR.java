package practica9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import practica8.Desplazamiento;
import practica8.Operacion;
import practica8.Reduccion;
import P1.P;
import base.Grammar;
import base.NonTerminals;
import base.Productions;
import base.Terminals;
import base.Vocabulary;

public class ConfiguracionLR {
	protected Grammar G;
	protected ArrayList<Vocabulary> conjuntoTVT;
	
	protected Operacion[][] Tabla;
	public ConfiguracionLR(Grammar g,ArrayList<Vocabulary> c){
		//G=g;
		//conjuntoTVT=c;
		NonTerminals E = new NonTerminals("E");
		NonTerminals T = new NonTerminals("T");
		NonTerminals F = new NonTerminals("F");
		NonTerminals E2 = new NonTerminals("E'");
		
		Terminals mas = new Terminals("+");
		Terminals por = new Terminals("*");
		Terminals id = new Terminals("id");
		Terminals abierto = new Terminals("(");
		Terminals cerrado = new Terminals(")");
		Terminals dolar = new Terminals("$");
		Terminals lambda = new Terminals("λ");
		Terminals punto = new Terminals(".");
		conjuntoTVT = new ArrayList<Vocabulary>();
		conjuntoTVT.add(E);
		conjuntoTVT.add(T);
		conjuntoTVT.add(F);
		conjuntoTVT.add(id);
		conjuntoTVT.add(mas);
		conjuntoTVT.add(por);
		conjuntoTVT.add(dolar);
		conjuntoTVT.add(abierto);
		conjuntoTVT.add(cerrado);
		
		Collection<Vocabulary> cp0 = new ArrayList<Vocabulary>();
		cp0.add(E);
		Productions p0 = new Productions("E'",cp0);
		Collection<Vocabulary> cp1 = new ArrayList<Vocabulary>();
		
		cp1.add(E);
		cp1.add(mas);
		cp1.add(T);
		Productions p1 = new Productions("E",cp1);
		
		Collection<Vocabulary> cp2 = new ArrayList<Vocabulary>();
		cp2.add(T);
		Productions p2 = new Productions("E",cp2);
		Collection<Vocabulary> cp3 = new ArrayList<Vocabulary>();
		
		cp3.add(T);
		cp3.add(por);
		cp3.add(F);
		Productions p3 = new Productions("T",cp3);
		
		Collection<Vocabulary> cp4 = new ArrayList<Vocabulary>();
		cp4.add(F);
		Productions p4 = new Productions("T",cp4);
Collection<Vocabulary> cp5 = new ArrayList<Vocabulary>();
		
		cp5.add(abierto);
		cp5.add(E);
		cp5.add(cerrado);
		Productions p5 = new Productions("F",cp5);
		
		Collection<Vocabulary> cp6 = new ArrayList<Vocabulary>();
		cp6.add(id);
		Productions p6 = new Productions("F",cp6);
		
		Collection<NonTerminals> nt = new HashSet<NonTerminals>();
		nt.add(E2);
		nt.add(E);
		nt.add(T);
		nt.add(F);
		
		Collection<Terminals> t = new HashSet<Terminals>();
		t.add(mas);
		t.add(por);
		t.add(id);
		t.add(abierto);
		t.add(cerrado);
		Collection<Productions> p = new ArrayList<Productions>();
		p.add(p0);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		
		//System.out.println(p.toString());
		Grammar gr = new Grammar(nt,t,p,E2);
		
		G=gr;
		Tabla=generarTabla(gr);
		imprimirTabla(Tabla);
	}
	public static void main(String[] args){
		
		ConfiguracionLR c = new ConfiguracionLR(null,null);
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
		 Collection<Productions> estados = new ArrayList<Productions>();
		 Collection<Vocabulary> cons= new ArrayList<Vocabulary>();
		 cons.add(G2.getPunto());
		 cons.add(G2.getS());
		 Operacion op;
		 Productions p = new Productions(G2.getS().getVocabulario(),cons);
		 estados.add(p);
		 Collection<Collection<Productions>> listaEstados = calculoDeConjuntos(G2,estados);
		 Operacion[][] tabla = new Operacion[listaEstados.size()][conjuntoTVT.size()];
		 int Nestado=-1;
		 for(Collection<Productions> estado:listaEstados){
			 Nestado++;
			 for(Vocabulary v:conjuntoTVT){
				 Collection<Productions> ir = ir_a (G2,estado,v);
				 
				 if(!ir.isEmpty()){
					 int index=orden(listaEstados,ir);
					 int indiceV=conjuntoTVT.indexOf(v);
					 if(v instanceof NonTerminals){
						 op=new Desplazamiento(null,index);
					 }else{
						 op=new Desplazamiento('D',index);
					 }
					 tabla[Nestado][indiceV]=op;
				 }
				 
			 }
			 //Buscamos los estados finales
			 for(Productions item: estado){
				 if(this.puntoAlFinal(item)){
					 if(item.getAntecedente().getVocabulario().equals(G2.getS().getVocabulario())){
						 int indiceV = conjuntoTVT.indexOf(G.getDolar());
						 op = new Desplazamiento(null,-1);
						 tabla[Nestado][indiceV]=op;
					 }else{
						 Collection<Terminals> col = siguientes(G2,item.getAntecedente());
						 for(Terminals nt: col){
							 int Nproduccion = buscarProduccionEnGramatica(G2,item);
							 int indiceV =conjuntoTVT.indexOf(nt);
							 op = new Reduccion('R',Nproduccion);
							 tabla[Nestado][indiceV]=op;
						 }
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
		Collection<Terminals> lista = new ArrayList<Terminals>();
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
		return lista;
		
	}
	private Collection<Terminals> iniciales(Grammar G2,Terminals t){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		conjunto.add(t);
		return conjunto;
	}
	
	private Collection<Terminals> iniciales(Grammar G2,NonTerminals nt){
		Set<Terminals> conjunto = new HashSet<Terminals>();
		for(Productions p : dameProducciones(G2,nt)){
			//System.out.println("Produccion: "+p.toString());
			conjunto.addAll(iniciales(G2,p.getConsecuente()));
		}
		return conjunto;
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
	
	private int orden(Collection<Collection<Productions>> lista,Collection<Productions> ir){
		/*
		int i=0;
		for(Collection<Productions> l:lista){
			for(Productions p:ir)
				if(contiene(l,p))
					return i;
			i++;
		}
			
		
		return -1;*/
		ArrayList<Vocabulary> cons = new ArrayList<Vocabulary>();
		ArrayList<Vocabulary> cons2 = new ArrayList<Vocabulary>();
		ArrayList<Productions> l2 = new ArrayList<Productions>();
		for(Productions p : ir){l2.add(p);}
		
		int orden=-1;
		for(Collection<Productions> c:lista){//ir conparando los collecciones de la lista con el colecction a comparar
			orden++;
			ArrayList<Productions> l1 = new ArrayList<Productions>();
			for(Productions p : c){l1.add(p);}
			
			
			
			if(c.size()==ir.size()){
				int i=0;
				for(i=0;i<l1.size();i++){
					if(l1.get(i).getAntecedente().getVocabulario().equals(l2.get(i).getAntecedente().getVocabulario())){
						//si antecedente son iguales miramos los consecuentes
						cons=(ArrayList<Vocabulary>)l1.get(i).getConsecuente();
						cons2=(ArrayList<Vocabulary>)l2.get(i).getConsecuente();
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
					return orden;
				}
				//return false;//el conjunto de items ya existia
			}else{
				//teinen distintas longitudes
			}
		}
		return -1;
	}

	public  Collection<Collection<Productions>> calculoDeConjuntos(Grammar G2,Collection<Productions> I){
		Collection<Collection<Productions>> estados=new ArrayList<Collection<Productions>>();
		Collection<Collection<Productions>> subEstados;
		Collection<Collection<Productions>> subEstados2;
		int size;
		Collection<Productions> E0 = cierre(G2,I);
		estados.add(E0);
		boolean cambia=true;
		while(cambia){
			size=estados.size();
			subEstados=new ArrayList<Collection<Productions>>();
			for(Collection<Productions> est:estados){
				subEstados2=new ArrayList<Collection<Productions>>();
				for(Vocabulary v: conjuntoTVT){//Simbolos de G'
					Collection<Productions> ir = ir_a(G2,est,v);
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
	
	private boolean contiene(Collection<Collection<Productions>> estados,Collection<Productions> ir){
		for(Collection<Productions> c:estados){//ir conparando los collecciones de la lista con el colecction a comparar
			ArrayList<Productions> l1 = new ArrayList<Productions>();
			for(Productions p : c){l1.add(p);}
			ArrayList<Productions> l2 = new ArrayList<Productions>();
			for(Productions p : ir){l2.add(p);}
			
			ArrayList<Vocabulary> cons;
			ArrayList<Vocabulary> cons2;
			if(c.size()==ir.size()){
				int i=0;
				for(i=0;i<l1.size();i++){
					if(l1.get(i).getAntecedente().getVocabulario().equals(l2.get(i).getAntecedente().getVocabulario())){//si antecedente son iguales miramos los consecuentes
						if(l1.get(i).getConsecuente().size()==l2.get(i).getConsecuente().size()){//si la longitud de los consecuentes son iguales
							for(int m=0;m<l1.get(i).getConsecuente().size();m++){
								cons=(ArrayList<Vocabulary>)l1.get(i).getConsecuente();
								cons2=(ArrayList<Vocabulary>)l2.get(i).getConsecuente();
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
	
	
	public Collection<Productions> ir_a(Grammar G2,Collection<Productions> I, Vocabulary v){
		Collection<Productions> conjunto = new ArrayList<Productions>();
		for(Productions item:I){
			if(!puntoAlFinal(item)){
				Vocabulary sig= dameSiguienteDelPunto(item);
				if(sig.getVocabulario().equals(v.getVocabulario())){
					Productions produccion = moverPunto(item);
					if(!conjunto.contains(produccion))
						conjunto.add(produccion);
				}
			}
				
		}
		for(Productions item:cierre(G2,conjunto))
			if(!contiene(conjunto,item))
				conjunto.add(item);
		return conjunto;
	}
	
	private Productions moverPunto(Productions item){
		
		
		ArrayList<Vocabulary> listaItem = new ArrayList<Vocabulary>();
		listaItem.addAll( item.getConsecuente());
		int indice = listaItem.indexOf(G.getPunto());
		listaItem.set(indice, listaItem.get(indice+1));
		listaItem.set(indice+1, G.getPunto());
		return new Productions(item.getAntecedente().getVocabulario(),listaItem);
		
	}
	/**
	 * 
	 * @param G2 es la gramatica G'
	 * @param I
	 */
	private Collection<Productions> cierre(Grammar G2,Collection<Productions> I){
		boolean cambia=false;
		//int tamaño;
		Collection<Productions>Cierre = new ArrayList<Productions>();
		Collection<Productions> subConjunto;
		int size;
		Cierre.addAll(I);
		while(!cambia){
			subConjunto=new ArrayList<Productions>();
			size=Cierre.size();
			for(Productions item:Cierre){
				if(!puntoAlFinal(item)){
					Vocabulary v = dameSiguienteDelPunto(item);
					for(Productions p: G2.getP())
						if(p.getAntecedente().getVocabulario().equals(v.getVocabulario())){
							Productions produccion = copiarProduccionConPunto(p);
							if(!contiene(Cierre,produccion))
								subConjunto.add(produccion);
						}
							
				}
			}
			Cierre.addAll(subConjunto);
			if(Cierre.size()==size)
				cambia=true;
		}
		return Cierre;
	}
	
	private boolean contiene(Collection<Productions>c,Productions h){
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


}
