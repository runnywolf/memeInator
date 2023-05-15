package main.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TemplateObject{
  public String type;
  public String text;
  public JLabel box;
  public int x, y, w, h;
  public double imageScale;
  public Image image;

  public TemplateObject(String templatePath, String objectString, Font font){
    String[] kvList = objectString.split(" ");
    for (String kv: kvList){
      String[] kvSplit = kv.split("=");
      switch (kvSplit[0]){
        case "type":
          type = kvSplit[1];
          break;
        case "string":
          text = kvSplit[1];
          break;
        case "x":
          x = Integer.valueOf(kvSplit[1]);
          break;
        case "y":
          y = Integer.valueOf(kvSplit[1]);
          break;
        case "w":
          w = Integer.valueOf(kvSplit[1]);
          break;
        case "h":
          h = Integer.valueOf(kvSplit[1]);
          break;
        case "scale":
          imageScale = Double.valueOf(kvSplit[1]);
          break;
      }
    }
    // read and encoding template format file

    switch (type){
      case "text":
        box = new JLabel(text, SwingConstants.CENTER);
        box.setFont(font);
        box.setForeground(Color.BLACK);
        box.setBounds(x, y, w, h);
        break;
      case "image":
        image = new ImageIcon(templatePath+"/"+text).getImage();
        w = (int)(image.getWidth(null)*imageScale);
        h = (int)(image.getHeight(null)*imageScale);
        Image scaleImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        box = new JLabel(new ImageIcon(scaleImage));
        box.setBounds(x, y, w, h);
        break;
    }
    // use data to make text or image JLabel
  }

  public String getString(){
    switch (type){
      case "text":
        return String.format("type=text string=%s x=%d y=%d w=%d h=%d", text, x, y, w, h);
      case "image":
        return String.format("type=image string=%s x=%d y=%d scale=%.3f", text, x, y, imageScale);
    }
    return null;
  }

  public void setText(String newText){
    text = newText;
    box.setText(text);
  }
  public void setRect(int x, int y, int w, int h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    switch (type){
      case "text":
        box.setBounds(x, y, w, h);
        break;
      case "image":
        box.setBounds(x, y, w, h);
        Image scaleImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        box.setIcon(new ImageIcon(scaleImage));
        break;
    }
  }
}