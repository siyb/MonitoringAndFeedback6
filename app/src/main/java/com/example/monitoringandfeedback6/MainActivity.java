package com.example.monitoringandfeedback6;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monitoringandfeedback6.widgets.UglyButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UglyButton b = findViewById(R.id.uglyButton);
        b.setOnUglyButtonClickedListener(new UglyButton.OnUglyButtonClickedListener() {
            @Override
            public void onUglyButtonClicked(UglyButton uglyButton) {
                Toast
                        .makeText(MainActivity.this, R.string.clicked, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
