import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class View {

    private JFrame jframe;
    private JPanel rightPanel, leftPanel, fNamePanel, eNamePanel, IDPanel, btnPanel1, btnPanel2;
    private JTextField text1, text2, text3;
    private JLabel lText1, lText2, lText3;
    private JTable table;
    private JScrollPane scroll;
    private JRadioButton rFNamn, rENamn, rID, rAll;
    private ButtonGroup btnGroup;
    private JButton searchBtn, addBtn, updateBtn, deleteBtn;

    private Controller controller;

    public View(){

        jframe = new JFrame("Databastest");
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        fNamePanel = new JPanel();
        eNamePanel = new JPanel();
        IDPanel = new JPanel();
        text1 = new JTextField();
        lText1 = new JLabel("Förnamn:");
        text2 = new JTextField();
        lText2 = new JLabel("Efternamn:");
        text3 = new JTextField();
        lText3 = new JLabel("ID:");
        rAll = new JRadioButton("Alla", true);
        rFNamn = new JRadioButton("Förnamn");
        rENamn = new JRadioButton("Efternamn");
        rID = new JRadioButton("ID");
        btnGroup = new ButtonGroup();
        btnPanel1 = new JPanel();
        btnPanel2 = new JPanel();
        searchBtn = new JButton("Sök");
        addBtn = new JButton("Lägg till");
        updateBtn = new JButton("Ändra");
        deleteBtn = new JButton("Ta bort");
        controller = new Controller(this); 

        table = new JTable();
        scroll = new JScrollPane(table);

        text1.setPreferredSize(new Dimension(100, 20));
        text2.setPreferredSize(new Dimension(100, 20));
        text3.setPreferredSize(new Dimension(100, 20));

        fNamePanel.add(lText1);
        fNamePanel.add(text1);

        eNamePanel.add(lText2);
        eNamePanel.add(text2);

        IDPanel.add(lText3);
        IDPanel.add(text3);

        rAll.setPreferredSize(new Dimension(100, 20));
        rFNamn.setPreferredSize(new Dimension(100, 20));
        rENamn.setPreferredSize(new Dimension(100, 20));
        rID.setPreferredSize(new Dimension(100, 20));

        searchBtn.addActionListener(controller);
        addBtn.addActionListener(controller);
        updateBtn.addActionListener(controller);
        deleteBtn.addActionListener(controller);

        btnPanel1.add(searchBtn);
        btnPanel1.add(addBtn);

        btnPanel2.add(updateBtn);
        btnPanel2.add(deleteBtn);

        leftPanel.add(fNamePanel);
        leftPanel.add(eNamePanel);
        leftPanel.add(IDPanel);
        btnGroup.add(rAll);
        btnGroup.add(rFNamn);
        btnGroup.add(rENamn);
        btnGroup.add(rID);
        leftPanel.add(rAll);
        leftPanel.add(rFNamn);
        leftPanel.add(rENamn);
        leftPanel.add(rID);
        leftPanel.add(btnPanel1);
        leftPanel.add(btnPanel2);
        leftPanel.setPreferredSize(new Dimension(200, 200));

        rightPanel.add(scroll);
        rightPanel.setPreferredSize(new Dimension(400, 400));

        jframe.add(leftPanel, BorderLayout.WEST);
        jframe.add(rightPanel, BorderLayout.CENTER);
        jframe.addWindowListener(controller);

        jframe.setSize(800, 600);
        jframe.setVisible(true);

    }

    public String getBtnGroup(){
        if(rFNamn.isSelected()){
            return "Förnamn";
        } else if (rENamn.isSelected()){
            return "Efternamn";
        } else if (rID.isSelected()){
            return "id";
        } else if (rAll.isSelected()){
            return "Alla";
        }

        return "Failure";
    }

    public void setTable(DefaultTableModel tableModel){
        table.setModel(tableModel);
    }

    public String getText1(){
        return text1.getText();
    }

    public String getText2(){
        return text2.getText();
    }

    public String getText3(){
        return text3.getText();
    }
    
    public static void main(String[] args) {
        new View();
    }
}
