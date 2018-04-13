package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyIconButton;
import javax.swing.*;

public class PanelMenu extends JPanel
{
    public MyIconButton btnMenuProject,btnMenuExperiment,btnMenuTest;
    public MyIconButton btnMenuNewCurve, btnMenuEstimateCurve, btnMenuLockedCurve;
    public MyIconButton btnMenuBatchNew, btnMenuBatchQuery;
    public MyIconButton btnMenuOption,btnMenuExit;

    public PanelMenu() {
        initSelf();
        initButtonMenu();
    }


    private void initSelf() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Const.GREEN);
        this.setBounds(0, 0, 120,Const.APP_HEIGHT);
    }

    private void initButtonMenu(){
        JLabel lblProject = new JLabel(Const.ICON_LABLE_PROJECT);
        JLabel lblCurve = new JLabel(Const.ICON_LABLE_CURVE);
        JLabel lblBatch = new JLabel(Const.ICON_LABLE_BATCH);
        JLabel lblSystem = new JLabel(Const.ICON_LABLE_SYSTEM);

        //项目管理、实验管理、测试管理
        btnMenuProject = new MyIconButton(Const.MENU_PROJECT, Const.MENU_PROJECT_CHECKED, Const.MENU_PROJECT);
        btnMenuExperiment = new MyIconButton(Const.MENU_EXPERIMENT, Const.MENU_EXPERIMENT_CHECKED, Const.MENU_EXPERIMENT);
        btnMenuTest = new MyIconButton(Const.MENU_TEST, Const.MENU_TEST_CHECKED, Const.MENU_TEST);

        //生成曲线、评估曲线、历史曲线
        btnMenuNewCurve = new MyIconButton(Const.MENU_NEW_CURVE, Const.MENU_NEW_CURVE_CHECKED, Const.MENU_NEW_CURVE);
        btnMenuEstimateCurve = new MyIconButton(Const.MENU_ESTIMATE_CURVE, Const.MENU_ESTIMATE_CURVE_CHECKED, Const.MENU_ESTIMATE_CURVE);
        btnMenuLockedCurve = new MyIconButton(Const.MENU_LOCKED_CURVE, Const.MENU_LOCKED_CURVE_CHECKED, Const.MENU_LOCKED_CURVE);

        //新建批号、追溯批号
        btnMenuBatchNew = new MyIconButton(Const.MENU_NEW_BATCH, Const.MENU_NEW_BATCH_CHECKED, Const.MENU_NEW_BATCH);
        btnMenuBatchQuery = new MyIconButton(Const.MENU_QUERY_BATCH, Const.MENU_QUERY_BATCH_CHECKED, Const.MENU_QUERY_BATCH);

        //设置、退出
        btnMenuOption = new MyIconButton(Const.MENU_OPTION, Const.MENU_OPTION_CHECKED, Const.MENU_OPTION);
        btnMenuExit = new MyIconButton(Const.MENU_EXIT, Const.MENU_EXIT_CHECKED, Const.MENU_EXIT);

        this.add(lblProject);
        this.add(btnMenuProject);
        this.add(btnMenuExperiment);
        this.add(btnMenuTest);
        this.add(lblCurve);
        this.add(btnMenuNewCurve);
        this.add(btnMenuEstimateCurve);
        this.add(btnMenuLockedCurve);
        this.add(lblBatch);
        this.add(btnMenuBatchNew);
        this.add(btnMenuBatchQuery);
        this.add(lblSystem);
        this.add(btnMenuOption);
        this.add(btnMenuExit);
    }


}
