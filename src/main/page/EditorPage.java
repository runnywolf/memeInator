package main.page;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EditorPage extends Page{
  private int toolbarHeight;
  private JPanel canvas;
  private int canvasX;
  private int canvasY;
  private int canvasWidth;
  private int canvasHeight;

  public EditorPage(JPanel pane, Font font){
    super(pane, font);
    
    add(makeToolList(), Integer.valueOf(0));
    toolbarHeight = 70;

    canvasX = 100;
    canvasY = 100;
    canvasWidth = 100;
    canvasHeight = 100;

    add(makeCanvas(), Integer.valueOf(0));
    add(makeCanvasCover(), Integer.valueOf(2));
  }

  private BetterButton makeButton(String path, String tip){
    BetterButton button = new BetterButton("", 0, null, null, 0, null);
    button.whenHover(null, null, new Color(90, 90, 90), tip);
    button.setBgImage(path);
    return button;
  }
  private BetterButton makeHomeButton(){
    BetterButton button = makeButton("img/toolbarIcon/home.png", "返回主選單");
    button.whenClickGoto("StartPage");
    return button;
  }
  private BetterButton makeNewTempButton(){
    BetterButton button = makeButton("img/toolbarIcon/newTemp.png", "建立新的空模板");
    return button;
  }
  private BetterButton makeOpenTempButton(){
    BetterButton button = makeButton("img/toolbarIcon/openTemp.png", "開啟之前儲存的模板");
    return button;
  }
  private BetterButton makeSaveButton(){
    BetterButton button = makeButton("img/toolbarIcon/save.png", "儲存為模板");
    return button;
  }
  private BetterButton makeToImagebutton(){
    BetterButton button = makeButton("img/toolbarIcon/toImage.png", "匯出為圖片檔");
    return button;
  }
  private BetterButton makeToImgurButton(){
    BetterButton button = makeButton("img/toolbarIcon/toImgur.png", "匯出並把圖片檔上傳至imgur");
    return button;
  }
  private BetterButton makeSetCanvasSizeButton(){
    BetterButton button = makeButton("img/toolbarIcon/?.png", "設定畫布範圍");
    return button;
  }
  private BetterButton makeAddImageButton(){
    BetterButton button = makeButton("img/toolbarIcon/addImage.png", "新增圖片");
    return button;
  }
  private BetterButton makeAddTextBoxButton(){
    BetterButton button = makeButton("img/toolbarIcon/addTextBox.png", "新增文字方塊");
    return button;
  }
  private JPanel makeToolList(){
    JButton[][] buttonGroups = {
      {makeHomeButton()},
      {makeNewTempButton(), makeOpenTempButton(), makeSaveButton(), makeToImagebutton(), makeToImgurButton()},
      {makeSetCanvasSizeButton(), makeAddImageButton(), makeAddTextBoxButton()}
    };
    // 按鈕排版

    JPanel toolbar = new JPanel(){
      @Override
      protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(127, 127, 127));
        g2d.setStroke(new BasicStroke(1));

        g2d.drawLine(0, 52, WINDOW_WIDTH, 52);
        int currentX = 5;
        for (JButton[] buttonGroup: buttonGroups){
          currentX += 40*buttonGroup.length+20;
          g2d.drawLine(currentX-10, 4, currentX-10, 48);
        }

        super.paintComponent(g2d);
      }
    };
    toolbar.setLayout(null);
    toolbar.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    toolbar.setOpaque(false);
    // init toolbar panel and draw toolbar dividing line

    int currentX = 0;
    for (JButton[] buttonGroup: buttonGroups){
      int groupN = buttonGroup.length;
      JPanel subToolBar = new JPanel(new GridLayout(1, groupN));
      subToolBar.setBounds(6+currentX, 6, 40*groupN, 40);
      subToolBar.setBackground(appBgColor);
      for (JButton button: buttonGroup) subToolBar.add(button);
      toolbar.add(subToolBar, Integer.valueOf(0));
      currentX += 40*groupN+20;
    }
    // draw buttons

    return toolbar;
  }

  private JPanel makeCanvas(){
    canvas = new JPanel();
    canvas.setLayout(null);
    canvas.setBounds(10, toolbarHeight, 1044, WINDOW_HEIGHT-toolbarHeight-49);
    canvas.setBackground(Color.WHITE);

    return canvas;
  }

  private JPanel makeCanvasCover(){
    JPanel cover = new JPanel(){
      @Override
      protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, getWidth(), canvasY);
        g2d.fillRect(0, canvasY, canvasX, canvasHeight);
        g2d.fillRect(canvasX+canvasWidth, canvasY, getWidth(), canvasHeight);
        g2d.fillRect(0, canvasY+canvasHeight, getWidth(), getHeight());

        super.paintComponent(g2d);
      }
    };
    cover.setOpaque(false);
    cover.setBounds(10, toolbarHeight, 1044, WINDOW_HEIGHT-toolbarHeight-49);

    return cover;
  }
}