package bott.app.game_2048_droid;

import bott.app.gameElements.Celda;
import bott.app.gameElements.Game;
import bott.app.gameElements.Tablero;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity implements OnGestureListener {

	public final String TAG = "MainAtivity";

	private Game game;
	private Tablero tablero;
	private static GridLayout tableroLayout;

	private static boolean captandoMovimiento = true;

	float[] history = new float[2];
	String[] direction = { "NONE", "NONE" };

	private GestureDetector gDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initApp();

		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				boolean movRealizado = false;
				// do{
				movRealizado = game.moverCeldas(Game.MOVER_ABAJO);
				game.generaNuevaCasilla();
				pintarTablero();
				// }while(movRealizado);

			}
		});

	}

	/**
	 * Inicializa todos los aspectos de la activity
	 */
	private void initApp() {

		initGame();
		initTablero();
		gDetector = new GestureDetector(getApplicationContext(),this);

	}

	/**
	 * Inicializa los aspectos internos del juego
	 */
	private void initGame() {
		game = Game.getInstance();
		game.initGame();
	}

	/**
	 * Inicia el apartado grafico del tablero
	 */
	private void initTablero() {
		tableroLayout = (GridLayout) findViewById(R.id.tableroLayout);
		// if(tableroLayout != null)
		// Log.i("as",tableroLayout.getChildCount() + "");

		tablero = game.getTablero();

		pintarTablero();
	}

	/**
	 * Pinta el estado actual del tablero
	 */
	private void pintarTablero() {
		if (tableroLayout != null) {
			for (int i = 0; i < Tablero.tamanioTablero; i++) {
				Celda c = tablero.getCasilla(i);
				tableroLayout.getChildAt(i).setBackground(
						this.getResources().getDrawable(c.getFondo()));
			}
		}
	}

	/**
	 * Mueve todas las celdas en la direccion indicada, hasta que no pueda mover
	 * ninguna
	 * 
	 * @param direccion
	 */
	private void moverCeldas(int direccion) {

		// Log.i(TAG, direccion +"");
		boolean movRealizado = false, newCasilla = false;
		do {
			movRealizado = game.moverCeldas(direccion);
			pintarTablero();
			if (movRealizado)
				newCasilla = true;
		} while (movRealizado);

		game.incrementarTurno();

		if (newCasilla) {
			game.generaNuevaCasilla();
			pintarTablero();
		}

		// game.getTablero().mostrarTablero();
		// Log.i(TAG, movRealizado+"");
		// game.getTablero().mostrarCasillasLibres();

		// FIXME Controlar esto. De esta forma no es optimo, debo controlarlo
		// antes de dejar mover de nuevo
		if (game.juegoPerdido() || game.juegoGanado())
			Log.i(TAG, "Juego ganado: " + game.juegoGanado()
					+ " Juego perdido: " + game.juegoPerdido());

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
		//Es necesario para utilizar el modo de reconocimiento de gestos
		return gDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX,
			float velocityY) {
		
		//FIXME No detecta bien el movimiento ABAJO
		Log.i(TAG, Math.abs(start.getX() - finish.getX()) +" " + Math.abs(start.getY() - finish.getY()));
		if (start.getRawY() < finish.getRawY() && Math.abs(start.getX() - finish.getX()) < 200
				&& Math.abs(start.getY() - finish.getY()) > 150) {
			Log.i(TAG, "abajo");
			moverCeldas(Game.MOVER_ABAJO);
		} 
		else if(start.getRawY() > finish.getRawY() && Math.abs(start.getX() - finish.getX()) < 200
				&& Math.abs(start.getY() - finish.getY()) > 150) {
			Log.i(TAG, "arriba");
			moverCeldas(Game.MOVER_ARRIBA);
		}
		else if(start.getRawX() < finish.getRawX() && Math.abs(start.getY() - finish.getY()) < 200){
			Log.i(TAG, "derecha");
			moverCeldas(Game.MOVER_DERECHA);
		}
		else if(start.getRawX() > finish.getRawX() && Math.abs(start.getY() - finish.getY()) < 200){
			Log.i(TAG, "izquierda");
			moverCeldas(Game.MOVER_IZQUIERDA);
		}
		
		return true;
	}
}
