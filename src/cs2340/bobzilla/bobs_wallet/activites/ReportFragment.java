package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.presenter.ReportActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.ReportActivityView;

/**
 * Activity that displays any report the user wants to create.
 * 
 * @author Jennifer
 * 
 */
public class ReportFragment extends Fragment implements ReportActivityView {
    /**
     * This is the presenter this activity is tied to.
     */
    private ReportActivityPresenter reportActivityPresenter;

    public enum ReportType {
        SPENDINGCATEGORY
    };

    /**
     * This is the type of the report specified by
     * the previous activity.
     */
    public static final String EXTRA_TYPE = "type";
    /**
     * This is the user name associated with the
     * previous activity.
     */
    public static final String EXTRA_USERNAME = "username";
    /**
     * Start date for the report, from the previous
     * activity.
     */
    public static final String EXTRA_STARTDATE = "startdate";
    /**
     * Ending date for the report, from the previous
     * activity.
     */
    public static final String EXTRA_ENDDATE = "enddate";
    /**
     * This is the report type.
     */
    private ReportType mType;
    /**
     * User name associated with the report.
     */
    private String mUserName;
    /**
     * Start date of the report.
     */
    private Date mStartDate;
    /**
     * Ending date of the report.
     */
    private Date mEndDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Retrieves information from fragment arguments
        mType = (ReportType) getArguments().getSerializable(EXTRA_TYPE);
        mUserName = getArguments().getString(EXTRA_USERNAME);
        mStartDate = (Date) getArguments().getSerializable(EXTRA_STARTDATE);
        mEndDate = (Date) getArguments().getSerializable(EXTRA_ENDDATE);

        reportActivityPresenter = new ReportActivityPresenter(this);
    }
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        
        // Set up action bar if available in API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return v;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View v = getView();

        TextView title = (TextView) v.findViewById(R.id.report_title);
        title.setText(reportActivityPresenter.getTitle());

        ListView listView = (ListView) v.findViewById(R.id.report_category_list);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                R.layout.user_finance_account_list_text_view);
        listView.setAdapter(arrayAdapter);

        for (String categories : reportActivityPresenter.getFormattedTotals()) {
            arrayAdapter.add(categories);
        }
    }
    
    // Allows hosting activity to create the fragment with arguments
    public static ReportFragment newInstance(ReportType type, String userName, Date startDate, Date endDate) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TYPE, type);
        args.putString(EXTRA_USERNAME, userName);
        args.putSerializable(EXTRA_STARTDATE, startDate);
        args.putSerializable(EXTRA_ENDDATE, endDate);
        
        // Create the ReportFragment
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        
        return fragment;
        
    }

    // Getters used by ReportActivityPresenter
    
    @Override
    public String getUserName() {
        return mUserName;
    }

    @Override
    public ReportType getReportType() {
        return mType;
    }

    @Override
    public Date getStartDate() {
        return mStartDate;
    }

    @Override
    public Date getEndDate() {
        return mEndDate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
