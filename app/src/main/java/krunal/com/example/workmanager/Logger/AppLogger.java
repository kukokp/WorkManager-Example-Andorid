package krunal.com.example.workmanager.Logger;

import android.util.Log;

import krunal.com.example.workmanager.MainApplication;


public class AppLogger {
    public static final String TAG = "WorkManager";
    public static String logFolderName = "/Logs";
    public static String logFileName = "/Worker.log";
    public static String zipFileName = "/Worker_.zip";
    private static boolean isDisplayApplicationLog = true;

    public static void showVerboseLog(final String message) {
        if (isDisplayApplicationLog)
            Log.v(TAG, message);

        if (null != MainApplication.mLoggerInstance)
            MainApplication.mLoggerInstance.finest(message);
    }

    public static void showDebugLog(final String message) {
        if (isDisplayApplicationLog) {
            Log.d(TAG, message);
            if (null != MainApplication.mLoggerInstance)
                MainApplication.mLoggerInstance.finer(message);
        }
    }

    public static void showInfoLog(final String message) {
        if (isDisplayApplicationLog) {
            Log.i(TAG, message);
            if (null != MainApplication.mLoggerInstance)
                MainApplication.mLoggerInstance.fine(message);
        }
    }

    public static void showErrorLog(final String message) {
        if (isDisplayApplicationLog)
            Log.e(TAG, message);
        if (null != MainApplication.mLoggerInstance)
            MainApplication.mLoggerInstance.severe(message);
    }

    public static void showWarningLog(final String message) {
        if (isDisplayApplicationLog) {
            Log.w(TAG, message);
            if (null != MainApplication.mLoggerInstance)
                MainApplication.mLoggerInstance.warning(message);
        }
    }
}