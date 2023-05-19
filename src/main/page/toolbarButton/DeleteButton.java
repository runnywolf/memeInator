package main.page.toolbarButton;

import main.page.EditorPage;

public class DeleteButton extends EmptyButton{
  public DeleteButton(EditorPage page, String tip, String path){
    super(page, tip, path);
  }

  @Override
  public void whenClick(){
    page.getTemplate().deleteObject(page.getObjectSelected());
    page.redrawTemplate();
    page.setDefaultButtonClick();
  }
}
