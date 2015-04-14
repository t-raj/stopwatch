package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
/**
 * Changed from running state. Decrements the number on the screen each tick and switches to alarming state when the number reaches 0.
 * Switches to stopped state when the button is pressed unless time on the screen is 0, in which case it switches to the incrementing state.
 */
class DecrementingState implements StopwatchState { //changed from RunningState
// because decrementing & incrementing are 2 different states

	public DecrementingState(final StopwatchSMStateView sm) {
		this.sm = sm;
	}

	private final StopwatchSMStateView sm;

    int i = 0;//minimum value to stay in the decrementing state

	@Override
	public void onSetReset() { //set time on screen to 0 and change state to stopped unless time on the screen is 0, in which case switch to incrementing state
        int time = this.getTime();
        time --;
        if(time == i){
            this.
            sm.actionReset();
            sm.toIncrementingState();
            sm.actionAlarm();
            //this.alarmStart();
        }
        else {
            sm.actionReset();
            sm.toStoppedState();
            sm.actionAlarm();
            //this.alarmStart();
        }
	}

    /*public void alarmStart(){
        sm.actionAlarm();
    }

    @Override
    public void alarmStop(){
        sm.actionReset();
    }
*/
	@Override
	public void onTick() { //decrement time shown on the screen every tick and switch to alarming state if value on the screen is 0
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
	} //updates runtime

	@Override
	public int getId() { //state ID is decrementing
		return R.string.DECREMENTING;
	} //changed the state ID from running to decrementing

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    } //return runtime
}
