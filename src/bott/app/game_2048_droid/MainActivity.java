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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener {

	public final String TAG = "MainAtivity";

	private Game game;
	private Tablero tablero;
	private static GridLayout tableroLayout;

	float[] history = new float[2];
	String[] direction = { "NONE", "NONE" };
	TextView finalScore;

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

				resetGame();

			}
		});

	}

	/**
	 * Inicializa todos los aspectos de la activity
	 */
	private void initApp() {
		
		finalScore = (TextView)findViewById(R.id.tvScore);
		
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
	 * Reset the game, set score and re-paint
	 */
	private void resetGame(){
		game = Game.resetGame();
		tablero = game.getTablero();
		finalScore.setText("0");
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
		
		//TODO Update the score		
		int score = game.changeTurn();
		if(score != -1){
			updateScore(score);
		}

		if (newCasilla) {
			game.generaNuevaCasilla();
			pintarTablero();
		}

		comprobarJuego();

	}
	
	private void comprobarJuego(){
		
		if(game.juegoGanado())
			Toast.makeText(getApplicationContext(), "WIN :D", Toast.LENGTH_SHORT).show();
		else if(game.juegoPerdido())
			Toast.makeText(getApplicationContext(), "LOOOOOSER :S", Toast.LENGTH_SHORT).show();
		
	}
	
	/**
	 * Update the actual score adding a new score
	 * @param score Value who update the actual score
	 */
	private void updateScore(int score){
		int actualScore = Integer.parseInt(finalScore.getText().toString());
		actualScore += score;
		
		finalScore.setText(actualScore+"");
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
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float velocityX,
			float velocityY) {
		
//		Log.i(TAG, "X inicial: " + start.getRawX() + " X inicial: " + finish.getRawX() + " Y inicial: " +
//				start.getRawY() + " Y final: " + finish.getRawY());
		
		//Diferencia el movimiento del eje X
		if(Math.abs(start.getRawX()- finish.getRawX()) > Math.abs(start.getRawY()- finish.getRawY())){
//			Log.i(TAG, "eje x");
			if(start.getRawX() > finish.getRawX()){
//				Log.i(TAG, "izquierda");
				moverCeldas(Game.MOVER_IZQUIERDA);
			}
			else{
//				Log.i(TAG, "derecha");
				moverCeldas(Game.MOVER_DERECHA);
			}
		}
		//Diferencia el movimiento del ejeY
		if(Math.abs(start.getRawX()- finish.getRawX()) < Math.abs(start.getRawY()- finish.getRawY())){
//			Log.i(TAG, "eje y");
			if(start.getRawY() > finish.getRawY()){
//				Log.i(TAG, "arriba");
				moverCeldas(Game.MOVER_ARRIBA);
			}
			else{
//				Log.i(TAG, "abajo");
				moverCeldas(Game.MOVER_ABAJO);
			}
		}
		return true;
	}
}
