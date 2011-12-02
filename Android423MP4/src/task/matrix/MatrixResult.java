package task.matrix;

import task.Result;

/**
 * Result for matrix addition
 * 
 * @author chengyin
 * 
 */
public class MatrixResult implements Result {
    int row;
    int col;
    int value;

    /**
     * Generate a result
     * 
     * @param row
     *            row of the element
     * @param col
     *            col of the element
     * @param value
     *            value of the addition result
     */
    public MatrixResult(int row, int col, int value) {
	super();

	this.row = row;
	this.col = col;
	this.value = value;
    }

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getValue() {
	return value;
    }
}
