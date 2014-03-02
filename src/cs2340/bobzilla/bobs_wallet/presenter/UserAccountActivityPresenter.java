package cs2340.bobzilla.bobs_wallet.presenter;

import cs2340.bobzilla.bobs_wallet.view.ClickListener;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;

public class UserAccountActivityPresenter implements ClickListener {
	private UserAccountActivityView userAccountActivityView;
	
	public UserAccountActivityPresenter(UserAccountActivityView view) {
		userAccountActivityView = view;
	}
	
	@Override
	public void onClick() {
		// In this method, handle the user's interaction with the 
		// dialog box's create account button! You should call this
		// method within the AlertDialogClickListener private inner
		// class. Move all the input clensing code here! You should
		// literally be calling just this method...
		// By the way, it will behoove you to make an exception for
		// when the finance account creation goes wrong. This way,
		// you can wrap your call to onClick() around  a try-catch
		// block. If the input is invalid you should throw your
		// custom exception. Look at the other two I made and
		// follow suit.
	}
	
}
