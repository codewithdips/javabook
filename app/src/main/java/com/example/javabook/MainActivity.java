package com.example.javabook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    EditText editText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         String[] tstory = getResources().getStringArray(R.array.title_story);
        String[] dstory = getResources().getStringArray(R.array.details_strory);

        listView = findViewById(R.id.list);
        editText = findViewById(R.id.etsearch);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                R.layout.row,R.id.rowtext,tstory);
        listView.setAdapter(adapter);

           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  // String t =dstory[position];


                   Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                   intent.putExtra("story",dstory);
                   intent.putExtra("position",position);
                   startActivity(intent);

               }
           });

           editText.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {

                   adapter.getFilter().filter(s);
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });

    }
}