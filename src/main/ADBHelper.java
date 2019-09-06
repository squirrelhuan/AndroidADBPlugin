package main;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/** javaDB helper
 * Created by huan on 2017/11/30.
 */
public class ADBHelper {
    public static final int SUCCESS = 0;            // 表示程序执行成功

    public static final String COMMAND = "java.exe -version";    // 要执行的语句
    public static final String SUCCESS_MESSAGE = "执行成功：";
    public static final String ERROR_MESSAGE = "出错了：";
    public static String Path_Temp;
    public static String Path_Def;
    private String directory_path;
    private static ADBHelper instance;

    public static  ADBHelper getInstance(){
        if (instance==null){
            instance = new ADBHelper();
        }
        return instance;
    }

    private ADBHelper() {
        init();
    }

    private  void init() {
        File directory = new File("adb");//设定为当前文件夹
        try {
            System.out.println("获取标准的路径="+directory.getCanonicalPath());//获取标准的路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("获取绝对路径="+directory.getAbsolutePath());//获取绝对路径
        System.out.println("获取class路径="+getClass().getClass()
                .getResource("/")
                .toString());//获取绝对路径
        if (!directory.exists()) {
            System.out.println("未找到 adb可执行文件");//获取绝对路径
            return;
        } else {
            Path_Temp = directory.getAbsolutePath();
            directory_path = new File("").getAbsolutePath();
            Path_Temp = Path_Temp.substring(0, Path_Temp.lastIndexOf(File.separator));
            Path_Def = Path_Temp+File.separator+"res/screen_android/def.png";
            System.out.println("获取绝对路径" + Path_Temp);//获取绝对路径
        }
    }
    OnScreenChangeListener onScreenChangeListener;
    public void startScreen(OnScreenChangeListener onScreenChangeListener) {
        this.onScreenChangeListener = onScreenChangeListener;
        isRunning = true;
        execute(" adb kill-server ");
        execute(" adb start-server ");
        execute(" adb devices ");
        execute(" adb connect 192.168.199.180:5555");
        execute(" adb connect 192.168.199.180:5555");
        //execute("alias remote-screencap='adb shell screencap -p | sed 's/\r$//'");//设置别名
        String f = directory_path+"/resource/screen_android/";
        System.out.println(f);
        File file = new File(f);
        if(!file.exists()){
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new Thread(runnable).start();
    }

    boolean isRunning = true;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (isRunning) {
                execute("adb -s 192.168.199.180:5555 shell screencap -p /sdcard/tmp.png");
                execute("adb -s 192.168.199.180:5555 pull /sdcard/tmp.png "+directory_path+"/resource/screen_android/");
                //execute(" remote-screencap > screen.png");
                onScreenChangeListener.onRefresh(directory_path+"/resource/screen_android/tmp.png");
            }
        }
    };

    private static void execute(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            // 打印程序输出
            readProcessOutput(process);
            // 等待程序执行结束并输出状态
            int exitCode = process.waitFor();
            if (exitCode == SUCCESS) {
                System.out.println(SUCCESS_MESSAGE + cmd);
            } else {
                System.err.println(ERROR_MESSAGE + exitCode + "\n\r" + cmd);
                switch (exitCode){
                    case 255:
                        System.out.println("端口ADB被占用");
                        break;
                }
            }
            process.destroy();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 打印进程输出
     *
     * @param process 进程
     */
    private static void readProcessOutput(final Process process) {
        // 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印
        read(process.getInputStream(), System.out);
        read(process.getErrorStream(), System.err);
    }

    // 读取输入流
    private static void read(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startAdbServer() {
        execute(" adb start-server ");
    }

    public void stopAdbServer() {
        execute(" adb kill-server ");
    }

    public void findDeviceList() {
        execute(" adb devices ");
    }

    public void stopScreen() {
        isRunning = false;
    }

    public static interface OnScreenChangeListener{
        void onRefresh(String path);
    }

}