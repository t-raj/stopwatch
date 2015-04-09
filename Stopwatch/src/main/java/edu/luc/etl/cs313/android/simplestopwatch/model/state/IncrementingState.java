package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;

import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.DefaultClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.DefaultTimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Created by emmahighland on 4/7/15.
 */
class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    int i = 3;
    int max = 99;
    int counter = 0;

    @Override
    public void onSetReset() {
        sm.actionInc();
        i = 3;
    }

   /* public void alarmStart(){
        sm.actionAlarm();
    }

    @Override
    public void alarmStop(){
        sm.actionReset();
    }*/

    @Override
    public void onTick() {
        i --;
        if(i == 0)
        {
            sm.actionAlarm();
            sm.toDecrementingState();
        }

        if (counter <= max)
        {
            counter++;
        }
        else{
            //this.alarmStart();
            sm.toAlarmingState();
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

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }
}
