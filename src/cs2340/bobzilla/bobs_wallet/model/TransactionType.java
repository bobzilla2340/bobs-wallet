package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;

/**
 * The TransactionType class is an enumerated type to designate transaction
 * types.
 *
 * @author Farhan
 */
public enum TransactionType implements Serializable {
    /**
     * DEPOSIT indicates that the user is adding money
     * into the account.
     * WITHDRAWAL indicates that the user is taking money
     * out of the account.
     */
    DEPOSIT, WITHDRAWAL
}
