package com.example.javabook;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Main2Activity extends AppCompatActivity {

    TextView textView;
    Button button;
    Button share_btn;
    Button nbtn,pbtn;
    int position;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.txt);
        button = findViewById(R.id.copy_bton);
        share_btn = findViewById(R.id.share_btn);
        nbtn = findViewById(R.id.next_btn);
        pbtn = findViewById(R.id.prev_btn);

      // String dstory = getIntent().getStringExtra("story");

        String[] dstory = getIntent().getStringArrayExtra("story");
        position =getIntent().getIntExtra("position",0);

        textView.setText(dstory[position]);
        button.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip=ClipData.newPlainText("Text", dstory[position]);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(Main2Activity.this,"copied",Toast.LENGTH_SHORT).show();
        });

        share_btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, dstory[position]);
            intent.setType("text/plain");
            intent=Intent.createChooser(intent,"share by");
            startActivity(intent);

        });

        nbtn.setOnClickListener(v -> {
            position = (position+1)% dstory.length;
            textView.setText(dstory[position]);
        });
        pbtn.setOnClickListener(v -> {
            position = (position-1)% dstory.length;
            textView.setText(dstory[position]);
        });
    }
}
