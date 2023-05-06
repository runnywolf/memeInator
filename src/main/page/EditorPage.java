package main.page;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class EditorPage extends Page{
  public EditorPage(JPanel pane, Font font){
    super(pane, font);
    
    add(makeToolList1(), Integer.valueOf(0));
    add(makeToolList2(), Integer.valueOf(0));
    add(makeToolList3(), Integer.valueOf(0));
    add(makeDividingLine(), Integer.valueOf(1));
  }

  private BetterButton makeToolListButton(String path, String tip){
    BetterButton button = new BetterButton("", 0, null, null, 0, null);
    button.whenHover(null, null, new Color(90, 90, 90), tip);
    button.setBgImage(path);
    return button;
  }
  private JPanel makeToolList1(){
    JPanel toolList = new JPanel(new GridLayout(1, 1, 0, 0));
    toolList.setBounds(6, 6, 40, 40);
    toolList.setBackground(darkModeBgColor);

    BetterButton homeButton = makeToolListButton("img/toolList/home.png", "返回主選單");
    homeButton.whenClickGoto("StartPage");
    toolList.add(homeButton);

    return toolList;
  }
  private JPanel makeToolList2(){
    JPanel toolList = new JPanel(new GridLayout(1, 5, 0, 0));
    toolList.setBounds(66, 6, 200, 40);
    toolList.setBackground(darkModeBgColor);

    BetterButton newTempButton = makeToolListButton("img/toolList/newTemp.png", "建立新的空模板");
    toolList.add(newTempButton);

    BetterButton openTempButton = makeToolListButton("img/toolList/openTemp.png", "開啟之前儲存的模板");
    toolList.add(openTempButton);

    BetterButton saveButton = makeToolListButton("img/toolList/save.png", "儲存為模板");
    toolList.add(saveButton);

    BetterButton toImageButton = makeToolListButton("img/toolList/toImage.png", "匯出為圖片檔");
    toolList.add(toImageButton);

    BetterButton toImgurButton = makeToolListButton("img/toolList/toImgur.png", "匯出並把圖片檔上傳至imgur");
    toolList.add(toImgurButton);

    return toolList;
  }
  private JPanel makeToolList3(){
    JPanel toolList = new JPanel(new GridLayout(1, 2, 0, 0));
    toolList.setBounds(286, 6, 80, 40);

    BetterButton addImageButton = makeToolListButton("img/toolList/addImage.png", "新增圖片");
    toolList.add(addImageButton);

    BetterButton addTextBoxButton = makeToolListButton("img/toolList/addTextBox.png", "新增文字方塊");
    toolList.add(addTextBoxButton);

    return toolList;
  }
  private JPanel makeDividingLine(){
    JPanel panel = new JPanel(){
      @Override
      protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(127, 127, 127));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(0, 52, WINDOW_WIDTH, 52);
        g2d.drawLine(56, 4, 56, 48);
        g2d.drawLine(276, 4, 276, 48);
        g2d.drawLine(376, 4, 376, 48);
        super.paintComponent(g2d);
      }
    };
    panel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    panel.setOpaque(false);

    return panel;
  }
}