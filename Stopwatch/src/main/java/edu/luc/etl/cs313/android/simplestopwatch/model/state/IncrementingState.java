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
 * added incrementing state. Time increments up to 99 when the button is pressed. If the button is not pressed for 3 ticks it sounds and switches to the decrementing state.
 */
class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    int i = 3; //max number of ticks before decrement
    int max = 99; //max number to increment up to
    int counter = 0; //current number on screen

    @Override
    public void onSetReset() {//increment and reset the max ticks before decrement to 3 when the button iis pressed
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
    public void onTick() {//increments time if it's under 99 and i isn't 0. If i is 0, it sounds and switches to the decrementing state. If the time is 99, it goes to the alarming state.
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
    } //updates runtime

    @Override
    public int getId() {
        return R.string.INCREMENTING;
    } //state ID is incrementing

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    } //gets runtime
}
