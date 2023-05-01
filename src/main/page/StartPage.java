package main.page;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.tool.CustomFont;

public class StartPage extends JLayeredPane{
  private CustomFont font;

  public StartPage(CustomFont f){
    font = f;
    add2button();
  }

  private void add2button(){
    JPanel twoButton = new JPanel(new GridLayout(1, 2));
    twoButton.setBounds(0, 100, 1080, 500);
    
    JButton blankStartButton = new JButton("從一個空白模板開始");
    blankStartButton.setFont(font.get(32));
    blankStartButton.setFocusPainted(false);
    twoButton.add(blankStartButton);

    JButton templateStartButton = new JButton("選擇想要的迷因模板");
    templateStartButton.setFont(font.get(32));
    templateStartButton.setFocusPainted(false);
    twoButton.add(templateStartButton);

    add(twoButton, JLayeredPane.DEFAULT_LAYER);
  }
}