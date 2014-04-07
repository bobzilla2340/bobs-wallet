package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
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
public class ReportActivity extends Activity implements ReportActivityView {
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
        setContentView(R.layout.activity_report);
        setupActionBar();

        // Retrieves information passed by last activity (usually
        // UserAccountActivity)
        Intent reportIntent = getIntent();
        mType = (ReportType) reportIntent.getSerializableExtra(EXTRA_TYPE);
        mUserName = reportIntent.getStringExtra(EXTRA_USERNAME);
        mStartDate = (Date) reportIntent.getSerializableExtra(EXTRA_STARTDATE);
        mEndDate = (Date) reportIntent.getSerializableExtra(EXTRA_ENDDATE);

        reportActivityPresenter = new ReportActivityPresenter(this);

        TextView title = (TextView) findViewById(R.id.report_title);
        title.setText(reportActivityPresenter.getTitle());

        ListView listView = (ListView) findViewById(R.id.report_category_list);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.user_finance_account_list_text_view);
        listView.setAdapter(arrayAdapter);

        for (String categories : reportActivityPresenter.getFormattedTotals()) {
            arrayAdapter.add(categories);
        }
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
                Log.e("ReportActivity", "About to navigate up.");
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
