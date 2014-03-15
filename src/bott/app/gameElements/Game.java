package bott.app.gameElements;

/**
 * Clase que agrupa las funciones y reglas del juego
 * @author bott
 *
 */
public class Game {
	
	private Tablero tablero;
	
	private int turnoDeJuego = 0;
	
	public void initGame(){
		
	}

	/**
	 * Movimiento direccional de las celdas del juego
	 * @param direccion
	 */
	public void moverCeldas(String direccion){
		
		tablero = Tablero.getInstance();
		
		if(tablero.moverIzquierda()){
			turnoDeJuego++;
		}
	}
}
