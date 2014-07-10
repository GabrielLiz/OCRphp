package servidorphpuca.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void GuardarActivity (View v)
	{
		Intent guardar1= new Intent(this, Secundario.class);
		startActivity(guardar1);
		finish();
	}
	
	public void SacarActivity (View v)
	{
		Intent sacar1= new Intent(this, Tercero.class);
		startActivity(sacar1);
		finish();
	}
	
	public void DetectarActivity (View v)
	{
		Intent detectar1= new Intent(this, Cuarto.class);
		startActivity(detectar1);
		finish();
	}
	
	public void CerrarActivity (View v)
	{
		//Intent salida=new Intent( Intent.ACTION_MAIN); 
		//finish(); // La cerramos.
		System.exit(0);
	}

}
