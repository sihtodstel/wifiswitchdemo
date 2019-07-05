package com.example.wifiswitchdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.MacAddress;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button search;
    private WifiManager wifiManager;
    private Boolean isOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=findViewById(R.id.searchwifi);
        search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                    wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.getScanResults();
                    wifiManager.getConnectionInfo();
                final NetworkSpecifier specifier = new WifiNetworkSpecifier.Builder()
                        .setSsidPattern(new PatternMatcher("test", PatternMatcher.PATTERN_PREFIX))
                        .setBssidPattern(MacAddress.fromString("10:03:23:00:00:00"),
                                MacAddress.fromString("ff:ff:ff:00:00:00"))
                        .build();
                final NetworkRequest request =new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .setNetworkSpecifier(specifier) .build();
                final ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback();
                    Intent searchwifi = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(searchwifi);

            }
        });
    }
}
