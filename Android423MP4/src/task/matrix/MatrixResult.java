package task.matrix;

import task.Result;

public class MatrixResult extends Result {
    int row;
    int col;
    int value;
    
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
