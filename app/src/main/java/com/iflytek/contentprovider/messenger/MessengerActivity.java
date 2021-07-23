package com.iflytek.contentprovider.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.iflytek.contentprovider.R;

public class MessengerActivity extends AppCompatActivity {
    public static final int MSG_FROM_CLIENT = 2;
    public static final String KEY_SEND = "send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain();
            message.what = MSG_FROM_CLIENT;
            Bundle bundle = new Bundle();
            bundle.putString(KEY_SEND, "hello, this is client.");
            message.setData(bundle);
            //注意下面这句，
            message.replyTo = mMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Messenger mMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVER:
                    String replayInfo = msg.getData().getString(MessengerService.KEY_REPLAY);
                    Log.e("cyli8", "receiver from server:" + replayInfo);
                    break;
            }
            super.handleMessage(msg);
        }
    }

}
