package main.page;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;

import main.MemeInator;
import main.page.makeToolbarButton.*;

public class EditorPage extends Page{
  private EmptyButton[][] buttonGroups;
  private int toolbarHeight;
  private JPanel paramBar;
  private JPanel canvas;
  private int canvasX;
  private int canvasY;
  private int canvasWidth;
  private int canvasHeight;

  public EditorPage(MemeInator frame){
    super(frame);
    
    canvasX = 100;
    canvasY = 100;
    canvasWidth = 100;
    canvasHeight = 100;
    
    EmptyButton[][] groups = {
      {
        new EmptyButton(this, "返回主選單", "img/toolbarIcon/home.png")
      },
      {
        new NewTempButton(this, "建立新的空模板", "img/toolbarIcon/newTemp.png"),
        new OpenTempButton(this, "開啟之前儲存的模板", "img/toolbarIcon/openTemp.png"),
        new SaveButton(this, "儲存為模板", "img/toolbarIcon/save.png"),
        new ToImagebutton(this, "匯出為圖片檔", "img/toolbarIcon/toImage.png"),
        new ToImgurButton(this, "匯出並把圖片檔上傳至imgur", "img/toolbarIcon/toImgur.png")
      },
      {
        new DefaultButton(this, "預設選取模式", "img/toolbarIcon/?.png"),
        new SetCanvasSizeButton(this, "設定畫布範圍", "img/toolbarIcon/setCanvasSize.png"),
        new AddImageButton(this, "新增圖片", "img/toolbarIcon/addImage.png"),
        new AddTextBoxButton(this, "新增文字方塊", "img/toolbarIcon/addTextBox.png")
      }
    }; // 按鈕排版
    buttonGroups = groups;

    paramBar = new JPanel(new CardLayout());
    add(makeToolbar(), Integer.valueOf(0));
    add(makeParamBar(), Integer.valueOf(100));
    setPanelPage("empty");
    toolbarHeight = 106;

    add(makeCanvas(), Integer.valueOf(0));
    add(makeCanvasCover(), Integer.valueOf(2));
  }

  private JPanel makeToolbar(){
    JPanel toolbar = new JPanel(){
      @Override
      protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(127, 127, 127));
        g2d.setStroke(new BasicStroke(1));

        g2d.drawLine(0, 52, WINDOW_WIDTH, 52);
        g2d.drawLine(0, 95, WINDOW_WIDTH, 95);
        int currentX = 5;
        for (EmptyButton[] buttonGroup: buttonGroups){
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
    for (EmptyButton[] buttonGroup: buttonGroups){
      int groupN = buttonGroup.length;
      JPanel subToolBar = new JPanel(new GridLayout(1, groupN));
      subToolBar.setBounds(6+currentX, 6, 40*groupN, 40);
      subToolBar.setBackground(appBgColor);
      for (EmptyButton button: buttonGroup) subToolBar.add(button.getButton());
      toolbar.add(subToolBar, Integer.valueOf(0));
      currentX += 40*groupN+20;
    }
    // draw buttons

    return toolbar;
  }

  private class EmptyBar extends JPanel{
    public EmptyBar(){
      super(new FlowLayout(FlowLayout.LEFT));
      setOpaque(false);
    }
  }

  private JPanel makeParamBar(){
    paramBar.setBounds(6, 54, WINDOW_WIDTH, 40);
    paramBar.setOpaque(false);
    
    paramBar.add(new EmptyBar(), "empty");
    paramBar.add(buttonGroups[2][1].getBar(), "setCanvasSize");

    return paramBar;
  }
  public void setPanelPage(String pageName){
    CardLayout cardLayout = (CardLayout) paramBar.getLayout();
    cardLayout.show(paramBar, pageName);
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

  public int getCanvasWidth(){return canvasWidth;}
  public int getCanvasHeight(){return canvasHeight;}
}