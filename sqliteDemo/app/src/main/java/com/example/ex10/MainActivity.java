package com.example.ex10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Rollno, Name, Marks;
    Button Insert, Delete, Update, View, ViewAll;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rollno = findViewById(R.id.Rollno);
        Name = findViewById(R.id.Name);
        Marks = findViewById(R.id.Marks);

        Insert = findViewById(R.id.Insert);
        Delete = findViewById(R.id.Delete);
        Update = findViewById(R.id.Update);
        View = findViewById(R.id.View);
        ViewAll = findViewById(R.id.all);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        // Create DB and table
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS student(" +
                "rollno TEXT PRIMARY KEY, " +
                "name TEXT, " +
                "marks TEXT)");
    }

    @Override
    public void onClick(View view) {

        if (view == Insert) {
            if (Rollno.getText().toString().isEmpty() ||
                    Name.getText().toString().isEmpty() ||
                    Marks.getText().toString().isEmpty()) {
                showMessage("Error", "Please enter all values");
                return;
            }

            db.execSQL("INSERT INTO student VALUES('" +
                    Rollno.getText() + "','" +
                    Name.getText() + "','" +
                    Marks.getText() + "')");

            showMessage("Success", "Record added");
            clearText();
        }

        if (view == Delete) {
            if (Rollno.getText().toString().isEmpty()) {
                showMessage("Error", "Enter Rollno");
                return;
            }

            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" +
                    Rollno.getText() + "'", null);

            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM student WHERE rollno='" +
                        Rollno.getText() + "'");
                showMessage("Success", "Deleted");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            c.close();
            clearText();
        }

        if (view == Update) {
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" +
                    Rollno.getText() + "'", null);

            if (c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" +
                        Name.getText() +
                        "', marks='" +
                        Marks.getText() +
                        "' WHERE rollno='" +
                        Rollno.getText() + "'");

                showMessage("Success", "Updated");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            c.close();
            clearText();
        }

        if (view == View) {
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" +
                    Rollno.getText() + "'", null);

            if (c.moveToFirst()) {
                Name.setText(c.getString(1));
                Marks.setText(c.getString(2));
            } else {
                showMessage("Error", "Not found");
                clearText();
            }
            c.close();
        }

        if (view == ViewAll) {
            Cursor c = db.rawQuery("SELECT * FROM student", null);

            if (c.getCount() == 0) {
                showMessage("Error", "No records");
                c.close();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (c.moveToNext()) {
                buffer.append("Rollno: ").append(c.getString(0)).append("\n");
                buffer.append("Name: ").append(c.getString(1)).append("\n");
                buffer.append("Marks: ").append(c.getString(2)).append("\n\n");
            }
            c.close();
            showMessage("Student Details", buffer.toString());
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Rollno.requestFocus();
    }
}