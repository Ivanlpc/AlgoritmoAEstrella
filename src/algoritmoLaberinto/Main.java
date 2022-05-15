package algoritmoLaberinto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
	public int filas=60;
	public int columnas=80;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		A algoritmo=new A();
		algoritmo.buscar();
		try {
		      File myObj = new File("salida.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("Archivo salida.txt creado");
		      } else {
		        System.out.println("El archivo ya existe.");
		      }
		    } catch (IOException e) {
		      System.out.println("Ocurrió un error.");
		      e.printStackTrace();
		    }
		try {
		      FileWriter salida = new FileWriter("salida.txt");
		      salida.write(algoritmo.toString());
		      System.out.println(algoritmo.toString());
		      salida.close();
		      System.out.println("Se ha escrito la salida en el archivo salida.txt.");
		    } catch (IOException e) {
		      System.out.println("Ocurrió un error.");
		      e.printStackTrace();
		    }

		
	}

}


