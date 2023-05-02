package main.page;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPage extends Page{
  public StartPage(Font font){
    super(font);
    setOpaque(true);
    setBackground(darkModeBgColor);

    add(makeZhtwTitle(), Integer.valueOf(0));
    add(makeEnTitle(), Integer.valueOf(0));
    add(make2button(), Integer.valueOf(0));
    add(makeVersion(), Integer.valueOf(1));
  }

  private JLabel makeZhtwTitle(){
    JLabel titleZhtw = new BetterTextBox("迷因產生器", 60, null, false, SwingConstants.CENTER, null);
    titleZhtw.setBounds(-6, 20, WINDOW_WIDTH, 70);
    return titleZhtw;
  }
  private JLabel makeEnTitle(){
    JLabel titleEn = new BetterTextBox("meme-inator", 16, null, false, SwingConstants.CENTER, null);
    titleEn.setBounds(-6, 90, WINDOW_WIDTH, 20);
    return titleEn;
  }
  private JPanel make2button(){
    int buttonGap = 6;

    JPanel twoButton = new JPanel(new GridLayout(1, 2, buttonGap, 0));
    twoButton.setBounds(buttonGap*2, 130, WINDOW_WIDTH-buttonGap*6, 500);
    twoButton.setBackground(darkModeBgColor);
    
    JButton blankStartButton = new BetterButton("從一個空白模板開始", 32, null, null, buttonGap, null); // if param == null -> use default setting
    twoButton.add(blankStartButton);

    JButton templateStartButton = new BetterButton("選擇想要的迷因模板", 32, null, null, buttonGap, null);
    twoButton.add(templateStartButton);

    return twoButton;
  }
  private JLabel makeVersion(){
    JLabel version = new BetterTextBox(VERSION, 16, null, false, SwingConstants.CENTER, null);
    version.setBounds(-6, 644, WINDOW_WIDTH, 20);
    return version;
  }
}