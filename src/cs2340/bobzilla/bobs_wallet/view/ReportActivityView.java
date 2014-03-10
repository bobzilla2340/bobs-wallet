package cs2340.bobzilla.bobs_wallet.view;

import java.util.Date;

import cs2340.bobzilla.bobs_wallet.activites.ReportActivity.ReportType;

public interface ReportActivityView {
	public String getUserName();
	public ReportType getReportType();
	public Date getStartDate();
	public Date getEndDate();
}
