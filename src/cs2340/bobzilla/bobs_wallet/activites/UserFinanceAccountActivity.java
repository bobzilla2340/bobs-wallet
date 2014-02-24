package cs2340.bobzilla.bobs_wallet.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import cs2340.bobzilla.bobs_wallet.R;

public class UserFinanceAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_finance_account);
		
		Intent userFinanceAccountActivity = getIntent();
		String financeAccountName = userFinanceAccountActivity.getStringExtra(UserAccountActivity.USER_FINANCE_ACCOUNT_NAME);
		
		TextView accountName = (TextView)findViewById(R.id.userFinanceAccountActivityAccountNameTextView);
		
		accountName.append(financeAccountName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_finance_account, menu);
		return true;
	}

}
