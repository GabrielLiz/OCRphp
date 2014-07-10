package servidorphpuca.example.com.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


public class TerceroImageFullSize extends Activity{
    ImageLoader loader;
    ImageView imageVi;
    String url=null;


    DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full_size);
        imageVi =(ImageView)findViewById(R.id.imagefullsize);


        loader = SitesAdapter.loader();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {


            url=extras.getString("urls");
        }

        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher).cacheOnDisk(true).cacheInMemory(false)
                .build();



        loader.displayImage(url,imageVi,options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                Log.i("gol","FAILED!!!)");
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    Log.i("gol","complete");
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                Log.i("gol","Cancell");
            }

        });





    }
}
