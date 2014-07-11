package servidorphpuca.example.com.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class Tercero extends Activity {
	
	SitesAdapter mAdapter;
	GridView gvImages;
	

	public void onCreate(Bundle savedInstanceState) {
		
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_tercero);
		        

		  gvImages = (GridView) findViewById(R.id.gv_images);
		  
	      
		  //Comprueba el estado del internet 
		  if(isNetworkAvailable()){
				Log.i("StackSites", "starting download Task");
				SitesDownloadTask download = new SitesDownloadTask();
				//Ejecuta la tarea
				download.execute();
			}else{
				
			}

        gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.i("gol",mAdapter.getItem(position).toString());

                    Intent imsagefullsize= new Intent(getApplicationContext(), TerceroImageFullSize.class);
                    Bundle bs= new Bundle();
                    bs.putString("urls",mAdapter.getItem(position).toString());

                    imsagefullsize.putExtras(bs);
                    // este es un test :D
                    startActivity(imsagefullsize);

            }
        });

	}


	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tercero, menu);
        return true;
    }



    
    private boolean isNetworkAvailable() {

	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

	       return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	} 
	
    


	private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

        MiThread tr;
		@Override
		protected Void doInBackground(Void... arg0) {
			//Download the file
			try {
				tr =new MiThread();
				//Adaptador que usaremos en el gridview
				mAdapter = new SitesAdapter(Tercero.this, -1, tr.getImagesStack("http://webucaphp.esy.es/image.php"));

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
					gvImages.setAdapter(mAdapter);
		}
	}

}

