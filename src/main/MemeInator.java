package main;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.page.*;
import main.tool.NewFont;

public class MemeInator extends JFrame{
  private NewFont newFont;
  private Font font;
  private Page[] pageList;

  public MemeInator(){
    super("迷因產生器");
    setSize(1080, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false); // set for "can't change the window size"
    
    newFont = new NewFont("font/NotoSansTC-Bold.otf"); // import font at "memeInator/font/NotoSansTC-Bold.otf"
    font = newFont.getF();

    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        JPanel pane = new JPanel(new CardLayout());
        
        pageList = new Page[3];
        pageList[0] = new StartPage(pane, font);
        pageList[1] = new EditorPage(pane, font);
        pageList[2] = new EditorPage(pane, font);

        pane.add(pageList[0], "StartPage");
        pane.add(pageList[1], "SearchPage");
        pane.add(pageList[2], "EditorPage");

        setContentPane(pane);
        setLocationByPlatform(true);
        setVisible(true);
      }
    });
  }
}