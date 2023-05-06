package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import main.page.*;
import main.tool.NewFont;

public class MemeInator extends JFrame{
  private NewFont newFont;

  public MemeInator(){
    super("迷因產生器");
    setSize(1080, 720); // window size
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false); // set for "can't change the window size"
    
    newFont = new NewFont("font/NotoSansTC-Bold.otf"); // import font at "memeInator/font/NotoSansTC-Bold.otf"

    SwingUtilities.invokeLater(new Runnable(){public void run(){init();}}); // init
  }

  private void init(){
    Font font = newFont.getF();
    
    UIManager.put("ToolTip.foreground", Color.WHITE);
    UIManager.put("ToolTip.background", Color.BLACK);
    ToolTipManager.sharedInstance().setInitialDelay(0);

    JPanel pane = new JPanel(new CardLayout());
    pane.add(new StartPage(pane, font), "StartPage");
    pane.add(new EditorPage(pane, font), "SearchPage");
    pane.add(new EditorPage(pane, font), "EditorPage");

    setContentPane(pane);
    setLocationByPlatform(true);
    setVisible(true);
  }
}