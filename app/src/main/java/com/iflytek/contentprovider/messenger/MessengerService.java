package com.iflytek.contentprovider.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    public static final int MSG_FROM_SERVER = 1;
    public static final String KEY_REPLAY = "replay";

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerActivity.MSG_FROM_CLIENT:
                    String sendInfo = msg.getData().getString(MessengerActivity.KEY_SEND);
                    Log.e("cyli8", "receive from client:" + sendInfo);
                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain();
                    message.what = MSG_FROM_SERVER;
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_REPLAY, "ok, i will replay you later");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private Messenger mMessenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}
