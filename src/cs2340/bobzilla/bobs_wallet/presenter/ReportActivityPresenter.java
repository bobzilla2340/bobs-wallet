package cs2340.bobzilla.bobs_wallet.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.activites.ReportActivity;
//import cs2340.bobzilla.bobs_wallet.activites.ReportActivity.ReportType;
import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.Transaction;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;
import cs2340.bobzilla.bobs_wallet.view.ReportActivityView;

/**
 * This is the presenter that interfaces between the ReportActivity and the
 * applicaition model. It contains various methods that are invoked by
 * interfaces to the android activities.
 * 
 * @author sai
 * 
 */
public class ReportActivityPresenter {

    /**
     * This is the view the presenter is tied to.
     */
    private ReportActivityView reportActivityView;
    /**
     * This is the user name that is associated with the current session.
     */
    private String mUserName;
    /**
     * This is the starting date of the report.
     */
    private Date mStartDate;
    /**
     * This is the ending date of the report.
     */
    private Date mEndDate;
    /**
     * This is the user object for the current session.
     */
    private User mUser;

    /**
     * Retrieves all the needed information from the view.
     * 
     * @param inputView
     *            the class that implements the ReportActivityView
     */
    public ReportActivityPresenter(final ReportActivityView inputView) {
        reportActivityView = inputView;
        mUserName = reportActivityView.getUserName();
        // mReportType = reportActivityView.getReportType();
        mStartDate = reportActivityView.getStartDate();
        mEndDate = reportActivityView.getEndDate();
        mUser = UserListSingleton.getInstance().getUserList()
                .getUser(mUserName);
    }

    /**
     * Returns a map containing category names as keys and the category amounts
     * as values.
     * 
     * @return a mapping between the category names and the amounts.
     */
    private Map<String, Double> getCategoryTotals() {
        Collection<FinanceAccount> accounts = mUser.getAccounts();
        String[] categories = ((ReportActivity) reportActivityView)
                .getResources().getStringArray(R.array.withdrawal_categories);

        Map<String, Double> totals = new LinkedHashMap<String, Double>();
        for (String category : categories) {
            totals.put(category, (double) 0);
        }

        // Adds all the withdrawal amounts for each category across all the
        // accounts of the user
        for (FinanceAccount account : accounts) {
            ArrayList<Transaction> withdrawals = account.getWithdrawals();
            for (Transaction t : withdrawals) {
                if (isValidDate(t.getTransactionDate())) {
                    double currentCategoryTotal = totals.get(t.getCategory());
                    currentCategoryTotal += Math.abs(t.getAmount());
                    totals.put(t.getCategory(), currentCategoryTotal);
                }
            }
        }

        // Adds the total withdrawal amount as a category to display to the user
        double totalWithdrawalAmount = 0;
        for (Double val : totals.values()) {
            totalWithdrawalAmount += val;
        }
        totals.put("Total", totalWithdrawalAmount);
        return totals;
    }

    /**
     * Constructs a customized title for the user.
     * 
     * @return the title of the report.
     */
    public final String getTitle() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy",
                Locale.getDefault());
        return "Spending Category Report for " + mUser.getFirstName() + " "
                + mUser.getLastName() + "\n" + sdf.format(mStartDate) + " to "
                + sdf.format(mEndDate);
    }

    /**
     * Returns a list of formatted categories and category totals.
     * 
     * @return a list of totals in string format.
     */
    public final List<String> getFormattedTotals() {
        ArrayList<String> result = new ArrayList<String>();

        Map<String, Double> totalsMap = getCategoryTotals();
        Set<String> categories = totalsMap.keySet();
        for (String cat : categories) {
            result.add(cat + ": \t" + totalsMap.get(cat));
        }
        return result;
    }

    /**
     * Helper method used to determine whether a transaction is within a date
     * range.
     * 
     * @param stringDate
     *            a date to be checked
     * @return returns true if the date is valid or not.
     */
    public final boolean isValidDate(final String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                Transaction.DATE_FORMAT_PATTERN, Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Consider date valid if parse fails.
            return true;
        }

        if (isSameDay(date, mStartDate) || isSameDay(date, mEndDate)) {
            return true;
        } else {
            return !date.before(mStartDate) && !date.after(mEndDate);
        }
    }

    /**
     * Helper method to determine whether 2 dates of (Date type) have the same
     * day.
     * 
     * @param date1
     *            This is the beginning date of the report.
     * @param date2
     *            This is the end date of the report.
     * @return returns true if date 1 and date 2 happen on the same day.
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2
                        .get(Calendar.DAY_OF_YEAR);
    }
}
