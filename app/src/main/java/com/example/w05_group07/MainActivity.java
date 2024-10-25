package com.example.w05_group07;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements MainCallbacks {
    FragmentTransaction ft;
    FragmentStudentList fragmentStudentList;
    FragmentStudentInfo fragmentStudentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        fragmentStudentList = FragmentStudentList.newInstance("first-blue");
        ft.replace(R.id.student_list, fragmentStudentList);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        fragmentStudentInfo = FragmentStudentInfo.newInstance("first-red");
        ft.replace(R.id.student_info, fragmentStudentInfo);
        ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {

        if (sender.equals("STUDENT_LIST")) {
            try {
                fragmentStudentInfo.onMsgFromMainToFragment("STUDENT_LIST", strValue);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
        if (sender.equals("STUDENT_LIST_CHOSEN_STUDENT")){
            try {
                fragmentStudentInfo.onMsgFromMainToFragment("STUDENT_LIST_CHOSEN_STUDENT", strValue);
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }
        if (sender.equals("STUDENT_INFO")) {
            fragmentStudentList.onMsgFromMainToFragment("STUDENT_INFO", strValue);
        }
    }
}