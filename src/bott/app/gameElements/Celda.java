package bott.app.gameElements;

/**
 * Representa una casilla del tablero de juego.
 * Puede contener numeros potencias de 2, desde el 2 hasta el 2048
 * @author bott
 *
 */
public class Celda {

	private int numero=0;
	private int color=0;
	
	Celda (){
	}
	
	Celda(int numero){
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Numero: " + numero + " Color:" + color;
	}
}
