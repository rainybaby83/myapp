
package dhyx.Class;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class MyScrollBarUI extends BasicScrollBarUI {

    public MyScrollBarUI(){
        super();
        configureScrollBarColors();
    }

    @Override
    protected void configureScrollBarColors() {
        //滚动滑块的颜色
        super.thumbColor = Const.GREEN_LIGHT;
        //滚动条上阴影
        super.thumbHighlightColor = Const.GREEN_LIGHT;
        //滚动条下阴影
        super.thumbLightShadowColor = Const.GREEN_LIGHT;
        super.thumbDarkShadowColor = Const.GREEN_LIGHT;

        //背景颜色
        super.trackColor = new Color(250,250,250);
//        super.trackHighlightColor = Color.red;
        super.scrollBarWidth = 15;
    }

    @Override
    protected JButton createDecreaseButton(int orientation)  {
        return new BasicArrowButton(orientation,
                Color.white,
                //右下角高亮1
                Color.white,
                //三角箭头+ 右下角高亮
                Color.gray,
                //右下角高亮2
                Color.white);
    }

    @Override
    protected JButton createIncreaseButton(int orientation)  {
        return new BasicArrowButton(orientation,
                Color.white,
                //右下角高亮1
                Color.white,
                //三角箭头+ 右下角高亮
                Color.gray,
                //右下角高亮2
                Color.white);
    }
}
