package com.example.w05_group07;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentStudentInfo extends Fragment implements FragmentCallbacks {
    private Student students[] = {new Student("Nguyen Van A", "A1", 9.0),
            new Student("Nguyen Van B", "A1", 5.5),
            new Student("Nguyen Van C", "A1", 6.3),
            new Student("Nguyen Van D", "A2", 7.6),
            new Student("Nguyen Van E", "A2", 4.6),
            new Student("Nguyen Van F", "A2", 9.7),
            new Student("Nguyen Van G", "A3", 2.8),
            new Student("Nguyen Van H", "A3", 5.4),
            new Student("Nguyen Van I", "A3", 8.7),
            new Student("Nguyen Van J", "A4", 0.4),
            new Student("Nguyen Van K", "A4", 5.7),
            new Student("Nguyen Van L", "A4", 6.9),
    };

    MainActivity main; Context context = null;
    TextView txtId, txtName, txtClass, txtScore;
    Button btnFirst, btnPrevious, btnNext, btnLast;
    Toast toast;
    public static FragmentStudentInfo newInstance(String strArg) {
        FragmentStudentInfo fragmentStudentInfo = new FragmentStudentInfo();
        Bundle args = new Bundle();
        args.putString("redArg1", strArg);
        fragmentStudentInfo.setArguments(args);
        return fragmentStudentInfo;
    }
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        }
        catch (IllegalStateException e){
            throw new IllegalStateException("Main must implement callback");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        LinearLayout layout_red = (LinearLayout) inflater.inflate(R.layout.layout_student_info, null);
        txtId = (TextView) layout_red.findViewById(R.id.txt_student_id);
        txtName = (TextView) layout_red.findViewById(R.id.txt_student_name);
        txtClass = (TextView) layout_red.findViewById(R.id.txt_student_class);
        txtScore = (TextView) layout_red.findViewById(R.id.txt_student_score);
        try {
            Bundle arguments = getArguments();
        }
        catch (Exception e) {
            Log.e("RED FRAGMENT ERROR: ", e.getMessage());
        }

        btnFirst = (Button) layout_red.findViewById(R.id.btn_first);
        btnPrevious = (Button) layout_red.findViewById(R.id.btn_previous);
        btnNext = (Button) layout_red.findViewById(R.id.btn_next);
        btnLast = (Button) layout_red.findViewById(R.id.btn_last);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "FIRST");
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "PREVIOUS");
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "NEXT");
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "LAST");
            }
        });
        return layout_red;
    }

    @Override
    public void onMsgFromMainToFragment(String sender, String strValue) {
        if (sender.equals("BLUE-FRAG-ID")) {
            txtId.setText(strValue);
        }
        if (sender.equals("BLUE-FRAG")) {
            txtName.setText("Họ tên: " + students[Integer.parseInt(strValue)].studentName);
            txtClass.setText("Lớp: " + students[Integer.parseInt(strValue)].studentClass);
            txtScore.setText("Điểm trung bình: " + String.valueOf(students[Integer.parseInt(strValue)].studentScore));
        }
    }
}
