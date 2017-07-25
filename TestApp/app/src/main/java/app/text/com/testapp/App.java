package app.text.com.testapp;

import android.app.Application;
import android.util.Log;

import com.mparticle.DeepLinkError;
import com.mparticle.DeepLinkListener;
import com.mparticle.DeepLinkResult;
import com.mparticle.MParticle;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MParticle.start(this);
        MParticle.getInstance().setDeepLinkListener(new DeepLinkListener() {
            @Override
            public void onResult(DeepLinkResult deepLinkResult) {
                Log.d("Link", deepLinkResult.getLink());
            }

            @Override
            public void onError(DeepLinkError deepLinkError) {
                Log.d("Link", "error");
            }
        });
    }
}
