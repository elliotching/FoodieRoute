package fcsit.foodieroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by elliotching on 28-Jun-16.
 */
public class SplashScreen extends AppCompatActivity {

    public void onCreate(Bundle ins) {
        super.onCreate(ins);
        finish();
        startActivity(new Intent(this, Makan_main.class));
    }
}
