package matrix;

public class Matrix {
    private int[][] data;
    
    public Matrix(int row, int col, int value) {
	this.data = new int[row][col];
	
	for (int r = 0; r < row; r++) {
	    for (int c = 0; c < col; c++) {
		this.data[r][c] = value;
	    }
	}
    }
}
