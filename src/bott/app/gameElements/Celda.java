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
			fondo = R.drawable.dos;
			break;
		
		case 4:
			fondo = R.drawable.cuatro;
			break;
		
		case 8:
			fondo = R.drawable.ocho;
			break;
			
		case 16:
			fondo = R.drawable.dieciseis;
			break;
			
		case 32:
			fondo = R.drawable.treintaydos;
			break;
			
		case 64:
			fondo = R.drawable.sesentaycuatro;
			break;
		
		case 128:
			fondo = R.drawable.cientoveintiocho;
			break;
			
		case 256:
			fondo = R.drawable.doscientoscincuentayseis;
			break;
			
		case 512:
			fondo = R.drawable.quinientosdoce;
			break;
			
		case 1024:
			fondo = R.drawable.milveinticuatro;
			break;
			
		case 2048:
			fondo = R.drawable.dosmilcuarentayocho;
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
