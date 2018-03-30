package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    TextView textView ;
    Button topRight ;
    Button topLeft;
    Button bottomRight ;
    Button bottomLeft;
    Button center ;
    Button nav;
    TextView textNrClick;

    int serviceStatus = 0;

    MyListener myListener = new MyListener();
    int nrClick = 0;

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            Button b = (Button) view;
            textView.setText(textView.getText().toString() + b.getText().toString());
            nrClick ++;
           textNrClick.setText(String.valueOf(nrClick));


           if(nrClick > 3 && serviceStatus == 0)
           {
               Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
               intent.putExtra("message", b.getText().toString());

                getApplicationContext().startService(intent);
                serviceStatus = 1;
           }
        }
    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                    String txtToSend = textView.getText().toString();
                    intent.putExtra("textTrimis", txtToSend);
                    startActivityForResult(intent, 1);

            }
        }


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        textView = (TextView)findViewById(R.id.textView);
        textNrClick = (TextView) findViewById(R.id.textView2);
        topRight = (Button) findViewById(R.id.buttonTopRight);
        topLeft = (Button)findViewById(R.id.buttonTopLeft);
        bottomRight = (Button) findViewById(R.id.buttonBottomRight);
        bottomLeft = (Button)findViewById(R.id.buttonBottomLeft);
        center = (Button) findViewById(R.id.buttonCenter);
        nav = (Button)findViewById(R.id.buttonNavigate);

        topLeft.setOnClickListener(myListener);
        topRight.setOnClickListener(myListener);
        bottomLeft.setOnClickListener(myListener);
        bottomRight.setOnClickListener(myListener);
        center.setOnClickListener(myListener);
        nav.setOnClickListener(buttonClickListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("nrClick", String.valueOf(nrClick));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("nrClick")) {
            textNrClick.setText(savedInstanceState.getString("nrClick"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
