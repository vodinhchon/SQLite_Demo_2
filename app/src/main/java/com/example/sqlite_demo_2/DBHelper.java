package com.example.sqlite_demo_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "BookDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String author = "create table Authors(id_author integer primary key, name text, address text, email text)";
        db.execSQL(author);

        String book = "create table Books(id_book integer primary key, title text, " +
                "id_author integer constraint id_author references Authors(id_author) on delete cascade on update cascade)";
        db.execSQL(book);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String book = "drop table if exists Books";
        String author = "drop table if exists Authors";
        db.execSQL(book);
        db.execSQL(author);
        onCreate(db);
    }

//    //insert Author
//    public int insertAuthor(Author author) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id_author", author.getId_author());
//        contentValues.put("name", author.getName());
//        contentValues.put("address", author.getAddress());
//        contentValues.put("email", author.getEmail());
//        int result = (int) db.insert("Authors", null, contentValues);
//        db.close();
//        return result;
//    }
//
//    //get Author
//    public Author getAuthor(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select * from Authors where id_author = " + id;
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Author author = new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
//                cursor.getString(3));
//        cursor.close();
//        return author;
//    }
//
//    //get All Author
//    public ArrayList<Author> getALlAuthor() {
//        ArrayList<Author> list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select * from Authors";
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        while (cursor.isAfterLast() == false) {
//            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
//                    cursor.getString(3)));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
//
//    //delete Author
//    public boolean deleteAuthor(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db.delete("Authors", "id_author" + "= ?", new String[]{String.valueOf(id)}) > 0) {
//            db.close();
//            return true;
//        }
////        String query = "delete from Authors where id_author = " + id;
////        Cursor cursor = db.rawQuery(query, null);
////        if (cursor == null)
////            return false;
////        else {
////            cursor.moveToFirst();
////            cursor.close();
////        }
//        return false;
//    }
//
//    //update Book
//    public boolean updateAuthor(Author author) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", author.getName());
//        contentValues.put("address", author.getAddress());
//        contentValues.put("email", author.getEmail());
//        if (db.update("Authors", contentValues, "id_author" + "=?", new String[]{String.valueOf(author.getId_author())}) > 0) {
//            db.close();
//            return true;
//        }
//        return false;
//    }
//
//    //insert Book
//    public int insertBook(Book book) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id_book", book.getId_book());
//        contentValues.put("title", book.getTitle());
//        contentValues.put("id_author", book.getId_author());
//        int result = (int) db.insert("Books", null, contentValues);
//        db.close();
//        return result;
//    }
//
//    //get Book
//    public Book getBook(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select * from Books where id = " + id;
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
//        cursor.close();
//        return book;
//    }
//
//    //get All Book
//    public ArrayList<Book> getALlBook() {
//        ArrayList<Book> list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select * from Books";
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        while (cursor.isAfterLast() == false) {
//            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
//
//    //delete Book
//    public boolean deleteBook(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db.delete("Books", "id_book" + "= ?", new String[]{String.valueOf(id)}) > 0) {
//            db.close();
//            return true;
//        }
////        String query = "delete from Books where id_book = " + id;
////        Cursor cursor = db.rawQuery(query, null);
////        if (cursor == null)
////            return false;
////        else {
////            cursor.moveToFirst();
////            cursor.close();
////        }
//        return false;
//    }
//
//    //update Book
//    public boolean updateBook(Book book) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("title", book.getTitle());
//        contentValues.put("id_author", book.getId_author());
//        if (db.update("Books", contentValues, "id_book" + "=?", new String[]{String.valueOf(book.getId_book())}) > 0) {
//            db.close();
//            return true;
//        }
//        return false;
//    }
//
//    public ArrayList<String> getBookAuthor(int id) {
//        ArrayList<String> list = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sqlstr = "select Books.id_author, title from Authors inner join Books on Authors.id_author = Books.id_author " +
//                "where Authors.id_author = " + id;
//        Cursor cursor = db.rawQuery(sqlstr, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        while (cursor.isAfterLast() == false) {
//            list.add(cursor.getInt(0) + "");
//            list.add(cursor.getString(1));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }

}
