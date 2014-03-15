package bott.app.gameElements;

import bott.app.game_2048_droid.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

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
	 * Devuelve el id del fondo de la casilla seleccionada
	 * @return id del drawable del fondo
	 */
	public int getFondo(){
		return fondo;
	}

	@Override
	public String toString() {
		return "Numero: " + numero + " Color:" + color;
	}
}
