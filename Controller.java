import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class Controller extends WindowAdapter implements ActionListener{
    
    private Model model;
    private View view;

    private String radio, knapp, fnamn, enamn, id;
    private DefaultTableModel tableModel;


    public Controller(View event){
        view = event;
        model = new Model();

        tableModel = new DefaultTableModel();

    }


    public void actionPerformed(ActionEvent e){

        knapp = e.getActionCommand();
        radio = view.getBtnGroup();
        fnamn = view.getText1();
        enamn = view.getText2();
        id = view.getText3();

        if(knapp == "Sök" && radio == "Alla"){
            tableModel = model.taUtAlla();
            view.setTable(tableModel);
        } else if(knapp == "Sök" && radio != "Alla"){
            tableModel = model.taUtSpecifik(fnamn, enamn, id, radio);
            view.setTable(tableModel);
        } else if (knapp == "Lägg till" && fnamn.length() != 0 && enamn.length() != 0){
            model.addToDB(fnamn, enamn);
            tableModel = model.taUtAlla();
            view.setTable(tableModel);
        } else if (knapp == "Ändra" && id.length() != 0){
            model.updateDB(fnamn, enamn, id);
            tableModel = model.taUtAlla();
            view.setTable(tableModel);
        } else if (knapp == "Ta bort" && id.length() != 0){
            model.deleteFromDB(Integer.parseInt(id));
            tableModel = model.taUtAlla();
            view.setTable(tableModel);
        }
    }

    public void windowClosing (WindowEvent e) {
		System.out.println("Fönster nedstängt");
		System.exit(1);
	}

}
