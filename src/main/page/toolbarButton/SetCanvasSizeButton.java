package main.page.toolbarButton;

import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.page.EditorPage;

public class SetCanvasSizeButton extends EmptyButton{
  JTextField widthTextField;
  JTextField heightTextField;
  boolean isInputValueSetting = false;

  public SetCanvasSizeButton(EditorPage page, String tip, String path){
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
    isInputValueSetting = true;
    widthTextField.setText(String.valueOf(page.getCanvasWidth()));
    heightTextField.setText(String.valueOf(page.getCanvasHeight()));
    isInputValueSetting = false;
    page.setCanvasSizeButtonClick();
  }

  private void whenInputChange(){
    if (isInputValueSetting) return;
    try{
      page.setCanvasWidth(Integer.valueOf(widthTextField.getText()));
    }catch (Exception ex){}
    try{
      page.setCanvasHeight(Integer.valueOf(heightTextField.getText()));
    }catch (Exception ex){}
    page.setCanvasSizeButtonClick();
  }
}