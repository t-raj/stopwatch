
package edu.luc.etl.cs313.android.simplestopwatch.model.state;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.content.Context;

import java.io.IOException;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateSource;

/**
 * Created by Tara on 3/29/2015.
 * Added state for when the stopwatch is alarming. The alarm continues sounding until the button is pressed, causing the state to change to stopped.
 */
public class AlarmingState implements StopwatchState {

    public AlarmingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;


    @Override
    public void onSetReset() { //switch to stopped state when button is pushed
        //this.alarmStop();
        sm.toStoppedState();

    }

    /*@Override
    public void alarmStart(){
        sm.actionAlarm();
    }

    @Override
    public void alarmStop(){
        sm.actionReset();
    }
*/
    @Override
    public void onTick() {//sound alarm every tick
        sm.actionAlarm();
    }

    @Override
    public void updateView() { //updates runtime
        sm.updateUIRuntime();
    }

    @Override
    public int getId() { //added a stateId for the alarming state
        return R.string.ALARMING;
    }

    @Override
    public int getTime(){ //returns runtime
        return sm.actionGetRuntime();
    }
}
