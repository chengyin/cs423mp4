package com.cs423mp4;

import runner.matrix.MatrixServerRunner;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;

public class ServerTask extends AsyncTask<MatrixServerRunner, Void, Void> {

    @Override
    protected Void doInBackground(MatrixServerRunner... params) {
	MatrixServerRunner serverRunner = params[0];

	Log.e("423-server", "Ready to run server");
	serverRunner.run();
	serverRunner.close();
	Log.e("423-server", "DONE");

	return null;
    }

}
