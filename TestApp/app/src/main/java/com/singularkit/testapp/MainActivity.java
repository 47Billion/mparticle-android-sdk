package com.singularkit.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mparticle.MParticle;
import com.mparticle.commerce.Cart;
import com.mparticle.commerce.CommerceApi;
import com.mparticle.commerce.Product;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEvent(View view) {
        MParticle.getInstance().logEvent("TestEvent", MParticle.EventType.Other);
    }

    public void sendMapEvent(View view) {
        Map map = new HashMap<String, String>();
        map.put("Key1", "Value1");
        MParticle.getInstance().logEvent("TestEvent", MParticle.EventType.Other, map);
    }

    public void sendRevenueEvent(View view) {
        Cart cart = Cart.getInstance(this);
        cart.clear();
        Product.Builder builder = new Product.Builder("Product Name ", "SKU", 5.6);
        builder.category("Electronics");
        builder.quantity(10);
        cart.add(builder.build());
        CommerceApi commerceApi = MParticle.getInstance().Commerce();
        commerceApi.checkout();
    }

    public void setUserAttributes(View view) {
        MParticle.getInstance().setUserAttribute(MParticle.UserAttributes.AGE, "35");
    }

    public void checkForDeepLink(View view) {
        MParticle.getInstance().getKitManager().checkForDeepLink();
    }
}
