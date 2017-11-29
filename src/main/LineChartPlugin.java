package main;

import interfaces.DialogBase;
import interfaces.Lorder;
import interfaces.PluginService;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.junit.Before;
import util.FileUtils;
import util.MySystem;
import view.*;

import java.io.File;
import java.lang.reflect.Method;
//import org.junit.Test;

public class LineChartPlugin extends PluginService implements CView.onClickListener {

	/*@Test
    public void testMethod(){
		System.out.println("test");
	}*/

    private CButton cButton01;
    private CButton cButton02;
    private CButton cButton03;
    private CButton cButton04;
    private CButton cButton05;
    private CButton cButton06;

    @Override
    public void onCreate(Lorder lorder) {
        super.onCreate(lorder);

        VerticleLayout verticleLayout = new VerticleLayout();
        verticleLayout.addView(LineChartHelper.test());
        setContentView("hello word", verticleLayout);

       /* cButton01 = new CButton("创建一个简单的pdf文件");
        cButton01.setId("id_001");
        cButton02 = new CButton("PDF文件设置文件属性");
        cButton02.setId("id_002");
        cButton03 = new CButton("PDF中添加图片");
        cButton03.setId("id_003");
        cButton04 = new CButton("PDF中创建表格");
        cButton04.setId("id_004");
        cButton05 = new CButton("PDF中创建列表");
        cButton05.setId("id_005");
        cButton06 = new CButton("PDF中设置样式/格式化输出，输出中文内容");
        cButton06.setId("id_006");

        CTextView cTextView = new CTextView();
        cTextView.setText("I am a textview please do not click me!");
        CEditView cEditView = new CEditView();
        cEditView.setSingleLine(true);
        cEditView.setSingleLine(true);
        cEditView.setText("我是一个单行的输入框");

        CImageView cImageView = new CImageView();
        cImageView.setImageResource("/fxml/image/folder_icon_16.png");

        CCheckBox cCheckBox = new CCheckBox();
        cCheckBox.setOnCheckedChangeListener(new CView.OnCheckedChangeListener() {
            @Override
            public void onCheckChanged(boolean b) {
                if (b) {
                    cCheckBox.setText("约");
                } else {
                    cCheckBox.setText("不约");
                }
            }
        });

        CProgressBar cProgressBar = new CProgressBar();
        cProgressBar.setProgress(0.5);

        //HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticleLayout verticleLayout = new VerticleLayout();
        verticleLayout.addView(cTextView);
        verticleLayout.addView(cButton01, cButton02, cButton03,cButton04,cButton05,cButton06);
        verticleLayout.addView(cEditView);
        verticleLayout.addView(cImageView);
        verticleLayout.addView(cCheckBox);
        verticleLayout.addView(cProgressBar);
        verticleLayout.addView(LineChartHelper.test());
        setContentView("hello word", verticleLayout);
        //cButton01.setText("弹窗按钮");
        cButton01.setOnClickListener(this);
        cButton02.setOnClickListener(this);
        cButton03.setOnClickListener(this);
        cButton04.setOnClickListener(this);
        cButton05.setOnClickListener(this);
        cButton06.setOnClickListener(this);
        System.out.println("A客户插件onCreate~");

        Thread thread = null;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 500; i++) {
                    //double a = Math.max(4.15, 4.153) * 3.1418926;
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    double b = i * 0.002;
                    Platform.runLater(new Runnable() {
                        public void run() {
                            cProgressBar.setProgress(b);
                            System.out.println("progress:" + b);
                        }
                    });
                }
                System.out.println("end");
            }
        });
        thread.start();*/
    }

    /**
     * 拦截器
     *
     * @param lorder
     * @return
     */
    public boolean intercept(Lorder lorder, DialogBase.DialogListener_Boolean listener_boolean) {
        String filePath = "D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf";
        if (!FileUtils.checkFileType(".pdf", filePath)) {
            System.out.println("文件不存在正在创建文件" + filePath + "。。。");
            //File file = new File(filePath);
            try {
                if (FileUtils.createFile(filePath)) {
                    System.out.println("文件创建成功");
                    return true;
                } else {
                    System.out.println("文件创建失败");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("文件创建失败");
                return false;
            }
        } else {
            //文件已存在是否要重写内容，重写会导致原文件内容丢失。是否继续
            lorder.showDialogBoolean("是否继续", "文件已存在是否要重写内容，重写会导致原文件内容丢失。是否继续", listener_boolean);
        }
        return true;
    }

    @Override
    public void onDestroy(Lorder lorder) {
        System.out.println("A客户插件onDestroy~");
    }
    //must

    public static void main(String[] args) {

    }

    @Override
    public void onClick(Parent parent) {

        intercept(mLorder, new DialogBase.DialogListener_Boolean() {
            @Override
            public void onClickedSure() {
                switch (parent.getId()) {
                    case "id_001":
                       // PDFHelper.makePDF01("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf");
                        break;
                    case "id_002":
                       // PDFHelper.makePDF02("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf");
                        break;
                    case "id_003":
                       // PDFHelper.makePDF03("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf", "C:/Users/huan/Desktop/ic_launcher.png", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2640570473,212514906&fm=27&gp=0.jpg");
                        break;
                    case "id_004":
                       // PDFHelper.makePDF04("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf");
                        break;
                    case "id_005":
                       // PDFHelper.makePDF05("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf");
                        break;
                    case "id_006":
                       // PDFHelper.makePDF06("D:/BaiduNetdiskDownload/PowerDesigner_15副本.pdf");
                        break;
                }
            }

            @Override
            public void onClickedCancle() {

            }
        });
        // lorder.showDialog("弹窗标题", "弹窗内容");
    }
}
