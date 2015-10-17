import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class InvoiceWindow extends JFrame {

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;

    JLabel jlDate;

    JLabel jlItemName;
    JLabel jlClientName;
    JLabel jlClientAddress;

    JTextField jtItemName;
    JTextField jtCompanyName;
    JTextField jtCompanyAddress;
    JTextField jtDate;

    JTextField jtClientName;
    JTextField jtClientAddress;

    String sCompanyName;
    String sCompanyAddress;

    String sClientName;
    String sClientAddress;

    String sDate;

    JPanel jpMain1;
    JPanel jpMainInfo;
    JPanel jpButtons;

    JButton jbSubmit;
    JButton jbAddItem;
    JButton jbDeleteItem;

    int iCurrentJPanel;
    int iNumOfItems;

    ArrayList<JPanel[]> alJPanel;
    JPanel[] aryPanelArray;

    JLabel jlQty;
    JLabel jlDescription;
    JLabel jlUnitPrice;

    JTextField jtQty;
    JTextField jtDescription;
    JTextField jtUnitPrice;


    public InvoiceWindow() {
        super("Invoice Program");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        addActions();
        setVisible(true);

    }

    public void addComponents() {

        alJPanel = new ArrayList<>();

        iCurrentJPanel = -1;
        iNumOfItems = 0;

        aryPanelArray = new JPanel[4];

        jlCompanyName = new JLabel("Company Name: ");
        jlCompanyAddress = new JLabel("Company Address: ");

        jlDate = new JLabel("Date: ");

        jlClientName = new JLabel("Client Name: ");
        jlClientAddress = new JLabel("Client Address: ");

        jtCompanyName = new JTextField();
        jtCompanyAddress = new JTextField();

        jtDate = new JTextField();

        jtClientName = new JTextField();
        jtClientAddress = new JTextField();

        jlCompanyName.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlCompanyAddress.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlDate.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlClientAddress.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlClientName.setBorder(new EmptyBorder(10, 10, 10, 10));

        jpMain1 = new JPanel(); //Just a place holder for the JPanels so it is in the centre
        jpMainInfo = new JPanel(new GridLayout(0, 2));  //Will have JLabels and JTextFields for Company and Client info
        jpButtons = new JPanel(new GridLayout(1, 1));   //Hold the submit button

        jpMain1.setLayout(new BoxLayout(jpMain1, BoxLayout.Y_AXIS));

        jbSubmit = new JButton("Submit");
        jbAddItem = new JButton("Add Item");
        jbDeleteItem = new JButton("Delete Item");

        //Add jpMain1 to the center of the JFrame
        add(jpMain1, BorderLayout.CENTER);

        //Add components to jpMainInfo
        jpMainInfo.add(jlClientName);
        jpMainInfo.add(jtClientName);

        jpMainInfo.add(jlClientAddress);
        jpMainInfo.add(jtClientAddress);

        jpMainInfo.add(jlDate);
        jpMainInfo.add(jtDate);

        jpMainInfo.add(jbAddItem);
        jpMainInfo.add(jbDeleteItem);

        //Add jpMainInfo to jpMain1
        jpMain1.add(jpMainInfo);

        //Add submit button to jpButtons
        jpButtons.add(jbSubmit);

        //Add jpButtons to jpMain1
        //Set Minimum size of the button panel
        jpMain1.add(jpButtons);

        Double dScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int iScreenWidth = dScreenWidth.intValue();

        jpButtons.setMaximumSize(new Dimension(iScreenWidth, 400));

        //Create a title bordered for the jPanels
        TitledBorder tbBorder = BorderFactory.createTitledBorder("Company and Client Info");
        tbBorder.setTitleColor(Color.BLACK);
        tbBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jpMainInfo.setBorder(tbBorder);
    }

    public void addActions() {


        jbSubmit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sCompanyName = jtCompanyName.getText();
                        sCompanyAddress = jtCompanyAddress.getText();

                        sClientName = jtClientName.getText();
                        sClientAddress = jtClientAddress.getText();

                        System.out.println("Company Name: " + sCompanyName);
                        System.out.println("Company Address: " + sCompanyAddress);
                        System.out.println("Client Name: " + sClientName);
                        System.out.println("Client Name: " + sClientAddress);
                        System.out.println();
                    }
                });

        jbAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iCurrentJPanel++;
                createItemPanel();
            }
        });

        jbDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemPanel();
            }
        });
    }

    /**
     * Creates an item panel
     * It also adds it to the arraylist holding
     * All textfields and stuff
     */
    public void createItemPanel() {
        iNumOfItems++;
        JPanel jpTemporary = createItemComponents();
        aryPanelArray = new JPanel[100000];

        TitledBorder tbBorder = BorderFactory.createTitledBorder("Item no. " + iNumOfItems);
        tbBorder.setTitleColor(Color.BLACK);
        tbBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jpTemporary.setBorder(tbBorder);

        jpMain1.remove(jbAddItem);
        jpMain1.remove(jbDeleteItem);

        jpMain1.remove(jpButtons);
        alJPanel.add(aryPanelArray);

        jpTemporary.add(jbAddItem);
        jpTemporary.add(jbDeleteItem);

        jpMain1.add(jpTemporary);
        jpMain1.add(jpButtons);

        aryPanelArray[iCurrentJPanel] = jpTemporary;
        alJPanel.add(aryPanelArray);

        System.out.println(Arrays.toString(aryPanelArray));

        revalidate();
    }


    /**
     * Creates all JTextFields and JLabels
     * For the item
     * Then adds a border with the item number on it
     * Also adds to array
     * Returns a JPanel
     */
    public JPanel createItemComponents() {

        System.out.println("Creating Components for Item");

        JLabel jlItemName = new JLabel("Item Name: ");
        JLabel jlItemQuantity = new JLabel("Item Quantity: ");
        JLabel jlItemPrice = new JLabel("Item Price: ");

        JTextField jtfItemName = new JTextField();
        JTextField jtfItemQuantity = new JTextField();
        JTextField jtfItemPrice = new JTextField();

        System.out.println("Adding Borders");

        jlItemName.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlItemQuantity.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlItemPrice.setBorder(new EmptyBorder(10, 10, 10, 10));

        System.out.println("Creating temporary JPanel");
        JPanel jpTemporary = new JPanel(new GridLayout(0, 2));

        jpTemporary.add(jlItemName);
        jpTemporary.add(jtfItemName);

        jpTemporary.add(jlItemQuantity);
        jpTemporary.add(jtfItemQuantity);

        jpTemporary.add(jlItemPrice);
        jpTemporary.add(jtfItemPrice);

        System.out.println("Adding Temporary JPanel to array aryPanelArray");
        aryPanelArray[iCurrentJPanel] = jpTemporary;


        return jpTemporary;
    }

    /**
     * Removes the latest item panel
     * Also removes from the array
     */
    public void removeItemPanel() {
        if (!(iNumOfItems == 0)) {
            System.out.println("REMOVING JPanel");
            jpMain1.remove(alJPanel.get(iCurrentJPanel)[iCurrentJPanel]);
            alJPanel.remove(alJPanel.get(iCurrentJPanel)[iCurrentJPanel]);

            revalidate();
        }
    }
}