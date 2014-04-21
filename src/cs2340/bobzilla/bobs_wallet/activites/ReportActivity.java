package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.support.v4.app.Fragment;
import cs2340.bobzilla.bobs_wallet.activites.ReportFragment.ReportType;

public class ReportActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        ReportType type = (ReportType) getIntent().getSerializableExtra(ReportFragment.EXTRA_TYPE);
        String userName = getIntent().getStringExtra(ReportFragment.EXTRA_USERNAME);
        Date startDate = (Date) getIntent().getSerializableExtra(ReportFragment.EXTRA_STARTDATE);
        Date endDate = (Date) getIntent().getSerializableExtra(ReportFragment.EXTRA_ENDDATE);
        
        return ReportFragment.newInstance(type, userName, startDate, endDate);
    }

}
