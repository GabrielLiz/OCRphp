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
		        
		  //Instancia de gridview
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
                    bs.putInt("position",position);
                     imsagefullsize.putExtras(bs);
                     startActivity(imsagefullsize);

            }
        });

		 
		  
		/*  
		 * gvImages.setAdapter(new ImageAdapter(this));
		  gvImages.setOnItemClickListener (new OnItemClickListener() {
		
			  public void onItemClick(AdapterView<?> parent, View v, int pos, long id) 
			  {
				  ImageView imageView= (ImageView) findViewById(R.id.image1);
				  imageView.setImageResource(getItem[pos]);
			  }
		  });

		}

	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private int itemBackground;
		 
		public ImageAdapter(Context c)
		{
			context= c;
		}*

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
		*/
		
		
		
	}
		  
		  /*
		  
		  mostrarTipoFichero.setAction(android.content.Intent.ACTION_VIEW);
	       mostrarTipoFichero.setDataAndType(Uri.fromFile(ficheroSeleccionado), "image/jpg");
	       startActivityForResult(mostrarTipoFichero, 0);
	       
	       */
		  
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tercero, menu);
        return true;
    }
    
    
    private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	} 
	
    
	/*
	 * AsyncTask that will download the xml file for us and store it locally.
	 * After the download is done we'll parse the local file.
	 */
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

