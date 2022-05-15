package algoritmoLaberinto;



public class Nodo{
private int h=0;
private int g;
private char symbol=' ';
private int x=0;
private int y=0;
private Nodo padre;
public Nodo(int x, int y) {
	this.x=x;
	this.y=y;
	
}
public Nodo() { //Estado inicial
	this.y=(int)(Math.random()*80-1);
	this.x=(int)(Math.random()*60-1);
	System.out.println("Nodo inicial establecido en X: "+x+" Y: "+y);
}
public Nodo(Nodo n) { //Estado final
	do {
		this.y=(int)(Math.random()*80-1);
		this.x=(int)(Math.random()*60-1);
		}while(this.x==n.getX() && this.y==n.getY());
	System.out.println("Nodo final establecido en X: "+x+" Y: "+y);
}
public int calcularH(Nodo n1) {
	return Math.abs(n1.getX()-this.x)+Math.abs(n1.getY()-this.y);

}
public void setX(int x) {
	this.x=x;
}
public void setY(int y) {
	this.y=y;
}
public void setG(int g) {
	this.g=g;
}
public void setH(int h) {
	this.h=h;
}
public Nodo getPadre() {
	return this.padre;
}
public char getSymbol() {
	return this.symbol;
}
public void setSymbol(char symbol) {
	this.symbol=symbol;
}
public void setPadre(Nodo padre) {
	this.padre = padre;
}
public int getH() {
	return h;
}
public int getG() {
	return g;
}
public int getX() {
	return this.x;
}
public int getY() {
	return this.y;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Nodo other = (Nodo) obj;
	if (x != other.getX() || y!= other.getY()) {
		return false;

	}
			
	return true;
}
@Override
public String toString() {
	return "X: "+this.x+" Y:"+this.y;
}
}

