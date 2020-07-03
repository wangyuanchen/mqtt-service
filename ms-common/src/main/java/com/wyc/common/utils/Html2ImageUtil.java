package com.wyc.common.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * @author LiuMi
 * @version V1.0
 * @Package com.aisino.social.common.utils
 * @Description: html 转 图片工具类
 * @date 2019/9/23 -   19:47
 */
public class Html2ImageUtil {
    public static int DEFAULT_IMAGE_WIDTH = 1200;
    public static int DEFAULT_IMAGE_HEIGHT = 700;
    public static boolean paintPage(Graphics g, int hPage, int pageIndex, JTextPane panel) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = ((BasicEditorPaneUI) panel.getUI()).getPreferredSize(panel);
        double panelHeight = d.height;
        double pageHeight = hPage;
        int totalNumPages = (int) Math.ceil(panelHeight / pageHeight);
        g2.translate(0f, -(pageIndex - 1) * pageHeight);
        panel.paint(g2);
        boolean ret = true;

        if (pageIndex >= totalNumPages) {
            ret = false;
            return ret;
        }
        return ret;
    }
    /**
     * html转换为ｊｐｅｇ文件
     * @param bgColor 图片的背景色
     * @param html html的文本信息
     * @param width 显示图片的Ｔｅｘｔ容器的宽度
     * @param height 显示图片的Ｔｅｘｔ容器的高度
     * @param eb 設置容器的边框
     * @param outputStream  输出流
     * @return
     * @throws Exception
     */
    public static void html2jpeg(Color bgColor, String html, int width,
                                 int height, EmptyBorder eb, ServletOutputStream outputStream) throws Exception {
        JTextPane tp = new JTextPane();
        tp.setSize(width, height);
        if (eb == null) {
            eb = new EmptyBorder(0, 50, 0, 50);
        }
        if (bgColor != null) {
            tp.setBackground(bgColor);
        }
        if (width <= 0) {
            width = DEFAULT_IMAGE_WIDTH;
        }
        if (height <= 0) {
            height = DEFAULT_IMAGE_HEIGHT;
        }
        tp.setBorder(eb);
        tp.setContentType("text/html");
        tp.setText(html);

        int pageIndex = 1;
        boolean bcontinue = true;
        while (bcontinue) {
            BufferedImage image = new BufferedImage(width,
                    height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.setClip(0, 0, width, height);
            bcontinue = paintPage(g, height, pageIndex, tp);
            g.dispose();
            ImageIO.write(image, "jpg", outputStream);
        }
    }
}
