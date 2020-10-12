package krunal.com.example.workmanager;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import krunal.com.example.workmanager.Logger.AppLogger;
import krunal.com.example.workmanager.Logger.MyFormatter;

public class MainApplication extends Application {

    public static Logger mLoggerInstance = null;
    private static MainApplication mInstance;
    private static Context mAppContext;
    private final String TAG = "[ MainApplication ]";

    public static Context getAppContext() {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext) {
        MainApplication.mAppContext = mAppContext;
    }

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setAppContext(getApplicationContext());
        initLogger(mAppContext);

    }

    private String createLoggingFolder(Context mContext) {
        if (null == mContext)
            mContext = getApplicationContext();
        String Dir = mContext.getExternalFilesDir(null) + "";
        File dir = new File(Dir + AppLogger.logFolderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    private void initLogger(final Context mContext) {
        try {
            mLoggerInstance = Logger.getLogger(AppLogger.TAG);
            String loggingPath = createLoggingFolder(mContext);
            AppLogger.showDebugLog("[ MainApplication ]  :: initLogger ::  loggingPath: [" + loggingPath + "]");
            System.setProperty("user.home", loggingPath);
            mLoggerInstance.setLevel(Level.ALL);
            FileHandler handler = new FileHandler("%h" + AppLogger.logFileName, 2000000, 5, true);
            handler.setFormatter(new MyFormatter());
            mLoggerInstance.addHandler(handler);
            // display the log in  Console Tray.
            ConsoleHandler consoleHandler = new ConsoleHandler();
            mLoggerInstance.addHandler(consoleHandler);
            handler.setLevel(Level.ALL);
            consoleHandler.setLevel(Level.ALL);
        } catch (Resources.NotFoundException e) {
            AppLogger.showErrorLog("[ MainApplication ]  ::  NotFoundException ::" + e.toString());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            AppLogger.showErrorLog("[ MainApplication ]  ::  FileNotFoundException ::" + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            AppLogger.showErrorLog("[ MainApplication ]  ::  IOException ::" + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            AppLogger.showErrorLog("[ MainApplication ]  ::  Exception ::" + e.toString());
            e.printStackTrace();
        }
    }
}