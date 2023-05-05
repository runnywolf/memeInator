package main.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class EditorPage extends Page{
  public EditorPage(JPanel pane, Font font){
    super(pane, font);
    
    add(makeToolList1(), Integer.valueOf(0));
    add(makeToolList2(), Integer.valueOf(0));
    add(makeToolList3(), Integer.valueOf(0));
  }

  private BetterButton makeToolListButton(String path){
    BetterButton button = new BetterButton("", 0, null, null, 0, null);
    button.whenHover(null, null, new Color(90, 90, 90));
    button.setBgImage(path);
    return button;
  }
  private JPanel makeToolList1(){
    JPanel toolList = new JPanel(new GridLayout(1, 1, 0, 0));
    toolList.setBounds(6, 6, 40, 40);
    toolList.setBackground(darkModeBgColor);

    BetterButton homeButton = makeToolListButton("img/toolList/home.png");
    homeButton.whenClickGoto("StartPage");
    toolList.add(homeButton);

    return toolList;
  }
  private JPanel makeToolList2(){
    JPanel toolList = new JPanel(new GridLayout(1, 5, 0, 0));
    toolList.setBounds(66, 6, 200, 40);
    toolList.setBackground(darkModeBgColor);

    BetterButton newTempButton = makeToolListButton("img/toolList/newTemp.png");
    toolList.add(newTempButton);

    BetterButton openTempButton = makeToolListButton("img/toolList/openTemp.png");
    toolList.add(openTempButton);

    BetterButton saveButton = makeToolListButton("img/toolList/save.png");
    toolList.add(saveButton);

    BetterButton toImageButton = makeToolListButton("img/toolList/toImage.png");
    toolList.add(toImageButton);

    BetterButton toImgurButton = makeToolListButton("img/toolList/toImgur.png");
    toolList.add(toImgurButton);

    return toolList;
  }
  private JPanel makeToolList3(){
    JPanel toolList = new JPanel(new GridLayout(1, 2, 0, 0));
    toolList.setBounds(286, 6, 80, 40);

    BetterButton addImageButton = makeToolListButton("img/toolList/addImage.png");
    toolList.add(addImageButton);

    BetterButton addTextBoxButton = makeToolListButton("img/toolList/addTextBox.png");
    toolList.add(addTextBoxButton);

    return toolList;
  }

  /* 返回主選單
   * 
   * 建立新的空模板
   * 開啟之前儲存的模板
   * 儲存為模板
   * 匯出為圖片檔
   * 匯出並把圖片檔上傳至imgur
   * 
   * 新增圖片
   * 新增文字方塊
  */ 
}