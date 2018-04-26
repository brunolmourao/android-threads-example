package comt.example.bruno.threadexemple;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class LoadImage extends AsyncTask<String,Void,Bitmap> {
    ProgressDialog load;
    WeakReference<ImageView> weakReference;
    LoadImage(WeakReference<ImageView> weakReference){
        this.weakReference = weakReference;
    }
    protected void OnPreExecute(){
        //load = ProgressDialog.show(MainActivity.this,"Baixando imagem");
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap imageBitmap = null;
        try {
            imageBitmap = DownloadImage.baixarImagem(params[0]);
        }catch (Exception e){

        }
        return imageBitmap;
    }
    protected void onPostExecute(Bitmap bitmap){
        ImageView image  = weakReference.get();
        if(bitmap!=null) {
            image.setImageBitmap(bitmap);
            Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
        }else{
            Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
        }
        Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
        load.dismiss();
    }
}

