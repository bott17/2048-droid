package bott.app.gameElements;

import android.util.Log;

/**
 * Clase que agrupa las funciones y reglas del juego
 * 
 * @author bott
 * 
 */
public class Game {

	public static final String TAG = "Game";
	public static final int MOVER_ARRIBA = 0;
	public static final int MOVER_ABAJO = 1;
	public static final int MOVER_IZQUIERDA = 2;
	public static final int MOVER_DERECHA = 3;
	
	public static final int valorParaGanar = 2048;

	private static Game instance = null;
	private static Tablero tablero;

	private static int turnoDeJuego = 1;
	private static int ultimaSuma = 0;

	
	private static void createInstance() {
		if (instance == null)
			instance = new Game();
	}

	/**
	 * Metodo para obtener el tablero de juego.
	 * 
	 * @return
	 */
	public static Game getInstance() {
		createInstance();
		return instance;
	}

	/**
	 * Inicializa el juego
	 */
	public Game initGame() {

		initTablero();

		return getInstance();

	}
	
	/**
	 * Reset the game
	 */
	public static Game resetGame(){
		instance = new Game();
		tablero = Tablero.resetTablero();
		
		return instance;
	}
	
	/**
	 * Change the turn of game, and getting the score of the last turn
	 * @return Score obtain in the last turn. -1 if no new score
	 */
	public int changeTurn(){
		
		turnoDeJuego++;
		
		if(ultimaSuma != 0){
			int temp = ultimaSuma;
			ultimaSuma = 0;
			return temp;
		}
		else
			return -1;
	}

	/**
	 * Movimiento direccional de las celdas del juego
	 * 
	 * @param direccion
	 */
	public boolean moverCeldas(int direccion) {
		boolean movimientoRealizado = false;
		if (tablero != null) {
			//do {
				switch (direccion) {
				case MOVER_ARRIBA:
					movimientoRealizado = tablero.moverArriba();
//					Log.i(TAG, movimientoRealizado + " Arriba");
					break;

				case MOVER_ABAJO:
					movimientoRealizado= tablero.moverAbajo();
//					Log.i(TAG, movimientoRealizado + " Abajo");
					break;

				case MOVER_IZQUIERDA:
					movimientoRealizado= tablero.moverIzquierda();
//					Log.i(TAG, movimientoRealizado + " Izquierda");
					break;

				case MOVER_DERECHA:
					movimientoRealizado= tablero.moverDerecha();
//					Log.i(TAG, movimientoRealizado + " Derecha");
					break;

				default:
					break;
				}
//			} while (movimientoRealizado);
		} else
			Log.i(TAG, "Tablero no inicializado");

		/*
		if (movimientoRealizado) {
			turnoDeJuego++;
		}
		*/
		
		return movimientoRealizado;
	}

	/**
	 * Inicia el tablero de juego
	 */
	private void initTablero() {

		tablero = Tablero.getInstance();

	}
	
	/**
	 * Devuelve el tablero de juego
	 * @return
	 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Devuelve el turno de juego
	 * @return
	 */
	public static int getTurno(){
		return turnoDeJuego;
	}
	
	/**
	 * Genera una nueva casilla en una posicion aleatoria
	 */
	public void generaNuevaCasilla(){
		tablero.generarCasilla(true);
	}
	
	/**
	 * Indica si se ha ganado el juego
	 * @return
	 */
	public boolean juegoGanado(){
		return tablero.casillaGanadora;
	}
	
	/**
	 * Indica si se ha perdido el juego
	 * @return
	 */
	public boolean juegoPerdido(){
		if(tablero.casillasLibres() <= 0 && !tablero.conMovimientos())
			return true;
		else
			return false;
	}
	
	public static void updateSuma(int turno, int suma){
		
		if(turno == turnoDeJuego){
			ultimaSuma += suma;
		}
		else
			ultimaSuma = suma;
	}
	
}
