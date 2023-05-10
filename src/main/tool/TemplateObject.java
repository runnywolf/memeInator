package main.tool;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TemplateObject{
  public String type;
  private String text;
  public JLabel box;
  public int x, y, w, h;
  private double imageScale;

  public TemplateObject(String templateName, String objectString, Font font){
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
    switch (type){
      case "text":
        box = new JLabel(text, SwingConstants.CENTER);
        box.setFont(font);
        box.setBounds(x, y, w, h);
        break;
      case "image":
        Image image = new ImageIcon("data/template/"+templateName+"/"+text).getImage();
        w = (int)(image.getWidth(null)*imageScale);
        h = (int)(image.getHeight(null)*imageScale);
        Image scaleImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        box = new JLabel(new ImageIcon(scaleImage));
        box.setBounds(x, y, w, h);
        break;
    }
  }
}
