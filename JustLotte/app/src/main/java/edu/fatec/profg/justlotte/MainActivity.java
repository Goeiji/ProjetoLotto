package edu.fatec.profg.justlotte;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText input4;
    private EditText input5;

    private TextView result1;
    private TextView result2;
    private TextView result3;
    private TextView result4;
    private TextView result5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.choose_01);
        input2 = findViewById(R.id.choose_02);
        input3 = findViewById(R.id.choose_03);
        input4 = findViewById(R.id.choose_04);
        input5 = findViewById(R.id.choose_05);

        result1 = findViewById(R.id.extraction_01);
        result2 = findViewById(R.id.extraction_02);
        result3 = findViewById(R.id.extraction_03);
        result4 = findViewById(R.id.extraction_04);
        result5 = findViewById(R.id.extraction_05);
    }

    public void drawNumbers(View view) {
        String value1 = input1.getText().toString();
        String value2 = input2.getText().toString();
        String value3 = input3.getText().toString();
        String value4 = input4.getText().toString();
        String value5 = input5.getText().toString();

        Integer choose1 = Integer.parseInt(value1);
        Integer choose2 = Integer.parseInt(value2);
        Integer choose3 = Integer.parseInt(value3);
        Integer choose4 = Integer.parseInt(value4);
        Integer choose5 = Integer.parseInt(value5);

        /* if(choose1 == 0 || choose2 == 0 || choose3 == 0 || choose4 == 0 || choose5 == 0) {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
        } */
    }
}