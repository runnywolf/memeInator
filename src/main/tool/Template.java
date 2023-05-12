package main.tool;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import main.page.EditorPage;

public class Template{
  private Font font;
  private int width, height;
  private ArrayList<TemplateObject> objList = new ArrayList<>();

  public Template(EditorPage page, String templatePath){
    if (templatePath == null){
      width = 500;
      height = 500;
      return;
    }

    font = page.getF(20);
    
    try{
      String path = templatePath+"/format.txt";
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

      String s = reader.readLine();
      String[] sSplit = s.split(" ");
      width = Integer.valueOf(sSplit[0]);
      height = Integer.valueOf(sSplit[1]); // first line of format.txt is two integer: template bg width & height

      while ((s = reader.readLine()) != null){
        if (!s.equals("")) objList.add(new TemplateObject(templatePath, s, font));
      }

      reader.close();
    }catch (Exception e){
      JOptionPane.showMessageDialog(null, "模板讀檔失敗", "錯誤", JOptionPane.ERROR_MESSAGE);
    }
    // read template
  }

  public JLayeredPane getTemplateLayer(int x, int y){
    JLayeredPane template = new JLayeredPane();
    template.setOpaque(false);
    template.setLayout(null);
    template.setBounds(x, y, width, height);
    for (int i = 0; i < objList.size(); i++) template.add(objList.get(i).box, Integer.valueOf(i));
    return template;
  }

  private RenderedImage toRenderedImage(Image image){
    if (image instanceof RenderedImage) return (RenderedImage) image;

    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    bufferedImage.getGraphics().drawImage(image, 0, 0, null);

    return bufferedImage;
  }
  public void saveTemplate(){
    String templateName = JOptionPane.showInputDialog(null, "[儲存為模板] 模板名稱");
    if (templateName == null) return; // 取消輸入 -> 回到 editor page
    if (!templateName.matches("^[a-zA-Z0-9]+$")){
      JOptionPane.showMessageDialog(null, "模板名稱只能由大小寫字母和數字組成", "警告", JOptionPane.WARNING_MESSAGE);
      return; // 檔案名稱不符合規定 -> 回到 editor page
    }
    // 模板名稱輸入和驗證

    File saveFolder = new File("data/save");
    File templateFolder = new File(saveFolder, templateName);
    // file path init

    if (templateFolder.exists() && templateFolder.isDirectory()){
      int choice = JOptionPane.showOptionDialog(null, "已經有同名模板檔，是否覆蓋？", "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      if (choice == JOptionPane.YES_OPTION) templateFolder.delete();
      else return; // 如果模板檔存在且選擇不覆蓋 -> 回到 editor page
    }
    // check template is exist

    templateFolder.mkdir();
    try{
      BufferedWriter writer = new BufferedWriter(new FileWriter("data/save/"+templateName+"/format.txt", StandardCharsets.UTF_8));
      writer.write(String.format("%d %d\n", width, height));
      for (TemplateObject obj: objList) writer.write(obj.getString()+"\n");
      writer.close();
    }catch (Exception e){
      JOptionPane.showMessageDialog(null, "format.txt存檔失敗", "錯誤", JOptionPane.WARNING_MESSAGE);
    }
    for (TemplateObject obj: objList) if (obj.type.equals("image")){
      try{
        File f = new File("data/save/"+templateName+"/"+obj.text);
        ImageIO.write(toRenderedImage(obj.image), "png", f);
      }catch (Exception e){
        JOptionPane.showMessageDialog(null, "圖片存檔失敗", "錯誤", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  public int getWidth(){return width;}
  public int getHeight(){return height;}
}