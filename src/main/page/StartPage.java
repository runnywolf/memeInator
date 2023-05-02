package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPage extends Page{
  public StartPage(int w, int h, Font font){
    super(w, h, font);
    setOpaque(true);
    setBackground(Color.BLACK);

    add(getZhtwTitle(), Integer.valueOf(0));
    add(getEnTitle(), Integer.valueOf(0));
    add(get2button(), Integer.valueOf(0));
  }

  private JLabel getZhtwTitle(){
    JLabel titleZhtw = new JLabel("迷因產生器", SwingConstants.CENTER);
    titleZhtw.setFont(getF(60));
    titleZhtw.setBounds(0, 20, windowWidth, 70);
    titleZhtw.setForeground(Color.WHITE);
    return titleZhtw;
  }
  private JLabel getEnTitle(){
    JLabel titleEn = new JLabel("meme-inator", SwingConstants.CENTER);
    titleEn.setFont(getF(16));
    titleEn.setBounds(0, 90, windowWidth, 20);
    titleEn.setForeground(darkModeBgColor);
    return titleEn;
  }
  private JPanel get2button(){
    JPanel twoButton = new JPanel(new GridLayout(1, 2));
    twoButton.setBounds(0, 130, windowWidth, 500);
    
    JButton blankStartButton = new BetterButton("從一個空白模板開始", 32, darkModeLightColor1, darkModeBgColor, 6, darkModeLightColor1);
    twoButton.add(blankStartButton);

    JButton templateStartButton = new BetterButton("選擇想要的迷因模板", 32, darkModeLightColor1, darkModeBgColor, 6, darkModeLightColor1);
    twoButton.add(templateStartButton);

    return twoButton;
  }
}