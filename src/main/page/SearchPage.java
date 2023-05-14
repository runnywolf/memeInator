package main.page;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import main.MemeInator;
import main.external.NewFont;

public class SearchPage extends Page{

    //DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    public SearchPage(MemeInator frame){
        super(frame);

        add(makeLabel(), Integer.valueOf(0));
        add(makePromptInput(), Integer.valueOf(0));
        add(makeResults(), Integer.valueOf(0));

        /*MouseHandler mouseHandler = new MouseHandler();
        Results.addMouseListener(mouseHandler);

        KeyHandler keyHandler = new KeyHandler();
        PromptInput.addKeyListener(keyHandler);

        PromptInput.addKeyListener(new KeyHandler() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchTags(PromptInput.getText());
            }
        });

        this.binddata();*/
    }

    private JLabel makeLabel(){
        JLabel Label = new JLabel();
        Label.setText("請輸入關鍵字：");
        Label.setBounds(60,30,100,50);
        return Label;
    }

    private JTextField makePromptInput(){
        JTextField PromptInput = new JTextField();
        PromptInput.setBounds(160,30,300,50);
        PromptInput.add(new JScrollPane(PromptInput));
        return PromptInput;
    }

    private JList<String> makeResults(){
        JList<String> Results = new JList<>();
        Results.setFont(new Font("Serif", Font.PLAIN, 24));
        Results.setBounds(60,100,400,500);
        return Results;
    }

    /*private ArrayList<String> GetLabel() {
        ArrayList<String> OriginalTags = new ArrayList<>();
        OriginalTags.add("標籤一");
        OriginalTags.add("標籤二");

        return OriginalTags;
    }

    private void binddata() {
        GetLabel().stream().forEach((OriginalTags) -> {
            defaultListModel.addElement(OriginalTags);
        }); 
        //Results.setModel(defaultListModel);
        //Results.setselectionModel(ListSelectionModel.SINGLE_SELECTION);
    }

    private void searchTags(String searchTerm) {
        DefaultListModel<String> NewListModel = new DefaultListModel<>();
        ArrayList<String> NewTags = GetLabel();
        NewTags.stream().forEach((tag) -> {
            String tagName = NewTags.toString().toLowerCase();
            if(tagName.contains(searchTerm.toLowerCase())){
                NewListModel.addElement(tag);
            }
        });
        defaultListModel = NewListModel;
        //Results.setmodel(defaultListModel);
    }

    private class MouseHandler extends MouseAdapter {
    }

    private class KeyHandler extends KeyAdapter {
    }*/

    
}
