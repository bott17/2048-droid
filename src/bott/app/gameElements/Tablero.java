package bott.app.gameElements;

import java.util.Map;
import java.util.TreeMap;

import android.util.Log;

/**
 * Representa el tablero de juego. Consta de un tablero de 4x4 casillas
 * Se trata de un objeto unico en todo el juego.
 * @author bott
 */

public class Tablero {
	
	public static final int TamanoTablero = 16; //Tamaño el tablero, 4x4=16
	public static final String TAG = "ClaseTablero";
	
	private static Tablero instance = null;
	private TreeMap<Integer, Celda> mapa;
	
	private Tablero(){
		
		initTablero();
		//mostrarTablero();
	}
	
	
	/**
	 * Constructor publico del tablero. Solo permite crear uno para todo el juego.
	 */
	public static void crateInstance(){
		if(instance==null)
			instance = new Tablero();
	}
	
	/**
	 * Metodo para obtener el tablero de juego.
	 * @return
	 */
	public static Tablero getInstance(){
		return instance;
	}
	
	/**
	 * Inicializa los elementos del tablero.
	 * @brief Crea un tablero de tamaño adecuado con las casillas vacias.
	 */
	private void initTablero(){
		mapa = new TreeMap<Integer, Celda>();
		
		//Mete en cada posicion una nueva celda
		for(int i=0; i<TamanoTablero; i++)
			mapa.put(i, new Celda());
		
		//Casillas que se iniciaran con numero
		int casilla1 = (int)(Math.random()*TamanoTablero-1); 
		int casilla2;
		//Asegurarse de que no se repite la misma casilla
		do{
			casilla2 = (int)(Math.random()*TamanoTablero-1);
		}while(casilla2 == casilla1);
		
		//25% de posibilidades de generar un 4 en vez de un 2
		if((int)(Math.random()*100)<25){
			mapa.put(casilla1, new Celda(4));
		}
		else
			mapa.put(casilla1, new Celda(2));
		
		mapa.put(casilla2, new Celda(2)); //Introduce el segundo numero
		
	}
	
	/**
	 * Visualiza el contenido del tablero
	 */
	public void mostrarTablero(){
		for(Map.Entry<Integer, Celda> celda : mapa.entrySet()){
			Log.i(TAG, celda.toString()+"\n");
		}
	}
	
	public Celda getCasilla(int fila, int columna){
		return mapa.get(convertFilColToKey(fila, columna));
	}
	public Celda getCasilla(int nCasilla){
		return mapa.get(nCasilla);
	}
	
	
	/**
	 * Convierte un formato de filas y columnas basado en celda 11 a una key de identificacion dentro del mapa
	 * @param fil Fila donde se encuentra la celda, 0-13
	 * @param col Columna donde se encuentra la celda, 0-13
	 * @return
	 */
	private int convertFilColToKey(int fil, int col){
		return 4*fil + col;
	}

}
