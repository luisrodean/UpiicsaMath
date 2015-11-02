package com.belu.upiicsamath.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belu.upiicsamath.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;

public class FragmentCalendar extends Fragment {

    private MaterialCalendarView calendario;

    public FragmentCalendar() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_calendar, container, false);
            calendario = (MaterialCalendarView) vista.findViewById(R.id.calendarView);
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Date date = new Date();
        date.setDate(2);
        calendario.setDateSelected(date,true);
    }
}
