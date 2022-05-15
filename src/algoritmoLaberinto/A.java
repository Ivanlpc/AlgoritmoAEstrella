package algoritmoLaberinto;

import java.util.ArrayList;
import java.util.List;
public class A {
	private Laberinto lb;
	private final Nodo nodoInicial;
	private final Nodo nodoFinal;
	private List<Nodo> nodosCerrados,nodosAbiertos, nodosExplorados,caminito;

	
public A () {
	this.lb=new Laberinto();
	this.nodoFinal=  lb.getEstadoFinal();
	this.nodoInicial= lb.getEstadoInicial();
	this.nodosCerrados=new ArrayList<>();
	this.nodosAbiertos=new ArrayList<>();
	this.nodosExplorados=new ArrayList<>();
	this.caminito=new ArrayList<>();
}

public List<Nodo> buscar() {
	nodosAbiertos.add(nodoInicial);

	while(!nodosAbiertos.isEmpty()) {
		
	Nodo analizar=menorF();	
	if(analizar.equals(nodoFinal)) {	
		System.out.println("FINAL DEL ALGORITMO");
		Nodo padre=analizar.getPadre();
		
		caminito.add(analizar);
		do {
			
			padre.setSymbol('+');
			//System.out.println("G: "+padre.getG()+" H: "+padre.getH()+" el padre es G: "+padre.getPadre().getG()+" H: "+padre.getPadre().getH());
			//System.out.println("Nodo inicial: "+nodoInicial.toString()+" nodo Final: "+nodoFinal.toString());
			caminito.add(padre);
			//System.out.println("Xd");
			padre=padre.getPadre();
		}while(!padre.equals(nodoInicial));
		return caminito;
				
		
	}
	nodosAbiertos.remove(analizar);
	nodosCerrados.add(analizar);
	List<Nodo> vecinos= getVecinos(analizar);
	for(Nodo n: vecinos) {
		if(nodosCerrados.contains(n)) {
			continue;
		}
		
		if(!nodosAbiertos.contains(n)) {
			nodosExplorados.add(n);
			n.setPadre(analizar);
			//n.setG(analizar.getG()+1);
			//n.setG(tentative_g);
			if(!nodosAbiertos.contains(n)) {
				nodosAbiertos.add(n);
				
			}
		}
	}
	}
	return caminito;
}
public int distEntreNodos(Nodo n1, Nodo n2) {
	return  Math.abs(n1.getX()-n2.getX())+Math.abs(n1.getY()-n2.getY());
}
public Laberinto actualizarMatriz(Laberinto lb){
	Nodo[][] laberinto=lb.getMatriz();
	laberinto[nodoInicial.getX()][nodoInicial.getY()].setSymbol('I');
	laberinto[nodoFinal.getX()][nodoFinal.getY()].setSymbol('O');
	for(Nodo n: nodosExplorados) {
		laberinto[n.getX()][n.getY()]=n;
	}
	return new Laberinto(laberinto);
		
}
public List<Nodo> getVecinos(Nodo n){
int fila=n.getY();
int columna=n.getX();
//System.out.println("Vecinos de "+fila+" "+columna);
List<Nodo> lista = new ArrayList<>();
//Posición abajo
if(fila+1<=79 && !lb.esObstaculo(fila+1, columna)) {
	Nodo vecino=lb.getNodo(fila+1, columna);
	//vecino.setPadre(n);
	vecino.setG(n.getG()+1);
	vecino.setH(vecino.calcularH(nodoFinal));
	lista.add(vecino);
	//System.out.println("X:"+vecino.getX()+" Y:"+vecino.getY());
}
//Posición arriba
if(fila-1>=0 && !lb.esObstaculo(fila-1, columna)) {
	Nodo vecino=lb.getNodo(fila-1, columna);
	vecino.setG(n.getG()+1);
	vecino.setH(vecino.calcularH(nodoFinal));
	//vecino.setPadre(n);
	lista.add(vecino);
	//System.out.println("X:"+vecino.getX()+" Y:"+vecino.getY());
}
//Posición derecha
if(columna+1<=59 && !lb.esObstaculo(fila, columna+1)) {
	Nodo vecino=lb.getNodo(fila, columna+1);
	vecino.setG(n.getG()+1);
	vecino.setH(vecino.calcularH(nodoFinal));
	//vecino.setPadre(n);
	lista.add(vecino);
	//System.out.println("X:"+vecino.getX()+" Y:"+vecino.getY());
}
//Posición izquierda
if(columna-1>=0 && !lb.esObstaculo(fila, columna-1)) {
	Nodo vecino=lb.getNodo(fila+1, columna-1);
	vecino.setG(n.getG()+1);
	vecino.setH(vecino.calcularH(nodoFinal));
	//vecino.setPadre(n);
	lista.add(vecino);
	//System.out.println("X:"+vecino.getX()+" Y:"+vecino.getY());
}
return lista;

}
public Nodo menorF() { 
	int f;
	Nodo respuesta=nodosAbiertos.iterator().next();
	int aux=respuesta.getG()+respuesta.getH();
	
	for(Nodo n : nodosAbiertos) {
		
		f=n.getG()+n.getH();
		if(f<aux) {
			aux=f;
			respuesta=n;
		}
		
	}
	
	return respuesta;
}
@Override
public String toString() {
	Laberinto matriz=actualizarMatriz(lb);
	
	return matriz.toString();
}
}
