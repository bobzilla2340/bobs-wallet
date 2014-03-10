package cs2340.bobzilla.bobs_wallet.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.activites.ReportActivity;
import cs2340.bobzilla.bobs_wallet.activites.ReportActivity.ReportType;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.Transaction;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ReportActivityView;

public class ReportActivityPresenter {
	private ReportActivityView reportActivityView;
	private String mUserName;
	private ReportType mReportType;
	private Date mStartDate;
	private Date mEndDate;
	private User mUser;
	
	public ReportActivityPresenter(ReportActivityView view) {
		reportActivityView = view;
		mUserName = reportActivityView.getUserName();
		mReportType = reportActivityView.getReportType();
		mStartDate = reportActivityView.getStartDate();
		mEndDate = reportActivityView.getEndDate();
		mUser = UserListSingleton.getInstance().getUserList()
				.getUser(mUserName);
		Log.e("ReportActivityPresenter", "ReportActivityPresenter instantiated.");
		Log.e("ReportActivityPresenter", mStartDate.toString());
		Log.e("ReportActivityPresenter", mEndDate.toString());
		Log.e("ReportActivityPresenter", mUserName);
	}
	
	public Map<String, Double> getCategoryTotals() {
		Collection<FinanceAccount> accounts = mUser.getAccounts();
		String[] categories = ((ReportActivity) reportActivityView).getResources()
				.getStringArray(R.array.withdrawal_categories);
		
		Map<String, Double> totals = new LinkedHashMap<String, Double>();
		for (String category : categories) {
			totals.put(category, (double) 0);
		}
		for (FinanceAccount account: accounts) {
			Log.e("ReportActivityPresenter", "Account name: " + account.getAccountName());
			ArrayList<Transaction> withdrawals = account.getWithdrawals();
			Log.e("ReportActivityPresenter", "Number of Withdrawals: " + withdrawals.size());
			for (Transaction t : withdrawals) {
				Log.e("ReportActivityPresenter", "Transaction: " + t.getCategory() + " " + t.getAmount());
				Log.e("ReportActivityPresenter", "Date of transaction: " + t.getTransactionDate());
				if (isValidDate(t.getTransactionDate())) {
					Log.e("ReportActivityPresenter", "Date is valid.");
					double currentCategoryTotal = totals.get(t.getCategory());
					currentCategoryTotal += Math.abs(t.getAmount());
					totals.put(t.getCategory(), currentCategoryTotal);
				}
			}
		}
		
		// Put "Total" and total withdrawal amount
		double totalWithdrawalAmount = 0;
		for (Double val : totals.values()) {
			totalWithdrawalAmount += val;
		}
		totals.put("Total", totalWithdrawalAmount);
		return totals;
	}
	
	public String getTitle() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
		return "Spending Category Report for " + mUser.getFirstName() + " " + mUser.getLastName() + "\n"
				+ sdf.format(mStartDate) + " to " + sdf.format(mEndDate);
	}
	
	public ArrayList<String> getFormattedTotals() {
		ArrayList<String> result = new ArrayList<String>();
		
		Map<String, Double> totalsMap = getCategoryTotals();
		Set<String> categories = totalsMap.keySet();
		for (String cat : categories) {
			result.add(cat + ": \t" + totalsMap.get(cat));	
		}
		return result;
	}
	
	public boolean isValidDate(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(Transaction.DATE_FORMAT_PATTERN);
		Date date;
		try {
			date = sdf.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			// Consider date valid if parse fails.
			return true;
		}
		
		if (isSameDay(date, mStartDate) || isSameDay(date, mEndDate)) return true;
		else return !date.before(mStartDate) && !date.after(mEndDate);
	}
	private boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
}
