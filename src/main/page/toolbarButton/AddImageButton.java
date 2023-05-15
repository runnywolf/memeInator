package main.page.toolbarButton;

import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.external.ChooseFolder;
import main.page.EditorPage;
import main.tool.TemplateObject;

public class AddImageButton extends EmptyButton{
  private JTextField widthTextField;
  private JTextField heightTextField;

  public AddImageButton(EditorPage page, String tip, String path){
    super(page, tip, path);
    makeBar();

    widthTextField.getDocument().addDocumentListener(new DocumentListener(){
      public void changedUpdate(DocumentEvent e){whenInputChange();}
      public void insertUpdate(DocumentEvent e){whenInputChange();}
      public void removeUpdate(DocumentEvent e){whenInputChange();}
    });
    heightTextField.getDocument().addDocumentListener(new DocumentListener(){
      public void changedUpdate(DocumentEvent e){whenInputChange();}
      public void insertUpdate(DocumentEvent e){whenInputChange();}
      public void removeUpdate(DocumentEvent e){whenInputChange();}
    });
  }

  private void makeBar(){
    bar.add(page.new BetterLabel("寬度:", 16, null, false, SwingConstants.LEFT, null));

    widthTextField = new JTextField();
    widthTextField.setPreferredSize(new Dimension(60, 30));
    bar.add(widthTextField);

    bar.add(page.new BetterLabel("    高度:", 16, null, false, SwingConstants.LEFT, null));

    heightTextField = new JTextField();
    heightTextField.setPreferredSize(new Dimension(60, 30));
    bar.add(heightTextField);
  }

  @Override
  public void whenClick(){
    page.setDefaultButtonClick();
    String imagePath = new ChooseFolder().getPath("img", "image");
    if (imagePath != null) page.getTemplate().addImage(imagePath);
  }

  private void whenInputChange(){
    TemplateObject obj = page.getObjectSelected();
    try{
      int width = Integer.valueOf(widthTextField.getText());
      int height = Integer.valueOf(heightTextField.getText());
      page.setTemplateObjectRect(obj, obj.x, obj.y, width, height);
    }catch (Exception e){}
  }

  public void setParam(int width, int height){
    widthTextField.setText(String.valueOf(width));
    heightTextField.setText(String.valueOf(height));
  }
}