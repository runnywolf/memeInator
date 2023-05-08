package main.page.makeToolbarButton;

import main.page.EditorPage;

public class ToImgurButton extends EmptyButton{
  public ToImgurButton(EditorPage page, String tip, String path){
    super(page, tip, path);
    button.whenClickGoto("StartPage");
  }
}