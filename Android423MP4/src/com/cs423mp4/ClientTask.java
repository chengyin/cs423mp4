package com.cs423mp4;

import runner.matrix.MatrixClientRunner;
import android.os.AsyncTask;

/**
 * Make all client activity separate from UI
 */
public class ClientTask extends AsyncTask<MatrixClientRunner, Void, Void> {

    /* Run in the background for client
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected Void doInBackground(MatrixClientRunner... params) {
	MatrixClientRunner mcr = params[0];
	
	mcr.run();
	
	return null;
    }

}
