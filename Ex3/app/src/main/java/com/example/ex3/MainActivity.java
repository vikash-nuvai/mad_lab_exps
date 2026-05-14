package com.example.ex3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText firstnum, secondnum;
    TextView result;
    Button add, sub, mul;

    double a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstnum = findViewById(R.id.first);
        secondnum = findViewById(R.id.second);

        add = findViewById(R.id.buttonadd);
        sub = findViewById(R.id.buttonsub);
        mul = findViewById(R.id.buttonmul);

        result = findViewById(R.id.result);

        add.setOnClickListener(v -> {
            a = Double.parseDouble(firstnum.getText().toString());
            b = Double.parseDouble(secondnum.getText().toString());
            c = a + b;
            result.setText("Sum = " + c);
        });

        sub.setOnClickListener(v -> {
            a = Double.parseDouble(firstnum.getText().toString());
            b = Double.parseDouble(secondnum.getText().toString());
            c = a - b;
            result.setText("Sub = " + c);
        });

        mul.setOnClickListener(v -> {
            a = Double.parseDouble(firstnum.getText().toString());
            b = Double.parseDouble(secondnum.getText().toString());
            c = a * b;
            result.setText("Mul = " + c);
        });
    }
}
