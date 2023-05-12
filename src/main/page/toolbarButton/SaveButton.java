package main.page.toolbarButton;

import main.page.EditorPage;

public class SaveButton extends EmptyButton{
  public SaveButton(EditorPage page, String tip, String path){
    super(page, tip, path);
  }

  @Override
  public void whenClick(){
    page.getTemplate().saveTemplate();
  }
}