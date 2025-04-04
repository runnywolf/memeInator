package main.page.toolbarButton;

import main.page.EditorPage;

public class DefaultButton extends EmptyButton{
  public DefaultButton(EditorPage page, String tip, String path){
    super(page, tip, path);
  }

  @Override
  public void whenClick(){
    page.setDefaultButtonClick();
  }
}