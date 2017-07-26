package com.mparticle.kits;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.apsalar.sdk.Apsalar;
import com.apsalar.sdk.ApsalarConfig;
import com.apsalar.sdk.DeferredDeepLinkHandler;
import com.mparticle.DeepLinkListener;
import com.mparticle.DeepLinkResult;
import com.mparticle.MPEvent;
import com.mparticle.MParticle;
import com.mparticle.internal.MPUtility;

import java.util.List;
import java.util.Map;

import static com.mparticle.internal.MPUtility.MessagingService.FCM;
import static com.mparticle.internal.MPUtility.MessagingService.GCM;

public class ApsalarKit extends KitIntegration implements KitIntegration.ActivityListener, KitIntegration.EventListener, KitIntegration.PushListener, DeferredDeepLinkHandler {

    private static final String API_KEY = "apiKey";
    private static final String API_SECRET = "secret";
    String apsalarKey;
    String apsalarSecret;
    ApsalarConfig config;
    Context context;

    @Override
    protected List<ReportingMessage> onKitCreate(Map<String, String> settings, Context context) {
        apsalarKey = settings.get(API_KEY);
        apsalarSecret = settings.get(API_SECRET);
        this.context = context;
        config = new ApsalarConfig(apsalarKey, apsalarSecret);
        config.withDDLTimeoutInSec(60);
        config.withDDLHandler(this);
        Apsalar.init(context, config);
        return null;
    }

    @Override
    public List<ReportingMessage> setOptOut(boolean b) {
        return null;
    }

    @Override
    public String getName() {
        return "Apsalar";
    }

    @Override
    public List<ReportingMessage> onActivityCreated(Activity activity, Bundle bundle) {
        return null;
    }

    @Override
    public List<ReportingMessage> onActivityStarted(Activity activity) {
        return null;
    }

    @Override
    public List<ReportingMessage> onActivityResumed(Activity activity) {
        Apsalar.onActivityResumed();
        return null;
    }

    @Override
    public List<ReportingMessage> onActivityPaused(Activity activity) {
        Apsalar.onActivityPaused();
        return null;
    }

    @Override
    public List<ReportingMessage> onActivityStopped(Activity activity) {
        return null;
    }

    @Override
    public List<ReportingMessage> onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        return null;
    }

    @Override
    public List<ReportingMessage> onActivityDestroyed(Activity activity) {
        return null;
    }

    @Override
    public List<ReportingMessage> leaveBreadcrumb(String s) {
        return null;
    }

    @Override
    public List<ReportingMessage> logError(String s, Map<String, String> map) {
        return null;
    }

    @Override
    public List<ReportingMessage> logException(Exception e, Map<String, String> map, String s) {
        return null;
    }

    @Override
    public List<ReportingMessage> logEvent(MPEvent mpEvent) {
        String eventName = mpEvent.getEventName();
        Map eventInfo = mpEvent.getInfo();
        Apsalar.event(eventName, eventInfo);
        return null;
    }

    @Override
    public List<ReportingMessage> logScreen(String s, Map<String, String> map) {
        return null;
    }

    @Override
    public void handleLink(String link) {
        DeepLinkListener deepLinkListener = MParticle.getInstance().getDeepLinkListener();
        if (deepLinkListener != null) {
            DeepLinkResult deepLinkResult = new DeepLinkResult();
            deepLinkResult.setServiceProviderId(MParticle.ServiceProviders.APSALAR);
            deepLinkResult.setLink(link);
            deepLinkListener.onResult(deepLinkResult);
        }
    }

    @Override
    public boolean willHandlePushMessage(Intent intent) {
        return false;
    }

    @Override
    public void onPushMessageReceived(Context context, Intent intent) {

    }

    @Override
    public boolean onPushRegistration(String deviceToken, String senderId) {
        switch (MPUtility.getAvailableInstanceId()) {
            case GCM:
                Apsalar.setGCMDeviceToken(deviceToken);
                break;
            case FCM:
                Apsalar.setFCMDeviceToken(deviceToken);
                break;
        }
        return true;
    }
}