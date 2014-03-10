package cs2340.bobzilla.bobs_wallet.activites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;

public class DatePickerFragment extends DialogFragment {
	OnDateChangeListener mCallback;
	String mDateType;
	public final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	Date mDate;
	
	public interface OnDateChangeListener {
		public void onDateChange(Date date, String dateType);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			mCallback = (OnDateChangeListener) activity;
		} catch(ClassCastException e) {
			Log.e("DatePicker Attach Error", e.getMessage());
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Current date as default
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		try {
			mDate = sdf.parse((month + 1) + "/" + day + "/" + year);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_date, null);
		mDateType = getArguments().getString(UserAccountActivity.EXTRA_DATETYPE);
		DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				try {
					mDate = sdf.parse((month + 1) + "/" + day + "/" + year);
				} catch (ParseException e) {

					e.printStackTrace();
				}
				
			}
		});
		
		// Create new instance of DatePickerDialog and return it
		//return new DatePickerDialog(getActivity(), this, year, month, day);
		AlertDialog.Builder result = new AlertDialog.Builder(getActivity())
			.setView(v)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mCallback.onDateChange(mDate, mDateType);
					
				}
			});
		if (mDateType.equals("start")) result.setTitle(R.string.date_picker_start_date_title);
		else result.setTitle(R.string.date_picker_end_date_title);
		return result.create();
	}	
}
