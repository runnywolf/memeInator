package main.page;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.MemeInator;
import main.page.toolbarButton.*;
import main.tool.Template;
import main.tool.TemplateObject;

public class EditorPage extends Page{
  private MemeInator frame;
  private EmptyButton[][] buttonGroups;
  private AddImageButton addImageButton;
  private AddTextBoxButton addTextBoxButton;
  private String paramBarCurrentPage;
  private JPanel paramBar;
  private final int TOOLBAR_HEIGHT = 106;
  private final int CANVAS_BG_WIDTH = 1044;
  private final int CANVAS_BG_HEIGHT = 565;
  private int lastX, lastY;
  private int mousePressX, mousePressY;
  private Template template;
  private JLayeredPane templateLayer;
  private TemplateObject objectSelected;
  private int canvasX, canvasY, canvasWidth, canvasHeight;
  private final int MIN_CANVAS_WIDTH = 10;
  private final int MIN_CANVAS_HEIGHT = 10;
  private JPanel canvasCover;
  private DragBorder dragBorder;

  public EditorPage(MemeInator frame){
    super(frame);
    this.frame = frame;
    
    addImageButton = new AddImageButton(this, "新增圖片", "img/toolbarIcon/addImage.png");
    addTextBoxButton = new AddTextBoxButton(this, "新增文字方塊", "img/toolbarIcon/addTextBox.png");
    EmptyButton[][] groups = {
      {
        new HomeButton(this, "返回主選單", "img/toolbarIcon/home.png")
      },
      {
        new NewTempButton(this, "建立新的空模板", "img/toolbarIcon/newTemp.png"),
        new OpenTempButton(this, "開啟之前儲存的模板", "img/toolbarIcon/openTemp.png"),
        new SaveButton(this, "儲存為模板", "img/toolbarIcon/save.png"),
        new ToImagebutton(this, "匯出為圖片檔", "img/toolbarIcon/toImage.png"),
        new ToImgurButton(this, "匯出並把圖片檔上傳至imgur", "img/toolbarIcon/toImgur.png")
      },
      {
        new DefaultButton(this, "預設選取模式", "img/toolbarIcon/default.png"),
        new SetCanvasSizeButton(this, "設定畫布範圍", "img/toolbarIcon/setCanvasSize.png"),
        addImageButton = new AddImageButton(this, "新增圖片", "img/toolbarIcon/addImage.png"),
        addTextBoxButton = new AddTextBoxButton(this, "新增文字方塊", "img/toolbarIcon/addTextBox.png"),
        new DeleteButton(this, "刪除目前選取的物件", "img/toolbarIcon/delete.png")
      }
    }; // 按鈕排版
    buttonGroups = groups;

    add(makeToolbar(), Integer.valueOf(0));

    add(paramBar = makeParamBar(), Integer.valueOf(0));
    setBarPage("default");

    add(makeCanvas(), Integer.valueOf(0));

    importTemplate(null);

    redrawCanvasCover(canvasX, canvasY, canvasWidth, canvasHeight);

    add(dragBorder = new DragBorder(), Integer.valueOf(3));
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

  private JPanel makeParamBar(){
    JPanel bar = new JPanel(new CardLayout());
    bar.setBounds(6, 54, WINDOW_WIDTH, 40);
    bar.setOpaque(false);
    
    bar.add(buttonGroups[2][0].getBar(), "default");
    bar.add(buttonGroups[2][1].getBar(), "setCanvasSize");
    bar.add(buttonGroups[2][2].getBar(), "addImage");
    bar.add(buttonGroups[2][3].getBar(), "addTextBox");

    return bar;
  }
  public void setBarPage(String pageName){
    paramBarCurrentPage = pageName;
    CardLayout cardLayout = (CardLayout) paramBar.getLayout();
    cardLayout.show(paramBar, paramBarCurrentPage);
  }

  private JPanel makeCanvas(){
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(10, TOOLBAR_HEIGHT, CANVAS_BG_WIDTH, CANVAS_BG_HEIGHT);
    panel.setBackground(Color.WHITE);

    panel.addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent me){
        mousePressX = me.getX();
        mousePressY = me.getY();
        
        switch (paramBarCurrentPage){
          case "default": case "addImage": case "addTextBox":
            canvasClickObject(template.whichObjectClicked(me.getX()-canvasX, me.getY()-canvasY));
            if (objectSelected == null) return;
            lastX = objectSelected.x;
            lastY = objectSelected.y;
            break;
          case "setCanvasSize":
            lastX = canvasX;
            lastY = canvasY;
            break;
        }
      }
    });
    panel.addMouseMotionListener(new MouseMotionAdapter(){
      @Override
      public void mouseDragged(MouseEvent me){
        int moveX = me.getX()-mousePressX;
        int moveY = me.getY()-mousePressY;

        switch (paramBarCurrentPage){
          case "default": case "addImage": case "addTextBox":
            if (objectSelected == null) return;
            objectSelected.setRect(lastX+moveX, lastY+moveY, objectSelected.w, objectSelected.h);
            dragBorder.setButtonLocation(canvasX+lastX+moveX, canvasY+lastY+moveY, objectSelected.w, objectSelected.h);
            break;
          case "setCanvasSize":
            canvasX = keepInRange(lastX+moveX, 0, CANVAS_BG_WIDTH-canvasWidth);
            canvasY = keepInRange(lastY+moveY, 0, CANVAS_BG_HEIGHT-canvasHeight);

            dragBorder.setButtonLocation(canvasX, canvasY, canvasWidth, canvasHeight);
            redrawCanvasCover(canvasX, canvasY, canvasWidth, canvasHeight);
            break;
        }
      }
    });

    return panel;
  }
  public void canvasClickObject(TemplateObject obj){
    if (obj == null) return;
    objectSelected = obj;

    switch (obj.type){
      case "text":
        addTextBoxButton.setParam(obj.text, obj.w, obj.h);
        setBarPage("addTextBox");
        break;
      case "image":
        addImageButton.setParam(obj.w, obj.h);
        setBarPage("addImage");
        break;
    }
    redrawTemplate();
    dragBorder.setButtonLocation(canvasX+obj.x, canvasY+obj.y, obj.w, obj.h);
    dragBorder.setVisible(true);
  }

  public void redrawTemplate(){
    if (templateLayer != null) remove(templateLayer);
    add(templateLayer = template.getTemplateLayer(10+canvasX, TOOLBAR_HEIGHT+canvasY), Integer.valueOf(1));
    redrawCanvasCover(canvasX, canvasY, canvasWidth, canvasHeight);
  }
  public void importTemplate(String templatePath){
    template = new Template(this, templatePath);
    canvasWidth = template.getWidth();
    canvasHeight = template.getHeight();
    canvasX = (CANVAS_BG_WIDTH-canvasWidth)/2;
    canvasY = (CANVAS_BG_HEIGHT-canvasHeight)/2;
    redrawTemplate();
    setDefaultButtonClick();
  }

  public void redrawCanvasCover(int x, int y, int w, int h){
    if (canvasCover != null) remove(canvasCover);

    JPanel cover = new JPanel(){
      @Override
      protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, getWidth(), y);
        g2d.fillRect(0, y, x, h);
        g2d.fillRect(x+w, y, getWidth(), h);
        g2d.fillRect(0, y+h, getWidth(), getHeight());

        super.paintComponent(g2d);
      }
    }; // draw cover

    cover.setOpaque(false);
    cover.setBounds(10, TOOLBAR_HEIGHT, CANVAS_BG_WIDTH, CANVAS_BG_HEIGHT);
    canvasCover = cover;
    add(canvasCover, Integer.valueOf(2));
  }

  private int keepInRange(int num, int lowerBound, int upperBound){
    return Math.min(Math.max(num, lowerBound), upperBound);
  }
  private class DragBorder extends JPanel{
    private final int MIN_WIDTH = MIN_CANVAS_WIDTH;
    private final int MIN_HEIGHT = MIN_CANVAS_HEIGHT;
    private int x, y, w, h;
    private JButton buttonNW, buttonN, buttonNE, buttonE, buttonSE, buttonS, buttonSW, buttonW;
    private int mouseX, mouseY;

    public DragBorder(){
      setBounds(10, TOOLBAR_HEIGHT, CANVAS_BG_WIDTH, CANVAS_BG_HEIGHT);
      setOpaque(false);
      setLayout(null);
      
      setVisible(false);
      initButton();
    }

    private void westPointMove(int buttonX){
      w = keepInRange(buttonSE.getX()-buttonX, MIN_WIDTH, buttonSE.getX()+5);
      x = buttonSE.getX()-w + 5;
    }
    private void eastPointMove(int buttonX){
      w = keepInRange(buttonX-buttonNW.getX(), MIN_WIDTH, CANVAS_BG_WIDTH-buttonNW.getX()-MIN_WIDTH+5);
    }
    private void northPointMove(int buttonY){
      h = keepInRange(buttonSE.getY()-buttonY, MIN_HEIGHT, buttonSE.getY()+5);
      y = buttonSE.getY()-h + 5;
    }
    private void southPointMove(int buttonY){
      h = keepInRange(buttonY-buttonNW.getY(), MIN_HEIGHT, CANVAS_BG_HEIGHT-buttonNW.getY()-MIN_HEIGHT+5);
    }
    private JButton makeButton(String type){
      BetterButton button = new BetterButton(null, 0, null, null, 1, null);
      button.whenHover(null, null, new Color(255, 0, 255), null);
      button.setSize(10, 10);
      button.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
          mouseX = me.getX();
          mouseY = me.getY();
        }
      });
      button.addMouseMotionListener(new MouseMotionAdapter(){
        @Override
        public void mouseDragged(MouseEvent me){
          int buttonX = button.getX()+(me.getX()-mouseX);
          int buttonY = button.getY()+(me.getY()-mouseY);
          switch (type){
            case "buttonNW":
              northPointMove(buttonY);
              westPointMove(buttonX);
              break;
            case "buttonN":
              northPointMove(buttonY);
              break;
            case "buttonNE":
              northPointMove(buttonY);
              eastPointMove(buttonX);
              break;
            case "buttonE":
              eastPointMove(buttonX);
              break;
            case "buttonSE":
              southPointMove(buttonY);
              eastPointMove(buttonX);
              break;
            case "buttonS":
              southPointMove(buttonY);
              break;
            case "buttonSW":
              southPointMove(buttonY);
              westPointMove(buttonX);
              break;
            case "buttonW":
              westPointMove(buttonX);
              break;
          }
          button.setLocation(buttonX, buttonY);
          calcButtonLocation();

          switch (paramBarCurrentPage){
            case "default":
              setTemplateObjectRect(objectSelected, x-canvasX, y-canvasY, w, h);
              break;
            case "addImage":
              setTemplateObjectRect(objectSelected, x-canvasX, y-canvasY, w, h);
              addImageButton.setParam(objectSelected.w, objectSelected.h);
              break;
            case "addTextBox":
              setTemplateObjectRect(objectSelected, x-canvasX, y-canvasY, w, h);
              addTextBoxButton.setParam(objectSelected.text, objectSelected.w, objectSelected.h);
              break;
            case "setCanvasSize":
              template.updateRect(x-canvasX, y-canvasY, w, h);

              canvasX = x;
              canvasY = y;
              canvasWidth = w;
              canvasHeight = h;

              buttonGroups[2][1].whenClick();
              redrawCanvasCover(canvasX, canvasY, canvasWidth, canvasHeight);
              break;
          }
        }
      });
      return button;
    }
    private void initButton(){
      add(buttonNW = makeButton("buttonNW"));
      add(buttonN = makeButton("buttonN"));
      add(buttonNE = makeButton("buttonNE"));
      add(buttonE = makeButton("buttonE"));
      add(buttonSE = makeButton("buttonSE"));
      add(buttonS = makeButton("buttonS"));
      add(buttonSW = makeButton("buttonSW"));
      add(buttonW = makeButton("buttonW"));
    }

    private void calcButtonLocation(){
      int buttonX = x-5;
      int buttonY = y-5;
      buttonNW.setLocation(buttonX, buttonY);
      buttonN.setLocation(buttonX+w/2, buttonY);
      buttonNE.setLocation(buttonX+w, buttonY);
      buttonE.setLocation(buttonX+w, buttonY+h/2);
      buttonSE.setLocation(buttonX+w, buttonY+h);
      buttonS.setLocation(buttonX+w/2, buttonY+h);
      buttonSW.setLocation(buttonX, buttonY+h);
      buttonW.setLocation(buttonX, buttonY+h/2);
    }
    public void setButtonLocation(int x, int y, int w, int h){
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      calcButtonLocation();
    }
  }
  
  public void setDefaultButtonClick(){
    if (dragBorder == null) return;

    objectSelected = null;
    setBarPage("default");
    dragBorder.setVisible(false);
  }
  public void setCanvasSizeButtonClick(){
    dragBorder.setButtonLocation(canvasX, canvasY, canvasWidth, canvasHeight);

    setBarPage("setCanvasSize");
    dragBorder.setVisible(true);
  }

  public Template getTemplate(){return template;}
  public TemplateObject getObjectSelected(){return objectSelected;}
  public int getCanvasX(){return canvasX;}
  public int getCanvasY(){return canvasY;}
  public int getCanvasWidth(){return canvasWidth;}
  public int getCanvasHeight(){return canvasHeight;}
  public DragBorder getDragBorder(){return dragBorder;}
  public Rectangle getRealCanvasRect(){
    int windowX = frame.getLocationOnScreen().x;
    int windowY = frame.getLocationOnScreen().y;
    return new Rectangle(7+windowX+10+canvasX, 31+windowY+TOOLBAR_HEIGHT+canvasY, canvasWidth, canvasHeight);
  }

  public void setCanvasSize(int width, int height){
    canvasWidth = keepInRange(width, MIN_CANVAS_WIDTH, CANVAS_BG_WIDTH-canvasX);
    canvasHeight = keepInRange(height, MIN_CANVAS_WIDTH, CANVAS_BG_HEIGHT-canvasY);
    redrawCanvasCover(canvasX, canvasY, canvasWidth, canvasHeight);
  }
  public void setTemplateObjectRect(TemplateObject obj, int x, int y, int width, int height){
    int w = keepInRange(width, MIN_CANVAS_WIDTH, CANVAS_BG_WIDTH-(canvasX+obj.x));
    int h = keepInRange(height, MIN_CANVAS_HEIGHT, CANVAS_BG_HEIGHT-(canvasY+obj.y));
    obj.setRect(x, y, w, h);
    dragBorder.setButtonLocation(canvasX+obj.x, canvasY+obj.y, obj.w, obj.h);
  }
}