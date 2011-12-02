package task;

/**
 * @author chengyin
 *
 */
public class MatrixJob extends Job {
    int row;
    int col;
    int m1;
    int m2;
    
    public MatrixJob(int row, int col, int m1, int m2) {
	this.row = row;
	this.col = col;
	this.m1 = m1;
	this.m2 = m2;
    }
    
    /* 
     * Generate message string for transmit
     * Format: (row),(col),(value_in_matrix1),(value_in_matri2)
     */
    public String toString() {
	String[] tokens = {Integer.toString(this.row),
		Integer.toString(this.col),
		Integer.toString(this.m1),
		Integer.toString(this.m2),
	};
	
	String str = tokens[0];
	
	for (int s = 1; s < tokens.length; s++) {
	    str += "," + tokens[s];
	}
	
	return str;
    }
}
