package main;

import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by squirrel桓 on 2017/11/29.
 */
public class LineChartHelper {

    final static Tooltip tooltip = new Tooltip();
    public static Chart test() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final LineChart<String, Number> lineChart =
                new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        List series = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Portfolio 1");

            series1.getData().add(new XYChart.Data("Jan", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Feb", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Mar", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Apr", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("May", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Jun", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Jul", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Aug", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Sep", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Oct", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Nov", random.nextInt(100)));
            series1.getData().add(new XYChart.Data("Dec", random.nextInt(100)));
            /*series1.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                //if (e.getButton() == MouseButton.SECONDARY)
                //   cm.show(pic, e.getScreenX(), e.getScreenY());
                tooltip.setText("point");
            });*/
            series.add(series1);
        }

        // Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series);
        return lineChart;
    }

}
