package task;

import java.util.ArrayList;

/**
 * Aggregation of results
 *
 * @param <R> Type of result of workload
 */
public class Results<R extends Result> {
    protected ArrayList<R> results = null;

    public Results() {
	results = new ArrayList<R>();
    }

    public Results(int capacity) {
	results = new ArrayList<R>(capacity);
    }

    /**
     * Add a result into the storage
     * 
     * @param result
     *            new result
     */
    public void addResult(R result) {
	this.results.add(result);
    }

    public void addResults(Results<R> newResults) {
	this.results.addAll(newResults.results);
    }
}