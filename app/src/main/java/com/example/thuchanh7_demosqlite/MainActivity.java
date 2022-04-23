package com.example.thuchanh7_demosqlite;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvStudent;
    com.example.thuchanh7_demosqlite.StudentAdapter adt;
    List<com.example.thuchanh7_demosqlite.Contact> contacttList = new ArrayList<>();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.example.thuchanh7_demosqlite.DatabaseHandler db = new com.example.thuchanh7_demosqlite.DatabaseHandler(this);

        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy"));
//        db.addContact(new Contact("Karthik"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        lvStudent = findViewById(R.id.lvStudent);
        contacttList = db.getAllContacts();

        adt = new com.example.thuchanh7_demosqlite.StudentAdapter(this, R.layout.list_item, contacttList);
        lvStudent.setAdapter(adt);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnRemove = findViewById(R.id.btnRemove);
        Button btnCancel = findViewById(R.id.btnCancel);
        TextView txt = findViewById(R.id.txtfield);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeButton(btnAdd,btnCancel,btnRemove);
                com.example.thuchanh7_demosqlite.Contact contact = new com.example.thuchanh7_demosqlite.Contact(txt.getText().toString());
                db.addContact(contact);
                contacttList.add(contact);
                txt.setText("");
                dataChange();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.example.thuchanh7_demosqlite.Contact contact = contacttList.get(index);
                db.deleteContact(contact);
                contacttList = db.getAllContacts();
                dataChange();
                activeButton(btnRemove,btnCancel,btnAdd);
            }
        });

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                Toast.makeText(MainActivity.this, index+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void dataChange(){
        com.example.thuchanh7_demosqlite.StudentAdapter adt = new com.example.thuchanh7_demosqlite.StudentAdapter(this, R.layout.list_item, contacttList);
        lvStudent.setAdapter(adt);
    }

    public void activeButton(Button btn1, Button btn2,Button btn3){
        btn1.setBackgroundColor(Color.parseColor("#C94820"));
        btn2.setBackgroundColor(Color.parseColor("#4C98D1"));
        btn3.setBackgroundColor(Color.parseColor("#4C98D1"));
    }
}