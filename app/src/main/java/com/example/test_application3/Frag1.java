package com.example.test_application3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Frag1 extends Fragment {
    private View view;
    CalendarView cal;
    TextView tv_text;
    TextView textView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag1,container, false);
        view=inflater.inflate(R.layout.frag1,container,false);
        cal=view.findViewById(R.id.cal);
        tv_text=view.findViewById(R.id.tv_text);
        textView2=view.findViewById(R.id.textView2);
        textView2.setText("- 아메리카노");
        textView2.setVisibility(View.VISIBLE);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                tv_text.setText(year + "년" + (month+1)+"월" + day + "일");
                if(year==2022&&(month+1)==6&&day==20){
                    textView2.setText("- 진라면\n\n- 초코우유\n\n- 배지밀 비 두유");
                    textView2.setVisibility(View.VISIBLE);
                }
                else if(year==2022&&(month+1)==6&&day==5){
                    textView2.setText("- 부산어묵\n\n- 꼬깔콘");
                    textView2.setVisibility(View.VISIBLE);
                }

                else textView2.setVisibility(View.INVISIBLE);
            }

        });
        return view;
    }


}
