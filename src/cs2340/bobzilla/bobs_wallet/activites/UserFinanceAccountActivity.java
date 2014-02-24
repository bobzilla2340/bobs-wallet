package cs2340.bobzilla.bobs_wallet.activites;

import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.R.layout;
import cs2340.bobzilla.bobs_wallet.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class UserFinanceAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_finance_account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_finance_account, menu);
		return true;
	}

}
