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
import cs2340.bobzilla.bobs_wallet.R;

/**
 * Custom fragment hosting a DatePicker dialog
 * @author Jennifer
 *
 */
public class DatePickerFragment extends DialogFragment {
	private OnDateChangeListener mCallback;
	private String mDateType;
	public final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	private Date mDate;
	
	/**
	 * Defines callback interface for hosting activity to implement
	 *
	 */
	public interface OnDateChangeListener {
		public void onDateChange(Date date, String dateType);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			// Casts hosting activity as an OnDateChangeListener
			mCallback = (OnDateChangeListener) activity;
		} catch(ClassCastException e) {
			Log.e("DatePicker Attach Error", e.getMessage());
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Sets current date as default date for DatePicker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		mDateType = getArguments().getString(UserAccountActivity.EXTRA_DATETYPE);
		try {
			mDate = sdf.parse((month + 1) + "/" + day + "/" + year);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		// Retrieves DatePicker and sets a listener to retrieve the date the user
		// inputed
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_date, null);
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
		
		// Create new instance of DatePickerDialog and returns it
		AlertDialog.Builder result = new AlertDialog.Builder(getActivity())
			.setView(v)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				
				// Whenever the user is satisfied with the date, the hosting activity's 
				// onDateChange method is called (this was from the interface defined above)
				// to pass to the hosting activity the date and date type.
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mCallback.onDateChange(mDate, mDateType);
					
				}
			});
		
		// The title of the dialog differs based on whether the user is selecting the
		// start date of the report or the end date.
		if (mDateType.equals("start")) result.setTitle(R.string.date_picker_start_date_title);
		else result.setTitle(R.string.date_picker_end_date_title);
		return result.create();
	}	
}
