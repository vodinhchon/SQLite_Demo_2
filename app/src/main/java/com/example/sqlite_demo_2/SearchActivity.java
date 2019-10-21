package com.example.sqlite_demo_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class SearchActivity extends AppCompatActivity {

    EditText editText_maso;
    Button button_find;
    GridView gridView_display;
    DBHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
