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
        try {
            MParticle.start(this);
        }catch(Exception e){
            Log.d("Apsalar",e.getMessage());
        }

        //ApsalarConfig config = new ApsalarConfig("animesh1", "F4YEQB1T");
        //config.withLoggingEnabled();
       // Apsalar.init(getApplicationContext(), config);

    }
}
