package edu.luc.etl.cs313.android.simplestopwatch.model.state;

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
	@Override public void onSetReset() { state.onSetReset(); }
	@Override public void onTick()      { state.onTick(); }

	@Override public void updateUIRuntime() { uiUpdateListener.updateTime(timeModel.getRuntime()); }

	// known states
	private final StopwatchState STOPPED     = new StoppedState(this);
	private final StopwatchState INCREMENTING     = new IncrementingState(this);
    private final StopwatchState ALARMING   = new AlarmingState(this);
    private final StopwatchState DECREMENTING = new DecrementingState(this);


	// transitions
	@Override public void toAlarmingState()    { setState(ALARMING); }
	@Override public void toStoppedState()    { setState(STOPPED); }
    @Override public void toDecrementingState() {setState(DECREMENTING);}
    @Override public void toIncrementingState() {setState(INCREMENTING);}


	// actions
	@Override public void actionInit()       { toStoppedState(); actionReset(); }
	@Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
	@Override public void actionStart()      { clockModel.start(); }
	@Override public void actionStop()       { clockModel.stop(); }
	@Override public void actionInc()        { timeModel.incRuntime(); actionUpdateView(); }
	@Override public void actionUpdateView() { state.updateView(); }
    @Override public void actionAlarm()      { toAlarmingState(); actionAlarm();}
    @Override public void actionDecrement()  { toDecrementingState(); actionDecrement();}
}
