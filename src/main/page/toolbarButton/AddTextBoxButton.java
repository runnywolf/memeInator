package main.page.toolbarButton;

import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.page.EditorPage;
import main.tool.TemplateObject;

public class AddTextBoxButton extends EmptyButton{
  private JTextField textTextField;
  private JTextField widthTextField;
  private JTextField heightTextField;

  public AddTextBoxButton(EditorPage page, String tip, String path){
    super(page, tip, path);
    makeBar();

    textTextField.getDocument().addDocumentListener(new DocumentListener(){
      public void changedUpdate(DocumentEvent e){whenInputChange();}
      public void insertUpdate(DocumentEvent e){whenInputChange();}
      public void removeUpdate(DocumentEvent e){whenInputChange();}
    });
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
    bar.add(page.new BetterLabel("文字內容:", 16, null, false, SwingConstants.LEFT, null));

    textTextField = new JTextField();
    textTextField.setPreferredSize(new Dimension(200, 30));
    bar.add(textTextField);

    bar.add(page.new BetterLabel("    寬度:", 16, null, false, SwingConstants.LEFT, null));

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
    
  }

  private void whenInputChange(){
    TemplateObject obj = page.getObjectSelected();
    obj.setText(textTextField.getText());
    try{
      int width = Integer.valueOf(widthTextField.getText());
      int height = Integer.valueOf(heightTextField.getText());
      page.setTemplateObjectRect(obj, obj.x, obj.y, width, height);
    }catch (Exception e){}
  }

  public void setParam(String text, int width, int height){
    textTextField.setText(text);
    widthTextField.setText(String.valueOf(width));
    heightTextField.setText(String.valueOf(height));
  }
}