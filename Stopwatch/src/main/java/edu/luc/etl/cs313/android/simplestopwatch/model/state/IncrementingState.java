package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

/**
 * Created by emmahighland on 4/7/15.
 */
class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onSetReset() {
        sm.actionInc();
        /*int time = this.getId();
        if(time < 99)
        {
            time ++;
        }*/
    }

    @Override
    public void onTick() {
        int time = this.getId();
        if(time == 3)
        {
            sm.actionAlarm();
            sm.actionDecrement();
            sm.actionUpdateView();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENTING;
    }
}
