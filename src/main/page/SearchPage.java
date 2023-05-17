package main.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.MemeInator;

public class SearchPage extends Page{
	private ArrayList<String[]> templateTag;
	private Set<String> tagSet;
	private JTextField searchTextField;
	private final int MAX_SEARCH_RESULT_N = 10;
	private JPanel searchTagList;
	private Set<String> selectedTagSet;
	private final int MAX_SELECT_TAG_N = 5;
	private JPanel selectedTag;

	public SearchPage(MemeInator frame){
		super(frame);
		templateTag = new ArrayList<>();
		tagSet = new HashSet<>();
		selectedTagSet = new HashSet<>();

		readTagFile();

		add(makeBackButton(), Integer.valueOf(0));
		add(makeSearchBar(), Integer.valueOf(0));
		clearSearch();
	}

	private JPanel makeBackButton(){
		JPanel backButtonPanel = new JPanel();
		backButtonPanel.setOpaque(false);
		backButtonPanel.setLayout(null);
		backButtonPanel.setBounds(0, 0, 200, 80);

		BetterButton homeButton = new BetterButton("", 0, null, null, 0, null);
		homeButton.setBounds(10, 10, 40, 40);
    homeButton.whenHover(null, null, new Color(90, 90, 90), "返回主選單");
    homeButton.setBgImage("img/toolbarIcon/home.png");
		homeButton.whenClickGoto("StartPage");
		homeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				clearSearch();
				removeAllTag();
			}
		});
		backButtonPanel.add(homeButton);

		return backButtonPanel;
	}

	private JPanel makeSearchBar(){
		JPanel searchBar = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(new Color(127, 127, 127));
				g2d.setStroke(new BasicStroke(1));

				g2d.drawLine(240, 0, 240, WINDOW_HEIGHT);

				super.paintComponent(g2d);
			}
		};
		searchBar.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		searchBar.setOpaque(false);
		searchBar.setLayout(null);

		searchTextField = new JTextField();
		searchTextField.setBounds(20, 60, 200, 30);
		searchTextField.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){whenInputChange();}
			public void insertUpdate(DocumentEvent e){whenInputChange();}
			public void removeUpdate(DocumentEvent e){whenInputChange();}
		});
		searchBar.add(searchTextField);

		BetterLabel title = new BetterLabel("選擇的tag:", 20, null, false, SwingConstants.LEFT, null);
		title.setBounds(20, 250, 100, 300);
		searchBar.add(title);

		return searchBar;
	}
	private void whenInputChange(){
		String searchString = searchTextField.getText();

		ArrayList<String> tagList = new ArrayList<>();
		for (String tag: tagSet) if (LCS(searchString, tag) > 0) tagList.add(tag);
		Collections.sort(tagList, new Comparator<String>(){
			public int compare(String a, String b){
				return Integer.compare(LCS(searchString, b), LCS(searchString, a));
			}
		});
		redrawSearchTagList(tagList);
	}

	private void redrawSearchTagList(ArrayList<String> tagList){
		if (searchTagList != null) remove(searchTagList);

		final int TAG_BUTTON_HEIGHT = 24;
		int l = tagList.size();

		JPanel list = new JPanel(new GridLayout(l, 1));
		list.setBounds(20, 94, 200, TAG_BUTTON_HEIGHT*l);
		list.setOpaque(false);

		int searchResultCount = 0;
		for (String s: tagList){
			searchResultCount++;
			if (searchResultCount > MAX_SEARCH_RESULT_N) break;

			BetterButton button = new BetterButton(s, 16, null, new Color(60, 60, 60), 0, null);
			button.whenHover(null, null, null, null);
			button.setHorizontalAlignment(JButton.LEFT);
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					clickSearchTagList(s);
				}
			});
			list.add(button);
		}

		searchTagList = list;
		add(searchTagList, Integer.valueOf(0));
		repaint();
	}
	private void clearSearch(){
		searchTextField.setText("");
		redrawSearchTagList(new ArrayList<>());
	}
	private void clickSearchTagList(String tag){
		addTag(tag);
		redrawSelectedTagList();
	}

	private void addTag(String tag){
		if (selectedTagSet.size() < MAX_SELECT_TAG_N) selectedTagSet.add(tag);
	}
	private void removeTag(String tag){
		selectedTagSet.remove(tag);
	}
	private void removeAllTag(){
		selectedTagSet = new HashSet<>();
	}

	private void redrawSelectedTagList(){
		if (selectedTag != null) remove(selectedTag);

		int l = selectedTagSet.size();

		JPanel tags = new JPanel(new GridLayout(l, 1));
		tags.setOpaque(false);
		tags.setBounds(20, 420, 200, 24*l);

		for (String tag: selectedTagSet){
			BetterButton button = new BetterButton(tag, 16, appBgColor, appLightColor1, 0, null);
			button.whenHover(null, appLightColor1, new Color(120, 120, 120), null);
			button.setHorizontalAlignment(JButton.LEFT);
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					clickTagButton(tag);
				}
			});
			tags.add(button);
		}

		selectedTag = tags;
		add(selectedTag, Integer.valueOf(0));
		repaint();
	}
	private void clickTagButton(String tag){
		removeTag(tag);
		redrawSelectedTagList();
	}

	private void readTagFile(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("data/template/templateTag.txt"), "UTF-8"));

			String s;
			while ((s = reader.readLine()) != null){
				if (!s.equals("")){
					String[] sSplit = s.split(" ");
					templateTag.add(sSplit);
					for (int i = 1; i < sSplit.length; i++) tagSet.add(sSplit[i]);
				}
			}

			reader.close();
		}catch (Exception e){}
	}

	private int LCS(String a, String b){
		int al = a.length();
		int bl = b.length();
		int[] dp = new int[bl+1];
		int[] dp_copy = new int[bl+1];
		dp[0] = 0;
		for (int i = 1; i <= al; i++) for (int j = 1; j <= bl; j++){
			dp_copy[j] = dp[j];
			dp[j] = (a.charAt(i-1) == b.charAt(j-1)) ? dp_copy[j-1]+1 : Math.max(dp[j], dp[j-1]);
		}
		return dp[bl];
	}
}