package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    public StopwatchAdapter myStop = new StopwatchAdapter();

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    protected void setState(final StopwatchState state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    private StopwatchUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final StopwatchUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    @Override
    public void onSetReset() {
        state.onSetReset();
    }

    /*@Override
    public void alarmStart() {
        this.actionAlarm();
    }

    @Override
    public void alarmStop() {
        this.actionStop();
    }*/

    @Override
    public void onTick() {
        state.onTick();
    }

    @Override
    public void updateUIRuntime() {
        uiUpdateListener.updateTime(timeModel.getRuntime());
    }

    // known states: changed running to decrementing and added incrementing and alarming
    private final StopwatchState STOPPED = new StoppedState(this);
    private final StopwatchState INCREMENTING = new IncrementingState(this);
    private final StopwatchState ALARMING = new AlarmingState(this);
    private final StopwatchState DECREMENTING = new DecrementingState(this);


    // transitions: changed running to decrementing and added incrementing and alarming
    @Override
    public void toAlarmingState() {
        setState(ALARMING);
    }

    @Override
    public void toStoppedState() {
        setState(STOPPED);
    }

    @Override
    public void toDecrementingState() {
        setState(DECREMENTING);
    }

    @Override
    public void toIncrementingState() {
        setState(INCREMENTING);
    }


    // actions
    @Override
    public void actionInit() {
        toStoppedState();
        actionReset();
    }

    @Override
    public void actionReset() {
        timeModel.resetRuntime();
        actionUpdateView();
    }

    @Override
    public void actionStart() {
        clockModel.start();
    }

    @Override
    public void actionStop() {
        clockModel.stop();
    }

    @Override
    public void actionInc() {
        timeModel.incRuntime();
        actionUpdateView();
    }

    @Override
    public void actionUpdateView() {
        state.updateView();
    }

    @Override
    public void actionAlarm() {
        uiUpdateListener.alarmStart();
    }

    //@Override public void actionDecrement()  { toDecrementingState(); actionDecrement(); actionUpdateView();}
    @Override
    public void actionDecrement() { //decrease the time by 1 when decrementing
        timeModel.decRuntime();
        actionUpdateView();
    }

    @Override
    public int actionGetRuntime() {
        timeModel.getRuntime();
        actionUpdateView();
        return timeModel.getRuntime();
    }
}
