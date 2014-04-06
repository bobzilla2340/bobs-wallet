package cs2340.bobzilla.bobs_wallet.view;

/**
 * This is the interface to the UserAccountAcitivityView that the presenter uses
 * to get information form.
 *
 * @author sai
 *
 */
public interface UserAccountActivityView {

    /**
     * This method gets the account name that the user typed in.
     *
     * @return String accountName the accountName that the user typed in.
     */
    String getAccountName();

    /**
     * This method gets the interest rate that the user wants to associate the
     * account with.
     *
     * @return String interestRate the interest rate that they user wants the
     *         account to be associated with.
     */
    String getInterestRate();

    /**
     * This method gets the user name that is associated with the account.
     *
     * @return String userName the username of that is associated with the
     *         account.
     */
    String getUserName();

}
