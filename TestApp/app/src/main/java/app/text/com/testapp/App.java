package app.text.com.testapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.mparticle.MParticle;


/**
 * Created by Dell on 20/07/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();        
        MParticle.start(this);   
    }
}
