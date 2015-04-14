package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView { //added methods to go to the incrementing and alarming states and changed toRunningState to toDecrementingState

	// transitions
	void toStoppedState();
	void toDecrementingState();
    void toIncrementingState();
    void toAlarmingState();

	// actions
	void actionInit();
	void actionReset();
    int actionGetRuntime();
	void actionStart();
	void actionStop();
	void actionInc();
    void actionDecrement(); //added method to decrement time
    void actionAlarm(); //added an action for alarming
	void actionUpdateView();


	// state-dependent UI updates
	void updateUIRuntime();
}
