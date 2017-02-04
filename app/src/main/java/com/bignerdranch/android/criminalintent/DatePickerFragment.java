package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Yogesh on 01-02-2017.
 */

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_date, container, false);
        Button okButton = (Button) v.findViewById(R.id.btn_date_picker_ok);
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
                getActivity().finish();

            }
        });

        return v;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        Date date = (Date) getArguments().getSerializable(ARG_DATE);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        View v = LayoutInflater.from(getActivity())
//                .inflate(R.layout.dialog_date, null);
//
//        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
//        mDatePicker.init(year, month, day, null);
//
//        return new AlertDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle(R.string.date_picker_title)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                int year = mDatePicker.getYear();
//                                int month = mDatePicker.getMonth();
//                                int day = mDatePicker.getDayOfMonth();
//                                Date date = new GregorianCalendar(year, month, day).getTime();
//                                sendResult(Activity.RESULT_OK, date);
//                            }
//                        })
//                .create();
//    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode, Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        if (getTargetFragment() == null) {
            getActivity().setResult(resultCode, intent);
//            getActivity().finish();
        } else {
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }


    }

}
