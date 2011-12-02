package matrix;

/**
 * A wrapper for matrix, using int[][] for storages.
 * 
 * @author chengyin
 * 
 */
public class Matrix {
    private int[][] data;
    private int row;
    private int col;

    /**
     * Generate a (row) by (col) matrix filled with (value)
     * 
     * @param row row count for the matrix
     * @param col column count for the matrix
     * @param value desired value in the matrix
     */
    public Matrix(int row, int col, int value) {
	this.row = row;
	this.col = col;
	this.data = new int[row][col];

	for (int r = 0; r < row; r++) {
	    for (int c = 0; c < col; c++) {
		this.data[r][c] = value;
	    }
	}
    }

    /**
     * Generate a matrix for an 2d array
     * 
     * @param data the matrix array
     */
    public Matrix(int[][] data) {
	this.row = data.length;
	this.col = data[0].length;
	this.data = data;
    }

    /**
     * Get an element from the matrix
     * @param row
     * @param col
     * @return element at (row, col)
     */
    public int getElement(int row, int col) {
	return this.data[row][col];
    }

    /**
     * @return raw data of the matrix
     */
    public int[][] getData() {
	return data;
    }

    /**
     * @return row count of the matrix
     */
    public int getRow() {
	return row;
    }

    /**
     * @return col count of the matrix
     */
    public int getCol() {
	return col;
    }
}
