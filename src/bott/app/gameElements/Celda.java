package bott.app.gameElements;

import bott.app.game_2048_droid.R;

/**
 * Representa una casilla del tablero de juego.
 * Puede contener numeros potencias de 2, desde el 2 hasta el 2048
 * @author bott
 *
 */
public class Celda {

	private int numero=0;
	private int color=0;
	private int fondo=R.drawable.gris2;
	
	private int ultimoTurnoSuma = 0;
	
	Celda (){
	}
	
	Celda(int numero){
		this.numero = numero;
		
		switch (numero) {
		case 2:
			fondo = R.drawable.ic_launcher;
			break;
		
		case 4:
			fondo = R.drawable.ic_launcher;
			break;
		
		case 8:
			
			break;
			
		case 16:
			
			break;
			
		case 32:
			
			break;
			
		case 64:
			
			break;
		
		case 128:
			
			break;
			
		case 256:
			
			break;
			
		case 512:
			
			break;
			
		case 1024:
			
			break;
			
		case 2048:
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * Indica si se trata de una celda vacia
	 * @return true si es una casilla vacia, false en caso contrario
	 */
	public boolean isNull(){
		if(numero == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Devuelve el id del fondo de la casilla seleccionada
	 * @return id del drawable del fondo
	 */
	public int getFondo(){
		return fondo;
	}
	
	/**
	 * Devuelve el valor de la casilla
	 * @return Valor de la casilla
	 */
	public int getValor(){
		return numero;
	}
	
	/**
	 * Devuelve el ultimo turno donde se sumo la celda
	 * @return
	 */
	public int getTurnoSuma(){
		return ultimoTurnoSuma;
	}
	
	/**
	 * Establece el ultimo turno donde se sumo la celda
	 * @param numero
	 */
	public void setTurnoSuma(int numero){
		ultimoTurnoSuma = numero;
	}

	@Override
	public String toString() {
		return "Numero: " + numero + " Color:" + color;
	}
}
