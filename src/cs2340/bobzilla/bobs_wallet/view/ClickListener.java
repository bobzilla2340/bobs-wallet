package cs2340.bobzilla.bobs_wallet.view;

/**
 * This is the interface between the presenter and the activity. The activity
 * invokes methods of this interface when it wants its user interactions to
 * update the model.
 *
 * @author sai
 *
 */
public interface ClickListener {

    /**
     * This method should be invoked by the activity whenever a user initiates
     * an interaction with the application.
     *
     * @throws Exception e - an exception that is thrown by the program
     * on an invalid operation.
     */
    void onClick() throws Exception;
}
