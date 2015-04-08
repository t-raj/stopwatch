package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

class DecrementingState implements StopwatchState { //changed from RunningState
// because decrementing & incrementing are 2 different states

	public DecrementingState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

	@Override
	public void onSetReset() {
		sm.actionStop();
	}

	@Override
	public void onTick() {
        sm.actionDecrement();
        sm.actionStop();
	}

	@Override
	public void updateView() {
		int timer;
        timer = this.getTime();
        timer --;
        sm.updateUIRuntime();
	}

	@Override
	public int getId() {
		return R.string.DECREMENTING;
	} //changed the state ID from running to decrementing

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }

}