package com.example.shayladd.spartan_grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewComparison extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_comparison);

    ArrayList<String[]> comparison_table = ((ComparisonArray)ViewComparison.this.getApplication()).getComparisonArray();
    TableLayout table = (TableLayout) findViewById(R.id.table);

    if (comparison_table.size() < 1) {
      TextView output = new TextView(this);
      output.setText("No matching classes found.");
      TableRow table_row = new TableRow(this);
      TableRow.LayoutParams output_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
      table.addView(output, output_params);
    } else {
      //add header entries
      String[] temp_header = comparison_table.get(0);

      TableRow table_row_header = new TableRow(this);

      TextView semester_header = new TextView(this);
      semester_header.setText(temp_header[0]);
      semester_header.setWidth(0);
      TableRow.LayoutParams semester_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.05);
      semester_params.rightMargin = 10;
      table_row_header.addView(semester_header, semester_params);

      TextView subject_code_header = new TextView(this);
      subject_code_header.setText(temp_header[2]);
      subject_code_header.setWidth(0);
      TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      subject_code_params.rightMargin = 10;
      table_row_header.addView(subject_code_header, subject_code_params);

      TextView course_title_header = new TextView(this);
      course_title_header.setText(temp_header[3]);
      TableRow.LayoutParams course_title_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.3);
      course_title_params.rightMargin = 10;
      table_row_header.addView(course_title_header, course_title_params);

      TextView professor_header = new TextView(this);
      professor_header.setText(temp_header[4]);
      TableRow.LayoutParams professor_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.3);
      professor_params.rightMargin = 10;
      table_row_header.addView(professor_header, professor_params);

      TextView avg_gpa_header = new TextView(this);
      avg_gpa_header.setText(temp_header[5]);
      TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.15);
      table_row_header.addView(avg_gpa_header, avg_gpa_params);

      TextView btn_header = new TextView(this);
      btn_header.setText("More Info...");
      TableRow.LayoutParams btn_params = new TableRow.LayoutParams(table.getWidth(), TableRow.LayoutParams.WRAP_CONTENT, (float) 0.1);
      table_row_header.addView(btn_header, btn_params);

      table.addView(table_row_header);
      //add class entries
      for (int i = 1; i < comparison_table.size(); i++) {
        String[] temp = comparison_table.get(i);

        TableRow table_row = new TableRow(this);

        TextView semester = new TextView(this);
        semester.setText(temp[0]);
        semester.setWidth(0);
        //TableRow.LayoutParams semester_params = new TableRow.LayoutParams((int) ((1 / 20) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        semester_params.rightMargin = 10;
        table_row.addView(semester, semester_params);

        TextView subject_code = new TextView(this);
        subject_code.setText(temp[1] + " " + temp[2] + " ");
        subject_code.setWidth(0);
        //TableRow.LayoutParams subject_code_params = new TableRow.LayoutParams((int) ((1 / 20) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        subject_code_params.rightMargin = 10;
        table_row.addView(subject_code, subject_code_params);

        TextView course_title = new TextView(this);
        course_title.setText(temp[3]);
        //TableRow.LayoutParams course_title_params = new TableRow.LayoutParams((int) ((3 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        course_title_params.rightMargin = 10;
        table_row.addView(course_title, course_title_params);

        TextView professor = new TextView(this);
        professor.setText(temp[4]);
        //TableRow.LayoutParams professor_params = new TableRow.LayoutParams((int) ((2 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        professor_params.rightMargin = 10;
        table_row.addView(professor, professor_params);

        TextView avg_gpa = new TextView(this);
        avg_gpa.setText(temp[5]);
        //TableRow.LayoutParams avg_gpa_params = new TableRow.LayoutParams((int) ((1 / 8.0) * table.getWidth()), TableRow.LayoutParams.WRAP_CONTENT, 1);
        table_row.addView(avg_gpa, avg_gpa_params);

        table.addView(table_row);
      }
    }
  }
}
