package main.external;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFolder{
  private JFileChooser fileChooser;

  public ChooseFolder(){
    fileChooser = new JFileChooser();
  }

  public String getPath(String folderPath, String mode){
    switch (mode){
      case "folder":
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        break;
      case "image":
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Files", "png"));
        break;
    }
    fileChooser.setCurrentDirectory(new File(folderPath));

    int option = fileChooser.showOpenDialog(new JFrame());
    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    }
    return null;
  }
}
