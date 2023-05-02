package main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.page.StartPage;
import main.tool.NewFont;

public class MemeInator extends JFrame{
  private JLayeredPane currentPage;
  private NewFont customFont;

  public MemeInator(){
    super("迷因產生器");
    setSize(1080, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false); // set for "can't change the window size"

    customFont = new NewFont("../font/NotoSansTC-Bold.otf"); // import font at "memeInator/font/NotoSansTC-Bold.otf"

    currentPage = new StartPage(1080, 720, customFont.getF()); // set the start page
    add(currentPage);
  }

  private void changePage(JLayeredPane nextPage){

  }
}