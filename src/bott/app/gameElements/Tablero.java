package bott.app.gameElements;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.util.Log;

/**
 * Representa el tablero de juego. Consta de un tablero de 4x4 casillas
 * Se trata de un objeto unico en todo el juego.
 * @author bott
 */

public class Tablero {
	
	public static final int TamanioTablero = 16; //Tamaño el tablero, 4x4=16
	public static final String TAG = "ClaseTablero";
	
	private static Tablero instance = null;
	private TreeMap<Integer, Celda> mapaCasillas;
	private ArrayList<Integer> indiceCasillasLibres;
	
	
	private Tablero(){
		
		initComponents();
//		for(int i=0; i<14; i++)
//			generarCasilla(true);
//		mostrarTablero();
		
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
	 * Inicia todos los componentes del tablero
	 */
	private void initComponents(){
		//Inicia el indice de casillas libres y mete todas las casillas
		indiceCasillasLibres = new ArrayList<Integer>();
		for(int i=0; i<TamanioTablero; i++)
			indiceCasillasLibres.add(i);
		
		initCasillas();
	}
	
	/**
	 * Rellena una casilla con una celda concreta
	 * @param celda
	 * @param posicion
	 * @return Indica si se pudo meter la celda en la posicion indicada
	 */
	private boolean rellenaCasilla(Celda celda, int posicion){
		
		if(positionIsEmpty(posicion)){
			mapaCasillas.put(posicion, celda);
			if(indiceCasillasLibres.contains(posicion)){
				indiceCasillasLibres.remove(indiceCasillasLibres.indexOf(posicion));
			}
			return true;
		}
		else
			return false;
		
	}
	
	/**
	 * Indica si la casilla esta libre
	 * @param posicion Posicion de la casilla
	 * @return True si esta libre o false en caso contrario
	 */
	private boolean positionIsEmpty(int posicion){
		if (mapaCasillas.get(posicion).getValor() == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Inicializa los elementos del tablero.
	 * @brief Crea un tablero de tamaño adecuado con las casillas vacias.
	 */
	private void initCasillas(){		
		
		mapaCasillas = new TreeMap<Integer, Celda>();
		
		//Mete en cada posicion una nueva celda
		for(int i=0; i<TamanioTablero; i++)
			mapaCasillas.put(i, new Celda());
		
		generarCasilla(false);
		
		generarCasilla(true);		
	}
	
	/**
	 * Visualiza el contenido del tablero
	 */
	public void mostrarTablero(){
		for(Map.Entry<Integer, Celda> celda : mapaCasillas.entrySet()){
			Log.i(TAG, celda.toString()+"\n");
		}
	}
	
	/**
	 * Genera de forma aleatoria una casilla con un 2 o un 4
	 * @brief la probabilidad de un 4 es del 25%
	 * @param aleatorio Indica si la casilla puede contener un 4 o un 2 (true) o solo un 2 (false)
	 */
	public void generarCasilla(boolean aleatorio){
		
		int casilla; 
		
		do{
			casilla = posicionLibreAleatoria();
		}while(!positionIsEmpty(casilla));
		
		if(aleatorio){
			if((int)(Math.random()*100)<25){
				rellenaCasilla(new Celda(4), casilla);
			}
			else
				rellenaCasilla(new Celda(2), casilla);
		}
		else
			rellenaCasilla(new Celda(2), casilla);
	}
	
	public Celda getCasilla(int fila, int columna){
		return mapaCasillas.get(convertFilColToKey(fila, columna));
	}
	/**
	 * Devuelve la casilla que esta en la posicion indicada
	 * @param nCasilla Posicion de la casilla
	 * @return Casilla correspondiente a esa posicion
	 */
	public Celda getCasilla(int nCasilla){
		return mapaCasillas.get(nCasilla);
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
	
	/**
	 * Genera aleatoriamente una posicion, correspondiente a una casilla libre
	 * @return Posicion aleatoria de la casilla libre
	 */
	private int posicionLibreAleatoria(){
		int casilla = (int)(Math.random()*100)%indiceCasillasLibres.size();
		
		return indiceCasillasLibres.get(casilla);
	}
	
	public boolean moverIzquierda(){
		
		return false;
	}

}
