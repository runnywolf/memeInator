package main;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.page.StartPage;

public class MemeInator extends JFrame{
  private JLayeredPane currentPage;

  public MemeInator(){
    super("迷因產生器");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    currentPage = new StartPage();
    add(currentPage);
  }
}