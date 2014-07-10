package servidorphpuca.example.com.myapplication;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Cuarto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuarto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cuarto, menu);
		return true;
	}
	
	public void AtrasActivity (View v)
	{
		Intent atras1= new Intent(this,MainActivity.class);
		startActivity(atras1);
		finish();
	}

}
