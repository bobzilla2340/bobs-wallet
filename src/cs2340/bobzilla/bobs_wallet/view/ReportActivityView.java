package cs2340.bobzilla.bobs_wallet.view;

import java.util.Date;

import cs2340.bobzilla.bobs_wallet.activites.ReportFragment.ReportType;

/**
 * Declares the interface for a report activity.
 * 
 * @author jack
 */
public interface ReportActivityView {

    /**
     * Gets the username for the current user.
     * 
     * @return th user's username
     */
    String getUserName();

    /**
     * Gets the type of the report selected.
     * 
     * @return the ReportType of the report
     */
    ReportType getReportType();

    /**
     * Gets the start date of the selected report.
     * 
     * @return the start date of the report
     */
    Date getStartDate();

    /**
     * Gets the end date of the selected report.
     * 
     * @return the end date of the report
     */
    Date getEndDate();
}
