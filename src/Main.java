import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.DocumentException;

public class Main {

    private JFrame frame;
    private JTextField tfDescrizione;
    private JTextField tfPrezzo;
    private JTextField tfQuantita;

    private JList<String> prodsList;
    private DefaultListModel<String> prodDescriptions;

    private static Receipt receipt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        receipt = new Receipt();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 427, 239);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Generatore di ricevute - by Massimo Tumolo");
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, "name_5575480396490");

        JLabel lblGeneratoreDiRicevute = new JLabel("Generatore di Ricevute - Basato su iText");
        lblGeneratoreDiRicevute.setFont(new Font("Dialog", Font.BOLD, 14));

        JLabel lblDescrizione = new JLabel("Descrizione");

        tfDescrizione = new JTextField();
        tfDescrizione.setColumns(10);

        JLabel lblPrezzo = new JLabel("Prezzo (euro)");

        tfPrezzo = new JTextField();
        tfPrezzo.setColumns(10);

        JLabel lblQuantit = new JLabel("Quantità");

        tfQuantita = new JTextField();
        tfQuantita.setColumns(10);

        JButton btnChiudi = new JButton("Chiudi");

        JButton btnTermina = new JButton("Completa");

        JButton btnAggiungi = new JButton("Aggiungi");

        JLabel lblConfirmation = new JLabel(".");
        lblConfirmation.setFont(new Font("Dialog", Font.BOLD, 11));
        lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
        gl_mainPanel.setHorizontalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel
                .createSequentialGroup()
                .addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel
                        .createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_mainPanel.createSequentialGroup().addContainerGap()
                                .addComponent(lblGeneratoreDiRicevute))
                        .addGroup(gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(lblDescrizione))
                        .addGroup(gl_mainPanel.createSequentialGroup().addGap(54).addComponent(btnAggiungi)
                                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnTermina).addGap(12)
                                .addComponent(btnChiudi, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_mainPanel.createSequentialGroup().addContainerGap()
                                .addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblPrezzo, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                        .addGroup(gl_mainPanel.createSequentialGroup()
                                                .addComponent(tfPrezzo, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblQuantit).addComponent(tfQuantita,
                                                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(gl_mainPanel.createSequentialGroup().addContainerGap().addComponent(tfDescrizione)))
                        .addGroup(gl_mainPanel.createSequentialGroup().addGap(32).addComponent(lblConfirmation,
                                GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE)));
        gl_mainPanel.setVerticalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_mainPanel.createSequentialGroup().addGap(7).addComponent(lblGeneratoreDiRicevute)
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDescrizione)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(tfDescrizione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_mainPanel.createSequentialGroup().addComponent(lblPrezzo)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(tfPrezzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_mainPanel.createSequentialGroup().addComponent(lblQuantit)
                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(tfQuantita,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)))
                        .addGap(17)
                        .addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnChiudi)
                                .addComponent(btnAggiungi).addComponent(btnTermina))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblConfirmation, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));
        mainPanel.setLayout(gl_mainPanel);

        JPanel productsPanel = new JPanel();
        frame.getContentPane().add(productsPanel, "name_6591295634625");
        productsPanel.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setContinuousLayout(true);
        productsPanel.add(splitPane, BorderLayout.SOUTH);

        JButton btnBack = new JButton("Indietro");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> products = receipt.getProducts();
                prodDescriptions = new DefaultListModel<String>();
                for (int i = 0; i < products.size(); ++i) {
                    prodDescriptions.addElement(products.get(i).getDescription() + " " + products.get(i).getPrice()
                            + "€ x" + products.get(i).getQuantity());
                }
                // Switch panels
                mainPanel.setVisible(true);
                productsPanel.setVisible(false);
            }
        });
        splitPane.setLeftComponent(btnBack);

        prodDescriptions = new DefaultListModel<String>();
        prodsList = new JList<String>(prodDescriptions);
        prodsList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int index = prodsList.getSelectedIndex();
                if (!arg0.getValueIsAdjusting() && index != -1) {
                    prodDescriptions.remove(index);
                    receipt.removeProduct(index);
                }
            }
        });
        productsPanel.add(prodsList);

        JButton btnComplete = new JButton("Completa e genera PDF");
        btnComplete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    PdfReceipt pdf = new PdfReceipt(receipt);
                    receipt.close();

                    pdf.preparePdf();
                    String fileName = pdf.getFileName();
                    pdf.close();
                    /* Here the PDF has been created, if supported open it. */
                    Desktop desktop;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                        File file = new File(fileName);
                        if (desktop.isSupported(Desktop.Action.OPEN)) {
                            System.out.println("OPEN action taken.");
                            desktop.open(file);
                            JOptionPane.showMessageDialog(null, "Completato! Il PDF si sta aprendo..");
                        } else {
                            JOptionPane.showMessageDialog(null, "Completato! Troverai il tuo scontrino in " + fileName);
                        }
                    }
                    System.exit(0);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    lblConfirmation.setText("Ooops... c'è qualche problema, ma non so quale sia!");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                } catch (IOException e) {
                    e.printStackTrace();
                    lblConfirmation.setText(
                            "I file \"resources/headers.txt\" e \"resources/number.txt\" non sono stati trovati! ");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                } catch (DocumentException e) {
                    e.printStackTrace();
                    lblConfirmation.setText(
                            "I file \"resources/headers.txt\" e \"resources/number.txt\" non sono stati trovati! ");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                }
            }
        });
        splitPane.setRightComponent(btnComplete);
        btnAggiungi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                Product p = new Product();
                if (!p.setDescription(tfDescrizione.getText())) {
                    lblConfirmation.setText("Descrizione non valida.");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                    return;
                }
                if (!p.setQuantityFromString(tfQuantita.getText())) {
                    lblConfirmation.setText(
                            "<html>Quantita' non valida.<br>Deve essere un numero, senza cifre decimali.</html>");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                    return;
                }
                if (!p.setPriceFromString(tfPrezzo.getText())) {
                    lblConfirmation.setText("<html>Prezzo non valido.<br>Hai usato il punto e non la virgola?</html>");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                    return;
                }

                try {
                    receipt.addProduct(p);
                    lblConfirmation.setText("Prodotto Inserito!");
                    lblConfirmation.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
                    tfQuantita.setText("");
                    tfDescrizione.setText("");
                    tfPrezzo.setText("");

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    lblConfirmation.setText("Ooops... c'è qualche problema, ma non so quale sia!");
                    lblConfirmation.setForeground(java.awt.Color.RED);
                }

            }
        });
        btnTermina.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                ArrayList<Product> products = receipt.getProducts();
                prodDescriptions.clear();
                for (int i = 0; i < products.size(); ++i) {
                    prodDescriptions.addElement(products.get(i).getDescription() + " " + products.get(i).getPrice()
                            + "€ x" + products.get(i).getQuantity());
                }
                prodsList.setModel(prodDescriptions);

                // Switch panels
                mainPanel.setVisible(false);
                productsPanel.setVisible(true);

            }
        });
        btnChiudi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
        });

    }
}
