package com.example.sqlite_demo_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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
    static final String URL = "content://author/authordata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        initView();
        eventClickSaveProvider();
        eventClickDeleteProvider();
        eventClickUpdateProvider();
        eventClickSelectProvider();
        eventClickExit();
    }

    private void eventClickSaveProvider() {
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maso = editText_maso.getText().toString();
                String hoten = editText_hoten.getText().toString();
                String diachi = editText_diachi.getText().toString();
                String email = editText_email.getText().toString();
                if (checkEmpty(maso, hoten, diachi, email)) {
                    ContentValues values = new ContentValues();
                    values.put("id_author", maso);
                    values.put("name", hoten);
                    values.put("address", diachi);
                    values.put("email", email);
                    Uri uri = Uri.parse(URL);
                    try {
                        getContentResolver().insert(uri, values);
                        clear();
                        selectItem(null, null);
                        Toast.makeText(getApplicationContext(), "Lưu thành công.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Lỗi ! Mã số đã tồn tại.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void eventClickDeleteProvider() {
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maso = editText_maso.getText().toString();
                if (!maso.isEmpty()) {
                    String[] id = {maso};
                    Uri uri = Uri.parse(URL);
                    int i = getContentResolver().delete(uri, "id_author=?", id);
                    if (i > 0) {
                        clear();
                        selectItem(null, null);
                        Toast.makeText(AuthorActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthorActivity.this, "Lỗi ! Mã số không chính xác.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AuthorActivity.this, "Hãy nhập mã số.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void eventClickUpdateProvider() {
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maso = editText_maso.getText().toString();
                String hoten = editText_hoten.getText().toString();
                String diachi = editText_diachi.getText().toString();
                String email = editText_email.getText().toString();
                if (checkEmpty(maso, hoten, diachi, email)) {
                    ContentValues values = new ContentValues();
                    values.put("id_author", maso);
                    values.put("name", hoten);
                    values.put("address", diachi);
                    values.put("email", email);
                    Uri uri = Uri.parse(URL);
                    int i = getContentResolver().update(uri, values, "id_author=?", new String[]{maso});
                    if (i > 0) {
                        clear();
                        selectItem(null, null);
                        Toast.makeText(AuthorActivity.this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthorActivity.this, "Lỗi ! Mã số không chính xác.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void eventClickSelectProvider() {
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maso = editText_maso.getText().toString();
                String[] id = {editText_maso.getText().toString()};
                if (!maso.isEmpty()) {
                    selectItem("id_author=?", id);
                } else {
                    selectItem(null, null);
                }
            }
        });
    }

    private void selectItem(String selection, String[] selectionArgs) {
        ArrayList<String> list_string = new ArrayList<>();
        Uri uri = Uri.parse(URL);
        try {
            Cursor cursor = getContentResolver().query(uri, null, selection, selectionArgs, "id_author");
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    list_string.add(cursor.getInt(0) + "");
                    list_string.add(cursor.getString(1) + "");
                    list_string.add(cursor.getString(2) + "");
                    list_string.add(cursor.getString(3) + "");
                } while (cursor.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AuthorActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView_display.setAdapter(adapter);
            } else {
                Toast.makeText(getApplicationContext(), "Không có kết quả !", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Không có kết quả !", Toast.LENGTH_SHORT).show();
        }
    }

    private void eventClickExit() {
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean checkEmpty(String id_author, String name, String address, String email) {
        if (id_author.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty()) {
            return false;
        }
        return true;
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
    }
}
