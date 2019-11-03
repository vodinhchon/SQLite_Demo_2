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

public class BookActivity extends AppCompatActivity {

    EditText editText_maso, editText_tieude, editText_masotacgia;
    Button button_select, button_save, button_update, button_delete, button_exit;
    GridView gridView_display;
    static final String URL = "content://book/bookdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
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
                String tieude = editText_tieude.getText().toString();
                String masotacgia = editText_masotacgia.getText().toString();
                if (checkEmpty(maso, tieude, masotacgia)) {
                    ContentValues values = new ContentValues();
                    values.put("id_book", maso);
                    values.put("title", tieude);
                    values.put("id_author", masotacgia);
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
                    int i = getContentResolver().delete(uri, "id_book=?", id);
                    if (i > 0) {
                        clear();
                        selectItem(null, null);
                        Toast.makeText(BookActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BookActivity.this, "Lỗi ! Mã số không chính xác.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BookActivity.this, "Hãy nhập mã số.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void eventClickUpdateProvider() {
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maso = editText_maso.getText().toString();
                String tieude = editText_tieude.getText().toString();
                String masotacgia = editText_masotacgia.getText().toString();
                if (checkEmpty(maso, tieude, masotacgia)) {
                    ContentValues values = new ContentValues();
                    values.put("id_book", maso);
                    values.put("title", tieude);
                    values.put("id_author", masotacgia);
                    Uri uri = Uri.parse(URL);
                    int i = getContentResolver().update(uri, values, "id_book=?", new String[]{maso});
                    if (i > 0) {
                        clear();
                        selectItem(null, null);
                        Toast.makeText(BookActivity.this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BookActivity.this, "Lỗi ! Mã số không chính xác.", Toast.LENGTH_SHORT).show();
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
                    selectItem("id_book=?", id);
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
            Cursor cursor = getContentResolver().query(uri, null, selection, selectionArgs, "id_book");
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    list_string.add(cursor.getInt(0) + "");
                    list_string.add(cursor.getString(1) + "");
                    list_string.add(cursor.getInt(2) + "");
                } while (cursor.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActivity.this,
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

    private boolean checkEmpty(String id_book, String title, String id_author) {
        if (id_book.isEmpty() || title.isEmpty() || id_author.isEmpty()) {
            return false;
        }
        return true;
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
    }
}
