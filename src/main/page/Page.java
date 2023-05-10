package main.page;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.MemeInator;

public class Page extends JLayeredPane{
  protected final String VERSION;
  protected final int WINDOW_WIDTH;
  protected final int WINDOW_HEIGHT;
  protected Color appBgColor;
  protected Color appLightColor1;
  private JPanel app;
  private Font font;

  protected Page(MemeInator frame){
    VERSION = frame.getVersion();
    WINDOW_WIDTH = frame.getWindowWidth();
    WINDOW_HEIGHT = frame.getWindowHeight();
    appBgColor = new Color(40, 40, 40);
    appLightColor1 = new Color(255, 255, 255);
    app = frame.getApp();
    font = frame.getF();

    setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    setOpaque(true);
    setBackground(appBgColor);
  }

  public Font getF(){ // only for Page constructor
    return font;
  }
  public Font getF(float fontSize){ // get the font, only font size
    return font.deriveFont(Font.PLAIN, fontSize);
  }
  public Font getF(float fontSize, int style){ // get the font, but you can choose style and size
    return font.deriveFont(style, fontSize);
  }

  protected void changePage(String pageName){
    CardLayout cardLayout = (CardLayout) app.getLayout();
    cardLayout.show(app, pageName);
  }
  // change page to page "pageName"

  public class BetterLabel extends JLabel{
    public BetterLabel(String text, float textSize, Color textColor, boolean isItalic, int horizonMode, Color bgColor){
      super(text, horizonMode);
      setFont(getF(textSize, isItalic?Font.ITALIC:Font.PLAIN));
      setForeground((textColor != null)?textColor:appLightColor1);
      if (bgColor != null){
        setOpaque(true);
        setBackground(bgColor);
      }
    }
  }
  /* BetterLabel():
   * 參數             | 若填null則自動設為... | 效果
   * String text      | 必填                 | 文字
   * int textSize     | 必填                 | 文字大小
   * Color textColor  | appLightColor1       | 文字顏色
   * boolean isItalic | 必填                 | 是否斜體
   * int horizonMode  | 必填                 | 向左對齊 -> SwingConstants.LEFT
   *                                           置中對齊 -> SwingConstants.CENTER
   *                                           向右對齊 -> SwingConstants.RIGHT
   * Color bgColor    | appBgColor           | 背景顏色
  */

  public class BetterButton extends JButton{
    private String oldText;
    private Color oldTextColor;
    private Color oldBgColor;

    public BetterButton(String text, int textSize, Color textColor, Color bgColor, int borderWidth, Color borderColor){
      super((text != null)?text:"");

      if (textSize > 0){
        setFont(getF(textSize));
        setForeground((textColor != null)?textColor:appLightColor1);
      }

      setBackground((bgColor != null)?bgColor:appBgColor);

      if (borderWidth == 0) setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0));
      else setBorder(BorderFactory.createLineBorder((borderColor != null)?borderColor:appLightColor1, borderWidth));
      
      setFocusPainted(false);
    }
    public void setBgImage(String path){
      setIcon(new ImageIcon(path));
    }
    public void whenHover(String text, Color textColor, Color bgColor, String tip){
      if (tip != null) setToolTipText(tip);

      addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent me){
          oldText = getText();
          oldTextColor = getForeground();
          oldBgColor = getBackground();
          setText((text != null)?text:oldText);
          setForeground((textColor != null)?textColor:appBgColor);
          setBackground((bgColor != null)?bgColor:appLightColor1);
        }
        @Override
        public void mouseExited(MouseEvent me){
          setText(oldText);
          setForeground(oldTextColor);
          setBackground(oldBgColor);
        }
      });
    }
    public void whenClickGoto(String nextPage){
      addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          changePage(nextPage);
        }
      });
    }
  }
  /* BetterButton():
   * 參數              | 若填null則自動設為... | 效果
   * String text       | "" (空字串)          | 按鈕內的文字
   * int textSize      | 必填                 | 文字大小
   * Color textColor   | appLightColor1       | 文字顏色
   * Color bgColor     | appBgColor           | 按鈕背景顏色
   * int borderWidth   | 必填                 | 邊框粗度
   * Color borderColor | appLightColor1  | 邊框顏色
  */
  /* .setBgImage():
   * 參數        | 若填null則自動設為... | 效果
   * String path | 必填                 | 設定按鈕的背景圖片
  */
  /* .whenHover():
   * 參數              | 若填null則自動設為... | 效果
   * String text       | 原本的字串            | 當滑鼠移到按鈕上, 文字改變
   * Color textColor   | appBgColor      | 當滑鼠移到按鈕上, 文字改變顏色
   * Color bgColor     | appLightColor1  | 當滑鼠移到按鈕上, 背景改變顏色
   * String tip        | 提示框不會出現        | 當滑鼠移到按鈕上, 會出現提示框
  */
  /* .whenClickGoto():
   * 參數            | 若填null則自動設為... | 效果
   * String nextPage | 必填                 | 當按鈕被點擊時, 切換到頁面nextPage, nextPage為page class name
  */
}