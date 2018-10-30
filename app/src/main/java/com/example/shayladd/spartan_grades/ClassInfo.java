package com.example.shayladd.spartan_grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassInfo extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_class_info);
    setTitle("Spartan Grades");

    Intent intent = getIntent();
    String[] messages = intent.getStringArrayExtra(SearchResults.key);
    String to_print = "";
    for (String s : messages){
      to_print += s;
    }
    TextView output = (TextView)findViewById(R.id.output);
    output.setText(to_print);
  }
}
