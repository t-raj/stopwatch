
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

/**
 * Created by Tara on 3/29/2015.
 */
public class AlarmingState implements StopwatchState { //added state for when the stopwatch is alarming

    public AlarmingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onSetReset() { //switch to stopped state when button is pushed
        sm.actionStop();
        sm.toStoppedState();
    }

    protected void playDefaultNotification() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onTick() {
        sm.actionAlarm();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() { //added a stateId for the alarming state
        return R.string.ALARMING;
    }

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }

    @Override
    public Context getApplicationContext() {
        return null;
    }
}
