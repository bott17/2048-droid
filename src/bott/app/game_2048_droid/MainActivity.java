package bott.app.game_2048_droid;

import bott.app.gameElements.Celda;
import bott.app.gameElements.Game;
import bott.app.gameElements.Tablero;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity {
	
	public final String TAG = "MainAtivity";
	
	private Game game;
	private Tablero tablero;
	private static GridLayout tableroLayout; 
	
	float [] history = new float[2];
    String [] direction = {"NONE","NONE"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initApp();
		
		Button b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean movRealizado= false;
				do{
					movRealizado = game.moverCeldas(Game.MOVER_ABAJO);
					pintarTablero();
					Log.i(TAG, "asda");
				}while(movRealizado);
				
			}
		});
		
	}
	
	/**
	 * Inicializa todos los aspectos de la activity
	 */
	private void initApp(){
		
		initGame();
		initTablero();
		
	}
	
	/**
	 * Inicializa los aspectos internos del juego
	 */
	private void initGame(){
		game = Game.getInstance();
		game.initGame();
	}

	/**
	 * Inicia el apartado grafico del tablero
	 */
	private void initTablero(){
		tableroLayout = (GridLayout)findViewById(R.id.tableroLayout);
		//if(tableroLayout != null)
			//Log.i("as",tableroLayout.getChildCount() + "");
		
		tablero = game.getTablero();
		
		pintarTablero();
	}
	
	/**
	 * Pinta el estado actual del tablero
	 */
	private void pintarTablero(){
		if(tableroLayout != null){
			for(int i = 0 ; i< Tablero.tamanioTablero; i++){
				Celda c= tablero.getCasilla(i);
				tableroLayout.getChildAt(i).setBackground(this.getResources().getDrawable(c.getFondo()));
			}
		}
	}
	
	/**
	 * Mueve todas las celdas en la direccion indicada, hasta que no pueda mover ninguna
	 * @param direccion
	 */
	private void moverCeldas(int direccion){
		
		Log.i(TAG, direccion +"");
		boolean movRealizado= false, newCasilla=false;
		do{
			movRealizado = game.moverCeldas(direccion);
			pintarTablero();
			if(movRealizado)
				newCasilla = true;
		}while(movRealizado);
		
		if(newCasilla){
			game.generaNuevaCasilla();
			pintarTablero();
		}
		
		game.getTablero().mostrarTablero();
		Log.i(TAG, movRealizado+"");
		game.getTablero().mostrarCasillasLibres();
		
		//FIXME Controlar esto. De esta forma no es optimo, debo controlarlo antes de dejar mover de nuevo
		if(game.juegoPerdido() || game.juegoGanado())
			Log.i(TAG, "Juego ganado: " + game.juegoGanado() + " Juego perdido: " + game.juegoPerdido());
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		float xChange = history[0] - event.getX();
        float yChange = history[1] - event.getY();
        history[0] = event.getX();
        history[1] = event.getY();

        
        if (xChange > 1.5 && (yChange > -2 && yChange < 2)){
        	moverCeldas(Game.MOVER_IZQUIERDA);
        }
        if (xChange < -1.5 && (yChange > -2 && yChange < 2)){
        	moverCeldas(Game.MOVER_DERECHA);
        }
        if (yChange > 1.5 && (xChange > -2 && xChange < 2)){
        	moverCeldas(Game.MOVER_ARRIBA);
        }
        if (yChange < -1.5 && (xChange > -2 && xChange < 2)){
        	moverCeldas(Game.MOVER_ABAJO);
        }
		
		return super.onTouchEvent(event);
	}
}
