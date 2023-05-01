package main.tool;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont{
  private Font font;

  public CustomFont(String path){
    try{
      font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(font);
    }catch (IOException|FontFormatException e) {
      font = Font.getFont(Font.DIALOG);
    }
  }

  public Font get(float fontSize){
    return font.deriveFont(Font.PLAIN, fontSize);
  }
  public Font get(float fontSize, int style){
    return font.deriveFont(style, fontSize);
  }
}