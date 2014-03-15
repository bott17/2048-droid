package bott.app.game_2048_droid;

import bott.app.gameElements.Celda;
import bott.app.gameElements.Tablero;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;

public class MainActivity extends Activity {
	
	private Tablero tablero;
	private static GridLayout tableroLayout; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initTablero();
		
	}

	/**
	 * Inicia el tablero de juego	
	 */
	private void initTablero(){
		tableroLayout = (GridLayout)findViewById(R.id.tableroLayout);
		if(tableroLayout != null)
			Log.i("as",tableroLayout.getChildCount() + "");
		
		Tablero.crateInstance();
		tablero = Tablero.getInstance();
		
		if(tableroLayout != null){
			for(int i = 0 ; i< Tablero.TamanoTablero; i++){
				Celda c= tablero.getCasilla(i);
				tableroLayout.getChildAt(i).setBackground(this.getResources().getDrawable(c.getFondo()));
			}
		}
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
}
