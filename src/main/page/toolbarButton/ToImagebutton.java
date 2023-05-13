package main.page.toolbarButton;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.page.EditorPage;

public class ToImagebutton extends EmptyButton{
  public ToImagebutton(EditorPage page, String tip, String path){
    super(page, tip, path);
  }

  @Override
  public void whenClick(){
    page.setDefaultButtonClick();
    
    String imageName = JOptionPane.showInputDialog(null, "[匯出為圖片檔] 圖片檔名稱");
    if (imageName == null) return;
    if (!imageName.matches("^[a-zA-Z0-9]+$")){
      JOptionPane.showMessageDialog(null, "圖片檔名只能由大小寫字母和數字組成", "警告", JOptionPane.WARNING_MESSAGE);
      return;
    }

    File imageFile = new File("img/save/"+imageName+".png");

    if (imageFile.exists()){
      int choice = JOptionPane.showOptionDialog(null, "已經有同名檔案，是否覆蓋？", "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      if (choice == JOptionPane.YES_OPTION) imageFile.delete();
      else return;
    }

    try{
      Robot robot = new Robot();
      BufferedImage image = robot.createScreenCapture(page.getRealCanvasRect());
      ImageIO.write(image, "png", new File("img/save/"+imageName+".png"));
    }catch (Exception e){}
  }
}