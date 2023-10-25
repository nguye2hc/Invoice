import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class InvoiceRunner extends JFrame {

    public static void main(String[] args)
    {
        InvoiceRunner Invoice = new InvoiceRunner();
    }
    JPanel mainPnl, customerPnl, itemPnl, displayPnl, controlPnl, controlPnl2, formatPnl;
    JTextField custNameTF, custStreetTF, custCityTF, custStateTF, custZipTF, itemNameTF, itemCostTF, itemQuanTF;
    JLabel custNameLbl, custStreetLbl, custCityLbl, custStateLbl, custZipLbl, itemNameLbl, itemCostLbl, itemQuanLbl;
    JButton custBtn, prodBtn, clearBtn, quitBtn;
    JTextArea textArea;
    JScrollPane pane;

    LineItems lineItem;
    Invoice invoice;
    Products product;
    Customers customer;
    Address address;

    ArrayList<LineItems> lines = new ArrayList<>();
    public InvoiceRunner() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeigh = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(920, 500);
        setLocation(screenWidth / 1/5, (screenHeigh - 500) / 2);

        setTitle("Invoice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGUI();
        setVisible(true);
    }
    public void createGUI() {
        mainPnl = new JPanel();
        customerPnl = new JPanel();
        itemPnl = new JPanel();
        displayPnl = new JPanel();
        formatPnl = new JPanel();
        controlPnl = new JPanel();
        controlPnl2 = new JPanel();

        mainPnl.add(customerPnl);
        mainPnl.add(itemPnl);
        mainPnl.add(formatPnl);
        formatPnl.add(controlPnl);
        formatPnl.add(displayPnl);
        formatPnl.add(controlPnl2);

        add(mainPnl);
        createCustomerInfoPnl();
        createItemInfoPnl();
        createInvoicePnl();
        createControlPnl();
        createControlPnl2();
    }
    public void createCustomerInfoPnl() {
        customerPnl.setLayout(new GridLayout(3, 2));
        customerPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "ADDRESS INFORMATION"));
        customerPnl.setAlignmentX(Component.CENTER_ALIGNMENT);

        custNameLbl = new JLabel("Name: ", JLabel.RIGHT);
        custNameTF = new JTextField(10);

        custStreetLbl = new JLabel("Street: ", JLabel.RIGHT);
        custStreetTF = new JTextField(10);

        custCityLbl = new JLabel("City: ", JLabel.RIGHT);
        custCityTF = new JTextField(5);

        custStateLbl = new JLabel("State: ", JLabel.RIGHT);
        custStateTF = new JTextField(5);

        custZipLbl = new JLabel("Zip Code: ", JLabel.RIGHT);
        custZipTF = new JTextField(7);

        customerPnl.add(custNameLbl);
        customerPnl.add(custNameTF);

        customerPnl.add(custStreetLbl);
        customerPnl.add(custStreetTF);

        customerPnl.add(custCityLbl);
        customerPnl.add(custCityTF);

        customerPnl.add(custStateLbl);
        customerPnl.add(custStateTF);

        customerPnl.add(custZipLbl);
        customerPnl.add(custZipTF);
    }
    public void createItemInfoPnl() {
        itemPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1, true), "ITEM INFORMATION"));
        itemPnl.setLayout(new GridLayout(1, 3));
        itemPnl.setAlignmentX(Component.CENTER_ALIGNMENT);

        itemNameLbl = new JLabel("Item Name: ", JLabel.RIGHT);
        itemNameTF = new JTextField(7);
        itemNameTF.setEnabled(false);

        itemCostLbl = new JLabel("Cost: ", JLabel.RIGHT);
        itemCostTF = new JTextField(7);
        itemCostTF.setEnabled(false);

        itemQuanLbl = new JLabel("Amount: ", JLabel.RIGHT);
        itemQuanTF = new JTextField(5);
        itemQuanTF.setEnabled(false);

        itemPnl.add(itemNameLbl);
        itemPnl.add(itemNameTF);

        itemPnl.add(itemCostLbl);
        itemPnl.add(itemCostTF);

        itemPnl.add(itemQuanLbl);
        itemPnl.add(itemQuanTF);
    }
    public void createInvoicePnl() {
        textArea = new JTextArea(15, 50);
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 14));
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        pane = new JScrollPane(textArea);

        displayPnl.add(pane);
    }
    public void createControlPnl() {
        controlPnl.setLayout(new GridLayout(2, 1));
        custBtn = new JButton("Address");
        custBtn.addActionListener((ActionEvent ae) -> {
            try {
                if (custNameTF.getText().isEmpty() || custStreetTF.getText().isEmpty() || custCityTF.getText().isEmpty() || custStateTF.getText().isEmpty() || custZipTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "One or more Address field is empty. Please Make sure you input data");
                }
                else if (Integer.parseInt(custZipTF.getText()) < 10000 || Integer.parseInt(custZipTF.getText()) > 99999) {
                    JOptionPane.showMessageDialog(null, "Zip Code has be a 5 digit number");
                }
                else {
                    tempClear();
                    disableCustBtn();
                    displayAddress(new Address(custStateTF.getText(), custCityTF.getText(), custStreetTF.getText(),
                            Integer.valueOf(custZipTF.getText())), custNameTF.getText());
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Zip Code cannot be a string value. Please enter a 5 digit number");
            }
        });

        prodBtn = new JButton("Item");
        prodBtn.setEnabled(false);
        prodBtn.addActionListener((ActionEvent ae) -> {
            try {
                if (itemNameTF.getText().isEmpty() || itemCostTF.getText().isEmpty() || itemQuanTF.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "One or more Item Info fields is empty. Please make sure you input data");
                } else {
                    tempClear();
                    displayProduct(new Products(itemNameTF.getText(), Double.valueOf(itemCostTF.getText())), Integer.valueOf(itemQuanTF.getText()));
                    tempClear2();
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cost and Amount cannot have a string value.\nMake sure it's a whole number.");
            }
        });
        controlPnl.add(custBtn);
        controlPnl.add(prodBtn);
    }
    public void createControlPnl2() {
        controlPnl2.setLayout(new GridLayout(2, 1));

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) -> clearInvoice());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl2.add(clearBtn);
        controlPnl2.add(quitBtn);
    }
    private void displayAddress(Address address,String cName) {
        Customers c = new Customers(cName, address);
        invoice = new Invoice(c);
        textArea.append(invoice.formatDisplay());
    }
    private void displayProduct(Products product, Integer prodQ) {
        LineItems newItem = new LineItems(product, prodQ);

        invoice.calcProduct(product, prodQ);

        textArea.append(invoice.formatDisplay());
    }
    private void tempClear() {
        textArea.setText(" ");
    }
    private void tempClear2(){
        itemNameTF.setText("");
        itemCostTF.setText("");
        itemQuanTF.setText("");
    }
    private void clearInvoice() {
        textArea.setText(" ");
        custNameTF.setText("");
        custStreetTF.setText("");
        custCityTF.setText("");
        custStateTF.setText("");
        custZipTF.setText("");

        itemNameTF.setText("");
        itemCostTF.setText("");
        itemQuanTF.setText("");

        enableCustomer();
    }
    private void disableCustBtn() {
        custNameTF.setEnabled(false);
        custStreetTF.setEnabled(false);
        custCityTF.setEnabled(false);
        custStateTF.setEnabled(false);
        custZipTF.setEnabled(false);

        custBtn.setEnabled(false);

        itemNameTF.setEnabled(true);
        itemQuanTF.setEnabled(true);
        itemCostTF.setEnabled(true);
        prodBtn.setEnabled(true);
    }
    private void enableCustomer()
    {
        custNameTF.setEnabled(true);
        custStreetTF.setEnabled(true);
        custCityTF.setEnabled(true);
        custStateTF.setEnabled(true);
        custZipTF.setEnabled(true);

        custBtn.setEnabled(true);

        itemNameTF.setEnabled(false);
        itemQuanTF.setEnabled(false);
        itemCostTF.setEnabled(false);
        prodBtn.setEnabled(false);
    }
}