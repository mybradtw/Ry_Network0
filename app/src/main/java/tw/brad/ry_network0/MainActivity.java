package tw.brad.ry_network0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cmgr;
    private MyNetworkReceiver myNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        myNetworkReceiver = new MyNetworkReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetworkReceiver, filter);
    }

    @Override
    public void finish() {
        unregisterReceiver(myNetworkReceiver);
        super.finish();
    }

    private boolean isConnectNetwork(){
        NetworkInfo info = cmgr.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnectedOrConnecting();
        return isConnected;
    }

    public void test1(View view) {
        Log.v("brad", "isConnected = " + isConnectNetwork());
    }

    private boolean isConnectWifi(){
        NetworkInfo info = cmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.isConnected();
    }

    private class MyNetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad", "isConnected = " + isConnectNetwork());
        }
    }

}
