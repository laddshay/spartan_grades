package com.example.shayladd.spartan_grades;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.MonthDisplayHelper;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchResults extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_results);

    ArrayList<String[]> master = new ArrayList<String[]>();
    ArrayList<String[]> results = new ArrayList<String[]>();
    TextView output = (TextView) findViewById(R.id.output);
    TableLayout table = (TableLayout) findViewById(R.id.table);

    //get search type and search term from search page call
    Intent intent = getIntent();
    String[] messages = intent.getStringArrayExtra(MainActivity.key);
    String search_by = messages[0];
    String search_term = messages[1];
    output.setText(search_by + ": "  + search_term);

    //import data from master CSV (located under app/res/raw/data.csv)

    master = readData();

    //decide which search method to call and store results in results ArrayList
    if (search_by.equals("Course Code")) {
      String[] codes = search_term.split(" ");
      results = searchCode(codes[0], codes[1], master);
    } else if (search_by.equals("Course Title")) {
      results = searchTitle(search_term, master);
    } else if (search_by.equals("Professor")) {
      results = searchProf(search_term, master);
    } else {
      output.setText("error");
    }

    //display data in TableLayout
/*
    String to_print = "";
    //add header row
    //add actual data
    for (String[] row : results) {
      for (String col : row) {
        to_print += col + " / ";
      }
      to_print += '\n';
    }
    output.setText(to_print);
    */
    for(int i = 1; i < results.size(); i++) {
      String[] temp = results.get(i);

      TableRow table_row = new TableRow(this);

      TextView subject_code = new TextView(this);
      subject_code.setText(temp[1] + " " + temp[2] + " ");
      subject_code.setWidth(0);
      TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams((int) ((1 / 20) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      subject_code_params.rightMargin = 10;
      table_row.addView(subject_code, subject_code_params);

      TextView course_title = new TextView(this);
      course_title.setText(temp[3]);
      TableRow.LayoutParams course_title_params = new TableRow.LayoutParams((int) ((3 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      course_title_params.rightMargin = 10;
      table_row.addView(course_title, course_title_params);

      TextView professor = new TextView(this);
      professor.setText(temp[4]);
      TableRow.LayoutParams professor_params = new TableRow.LayoutParams((int) ((3 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      professor_params.rightMargin = 10;
      table_row.addView(professor, professor_params);

      TextView avg_gpa = new TextView(this);
      avg_gpa.setText(temp[5]);
      TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      table_row.addView(avg_gpa, avg_gpa_params);

      table.addView(table_row);
    }
  }

  private ArrayList<String[]> readData(){
    try {
      InputStream is = getResources().openRawResource(R.raw.data);
      CSVReader reader = new CSVReader(new InputStreamReader(is));
      List<String[]> result_arr = reader.readAll();
      ArrayList<String[]> result = (ArrayList<String[]>)(result_arr);
      return result;
    } catch(Exception e) {
      TextView output = (TextView) findViewById(R.id.output);
      output.setText(e.getMessage());
      return new ArrayList<String[]>();
    }
  }

  private static ArrayList<String[]> searchCode(String subject, String number, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    subject = subject.toUpperCase();
    number = number.toUpperCase();
    subject = subject.trim();
    number = number.trim();
    for (String[] ele : master) {
      if (subject.equals(ele[1]) && number.equals(ele[2])) {
        result.add(ele);
      }
    }
    return result;
  }

  private static ArrayList<String[]> searchProf(String prof_name, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    prof_name = prof_name.trim();
    prof_name = prof_name.toUpperCase();
    String[] tokenized_prof_name = prof_name.split(" ");
    for (String[] ele : master) {
      String names_of_profs = ele[4].toUpperCase();
      boolean flag = true;
      for (String t_name : tokenized_prof_name) {
        if (names_of_profs.indexOf(t_name.trim()) == -1) {
          flag = false;
        }
      }
      if (flag == true) {
        result.add(ele);
      }
    }
    return result;
  }

  private static ArrayList<String[]> searchTitle(String title, ArrayList<String[]> master) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    title = title.trim();
    title = title.toUpperCase();
    String[] tokenized_class_title = title.split(" ");
    for (String[] ele : master) {
      String master_title = ele[3].toUpperCase();
      boolean flag = true;
      for (String t_title : tokenized_class_title) {
        if (master_title.indexOf(t_title.trim()) == -1) {
          flag = false;
        }
      }
      if (flag == true) {
        result.add(ele);
      }
    }
    return result;
  }
}
