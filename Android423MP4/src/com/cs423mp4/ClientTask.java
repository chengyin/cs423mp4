package com.cs423mp4;

import runner.matrix.MatrixClientRunner;
import android.os.AsyncTask;

/**
 * Make all client activity separate from UI
 */
public class ClientTask extends AsyncTask<MatrixClientRunner, Void, Void> {

    @Override
    protected Void doInBackground(MatrixClientRunner... params) {
	MatrixClientRunner mcr = params[0];
	
	mcr.run();
	
	return null;
    }

}
