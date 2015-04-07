package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class DecrementingState implements StopwatchState { //changed from RunningState
// because decrementing & incrementing are 2 different states

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
        int time = this.getId();
        if(time > 0)
        {
            sm.actionDecrement(); //change to decrement method
        }
        else {
            sm.actionAlarm();
        }
        sm.actionUpdateView();
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
