import javax.swing.*;

public class Card extends JLabel {
    //point为点数，function表示近中远三个类型，依次为1、2、3
    private final int point;
    private final int function;
    public final boolean isHero;
    Icon Icon;
    boolean selected;

    int getFunction() {
        return this.function;
    }

    int getPoint() {
        return this.point;
    }
    //最基础卡牌生成目前需要图片路径，点数，近中远类型，是否是英雄牌
    Card(String ImgPath, int point, int function, boolean isHero) {
        this.point = point;
        this.function = function;
        this.isHero = isHero;
        //此大小仍然未知
        this.setSize(239, 270);
        try {
            Icon = new ImageIcon(ImgPath);
            this.setIcon(Icon);
        } catch (Exception e) {
            System.out.println("illegal path");
        }
        selected = false;
    }
}
