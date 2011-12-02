package matrix;

public class Matrix {
    private int[][] data;
    private int row;
    private int col;
    
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
    
    public Matrix(int[][] data) {
	this.row = data.length;
	this.col = data[0].length;
	this.data = data;
    }
    
    public int getElement(int row, int col) {
	return this.data[row][col];
    }
    
    public int[][] getData() {
        return data;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
