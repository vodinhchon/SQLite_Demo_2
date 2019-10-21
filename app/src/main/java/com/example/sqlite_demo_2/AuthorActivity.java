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

public class AuthorActivity extends AppCompatActivity {

    EditText editText_maso, editText_diachi, editText_email, editText_hoten;
    Button button_select, button_save, button_update, button_delete, button_exit;
    GridView gridView_display;
    DBHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
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
                ArrayList<Author> list_author = new ArrayList<>();
                ArrayList<String> list_string = new ArrayList<>();
                list_author = dbHelper.getALlAuthor();
                for (Author author : list_author) {
                    list_string.add(author.getId_author() + "");
                    list_string.add(author.getName() + "");
                    list_string.add(author.getAddress() + "");
                    list_string.add(author.getEmail() + "");
                }
                adapter = new ArrayAdapter<String>(AuthorActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView_display.setAdapter(adapter);
            }
        });
    }

    private void eventClickSave() {
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(editText_maso.getText().toString()));
                author.setName(editText_hoten.getText().toString());
                author.setAddress(editText_diachi.getText().toString());
                author.setEmail(editText_email.getText().toString());
                if (dbHelper.insertAuthor(author) > 0) {
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
                    dbHelper.deleteAuthor(idkq);
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
        editText_diachi.setText("");
        editText_email.setText("");
        editText_hoten.setText("");
    }

    private void initView() {
        editText_maso = (EditText) findViewById(R.id.editText_maso);
        editText_hoten = (EditText) findViewById(R.id.editText_hoten);
        editText_diachi = (EditText) findViewById(R.id.editText_diachi);
        editText_email = (EditText) findViewById(R.id.editText_email);

        button_select = (Button) findViewById(R.id.button_select);
        button_save = (Button) findViewById(R.id.button_save);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_exit = (Button) findViewById(R.id.button_exit);

        gridView_display = (GridView) findViewById(R.id.gridView_display);
        dbHelper = new DBHelper(AuthorActivity.this);
    }
}
