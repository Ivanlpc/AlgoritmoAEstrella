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
Nodo [][] matriz=new Nodo[60][80]; //60 filas 80 columnas
matriz[e1.getX()][e1.getY()]=e1;
matriz[e2.getX()][e2.getY()]=e2;
	for(int i=0;i<60;i++) {
		for(int j=0;j<80;j++) {
			Nodo n = new Nodo(i,j);
			n.setX(i);
			n.setY(j);
			int numero=(int) (Math.random()*10);
		if(numero>7 && !n.equals(e1) && !n.equals(e2)) {
			n.setSymbol('*');
			matriz[i][j]=n;
		}else if(!n.equals(e1) && !n.equals(e2)){
			n.setH(n.calcularH(e2));

			matriz[i][j]=n;
		}else {
			
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
	if(x<=59 && y<=79) {
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
    for (int x=0; x<60; x++) { 
        for (int y=0; y<80; y++) { 
        	
            salida += this.matriz[x][y].getSymbol(); 
        }
        salida += "\n"; 
    }
    return salida;
}
	
	}
