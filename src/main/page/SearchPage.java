package main.page;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.RootPaneUI;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import main.MemeInator;
import main.external.NewFont;

public class SearchPage extends Page{

    DefaultListModel defaultListModel = new DefaultListModel<>();

    public SearchPage(MemeInator frame){
        super(frame);

        JLabel Label = new JLabel();
        Label.setText("請輸入關鍵字：");
        Label.setBounds(60,30,100,50);
        add(Label);

        JTextArea PromptInput = new JTextArea();
        PromptInput.setBounds(160,30,300,50);
        add(PromptInput);
        PromptInput.add(new JScrollPane(PromptInput));

        JTable Results = new JTable();
        Results.setFont(new Font("Serif", Font.PLAIN, 24));
        Results.setBounds(60,100,400,500);
        add(Results);

        MouseHandler mouseHandler = new MouseHandler();
        Results.addMouseListener(mouseHandler);

        KeyHandler keyHandler = new KeyHandler();
        PromptInput.addKeyListener(keyHandler);

        this.binddata();
    }

    private ArrayList GetLabel() {
        ArrayList Tags = new ArrayList();
        Tags.add("標籤一");
        Tags.add("標籤二");

        return Tags;
    }

    private void binddata() {
        GetLabel().stream().forEach((Tags) -> {
            defaultListModel.addElement(Tags);
        }); 
        //Results.setModel(defaultListModel);
    }

    private void searchTags(String searchTerm) {
        DefaultListModel foundTags = new DefaultListModel();
        ArrayList Tags = GetLabel();
        Tags.stream().forEach((tag) -> {
            String tagName = Tags.toString().toLowerCase();
            if(tagName.contains(searchTerm.toLowerCase())){
                foundTags.addElement(Tags);
            }
        });
        defaultListModel  = foundTags;
        //Results.setmodel(defaultListModel);
    }

    private class MouseHandler extends MouseAdapter {
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            searchTags(PromptInput.getText());
        }
    }
}
