package servidorphpuca.example.com.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

 
public class SitesAdapter extends ArrayAdapter<String> implements FileNameGenerator {

	 ImageLoader imageLoader;
     static ImageLoader loader;
	DisplayImageOptions options;
    String name;
    Context xtc;
	//M�todo que toma una lista de String

	public SitesAdapter(Context ctx, int textViewResourceId, List<String> sites) {
		super(ctx, textViewResourceId, sites);
		
		//cacheDir configura una carpeta individual.
	//	File cacheDir = StorageUtils.getIndividualCacheDirectory(ctx);

        xtc=ctx;

	}

    public static  ImageLoader loader(){

                return loader;
            }
	
	//El m�todo getView se llama por cada imagen almacenada en el servidor
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		RelativeLayout row = (RelativeLayout)convertView;
		Log.i("StackSites", "getView pos = " + pos);
		if(null == row){
		
			LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = (RelativeLayout)inflater.inflate(R.layout.row_site, null);
		}
		
		//Se obtiene las referencias
		final ImageView iconImg = (ImageView)row.findViewById(R.id.iconImg);
		
		final ProgressBar indicator = (ProgressBar)row.findViewById(R.id.progress);
		
		//Si est� la imagen carg�ndose que sea visible la imagen de carga e invisible la imagen. 
		indicator.setVisibility(View.VISIBLE);
		iconImg.setVisibility(View.INVISIBLE);


		ImageLoadingListener listener = new ImageLoadingListener(){


			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				
			}

			
			//Cuando la imagen haya terminado de cargar, que el indicador est� invisible y la imagen est� visible.
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				indicator.setVisibility(View.INVISIBLE);
				iconImg.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				
			}
			
		};
        String []data = getItem(pos).split("/");
        name=data[data.length-1];

        if (imageLoader==null){


            imageLoader = ImageLoader.getInstance();
           // ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(xtc).diskCache(new UnlimitedDiscCache(cacheDir,null, this)).build();


            imageLoader.init(ImageLoaderConfiguration.createDefault(xtc));
            loader=imageLoader;
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_launcher).cacheOnDisk(true).cacheInMemory(false)
                    .build();

        }



        imageLoader.displayImage(getItem(pos), iconImg,options, listener);

		
		//Se descarga la imagen, se guarda en la cache y se muestra.

			return row;
				
				
	}

    @Override
    public String generate(String s) {
    return name;

    }
}
