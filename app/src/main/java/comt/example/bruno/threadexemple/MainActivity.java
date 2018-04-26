package comt.example.bruno.threadexemple;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button botao;
    ProgressBar pb;
    WeakReference<ImageView> weakReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.button);
        image = findViewById(R.id.imageView);
        pb = findViewById(R.id.progressBar);
        weakReference = new WeakReference<ImageView>(image);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarAsyncTask("https://upload.wikimedia.org/wikipedia/pt/3/3b/Dark_Side_of_the_Moon.png",weakReference);
            }
        });
    }
    private void chamarAsyncTask(String url,WeakReference<ImageView> weakReference){
        LoadImage ld = new LoadImage(weakReference);
        ld.execute(url);
    }

}
