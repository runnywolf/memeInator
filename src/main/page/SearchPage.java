package main.page;

import java.awt.*;
import javax.swing.*;

public class SearchPage extends Page{
    public SearchPage(int w, int h, Font font){
        super(w, h, font);
        setOpaque(true);
        setBackground(darkModeBgColor);
    
        add(PromptInput(), Integer.valueOf(0));
    }

    private JTextField PromptInput(){
        JTextField PromptLabel = new JTextField();
        PromptLabel.setText("請輸入關鍵字：");
        PromptLabel.setBounds(60,30,320,50);
        return PromptLabel;
    }
    
}
