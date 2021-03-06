package task.matrix;

import java.io.Serializable;

import task.Job;

/**
 * Job in the Matrix-Addition
 * 
 */
public class MatrixJob extends Job implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -298967412936119125L;
    private int row;
    private int col;
    private int m1;
    private int m2;

    /**
     * Create a matrix addition job
     * 
     * @param row
     *            row of the element in the matrix
     * @param col
     *            col of the element in the matrix
     * @param m1
     *            value of the element in matrix1
     * @param m2
     *            value of the element in matrix2
     */
    public MatrixJob(int row, int col, int m1, int m2) {
	super();

	this.row = row;
	this.col = col;
	this.m1 = m1;
	this.m2 = m2;
    }

    /**
     * Generate message string for transmit Format:
     * (row),(col),(value_in_matrix1),(value_in_matri2)
     *
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
	String[] tokens = { Integer.toString(row),
		Integer.toString(col), Integer.toString(m1),
		Integer.toString(m2), };

	String str = tokens[0];

	for (int s = 1; s < tokens.length; s++) {
	    str += "," + tokens[s];
	}

	return str;
    }

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getM1() {
	return m1;
    }

    public int getM2() {
	return m2;
    }
}
