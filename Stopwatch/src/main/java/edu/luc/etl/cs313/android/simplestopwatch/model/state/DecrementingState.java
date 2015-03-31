package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.view.View;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class DecrementingState implements StopwatchState { //changed from RunningState because decrementing & incrementing are 2 different states

	public DecrementingState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

	@Override
	public void onSetReset() {
		sm.actionStop();
		sm.toStoppedState();
	}


	@Override
	public void onTick() {
		sm.actionInc(); //change to decrement method
		//sm.toDecrementingState();
	}

    public void onDecrement(final View view) { //new decrement method that decrements the time & calls update view
        sm.actionDecrement();
        updateView();
    }

	@Override
	public void updateView() {
		sm.updateUIRuntime();
	}

	@Override
	public int getId() {
		return R.string.DECREMENTING;
	} //changed the state ID from running to decrementing
}
