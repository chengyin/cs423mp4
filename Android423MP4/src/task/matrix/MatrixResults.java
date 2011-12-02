package task.matrix;

import java.io.Serializable;

import task.Result;
import task.Results;
import matrix.Matrix;

/**
 * Storage for matrix addition results
 * 
 * @author chengyin
 * 
 */
public class MatrixResults extends Results implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 846281009776986124L;

    /**
     * Find out how many rows are in the matrix.
     * 
     * @return row count of the matrix
     */
    private int getMaxRow() {
	int row = 0, r;

	for (Result result : this.results) {
	    r = ((MatrixResult) result).getRow();
	    if (r > row) {
		row = r;
	    }
	}

	return row;
    }

    /**
     * Find out how many columns are in the matrix.
     * 
     * @return column count of the matrix
     */
    private int getMaxCol() {
	int col = 0, c;

	for (Result result : this.results) {
	    c = ((MatrixResult) result).getCol();
	    if (c > col) {
		col = c;
	    }
	}

	return col;
    }

    /**
     * Generate the final matrix from the results
     * 
     * @return A matrix from the results
     */
    public Matrix generateResultMatrix() {
	int row = this.getMaxRow(), col = this.getMaxCol();
	int[][] matrix = new int[row][col];
	MatrixResult res;

	for (Result result : this.results) {
	    res = (MatrixResult) result;
	    matrix[res.getRow()][res.getCol()] = res.getValue();
	}

	return (new Matrix(matrix));
    }
}
