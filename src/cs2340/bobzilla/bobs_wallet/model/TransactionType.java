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
     * DEPOSIT indicates that the transaction added money.
     * WITHDRAWAL indicates that the transaction removed
     * money.
     */
    DEPOSIT, WITHDRAWAL
}
