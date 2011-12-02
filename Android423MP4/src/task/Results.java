package task;

import java.util.ArrayList;

/**
 * Storage for Result.
 * 
 * @author chengyin
 *
 */
public class Results {
    protected ArrayList<Result> results;
    
    /**
     * Add a result into the storage
     * @param result new result
     */
    public void addResult(Result result) {
	this.results.add(result);
    }

    public void addResults(Results remote_result) {
	this.results.addAll(remote_result.results);
    }
}
