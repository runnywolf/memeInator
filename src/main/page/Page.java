package main.page;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Page extends JLayeredPane{
  protected final String VERSION = "v0.dev-9";
  protected final int WINDOW_WIDTH = 1080;
  protected final int WINDOW_HEIGHT = 720;
  private Font font;
  protected Color darkModeBgColor;
  protected Color darkModeLightColor1;

  protected Page(Font font){
    this.font = font;
    darkModeBgColor = new Color(40, 40, 40);
    darkModeLightColor1 = new Color(255, 255, 255);
  }

  protected Font getF(float fontSize){
    return font.deriveFont(Font.PLAIN, fontSize);
  }
  // get the font, only font size

  protected Font getF(float fontSize, int style){
    return font.deriveFont(style, fontSize);
  }
  // get the font, but you can choose style and size

  protected class BetterTextBox extends JLabel{
    public BetterTextBox(String text, int textSize, Color textColor, boolean isItalic, int horizonMode, Color bgColor){
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
    public BetterButton(String text, int textSize, Color textColor, Color bgColor, int borderWidth, Color borderColor){
      super(text);
      setFont(getF(textSize));
      setForeground((textColor != null)?textColor:darkModeLightColor1);
      setBackground((bgColor != null)?bgColor:darkModeBgColor);
      setBorder(BorderFactory.createLineBorder((borderColor != null)?borderColor:darkModeLightColor1, borderWidth));
      setFocusPainted(false);
    }
  }
  /* 參數              | 若填null則自動設為... | 效果
   * String text       | 必填                 | 按鈕內的文字
   * int textSize      | 必填                 | 文字大小
   * Color textColor   | darkModeLightColor1  | 文字顏色
   * Color bgColor     | darkModeBgColor      | 按鈕背景顏色
   * int borderWidth   | 必填                 | 邊框粗度
   * Color borderColor | darkModeLightColor1  | 邊框顏色
  */
}