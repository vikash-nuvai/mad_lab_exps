package com.example.ex7;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText itemInput;
    Button addBtn;
    RecyclerView recyclerView;

    ArrayList<String> itemList;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemInput = findViewById(R.id.itemInput);
        addBtn = findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.recyclerView);

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(v -> {
            String item = itemInput.getText().toString();

            if (!item.isEmpty()) {
                itemList.add(item);
                adapter.notifyDataSetChanged();
                itemInput.setText("");
            }
        });
    }
}