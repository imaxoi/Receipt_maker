
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;

/*	This software is based on the iTexts5 API, under the
 *  AGP License (https://itextpdf.com/agpl).
 * */

public class Main {

  public static void main(String[] args) throws IOException, DocumentException {
    try {
      PdfReceipt pdf = new PdfReceipt();
      pdf.preparePdf();
      String fileName = pdf.getFileName();
      pdf.close();

      Thread.sleep(1000);

      /*Here the PDF has been created, if supported open it.*/
      Desktop desktop;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
        File file = new File(fileName);
        if (desktop.isSupported(Desktop.Action.OPEN)) {
          System.out.println("OPEN action taken.");
          desktop.open(file);
        } else {
          JOptionPane.showMessageDialog(
              null, "Completato! Troverai il tuo scontrino in " + fileName);
        }
      }

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Qualcosa è andato storto: \n" +
                                              e.getMessage());
    }
  }
}
