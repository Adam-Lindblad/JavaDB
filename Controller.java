import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowListener;


public class Controller /*extends WindowAdapter*/ implements ActionListener{
    
    private Model model;
    private View view;

    private String radio, knapp, fnamn, enamn, id;


    public Controller(View event){
        view = event;
        model = new Model();

    }

    public void actionPerformed(ActionEvent e){

        knapp = e.getActionCommand();
        radio = view.getBtnGroup();
        fnamn = view.getText1();
        enamn = view.getText2();
        id = view.getText3();

        /*if(knapp == "Sök" && radio == "Alla"){
            model.taUtAlla();
        }*/



    }

    /*public void windowClosing (WindowEvent e) {
		System.out.println("Fönster nedstängt");
		System.exit(1);
	}*/

}
