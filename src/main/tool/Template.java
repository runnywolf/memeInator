package main.tool;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.page.EditorPage;

public class Template extends JLayeredPane{
  private Font font;
  private int w, h;
  private ArrayList<TemplateObject> objList = new ArrayList<>();

  public Template(EditorPage page, String templateName){
    font = page.getF(16);
    setBounds(10, 106, 1044, 565);
    setOpaque(false);
    setLayout(null);

    try{
      String path = String.format("data/template/%s/format.txt", templateName);
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
      String s;
      while ((s = reader.readLine()) != null) objList.add(new TemplateObject(templateName, s, font));
      reader.close();
    }catch (Exception e){} // read template
    

    for (TemplateObject obj: objList){
      
    }

    add(objList.get(0).box, Integer.valueOf(0));
  }
}