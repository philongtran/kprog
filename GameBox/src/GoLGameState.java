import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * this class is responsible for saving and loading the game of life
 * 
 * @author Phi Long Tran <191624>
 * @author Manuel Wessner <191711>
 * @author Steve Nono <191709>
 */
public final class GoLGameState {
  private static final String FILEEXTENSION = "gol";
  private static final String FILEEXTENSION_WITH_DOTPREFIX = "." + FILEEXTENSION;


  /**
   * Saves current board
   * 
   * @param board
   * @param parentWindow - reference for save dialog
   */
  public static void save(GoLBoard board, Component parentWindow) {
    JFileChooser saveDialog = createFileChooser();
    int saveReturnCode = saveDialog.showSaveDialog(parentWindow);
    if (saveReturnCode == JFileChooser.APPROVE_OPTION) {
      Path saveFile = getSaveFileName(saveDialog);
      try (OutputStream outputStream = Files.newOutputStream(saveFile);
          ObjectOutputStream objectOutStream = new ObjectOutputStream(outputStream);) {
        objectOutStream.writeObject(board);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Loads current board
   * 
   * @param parentWindow - reference for openDialog
   * @return loaded Board or null if board can not be loaded
   */
  public static GoLBoard load(Component parentWindow) {
    JFileChooser openDialog = createFileChooser();
    int openDialogReturnCode = openDialog.showOpenDialog(parentWindow);
    if (openDialogReturnCode == JFileChooser.APPROVE_OPTION) {
      Path loadedFile = openDialog.getSelectedFile().toPath();
      try (InputStream inputStream = Files.newInputStream(loadedFile);
          ObjectInputStream objectInStream = new ObjectInputStream(inputStream);) {
        GoLBoard loadedBoard = (GoLBoard) objectInStream.readObject();
        return loadedBoard;
      } catch (IOException | ClassNotFoundException e1) {
        e1.printStackTrace();
      }
    }
    return null;
  }

  private static JFileChooser createFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(
        "GameOfLife-File " + FILEEXTENSION_WITH_DOTPREFIX, FILEEXTENSION);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.addChoosableFileFilter(extensionFilter);
    fileChooser.setFileFilter(extensionFilter);
    return fileChooser;
  }

  private static Path getSaveFileName(JFileChooser saveDialog) {
    Path saveFile = saveDialog.getSelectedFile().toPath();
    String saveFileAsString = saveFile.toString();
    boolean hasGofFileExtension = saveFileAsString.endsWith(FILEEXTENSION_WITH_DOTPREFIX);
    if (!hasGofFileExtension) {
      saveFile = Paths.get(saveFileAsString + FILEEXTENSION_WITH_DOTPREFIX);
    }
    return saveFile;
  }
}
