package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.sql.Date;
import java.util.Random;

public class PracticalTest01Var04Service extends Service {
    public PracticalTest01Var04Service() {
    }

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      String textToSend = "Service";
        processingThread = new ProcessingThread(this, textToSend);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}

class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

   private String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;

      this.text = text;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("action1");
        intent.putExtra("message", this.text);
        Log.d("[Message]","trimit");
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
