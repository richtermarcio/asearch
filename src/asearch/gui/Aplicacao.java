package asearch.gui;
import javax.swing.UIManager;

import asearch.indexador.Indexador;

import java.awt.*;
import java.io.IOException;

public class Aplicacao {
  boolean packFrame = false;

  //Construct the application
  public Aplicacao() {
    TelaPrincipal frame = new TelaPrincipal();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
  //Main method
  public static void main(String[] args) throws IOException, ClassNotFoundException {
	  if (args.length == 1) {
			Indexador.carregarBaseIndices(args[0]);
			Indexador.atualizarPesos();

			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			new Aplicacao();
		} else {
			System.out.println("uso: Aplicacao [base]");
		}

  }
}
