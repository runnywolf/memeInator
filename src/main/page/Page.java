package main.page;

import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Page extends JLayeredPane{
  protected final String VERSION = "v0.dev-12";
  protected final int WINDOW_WIDTH = 1080;
  protected final int WINDOW_HEIGHT = 720;
  private JPanel pane;
  private Font font;
  protected Color darkModeBgColor;
  protected Color darkModeLightColor1;
  private int nextPageID;

  protected Page(JPanel pane, Font font){
    this.pane = pane;
    this.font = font;
    darkModeBgColor = new Color(40, 40, 40);
    darkModeLightColor1 = new Color(255, 255, 255);

    setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    setOpaque(true);
    setBackground(darkModeBgColor);
    nextPageID = -1;
  }

  protected Font getF(){ // only for Page constructor
    return font;
  }
  protected Font getF(float fontSize){ // get the font, only font size
    return font.deriveFont(Font.PLAIN, fontSize);
  }
  protected Font getF(float fontSize, int style){ // get the font, but you can choose style and size
    return font.deriveFont(style, fontSize);
  }

  protected void changePage(int nextPageID){
    this.nextPageID = nextPageID;
  }
  // change page to (Page)nextPage

  public int getNextPage(){
    return nextPageID;
  }
  // get nextPage. if nextPage != null -> need to change page.

  protected class BetterTextBox extends JLabel{
    public BetterTextBox(String text, float textSize, Color textColor, boolean isItalic, int horizonMode, Color bgColor){
      super(text, horizonMode);
      setFont(getF(textSize, isItalic?Font.ITALIC:Font.PLAIN));
      setForeground((textColor != null)?textColor:darkModeLightColor1);
      if (bgColor != null){
        setOpaque(true);
        setBackground(bgColor);
      }
    }
  }
  /* 參數             | 若填null則自動設為... | 效果
   * String text      | 必填                 | 文字
   * int textSize     | 必填                 | 文字大小
   * Color textColor  | darkModeLightColor1  | 文字顏色
   * boolean isItalic | 必填                 | 是否斜體
   * int horizonMode  | 必填                 | 向左對齊 -> SwingConstants.LEFT
   *                                           置中對齊 -> SwingConstants.CENTER
   *                                           向右對齊 -> SwingConstants.RIGHT
   * Color bgColor    | darkModeBgColor      | 背景顏色
  */

  protected class BetterButton extends JButton{
    private String oldText;
    private Color oldTextColor;
    private Color oldBgColor;

    public BetterButton(String text, int textSize, Color textColor, Color bgColor, int borderWidth, Color borderColor){
      super(text);
      setFont(getF(textSize));
      setForeground((textColor != null)?textColor:darkModeLightColor1);
      setBackground((bgColor != null)?bgColor:darkModeBgColor);
      setBorder(BorderFactory.createLineBorder((borderColor != null)?borderColor:darkModeLightColor1, borderWidth));
      setFocusPainted(false);
    }
    public void whenHover(String text, Color textColor, Color bgColor){
      addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent me){
          oldText = getText();
          oldTextColor = getForeground();
          oldBgColor = getBackground();
          setText((text != null)?text:oldText);
          setForeground((textColor != null)?textColor:darkModeBgColor);
          setBackground((bgColor != null)?bgColor:darkModeLightColor1);
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
          CardLayout cardLayout = (CardLayout) pane.getLayout();
          cardLayout.show(pane, nextPage);
        }
      });
    }
  }
  /* BetterButton():
   * 參數              | 若填null則自動設為... | 效果
   * String text       | 必填                 | 按鈕內的文字
   * int textSize      | 必填                 | 文字大小
   * Color textColor   | darkModeLightColor1  | 文字顏色
   * Color bgColor     | darkModeBgColor      | 按鈕背景顏色
   * int borderWidth   | 必填                 | 邊框粗度
   * Color borderColor | darkModeLightColor1  | 邊框顏色
  */
  /* .whenHover():
   * 參數              | 若填null則自動設為... | 效果
   * String text       | 原本的字串           | 當滑鼠移到按鈕上, 文字改變
   * Color textColor   | darkModeBgColor      | 當滑鼠移到按鈕上, 文字改變顏色
   * Color bgColor     | darkModeLightColor1  | 當滑鼠移到按鈕上, 背景改變顏色
  */
  /* .whenClickGoto():
   * 參數            | 若填null則自動設為... | 效果
   * String nextPage | 必填                 | 當按鈕被點擊時, 切換到頁面nextPage, nextPage為page class name
  */
}