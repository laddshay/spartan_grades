package com.example.shayladd.spartan_grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResults extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_results);

    Intent intent = getIntent();
    String[] messages = intent.getStringArrayExtra(MainActivity.key);
    String output_str = messages[0] + "\n" + messages[1];
    TextView output_text = findViewById(R.id.output_text);
    output_text.setText(output_str);
  }
}
