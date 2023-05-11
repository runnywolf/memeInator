package main.tool;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

import main.page.EditorPage;

public class Template{
  private Font font;
  private int width, height;
  private ArrayList<TemplateObject> objList = new ArrayList<>();

  public Template(EditorPage page, String templateName){
    if (templateName == null){
      width = 500;
      height = 500;
      return;
    }

    font = page.getF(20);
    
    try{
      String path = String.format("data/template/%s/format.txt", templateName);
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

      String s = reader.readLine();
      String[] sSplit = s.split(" ");
      width = Integer.valueOf(sSplit[0]);
      height = Integer.valueOf(sSplit[1]); // first line of format.txt is two integer: template bg width & height

      while ((s = reader.readLine()) != null) objList.add(new TemplateObject(templateName, s, font));

      reader.close();
    }catch (Exception e){}
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

  public int getWidth(){return width;}
  public int getHeight(){return height;}
}