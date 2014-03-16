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

	private static Game instance = null;
	private static Tablero tablero;

	private int turnoDeJuego = 0;

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

		if (movimientoRealizado) {
			turnoDeJuego++;
		}
		
		return movimientoRealizado;
	}

	/**
	 * Inicia el tablero de juego
	 */
	private void initTablero() {

		tablero = Tablero.getInstance();

	}

	public Tablero getTablero() {
		return tablero;
	}
}
