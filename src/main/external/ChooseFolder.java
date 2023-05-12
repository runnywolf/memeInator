package main.external;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ChooseFolder{
  private JFileChooser fileChooser;

  public ChooseFolder(){
    fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
  }

  public String getPath(String folderPath){
    File initialDirectory = new File(folderPath);
    fileChooser.setCurrentDirectory(initialDirectory);

    int option = fileChooser.showOpenDialog(new JFrame());
    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    }
    return null;
  }
}
