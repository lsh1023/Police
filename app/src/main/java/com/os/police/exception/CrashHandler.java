package com.os.police.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;

import com.os.police.utils.AppToastMgr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by LSH on 2017/7/22.
 * 全局异常处理
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private static CrashHandler mInstance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //存储设备信息和异常信息
    private Map<String, String> mInfo = new HashMap<>();
    //文件日期格式
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CrashHandler() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CrashHandler getInstance() {
        if (mInstance == null) {
            synchronized (CrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandler();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        /**
         * 1.收集错误信息
         * 2.保存错误信息
         * 3.上传到服务器
         */

        if (!handleException(e)) {
            //未处理，调用系统默认的处理器处理
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(t, e);
            } else {
                //已经人为处理
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                //结束进程
                Process.killProcess(Process.myPid());
                //退出程序，0:正常退出，1:异常退出
                System.exit(1);
            }

        }


    }

    /**
     * 人为处理异常
     *
     * @param e
     * @return true:已经处理，false：未处理
     */
    private boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        }
        //Toast提示
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                AppToastMgr.ToastShortCenter(mContext, "UncaughtException");
                Looper.loop();
            }
        }.start();

        //收集错误信息
        collectionErrorInfo();
        //保存错误信息
        saveErrorInfo(e);
        return false;
    }

    private void saveErrorInfo(Throwable e) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : mInfo.entrySet()) {
            String keyName = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(keyName + "=" + value + "\n");
        }

        //装饰者模式
        Writer writer = new StringWriter();
        PrintWriter printerWriter = new PrintWriter(writer);
        e.printStackTrace(printerWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printerWriter);
            cause = e.getCause();
        }
        printerWriter.close();

        String result = writer.toString();
        stringBuffer.append(result);
        long curTime = System.currentTimeMillis();
        String time = dateFormat.format(new Date());
        String fileName = "crash-" + time + "-" + curTime + ".log";

        //判断有没有SD卡
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            String path = "/sdcard/crash";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + fileName);
                fos.write(stringBuffer.toString().getBytes());
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void collectionErrorInfo() {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = TextUtils.isEmpty(pi.versionName) ? "未设置版本名称" : pi.versionName;
                String versionCode = pi.versionCode + "";
                mInfo.put("versionName", versionName);
                mInfo.put("versionCode", versionCode);
            }
            //反射
            Field[] fields = Build.class.getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    mInfo.put(field.getName(), field.get(null).toString());
                }
            }
        } catch (PackageManager.NameNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
