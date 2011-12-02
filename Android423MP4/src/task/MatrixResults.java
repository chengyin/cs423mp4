package task;

import matrix.Matrix;

public class MatrixResults extends Results {
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
