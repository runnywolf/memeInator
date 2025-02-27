package main.page.toolbarButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import main.page.EditorPage;
import main.page.Page.BetterButton;

public class EmptyButton{
  protected EditorPage page;
  protected BetterButton button;
  protected JPanel bar;

  public EmptyButton(EditorPage page, String tip, String path){
    this.page = page;
    button = page.new BetterButton("", 0, null, null, 0, null);
    button.whenHover(null, null, new Color(90, 90, 90), tip);
    button.setBgImage(path);
    button.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        whenClick();
      }
    });

    bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    bar.setOpaque(false);
  }

  public void whenClick(){}

  public BetterButton getButton(){return button;}
  public JPanel getBar(){return bar;}
}