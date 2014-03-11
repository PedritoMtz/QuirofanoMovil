package com.hu.quirofano;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
 
import java.util.Calendar;
 
public class TimePickerFragment extends DialogFragment 
{
    //private TimePickedListener mListener;
	OnTimeSetListener ondateSet;
	
	public TimePickerFragment() {
	 }
	
	 public void setCallBack(OnTimeSetListener ondate) {
		 ondateSet = ondate;
	 }
	
	 private int hour, minutes;
	 
	 @Override
	 public void setArguments(Bundle args) {
		 super.setArguments(args);
		 hour = args.getInt("hour");
		 minutes = (args.getInt("minutes"));
	 }
	
	 @Override
	 public Dialog onCreateDialog(Bundle savedInstanceState) {
		 return new TimePickerDialog(getActivity(), ondateSet, hour, minutes, DateFormat.is24HourFormat(getActivity()));
	 }
}
