package com.dhyx.myclass;

import com.dhyx.MainApp;
import com.mindfusion.charting.*;
import com.mindfusion.charting.swing.Dashboard;
import com.mindfusion.charting.swing.LayoutBuilder;
import com.mindfusion.drawing.DashStyle;
import com.mindfusion.drawing.SolidBrush;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定义的统计面板
 * @author R2
 * 2018.3.13
 */
public class MyChart extends Dashboard {


    public Axis xAxis;
    public Axis yAxis;
    private Plot2D gridArea;
    private ArrayList<Series> lineSeriesList = new ArrayList<>();
    private ArrayList<Series> x1x2SeriesList = new ArrayList<>();
    private ObservableList<Series> ols,ols2;
    private LineRenderer lineRenderer;
    private LineRenderer x1x2Renderer;
    private LayoutBuilder layoutBuilder;
    private Series2D defaultSeries = new Series2D(Arrays.asList(0D), Arrays.asList(0D), Collections.singletonList(""));

    private String projectID = "";
    private Statement stmt = MainApp.myDB.stmt;

    public MyChart() {
        super();
        initAxis();
        initGridArea(xAxis,yAxis);
        initRenderer();
        initOther();
    }


    private void initOther() {
        layoutBuilder = new LayoutBuilder(this);
        XAxisRenderer gridAreaXRenderer = new XAxisRenderer(xAxis);
        YAxisRenderer gridAreaYRenderer = new YAxisRenderer(yAxis);
        layoutBuilder.createAndAddPlotWithBottomAndLeftAxes(gridArea, gridAreaXRenderer, gridAreaYRenderer);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue));
    }


    /**
     * 初始化网格区域
     */
    private void initGridArea(Axis xAxis, Axis yAxis) {
        //网格
        gridArea = new Plot2D();
        //网格区域背景色，None时才显示
        gridArea.setBackground(new SolidBrush(Color.white));
        //网格区域类型
        gridArea.setGridType(GridType.Crossed);
        //网格区域背景色
        gridArea.setGridColor1(Color.white);
        gridArea.setGridColor2(Color.white);
        //网格线类型，实线虚线
        gridArea.setGridLineStyle(DashStyle.Dash);
        //网格线颜色
        gridArea.setGridLineColor(new Color(222, 222, 222));
        //网格线对应的坐标轴
        gridArea.setXAxis(xAxis);
        gridArea.setYAxis(yAxis);
    }


    /**
     * 初始化坐标轴
     */
    private void initAxis() {
        xAxis = new Axis();
        xAxis.setMaxValue(300.0);
        xAxis.setMinValue(0.0);
        xAxis.setInterval(50);
        xAxis.setTitle("");

        yAxis = new Axis();
        yAxis.setMinValue(0.0);
        yAxis.setMaxValue(12000.0);
        yAxis.setInterval(2000);
        yAxis.setTitle("");
    }


    private void initRenderer() {
        //折线样式
        UniformSeriesStyle seriesStyle = new UniformSeriesStyle();
        seriesStyle.setUniformStroke(new SolidBrush(Color.blue));

        UniformSeriesStyle seriesStyle2 = new UniformSeriesStyle();
        seriesStyle2.setUniformStroke(new SolidBrush(Color.red));

        //可以拖动的坐标轴。去掉这四行，则不能拖动
        lineSeriesList.add(defaultSeries);
        ols = FXCollections.observableList(lineSeriesList);
        ols2 = FXCollections.observableList(x1x2SeriesList);
        lineRenderer = new LineRenderer(ols);
        x1x2Renderer =  new LineRenderer(ols2);
        lineRenderer.setSeriesStyle(seriesStyle);
        x1x2Renderer.setSeriesStyle(seriesStyle2);
        gridArea.getSeriesRenderers().add(lineRenderer);
        gridArea.getSeriesRenderers().add(x1x2Renderer);
    }

    /**
     * 更新统计图
     * @param mytable 传入的表格组件
     */
    public void updateChart(MyTable mytable,String varProjectID) {
        int rowCount = mytable.getRowCount();
        int columnCount = mytable.getColumnCount();
        lineSeriesList.clear();

        if (rowCount > 0) {
            Series2D[] seriesData = new Series2D[rowCount];
            java.util.List<Double> xList = new ArrayList<>();

            //每个series都要有x数据和y轴数据。由于x坐标相同，所以构建一个通用的x数据：xList
            for (int column = 1; column < columnCount; column++) {
                //第0列是TestID，所以从1开始
                xList.add(NumberUtils.toDouble(mytable.getColumnName(column)));
            }

            //循环构建Series2D数组
            for (int row = 0; row < rowCount; row++) {
                //构建每个Series2D的yList
                List<Double> yList = new ArrayList<>();
                for (int column = 1; column < columnCount; column++) {
                    yList.add(NumberUtils.toDouble(mytable.getValueAt(row, column).toString()));
                }

                //用xList和yList构建series2D数组
                seriesData[row] = new Series2D(xList, yList, null);
                lineSeriesList.add(seriesData[row]);

            }

            if (!projectID.equals(varProjectID)) {
                projectID = varProjectID;
            }

            //获取四个边界的x、y值
            int x[] = getXBorder();
            int y[] = new int[x.length];
            int tmpInt = 0;
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < rowCount; j++) {
                    tmpInt = (int) mytable.getValueAt(j, x[i]);
                    y[i] = Math.max(y[i], tmpInt);
                }
            }
            updateChartX1X2(x,y);

        } else {
            //没有任何测试数据时，用默认的点(0,0)构建seriesList
            lineSeriesList.clear();
            x1x2SeriesList.clear();
        }

        lineRenderer.dataChanged();
        x1x2Renderer.dataChanged();
    }


    /**
     * 根据项目x1、x2的左右位置，构建纵坐标界线
     */
    private void updateChartX1X2(int[] x, int[] y) {
        //全部循环完成后，将series2D构建为seriesList

        Series2D[] seriesData = new Series2D[4];
        x1x2SeriesList.clear();

        for (int i = 0; i < x.length; i++) {
            ArrayList<Double> xList = new ArrayList<>();
            ArrayList<Double> yList = new ArrayList<>();
            xList.add((double) x[i]);
            xList.add((double) x[i]);
            yList.add(0D);
            yList.add((double) y[i]);
            seriesData[i] = new Series2D(xList, yList, null);
            x1x2SeriesList.add(seriesData[i]);
        }

        ols2 = FXCollections.observableList(x1x2SeriesList);

    }


    private int[] getXBorder() {
        int[] x = new int[4];
        //获取四个边界
        try {
            String sql = "SELECT DISTINCT `X1左边界`,`X1右边界`,`X2左边界`,`X2右边界` FROM view_project " +
                    "WHERE `项目ID` = " + projectID ;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                for(int i = 0;i<4;i++) {
                    x[i] = rs.getInt(i+1);
                }
            }
        } catch (SQLException e) {
            for(int i = 0;i<4;i++) {
                x[i] = 0;
            }
        }
        return x;
    }

}
