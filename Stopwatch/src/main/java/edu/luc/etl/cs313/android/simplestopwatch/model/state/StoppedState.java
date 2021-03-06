package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;

import edu.luc.etl.cs313.android.simplestopwatch.R;

/**
 * Switches to the incrementing state when the button is pushed
 */

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

   /* public void alarmStart(){
        sm.actionAlarm();
    }

    @Override
    public void alarmStop(){
        sm.actionStop();
    }*/

    @Override
    public void onTick() {
        sm.actionReset();
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
    public int getTime() {
        return sm.actionGetRuntime();
    }

}
