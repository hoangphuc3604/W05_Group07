package com.example.w05_group07;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentStudentList extends Fragment implements FragmentCallbacks{
    MainActivity main; Context context = null;
    TextView txtBlue;
    public ListView listView;
    Toast toast;
    int previousClickedPosition = -1;
    private String ids[] = {"A1001", "A1002", "A1003", "A2001", "A2002", "A2003",
                            "A3001", "A3002", "A3003", "A4001", "A4002", "A4003"};
    private Integer avatars[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d
                                , R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h
                                , R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l};
    public static FragmentStudentList newInstance(String strArg) {
        FragmentStudentList fragmentStudentList = new FragmentStudentList();
        Bundle args = new Bundle();
        args.putString("blueArg1", strArg);
        fragmentStudentList.setArguments(args);
        return fragmentStudentList;
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
        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_student_list, null);
        txtBlue = (TextView) layout_blue.findViewById(R.id.txt_chosen_student);
        listView = (ListView) layout_blue.findViewById(R.id.student_listview);
        CustomArrayAdapter adapter = new CustomArrayAdapter(context, R.layout.list_item, avatars, ids);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.onMsgFromFragToMain("BLUE-FRAG", String.valueOf(position));
                main.onMsgFromFragToMain("BLUE-FRAG-ID", ids[position]);
                txtBlue.setText("Mã số: " + ids[position]);
                if (previousClickedPosition != -1) {
                    View previousClickedView = parent.getChildAt(previousClickedPosition);
                    previousClickedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
                view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                previousClickedPosition = position;
            }
        });
        try {
            Bundle arguments = getArguments();
        }
        catch (Exception e) {
            Log.e("BLUE FRAGMENT ERROR: ", e.getMessage());
        }
        return layout_blue;
    }
    @Override
    public void onMsgFromMainToFragment(String sender, String strValue) {
        if (sender.equals("RED-FRAG")) {
            if (strValue.equals("FIRST")) {
                listView.performItemClick(listView.getChildAt(0), 0, 0);
                if (previousClickedPosition != -1) {
                    View previousClickedView = listView.getChildAt(previousClickedPosition);
                    previousClickedView.setBackgroundColor(0xff00ff00);
                }
                listView.getChildAt(0).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                previousClickedPosition = 0;
            }
            if (strValue.equals("LAST")) {
                listView.performItemClick(listView.getChildAt(ids.length-1), ids.length-1, ids.length-1);
                if (previousClickedPosition != -1) {
                    View previousClickedView = listView.getChildAt(previousClickedPosition);
                    previousClickedView.setBackgroundColor(0xff00ff00);
                }
                listView.getChildAt(ids.length-1).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                previousClickedPosition = ids.length-1;
            }
            if (strValue.equals("PREVIOUS")) {
                int position = listView.getCheckedItemPosition();
                if (position > 0) {
                    listView.performItemClick(listView.getChildAt(position-1), position-1, position-1);
                    if (previousClickedPosition != -1) {
                        View previousClickedView = listView.getChildAt(previousClickedPosition);
                        previousClickedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }
                    listView.getChildAt(position-1).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    previousClickedPosition = position-1;
                }
            }
            if (strValue.equals("NEXT")) {
                int position = listView.getCheckedItemPosition();
                if (position < ids.length - 1) {
                    listView.performItemClick(listView.getChildAt(position+1), position+1, position+1);
                    if (previousClickedPosition != -1) {
                        View previousClickedView = listView.getChildAt(previousClickedPosition);
                        previousClickedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    }
                    listView.getChildAt(position+1).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    previousClickedPosition = position+1;
                }
            }
        }
    }
}
