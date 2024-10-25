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

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class FragmentStudentList extends Fragment implements FragmentCallbacks{
    MainActivity main = null;
    Context context = null;
    TextView textChosenStudent;
    public ListView listView;

    int previouslyClickedPosition = -1;
    private final String[] ids = {"A1001", "A1002", "A1003", "A2001", "A2002", "A2003",
                            "A3001", "A3002", "A3003", "A4001", "A4002", "A4003"};
    private final Integer[] avatars = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = requireActivity();
            main = (MainActivity) requireActivity();
        }
        catch (IllegalStateException e){
            throw new IllegalStateException("Main must implement callback");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        LinearLayout layout_student_list_item = (LinearLayout) inflater.inflate(R.layout.layout_student_list, null);

        textChosenStudent = layout_student_list_item.findViewById(R.id.txt_chosen_student);
        listView = layout_student_list_item.findViewById(R.id.student_listview);

        CustomArrayAdapter adapter = new CustomArrayAdapter(context, R.layout.list_item, avatars, ids);

        listView.setAdapter(adapter);
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("DEBUG", "click event" );
                main.onMsgFromFragToMain("STUDENT_LIST", String.valueOf(position));
                main.onMsgFromFragToMain("STUDENT_LIST_CHOSEN_STUDENT", ids[position]);

                textChosenStudent.setText("Mã số: " + ids[position]);
                onSelectedStudentChanged(position);
                previouslyClickedPosition = position;
            }
        });
        Bundle arguments = getArguments();

        return layout_student_list_item;
    }

    public void onSelectedStudentChanged(int new_position){
        if (previouslyClickedPosition >= 0 && previouslyClickedPosition <= listView.getCount() - 1){
            View last_view = listView.getChildAt(previouslyClickedPosition);
            last_view.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        }
        if (new_position >= 0 && new_position <= listView.getCount() - 1){
            View current_view = listView.getChildAt(new_position);
            current_view.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_alpha));
            previouslyClickedPosition = new_position;
        }
        if (new_position == listView.getCount() - 1){
            main.onMsgFromFragToMain("STUDENT_LIST", "REACHED_END");
        }
        if (new_position == 0){
            main.onMsgFromFragToMain("STUDENT_LIST", "REACHED_START");
        }
    }

    @Override
    public void onMsgFromMainToFragment(String sender, String strValue) {
        if (sender.equals("STUDENT_INFO")) {
            switch (strValue) {
                case "FIRST": {
                    Log.i("DEBUG", "Go to first");
                    listView.performItemClick(listView.getChildAt(0), 0, 0);
                    break;
                }
                case "LAST": {
                    Log.i("DEBUG", "Go to last");
                    int position = ids.length - 1;
                    listView.performItemClick(listView.getChildAt(position), position, position);
                    break;
                }
                case "PREVIOUS": {
                    Log.i("DEBUG", "Go to previous");
                    int current_position = listView.getCheckedItemPosition();
                    if (current_position > 0) {
                        int position = current_position - 1;
                        listView.performItemClick(listView.getChildAt(position), position, position);
                    }
                    break;
                }
                case "NEXT": {
                    Log.i("DEBUG", "Go to next");
                    int current_position = listView.getCheckedItemPosition();
                    if (current_position < ids.length - 1) {
                        int position = current_position + 1;
                        listView.performItemClick(listView.getChildAt(position), position, position);
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }
}
