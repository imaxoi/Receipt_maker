import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Receipt {
    private ArrayList<Product> products;
    private int ID;
    boolean closed;

    public Receipt() {
        products = new ArrayList<Product>();
        closed = false;
        if (!getIdFromFile()) {
            closed = true;
        }
    }

    public void close() {
        closed = true;
        setIdOnFile();
    }

    public float getTotal() {
        float total = 0;
        for (int i = 0; i < products.size(); ++i) {
            Product p = products.get(i);
            total += (p.getPrice() * p.getQuantity());
        }
        return total;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getID() {
        return ID;
    }

    public void setProductsFromInput() throws IllegalAccessException {
        if (closed == true)
            throw new IllegalAccessException("Receipt has been closed. ");
        int productsLeft = JOptionPane.YES_OPTION;

        while (productsLeft == JOptionPane.YES_OPTION) {
            Product newProduct = new Product();

            while (!newProduct.setDescription(JOptionPane.showInputDialog("Descrizione del prodotto: ")))
                ;

            while (!newProduct.setPriceFromString(
                    JOptionPane.showInputDialog("Prezzo:\n(NO simbolo euro, usare il . per i decimali, es. 12.34) ")))
                ;

            while (!newProduct.setQuantityFromString(JOptionPane.showInputDialog("Quantita': ")))
                ;

            products.add(newProduct);

            productsLeft = JOptionPane.showConfirmDialog(null,
                    "Inserire un altro prodotto? Scegliere no per terminare.", "Proseguire?",
                    JOptionPane.YES_NO_OPTION);
        }
    }

    public void addProduct(Product newProduct) throws IllegalAccessException {
        if (closed == true)
            throw new IllegalAccessException("Receipt has been closed. ");
        products.add(newProduct);

    }

    public void removeProduct(int index) {
        products.remove(index);

    }

    private boolean getIdFromFile() {
        try {
            File f = new File("resources/number.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String idOnFile = "";

            if ((idOnFile = b.readLine()) != null) {
                ID = Integer.parseInt(idOnFile) + 1;
                b.close();
                return true;
            } else {
                b.close();
                return false;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossibile accedere al file resources/number.txt");
            return false;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Il file resources/number.txt è corrotto. \n"
                    + "Assicurarsi contenga unicamente il numero della prossima ricevuta.");
            return false;
        }
    }

    private boolean setIdOnFile() {
        try {
            PrintWriter writer = new PrintWriter("resources/number.txt");
            writer.println(Integer.toString(ID));
            writer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossibile accedere al file resources/number.txt");
            return false;
        }
    }
}
