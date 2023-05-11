package main.page.toolbarButton;

import main.page.EditorPage;

public class HomeButton extends EmptyButton{
  public HomeButton(EditorPage page, String tip, String path){
    super(page, tip, path);
    button.whenClickGoto("StartPage");
  }

  @Override
  public void whenClick(){
    page.setDefaultButtonClick();
    page.importTemplate(null);
  }
}