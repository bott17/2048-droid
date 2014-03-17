package bott.app.gameElements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import android.util.Log;

/**
 * Representa el tablero de juego. Consta de un tablero de 4x4 casillas
 * Se trata de un objeto unico en todo el juego.
 * @author bott
 */

public class Tablero {
	
	public static final int tamanioTablero = 16; //Tamaño el tablero, 4x4=16
	public static final String TAG = "ClaseTablero";
	
	public static boolean casillaGanadora = false;
	
	private static Tablero instance = null;
	private  TreeMap<Integer, Celda> mapaCasillas;
	private ArrayList<Integer> indiceCasillasLibres;
	
	
	private Tablero(){
		
		initComponents();
//		for(int i=0; i<14; i++)
//			generarCasilla(true);
		mostrarTablero();
		
	}
	
	
	/**
	 * Constructor publico del tablero. Solo permite crear uno para todo el juego.
	 */
	public static void createInstance(){
		if(instance==null)
			instance = new Tablero();
	}
	
	/**
	 * Metodo para obtener el tablero unico de juego.
	 * @return
	 */
	public static Tablero getInstance(){
		createInstance();
		return instance;
	}
	
	/**
	 * Inicia todos los componentes del tablero
	 */
	private void initComponents(){
		//Inicia el indice de casillas libres y mete todas las casillas
		indiceCasillasLibres = new ArrayList<Integer>();
		for(int i=0; i<tamanioTablero; i++)
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
		if (getCasilla(posicion).getValor() == 0)
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
		for(int i=0; i<tamanioTablero; i++)
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
	
	public void mostrarCasillasLibres(){
		Collections.sort(indiceCasillasLibres);
		Log.i(TAG, indiceCasillasLibres.toString());
	}
	
	/**
	 * Genera de forma aleatoria una casilla con un 2 o un 4
	 * @brief la probabilidad de un 4 es del 25%
	 * @param aleatorio Indica si la casilla puede contener un 4 o un 2 (true) o solo un 2 (false)
	 */
	public void generarCasilla(boolean aleatorio){
		
		int casilla; 
		Log.i(TAG, "asdas");
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
	
	private void deleteCelda(int posicion){
		mapaCasillas.put(posicion, new Celda());
		indiceCasillasLibres.add(posicion);
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
	
	/**
	 * Desplaza todas las casillas una posicion a la izquierda, si es posible
	 * @return true si se pudo desplazar, false en caso contrario
	 */
	public boolean moverIzquierda(){
		
		boolean movimientoRealizado = false;
		
		//Recorre todas las casillas, de izquierda a derecha
		for(int i=1; i < tamanioTablero; i++){
			//Si no se trata de una casilla situada en la primera columna
			if(i%4 != 0){
				//No mueve casillas vacias
				if(!getCasilla(i).isNull()){
					//Si la celda de la izquierda esta vacia
					if(positionIsEmpty(i-1)){
						moverCelda(i, i-1, getCasilla(i));
						movimientoRealizado = true;
					}
					//TODO Si esta ocupada hay que comprobar si se pueden sumar
					else{
						boolean sumado = sumaCelda(i,i-1);
						if(sumado)
							movimientoRealizado = true;
					}
				}
			}
		}
		
		return movimientoRealizado;
	}
	
	/**
	 * Desplaza todas las casillas una posicion a la derecha, si es posible
	 * @return true si se pudo desplazar, false en caso contrario
	 */
	public boolean moverDerecha(){
		
		boolean movimientoRealizado = false;
		
		//Recorre todas las casillas, de derecha a izquierda
		for(int i=tamanioTablero-1; i >= 0; --i){
			//Si no se trata de una casilla situada en la ultima columna
			if(i%4 != 3){
				//No mueve casillas vacias
				if(!getCasilla(i).isNull()){
					//Si la celda de la derecha esta vacia
					if(positionIsEmpty(i+1)){
						moverCelda(i, i+1, getCasilla(i));
						movimientoRealizado = true;
					}
					//TODO Si esta ocupada hay que comprobar si se pueden sumar
					else{
						boolean sumado = sumaCelda(i,i+1);
						if(sumado)
							movimientoRealizado = true;
					}
				}
			}
		}
		
		return movimientoRealizado;
	}
	
	/**
	 * Desplaza todas las casillas una posicion hacia arriba, si es posible
	 * @return true si se pudo desplazar, false en caso contrario
	 */
	public boolean moverArriba(){
		
		boolean movimientoRealizado = false;
		
		//Recorre todas las casillas, de arriba a abajo
		for(int i=4; i < tamanioTablero; i++){
			//Si no se trata de una casilla situada en la primera fila
			if(i>3){
				//No mueve casillas vacias
				if(!getCasilla(i).isNull()){
					//Si la celda de encima esta vacia
					if(positionIsEmpty(i-4)){
						moverCelda(i, i-4, getCasilla(i));
						movimientoRealizado = true;
					}
					//TODO Si esta ocupada hay que comprobar si se pueden sumar
					else{
						boolean sumado = sumaCelda(i,i-4);
						if(sumado)
							movimientoRealizado = true;
					}
				}
			}
		}
		
		return movimientoRealizado;
	}
	
	/**
	 * Desplaza todas las casillas una posicion hacia abajo, si es posible
	 * @return true si se pudo desplazar, false en caso contrario
	 */
	public boolean moverAbajo(){
		
		boolean movimientoRealizado = false;
		
		//Recorre todas las casillas, de abajo arriba
		for(int i=tamanioTablero-4; i >= 0; --i){
			//Si no se trata de una casilla situada en la ultima fila
			if(i < 12){
				//No mueve casillas vacias
				if(!getCasilla(i).isNull()){
					//Si la celda de encima esta vacia
					if(positionIsEmpty(i+4)){
						moverCelda(i, i+4, getCasilla(i));
						movimientoRealizado = true;
					}
					//TODO Si esta ocupada hay que comprobar si se pueden sumar
					else{
						boolean sumado = sumaCelda(i,i+4);
						if(sumado)
							movimientoRealizado = true;
					}
				}
			}
		}
		
		return movimientoRealizado;
	}
	
	/**
	 * Mueve una celda de posicion.
	 * @exception Las posiciones deben ser correctas
	 * @param posInicial Donde se encuentra la celda a mover
	 * @param posFinal Donde se va a colocar
	 * @param celda Celda que se desea mover
	 */
	private void moverCelda(int posInicial, int posFinal, Celda celda){
//		mapaCasillas.put(posFinal, celda);
		rellenaCasilla(celda, posFinal);
		
		deleteCelda(posInicial);
	}
	
	private boolean sumaCelda(int posinicial, int posFinal){
		if(getCasilla(posinicial).getValor() == getCasilla(posFinal).getValor() 
				&& (getCasilla(posFinal).getTurnoSuma() < Game.getTurno())){
			
			Celda cel = new Celda(getCasilla(posinicial).getValor()*2);
			cel.setTurnoSuma(Game.getTurno());
			mapaCasillas.put(posFinal, cel);
			
			deleteCelda(posinicial);
			
			if(cel.getValor() == Game.valorParaGanar)
				casillaGanadora = true;
			
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Indica el numero de casillas libres en el tablero
	 * @return
	 */
	public int casillasLibres(){
		return indiceCasillasLibres.size();
	}
}
