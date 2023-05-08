package main.page;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.MemeInator;
import main.external.NewFont;

public class SearchPage extends Page{
    public SearchPage(MemeInator frame){
        super(frame);

        add(PromptInput(), Integer.valueOf(0));
        add(SearchButton(), Integer.valueOf(0));
        add(ScrollPane(), Integer.valueOf(0));
        add(IndexButton1(), Integer.valueOf(0));
        add(IndexButton2(), Integer.valueOf(0));
        add(IndexButton3(), Integer.valueOf(0));
        add(IndexButton4(), Integer.valueOf(0));
        add(IndexButton5(), Integer.valueOf(0));

        ButtonHandler handler = new ButtonHandler();
        SearchButton().addActionListener(handler);
        IndexButton1().addActionListener(handler);
        IndexButton2().addActionListener(handler);
        IndexButton3().addActionListener(handler);
        IndexButton4().addActionListener(handler);
        IndexButton5().addActionListener(handler);
    }

    private JTextField PromptInput(){
        JTextField PromptLabel = new JTextField();
        PromptLabel.setToolTipText("請輸入關鍵字：");
        PromptLabel.setBounds(60,30,300,50);
        return PromptLabel;
    }

    private JButton SearchButton(){
        JButton SearchButton = new JButton();
        SearchButton.setText("搜尋");
        SearchButton.setBounds(360,30,100,50);
        return SearchButton;
    }

    private JScrollPane ScrollPane(){
        JScrollPane ScrollPane = new JScrollPane();
        String[] tableColumn = {"搜尋結果"};
        String[][] tableData = {};
        JTable TagSearchResult = new JTable(tableData, tableColumn);
        TagSearchResult.setFont(new Font("Serif", Font.PLAIN, 24));

        
        ScrollPane.setBounds(60,100,400,500);
        ScrollPane.setViewportView(TagSearchResult);
        ScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        return ScrollPane;
    }

    private JButton IndexButton1(){
        JButton IndexButton1 = new JButton();
        IndexButton1.setText("IndexButton1");
        IndexButton1.setVisible(true);
        return IndexButton1;
    }

    private JButton IndexButton2(){
        JButton IndexButton2 = new JButton();
        IndexButton2.setText("IndexButton2");
        IndexButton2.setBounds(60,170,400,50);
        IndexButton2.setVisible(true);
        return IndexButton2;
    }

    private JButton IndexButton3(){
        JButton IndexButton3 = new JButton();
        IndexButton3.setText("IndexButton3");
        IndexButton3.setBounds(60,240,400,50);
        IndexButton3.setVisible(true);
        return IndexButton3;
    }

    private JButton IndexButton4(){
        JButton IndexButton4 = new JButton();
        IndexButton4.setText("IndexButton4");
        IndexButton4.setBounds(60,310,400,50);
        IndexButton4.setVisible(true);
        return IndexButton4;
    }

    private JButton IndexButton5(){
        JButton IndexButton5 = new JButton();
        IndexButton5.setText("IndexButton5");
        IndexButton5.setBounds(60,380,400,50);
        IndexButton5.setVisible(true);
        return IndexButton5;
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent Event) {
            if(Event.getActionCommand().equals("搜尋")){
                String String = "";
                String NewString = PromptInput().getText();
            }
        }
    }
}
