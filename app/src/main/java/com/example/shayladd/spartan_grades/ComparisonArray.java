package com.example.shayladd.spartan_grades;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class ComparisonArray extends Application {
  private ArrayList<String[]> comparison_array = new ArrayList<String[]>();

  public ArrayList<String[]> getComparisonArray(){
    return comparison_array;
  }

  public void removeEntry(int index){
    comparison_array.remove(index);
  }

  public void insertComparisonArray(String[] temp_array){
    boolean flag = true;
    for (String[] ary : comparison_array){
      if (Arrays.equals(ary, temp_array)){
        flag = false;
      }
    }
    if (flag) { //we can add non-duplicate
      comparison_array.add(temp_array);
    }
  }
}

