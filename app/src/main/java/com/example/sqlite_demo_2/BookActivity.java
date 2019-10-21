package com.example.sqlite_demo_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    EditText editText_maso, editText_tieude, editText_masotacgia;
    Button button_select, button_save, button_update, button_delete, button_exit;
    GridView gridView_display;
    DBHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
        eventClickSelect();
        eventClickSave();
        eventClickUpdate();
        eventClickDelete();
        eventClickExit();
    }

    private void eventClickExit() {
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void eventClickSelect() {
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Book> list_book = new ArrayList<>();
                ArrayList<String> list_string = new ArrayList<>();
                list_book = dbHelper.getALlBook();
                for (Book book : list_book) {
                    list_string.add(book.getId_book() + "");
                    list_string.add(book.getTitle() + "");
                    list_string.add(book.getId_author() + "");
                }
                adapter = new ArrayAdapter<String>(BookActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView_display.setAdapter(adapter);
            }
        });
    }

    private void eventClickSave() {
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId_book(Integer.parseInt(editText_maso.getText().toString()));
                book.setTitle(editText_tieude.getText().toString());
                book.setId_author(Integer.parseInt(editText_masotacgia.getText().toString()));
                if (dbHelper.insertBook(book) > 0) {
                    clear();
                    Toast.makeText(getApplicationContext(), "Đã lưu thành công", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Lưu không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eventClickUpdate() {
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void eventClickDelete() {
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText_maso.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    dbHelper.deleteBook(idkq);
                    adapter.notifyDataSetChanged();
                    clear();
                    Toast.makeText(getApplicationContext(), "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clear() {
        editText_maso.setText("");
        editText_tieude.setText("");
        editText_masotacgia.setText("");
    }

    private void initView() {
        editText_maso = (EditText) findViewById(R.id.editText_maso);
        editText_tieude = (EditText) findViewById(R.id.editText_tieude);
        editText_masotacgia = (EditText) findViewById(R.id.editText_masotacgia);

        button_select = (Button) findViewById(R.id.button_select);
        button_save = (Button) findViewById(R.id.button_save);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_exit = (Button) findViewById(R.id.button_exit);

        gridView_display = (GridView) findViewById(R.id.gridView_display);
        dbHelper = new DBHelper(BookActivity.this);
    }
}
