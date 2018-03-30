package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    TextView text;
    Button okButton;
    Button cancelButton;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.buttonC:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.buttonV:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        text = (TextView)findViewById(R.id.textViewSecond);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("textTrimis")) {
            String numberOfClicks = intent.getStringExtra("textTrimis");
            text.setText(numberOfClicks);
        }

        okButton = (Button)findViewById(R.id.buttonC);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.buttonV);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}
