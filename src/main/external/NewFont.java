package main.external;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class NewFont{
  private Font font;

  public NewFont(String path){
    try{
      font = Font.createFont(Font.TRUETYPE_FONT, new File(path)); // get font file
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // 將font加到字體顯示環境
      ge.registerFont(font);
    }catch (IOException|FontFormatException e) { // if can't find the font file -> use system default font
      font = Font.getFont(Font.DIALOG);
    }
  }

  public Font getF(){
    return font;
  }
}