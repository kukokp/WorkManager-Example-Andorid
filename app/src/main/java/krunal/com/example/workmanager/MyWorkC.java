package krunal.com.example.workmanager;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import krunal.com.example.workmanager.Logger.AppLogger;

public class MyWorkC extends Worker {

    private static final String TAB = MyWorkC.class.getSimpleName();

    public MyWorkC(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        AppLogger.showDebugLog(TAB+" My WorkC");
        return Result.success();
    }
}
