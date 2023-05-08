package main.page.makeToolbarButton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.page.EditorPage;

public class SetCanvasSizeButton extends EmptyButton{
  JTextField widthTextField;
  JTextField heightTextField;

  public SetCanvasSizeButton(EditorPage page, String tip, String path){
    super(page, tip, path);

    makeBar();
    
    button.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){whenClick();}
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
  private void whenClick(){
    page.setPanelPage("setCanvasSize");
    widthTextField.setText(String.valueOf(page.getCanvasWidth()));
    heightTextField.setText(String.valueOf(page.getCanvasHeight()));
  }
}