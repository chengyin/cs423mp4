package control.state;

import control.HardwareMonitor;

import task.matrix.MatrixJobQueue;
import channel.Channel;

/**
 * An abstract handler used for state transfer and listen
 * 
 * @param <C>
 *            Channel Type (client or server)
 */
public abstract class AbstractStateHandler<C extends Channel> {
    protected MatrixJobQueue jobQueue;
    protected HardwareMonitor hwMonitor;
    protected C channel;
    protected State state;

    /**
     * Considers remaining job queue elements
     * 
     * @return State of node
     */
    public State getState() {
	state.setJobQueueRemaining(jobQueue.jobCount());
	return state;
    }

    /**
     * Constructor that also attaches current state to hardware monitor
     * 
     * @param jobQueue
     *            Local job queue
     * @param hwMonitor
     *            Hardware monitor
     * @param channel
     *            Server or client
     */
    public AbstractStateHandler(MatrixJobQueue jobQueue,
	    HardwareMonitor hwMonitor, C channel) {
	this.jobQueue = jobQueue;
	this.hwMonitor = hwMonitor;
	this.channel = channel;
	this.state = new State(0, this.hwMonitor);
    }
}