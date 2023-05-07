package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import main.page.*;
import main.external.NewFont;

public class MemeInator extends JFrame{
  private final String VERSION = "v0.dev-17";
  private final int WINDOW_WIDTH = 1080;
  private final int WINDOW_HEIGHT = 720;
  private final Color APP_BG_COLOR = new Color(40, 40, 40);
  private final Color APP_COLOR1 = new Color(255, 255, 255);
  private NewFont newFont;
  private JPanel app;
  
  public MemeInator(){
    super("迷因產生器");
    setSize(1080, 720); // window size
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false); // set for "can't change the window size"
    
    newFont = new NewFont("font/NotoSansTC-Bold.otf"); // import font at "memeInator/font/NotoSansTC-Bold.otf"

    SwingUtilities.invokeLater(new Runnable(){
      public void run(){init();}
    }); // init
  }

  private void init(){
    Font font = newFont.getF();
    
    UIManager.put("ToolTip.font", font.deriveFont(Font.PLAIN, 14));
    UIManager.put("ToolTip.foreground", new Color(255, 255, 127));
    UIManager.put("ToolTip.background", Color.BLACK);
    UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE, 1));
    ToolTipManager.sharedInstance().setInitialDelay(0);

    UIManager.put("TextField.font", font.deriveFont(Font.PLAIN, 16));
    UIManager.put("TextField.foreground", new Color(0, 255, 255));
    UIManager.put("TextField.background", new Color(80, 80, 80));
    UIManager.put("TextField.caretForeground", Color.WHITE);
    UIManager.put("TextField.margin", new Insets(0, 10, 0, 0));
    UIManager.put("TextField.border", BorderFactory.createLineBorder(Color.WHITE, 2));

    app = new JPanel(new CardLayout());
    app.add(new StartPage(this), "StartPage");
    app.add(new EditorPage(this), "SearchPage");
    app.add(new EditorPage(this), "EditorPage");

    setContentPane(app);
    setLocationByPlatform(true);
    setVisible(true);
  }

  public String getVersion(){return VERSION;}
  public int getWindowWidth(){return WINDOW_WIDTH;}
  public int getWindowHeight(){return WINDOW_HEIGHT;}
  public Color getAppBgColor(){return APP_BG_COLOR;}
  public Color getAppColor1(){return APP_COLOR1;}
  public Font getF(){return newFont.getF();}
  public JPanel getApp(){return app;}
}