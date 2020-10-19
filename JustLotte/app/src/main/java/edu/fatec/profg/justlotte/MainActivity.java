package edu.fatec.profg.justlotte;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
    private List<TextView> results;

    private TextView extractionTitle;
    private TextView resultTitle;
    private TextView totalResults;
    private TextView hitText;


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

        results = new ArrayList<TextView>();

        results.add(result1);
        results.add(result2);
        results.add(result3);
        results.add(result4);
        results.add(result5);

        extractionTitle = findViewById(R.id.title_02);
        resultTitle = findViewById(R.id.title_03);
        totalResults = findViewById(R.id.text_result);
        hitText = findViewById(R.id.title_04);
    }

    public List<Integer> generateSortedNumbers() {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 5;)
        {
            int rand = ((int)(Math.random() * 50)) + 1;
            if(!list.contains(rand))
            {
                list.add(rand);
                i++;
            }
        }

        Collections.sort(list);

        return list;
    }

    public void showNumbers(final List<Integer> numbers, final int index) {
        int numbersSize = numbers.size();
        Log.d("tag", "tamanho " + numbersSize);
        if (index < numbers.size()) {
            new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        TextView result = results.get(index);
                        result.setText("" + numbers.get(index));
                        result.setVisibility(View.VISIBLE);
                        showNumbers(numbers, index + 1);
                        return;
                    }
                }, 2000);
        }
    }

    public boolean existsRepeatedNumbers() {
        String value1 = input1.getText().toString();
        String value2 = input2.getText().toString();
        String value3 = input3.getText().toString();
        String value4 = input4.getText().toString();
        String value5 = input5.getText().toString();

        if (value1.equals(value2) || value1.equals(value3) || value1.equals(value4) || value1.equals(value5) ||
                value2.equals(value3) || value2.equals(value4) || value2.equals(value5) ||
                value3.equals(value4) || value3.equals(value5) ||
                value4.equals(value5)
        ) {
            return true;
        }

        return false;
    }

    public void drawNumbers(View view) {
        String value1 = input1.getText().toString();
        String value2 = input2.getText().toString();
        String value3 = input3.getText().toString();
        String value4 = input4.getText().toString();
        String value5 = input5.getText().toString();

        if (value1.equals("") || value2.equals("") || value3.equals("") || value4.equals("") || value5.equals("")) {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
            return;
        }

        if (this.existsRepeatedNumbers()) {
            Toast.makeText(this, R.string.repeated_numbers, Toast.LENGTH_LONG).show();
            return;
        }

        int choose1 = Integer.parseInt(value1);
        int choose2 = Integer.parseInt(value2);
        int choose3 = Integer.parseInt(value3);
        int choose4 = Integer.parseInt(value4);
        int choose5 = Integer.parseInt(value5);

        if (choose1 < 1 || choose1 > 50 || choose2 < 1 || choose2 > 50 || choose3 < 1 || choose3 > 50 || choose4 < 1 || choose4 > 50 || choose5 < 1 || choose5 > 50) {
            Toast.makeText(this, R.string.invalid_numbers, Toast.LENGTH_LONG).show();
            return;
        }

        // Continuar logica aqui
        List<Integer> numbers = this.generateSortedNumbers();

        // Mostrando bolas com delay entre cada uma
        this.showNumbers(numbers, 0);

        // Mostrando título da área de extração
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        extractionTitle.setVisibility(View.VISIBLE);
                    }
                }, 2000);

        // Mostrando area dos resultados depois da extração
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        resultTitle.setVisibility(View.VISIBLE);
                        totalResults.setVisibility(View.VISIBLE);
                        hitText.setVisibility(View.VISIBLE);
                    }
                }, 12000); // esperando mostrar todas as bolas
    }
}