package com.lambdaschool.healthchaser;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.onCompleteListener.onComplete(hourOfDay,  minute);
    }

    /**
     * Interface
     * @see  <a href="https://stackoverflow.com/questions/15121373/returning-string-from-dialog-fragment-back-to-activity/15121529#15121529">Stack Overflow</a>
     */
    public interface OnCompleteListener {
        void onComplete(int hourOfDay, int minute);
    }

    private OnCompleteListener onCompleteListener;

    // Make sure the Context implemented it
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {
            this.onCompleteListener = (OnCompleteListener) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }
}