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
	nodosAbiertos.add(nodoInicial); //Agrega el nodo inicial a la lista de nodos abiertos
	while(!nodosAbiertos.isEmpty()) {
	Nodo analizar=menorF();	
	if(analizar.equals(nodoFinal)) {	
		System.out.println("FINAL DEL ALGORITMO");
		Nodo padre=analizar.getPadre();
		caminito.add(analizar);
		do {
			padre.setSymbol('+'); //Le establece el simbolo de la suma a cada nodo del camino
			caminito.add(padre); 
			padre=padre.getPadre(); //Hace el camino a la inversa, empieza desde el nodo final y accede al padre de ese nodo hasta llegar al inicial y lo va guardando en un ArrayList
		}while(!padre.equals(nodoInicial)); //Mientras no haya llegado al nodo inicial, sigue buscando el padre de cada nodo hasta llegar
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

public Laberinto actualizarMatriz(Laberinto lb){
	Nodo[][] laberinto=lb.getMatriz(); //Guarda en un objeto el laberinto generado al principio
	laberinto[nodoInicial.getX()][nodoInicial.getY()].setSymbol('I'); //Al guardar el camino y establecerle el símbolo +, se ha sobreescrito el símbolo de los nodos inicial y final
	laberinto[nodoFinal.getX()][nodoFinal.getY()].setSymbol('O');
	for(Nodo n: nodosExplorados) { 
		laberinto[n.getX()][n.getY()]=n; //Actualiza el laberinto con los nuevos nodos que contienen el camino
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
	Nodo respuesta=nodosAbiertos.iterator().next(); //Coge el primer nodo de la lista de nodos abiertos
	int aux=respuesta.getG()+respuesta.getH(); //Calcula la F del primer nodo de la lista anterior
	for(Nodo n : nodosAbiertos) { //Evalúa cada nodo de la lista de nodos Abiertos
		
		f=n.getG()+n.getH(); 
		if(f<aux) { //Si encuentra una F inferior a la del nodo anteriormente evaluado la guarda en la variable aux y sigue comparando
			aux=f;
			respuesta=n; //guarda el nodo con menor f encontrada
		}
		
	}
	
	return respuesta;
}
@Override
public String toString() {
	Laberinto matriz=actualizarMatriz(lb); 
	
	return matriz.toString(); //Objeto laberinto toString();
}
}
