package main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.page.StartPage;
import main.tool.CustomFont;

public class MemeInator extends JFrame{
  private JLayeredPane currentPage;
  private CustomFont customFont;

  public MemeInator(){
    super("迷因產生器");
    setSize(1080, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    customFont = new CustomFont("../font/NotoSansTC-Bold.otf");

    currentPage = new StartPage(customFont);
    add(currentPage);
  }
}