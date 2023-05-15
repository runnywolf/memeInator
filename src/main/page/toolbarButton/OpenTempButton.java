package main.page.toolbarButton;

import main.external.ChooseFolder;
import main.page.EditorPage;

public class OpenTempButton extends EmptyButton{
  public OpenTempButton(EditorPage page, String tip, String path){
    super(page, tip, path);
  }

  @Override
  public void whenClick(){
    String templatePath = new ChooseFolder().getPath("data/save", "folder");
    if (templatePath != null) page.importTemplate(templatePath);
  }
}