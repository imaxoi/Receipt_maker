import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

/*	This software is based on the iTexts5 API, under the
 *  AGP License (https://itextpdf.com/agpl).
 * */

public class PdfReceipt {
  private String filename;
  private File file;
  private Document document;
  private Receipt r;
  private boolean closed;

  public PdfReceipt() {
    r = new Receipt();

    filename = "scontrini/ricevuta_nr_" + r.getID() + ".pdf";

    file = new File(filename);
    file.getParentFile().mkdirs();

    document = new Document();

    closed = false;
  }

  public void preparePdf()
      throws IOException, DocumentException, IllegalAccessException {
    if (closed)
      throw new IllegalAccessException("preparePdf has been closed. ");

    PdfWriter.getInstance(document, new FileOutputStream(filename));

    Rectangle one = new Rectangle(164, 580);
    document.setPageSize(one);
    document.setMargins(6, 29, 6, 14);

    document.open();

    /*Preparing headers*/
    ArrayList<Paragraph> headers = getHeaders();
    for (int i = 0; i < headers.size(); ++i)
      document.add(headers.get(i));

    /*Adding additional headers, if any*/
    ArrayList<Paragraph> additionalHeaders = getAdditionalHeaders();
    for (int i = 0; i < additionalHeaders.size(); ++i)
      document.add(additionalHeaders.get(i));

    r.setProducts();

    /* Adding product list */
    Font font = FontFactory.getFont(FontFactory.HELVETICA, 9);
    for (int i = 0; i < r.getProducts().size(); i++) {
      Paragraph pDesc =
          new Paragraph(r.getProducts().get(i).getDescription(), font);
      document.add(pDesc);
      Paragraph pQtyPrice = new Paragraph(
          Integer.toString(r.getProducts().get(i).getQuantity()) + "x" +
              Float.toString(r.getProducts().get(i).getPrice()) + "€",
          font);
      pQtyPrice.setAlignment(Element.ALIGN_RIGHT);
      document.add(pQtyPrice);
    }

    Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
    Paragraph total = new Paragraph(
        "Totale: " + Float.toString(r.getTotal()) + "€", fontTotal);
    total.setAlignment(Element.ALIGN_RIGHT);
    document.add(total);
  }

  public void close() {
    closed = true;
    document.close();
    r.close();
  }

  private ArrayList<Paragraph> getHeaders() {
    ArrayList<Paragraph> headers = new ArrayList<Paragraph>();
    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
    try {
      File f = new File("resources/headers.txt");
      BufferedReader b = new BufferedReader(new FileReader(f));
      String readLine = "";

      while ((readLine = b.readLine()) != null) {
        Paragraph header = new Paragraph(readLine, font);
        header.setAlignment(Element.ALIGN_CENTER);
        headers.add(header);
      }

      b.close();

    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null, "Impossibile accedere al file resources/headers.txt");
    }

    String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    Paragraph dateHeader =
        new Paragraph("Ricevuta nr. " + r.getID() + " del " + timeStamp, font);
    dateHeader.setAlignment(Element.ALIGN_CENTER);
    headers.add(dateHeader);

    return headers;
  }

  private ArrayList<Paragraph> getAdditionalHeaders() {
    ArrayList<Paragraph> headers = new ArrayList<Paragraph>();
    Font font = FontFactory.getFont(FontFactory.HELVETICA, 8);
    try {
      File f = new File("resources/aggiunte.txt");
      BufferedReader b = new BufferedReader(new FileReader(f));
      String readLine = "";

      while ((readLine = b.readLine()) != null) {
        Paragraph header = new Paragraph(readLine, font);
        header.setAlignment(Element.ALIGN_CENTER);
        headers.add(header);
      }
      b.close();

    } catch (IOException e) {
      /* These are additional headers, if a problem rises just ignore it. */
    }
    return headers;
  }

  public String getFileName() { return filename; }
}
