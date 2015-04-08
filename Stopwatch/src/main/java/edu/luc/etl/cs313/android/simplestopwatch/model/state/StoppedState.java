package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {

	public StoppedState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

	@Override
	public void onSetReset() { //switch to incrementing state when button is pressed
		sm.actionStart();
		sm.toIncrementingState();
	}

	@Override
	public void onTick() {
        sm.actionStop();
	}

	@Override
	public void updateView() {
		sm.updateUIRuntime();
	}

	@Override
	public int getId() {
		return R.string.STOPPED;
	}

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }
}
