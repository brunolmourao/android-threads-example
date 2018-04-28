package comt.example.bruno.threadexemple;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button botao;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.button);
        image = findViewById(R.id.imageView);
        pb = findViewById(R.id.progressBar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button:
                        pb.setVisibility(View.VISIBLE);
                        LoadImage loadImage = new LoadImage();
                        loadImage.execute("https://upload.wikimedia.org/wikipedia/pt/3/3b/Dark_Side_of_the_Moon.png");
                        break;
                }
            }
        });
    }
    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String[] params) {
            try {
                return DownloadImage.baixarImagem(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
            pb.setVisibility(View.INVISIBLE);
            Log.v("tag","Rodando após o back");
        }

    }
}




