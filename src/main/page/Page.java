package main.page;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class Page extends JLayeredPane{
  protected int windowWidth;
  protected int windowHeight;
  private Font font;
  protected Color darkModeBgColor;
  protected Color darkModeLightColor1;

  protected Page(int windowWidth, int windowHeight, Font font){
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.font = font;
    darkModeBgColor = new Color(30, 30, 30);
    darkModeLightColor1 = new Color(255, 255, 255);
  }

  protected Font getF(float fontSize){ // get the font, only font size
    return font.deriveFont(Font.PLAIN, fontSize);
  }
  protected Font getF(float fontSize, int style){ // get the font, but you can choose style and size
    return font.deriveFont(style, fontSize);
  }

  protected class BetterButton extends JButton{
    public BetterButton(String text, int textSize, Color textColor, Color bgColor, int borderWidth, Color borderColor){
      super(text);
      setFont(getF(textSize));
      setForeground(textColor);
      setBackground(bgColor);
      setBorder(BorderFactory.createLineBorder(borderColor, borderWidth));
      setFocusPainted(false);
    }
  }
}