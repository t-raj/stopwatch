package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

class DecrementingState implements StopwatchState { //changed from RunningState
// because decrementing & incrementing are 2 different states

	public DecrementingState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

    int i = 0;

	@Override
	public void onSetReset() {
        int time = this.getTime();
        time --;
        if(time == i){
            sm.actionReset();
            sm.toIncrementingState();
        }
        else{
            sm.actionReset();
            sm.toStoppedState();
        }
	}

	@Override
	public void onTick() {
        int time = this.getTime();
        time --;
        sm.actionDecrement();
        sm.actionUpdateView();
        if(time == i){
        sm.toAlarmingState();}
	}

	@Override
	public void updateView() {
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

    @Override
    public Context getApplicationContext() {
        return null;
    }

}
