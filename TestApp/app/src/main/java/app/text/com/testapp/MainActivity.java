package app.text.com.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.apsalar.sdk.Apsalar;
import com.mparticle.MParticle;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEvent(View view){
        MParticle.getInstance().logEvent("TestEvent", MParticle.EventType.Other);
        Set<Integer> kits = MParticle.getInstance().getKitManager().getSupportedKits();
        boolean status = Apsalar.event("Hello");
        Log.d("Apsalar"," Kit initialized "+status);

    }
}
