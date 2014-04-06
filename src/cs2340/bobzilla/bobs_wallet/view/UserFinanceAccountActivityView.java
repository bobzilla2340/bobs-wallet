package cs2340.bobzilla.bobs_wallet.view;

import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * This interface serves as a method of obtaining
 * user interactions to the application in the User
 * Finance Account Activity view.
 *
 * @author sai
 *
 */
public interface UserFinanceAccountActivityView {

    /**
     * This method returns the transaction that the use wants to place on their
     * financial account.
     *
     * @return String amount The amount the user wants to withdraw or deposit.
     */
    String getTransactionAmount();

    /**
     * This method returns the account name the user wants to open.
     *
     * @return String name The name of the account the user wants to inspect
     */
    String getAccountName();

    /**
     * This method returns the username that is associated with the account.
     *
     * @return String userName The username that the account is associated with.
     */
    String getUsername();

    /**
     * This method returns the category of the transaction.
     *
     * @return String category The type of transaction.
     */
    String getCategory();

    /**
     * This method returns whether the transaction was a deposit or a
     * withdrawal.
     *
     * @return TransactionType type Indicates if the transaction was a deposit
     *         or a withdrawal.
     */
    TransactionType getTransactionType();

}
