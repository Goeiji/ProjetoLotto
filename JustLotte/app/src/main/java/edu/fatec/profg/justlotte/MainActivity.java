package edu.fatec.profg.justlotte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText input1;
    private EditText input2;
    private EditText input3;
    private EditText input4;
    private EditText input5;
    private List<EditText> inputs;

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

    private Button btnSort;

    private ImageView winImage;
    private MediaPlayer mp;

    private int countHits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.choose_01);
        input2 = findViewById(R.id.choose_02);
        input3 = findViewById(R.id.choose_03);
        input4 = findViewById(R.id.choose_04);
        input5 = findViewById(R.id.choose_05);

        inputs = new ArrayList<EditText>();

        inputs.add(input1);
        inputs.add(input2);
        inputs.add(input3);
        inputs.add(input4);
        inputs.add(input5);

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

        btnSort = findViewById(R.id.btn_sort);

        winImage = findViewById(R.id.win_image);
        // Cria o Media Player para tocar o som ao acertar todos os números
        mp = MediaPlayer.create(this, R.raw.win_sound);

        countHits = 0;
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

    public void showNumbers(final List<Integer> choices, final List<Integer> numbers, final int index) {
        int numbersSize = numbers.size();
        Log.d("tag", "tamanho " + numbersSize);
        if (index < numbers.size()) {
            new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        for (int i = 0; i < choices.size(); i++) {
                            if(choices.get(i) == numbers.get(index)) {
                                TextView result = results.get(index);
                                result.setBackgroundResource(R.drawable.ellipse1);
                                result.setTextColor(getResources().getColor(R.color.colorWhite));
                                result.setHintTextColor(getResources().getColor(R.color.colorWhite));

                                EditText input = inputs.get(i);
                                input.setBackgroundResource(R.drawable.ellipse1);
                                input.setTextColor(getResources().getColor(R.color.colorWhite));
                                input.setHintTextColor(getResources().getColor(R.color.colorWhite));

                                countHits++; // Contabiliza a quantidade de acertos
                            }
                        }

                        TextView result = results.get(index);
                        result.setText("" + numbers.get(index));
                        result.setVisibility(View.VISIBLE);
                        showNumbers(choices, numbers, index + 1);
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

    public void closeImage(View view) {
        ImageView img = (ImageView) view;
        img.setVisibility(View.GONE);
    }

    public void drawNumbers(final View view) {
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

        List<Integer> choices = new ArrayList<Integer>();

        choices.add(choose1);
        choices.add(choose2);
        choices.add(choose3);
        choices.add(choose4);
        choices.add(choose5);

        if (choose1 < 1 || choose1 > 50 || choose2 < 1 || choose2 > 50 || choose3 < 1 || choose3 > 50 || choose4 < 1 || choose4 > 50 || choose5 < 1 || choose5 > 50) {
            Toast.makeText(this, R.string.invalid_numbers, Toast.LENGTH_LONG).show();
            return;
        }

        List<Integer> numbers = this.generateSortedNumbers();

        // Mostrando bolas com delay entre cada uma
        this.showNumbers(choices, numbers, 0);

        // Mostrando título da área de extração
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        extractionTitle.setVisibility(View.VISIBLE);
                    }
                }, 2000);

        // this.showResultHits(choices, numbers);

        // Mostrando area dos resultados depois da extração
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // Alterar texto do Botão e sobrescrever função onClick para realizar o reset
                        btnSort.setText(R.string.btn_text2);
                        btnSort.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });

                        // Atribui a quantidade de acertos ao TextView e verifica se foram acertados todos os números
                        totalResults.setText("" + countHits);
                        if(countHits == 1) {
                            hitText.setText(R.string.title04_singular);
                        } else if (countHits == 5) {
                            winImage.setVisibility(View.VISIBLE); // Mostrar imagem de win
                            mp.start(); // Tocar áudio de win
                        }

                        resultTitle.setVisibility(View.VISIBLE);
                        totalResults.setVisibility(View.VISIBLE);
                        hitText.setVisibility(View.VISIBLE);
                    }
                }, 12000); // esperando mostrar todas as bolas
    }
}