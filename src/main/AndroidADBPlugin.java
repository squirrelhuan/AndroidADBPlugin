package main;

import interfaces.Lorder;
import interfaces.PluginService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import org.junit.Test;
import view.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by squirrel桓 on 2017/11/29.
 */
public class AndroidADBPlugin extends PluginService implements CView.onClickListener {
    //final static Tooltip tooltip = new Tooltip();


    ADBHelper adbHelper = ADBHelper.getInstance();

    public static void main(String[] args) {
        ADBHelper adbHelper = ADBHelper.getInstance();

        adbHelper.startScreen(new ADBHelper.OnScreenChangeListener() {
            @Override
            public void onRefresh(String path) {
                System.out.println("刷新" + path);
            }
        });
    }

    private CButton cButton01;
    private CButton cButton02;
    private CButton cButton03;
    private CButton cButton04;
    private CButton cButton05;
    private CButton cButton06;

    @Override
    public void onCreate(Lorder lorder) {
        super.onCreate(lorder);


        VerticleLayout rootLayout = new VerticleLayout();
        setContentView("ADB插件", rootLayout);


        HorizontalLayout menuLayout = new HorizontalLayout();
        rootLayout.addView(menuLayout);

        HorizontalLayout bodyLayout = new HorizontalLayout();
        rootLayout.addView(bodyLayout);

        VerticleLayout devListLayout = new VerticleLayout();
        VerticleLayout verticleLayout = new VerticleLayout();

        bodyLayout.addView(devListLayout);
        bodyLayout.addView(verticleLayout);

        cButton01 = new CButton("开启 adb server");
        cButton01.setId("id_001");
        menuLayout.addView(cButton01);

        cButton02 = new CButton("关闭 adb server");
        cButton02.setId("id_002");
        menuLayout.addView(cButton02);

        cButton03 = new CButton("查看设备列表");
        cButton03.setId("id_003");
        menuLayout.addView(cButton03);

        cButton04 = new CButton("投屏");
        cButton04.setId("id_004");
        menuLayout.addView(cButton04);


        cButton05 = new CButton("停止投屏");
        cButton05.setId("id_005");
        menuLayout.addView(cButton05);


        cButton06 = new CButton("投影");
        cButton06.setId("id_006");
        //verticleLayout.addView(cButton06);

        CImageView cImageView = new CImageView();//adbHelper.Path_Def,100,100,false,false
        cImageView.setImageResource("file:"+adbHelper.Path_Def);//"/fxml/image/folder_icon_16.png"
        cImageView.setFitHeight(500);
        cImageView.setFitWidth(500);
        cImageView.setPreserveRatio(true);
        verticleLayout.addView(cImageView);


        cButton01.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClick(Parent parent) {
//                System.out.println("aaa");
                adbHelper.startAdbServer();
            }
        });
        cButton02.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClick(Parent parent) {
//                System.out.println("aaa");
                adbHelper.stopAdbServer();
            }
        });
        cButton03.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClick(Parent parent) {
//                System.out.println("aaa");
                adbHelper.findDeviceList();
            }
        });
        cButton04.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClick(Parent parent) {
//                System.out.println("aaa");
                adbHelper.startScreen(new ADBHelper.OnScreenChangeListener() {
                    @Override
                    public void onRefresh(String path) {
                        System.out.println("刷新" + path);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cImageView.setImageResource(path);
                            }
                        });
                    }
                });
            }
        });
        cButton05.setOnClickListener(new CView.onClickListener() {
            @Override
            public void onClick(Parent parent) {
//                System.out.println("aaa");
                adbHelper.stopScreen();
            }
        });

    }

    @Override
    public void onClick(Parent parent) {

    }


}
