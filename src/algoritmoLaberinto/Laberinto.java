package algoritmoLaberinto;

public class Laberinto {
private Nodo [][] matriz=new Nodo[60][80];
private Nodo e1;
private Nodo e2;
public Laberinto(Nodo [][]matriz) {
	this.matriz=matriz;
}
public Laberinto() {
	this.matriz=generarLab();
}


private Nodo[][] generarLab(){
	this.e1=new Nodo(); //Nodo del estado inicial
	 this.e2=new Nodo(e1); //Nodo del estado final
	 e1.setSymbol('I');
	 e2.setSymbol('O');
	 e1.setH(e1.calcularH(e2)); //Calcula la H inicial
Nodo [][] matriz=new Nodo[Main.filas][Main.columnas]; //60 filas 80 columnas
matriz[e1.getX()][e1.getY()]=e1;
matriz[e2.getX()][e2.getY()]=e2;
	for(int i=0;i<Main.filas;i++) {
		for(int j=0;j<Main.columnas;j++) {
			Nodo n = new Nodo(i,j); //Crea el objeto Nodo y lo guarda dentro de la matriz
			n.setX(i);
			n.setY(j);
			int numero=(int) (Math.random()*10);
		if(numero>7 && !n.equals(e1) && !n.equals(e2)) {
			n.setSymbol('*'); //Si el objeto es un obstáculo tendrá ese símbolo
			matriz[i][j]=n;
		}else if(!n.equals(e1) && !n.equals(e2)){
			n.setH(n.calcularH(e2)); //Calcula el valor de la función heurística de cada nodo a la hora de crear el laberinto

			matriz[i][j]=n; //Guarda el nodo creado en la matriz,se repite el proceso con cada coordenada de la matriz
		}
				}
	}
		return matriz;
}
public Nodo[][] getMatriz(){
	return this.matriz;
}
public Nodo getNodo(int x, int y) {
	return matriz[y][x];
}
public boolean esObstaculo(int x, int y) {
	if(x<Main.filas && y<Main.columnas) {
	if(matriz[x][y].getSymbol()=='*') {
		return true;
	}else {
		return false;
	}
	}else {
		return true;
	}
}
public Nodo getEstadoInicial() {
	return this.e1;
}
public Nodo getEstadoFinal() {
	return this.e2;
}

public String toString() { 
    String salida = "";    
    for (int x=0; x<Main.filas; x++) { 
        for (int y=0; y<Main.columnas; y++) { 
        	
            salida += this.matriz[x][y].getSymbol(); 
        }
        salida += "\n"; 
    }
    return salida;
}
	
	}
