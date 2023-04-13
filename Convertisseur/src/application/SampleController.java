package application;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

/*
* Author: Eric Li
* Date: 6 avril 2023
*Description: Convertisseur d'Unités
*/

public class SampleController implements Initializable
{

	@FXML
    private TextField txtM1;

    @FXML
    private TextField txtM2;
    
    @FXML
    private TextField txtAP1;

    @FXML
    private TextField txtAP2;
    
	@FXML
    private TextField txtE1;

    @FXML
    private TextField txtE2;

    @FXML
    private ComboBox<String> cboM1;
    
    @FXML
    private ComboBox<String> cboM2;
    
    //Liste avec les unités de masse pour le combobox.
    public ObservableList<String> listM=FXCollections.observableArrayList("Milligramme", "Gramme", "Kilogramme");
    
    //Tableau qui représente/reprend les valeurs de masse. Cela veut dire que 1mg = 0.001g et 1mg = 0.000001kg.
    private double masse[] = {1.0, 0.001, 0.000001};

    @FXML
    private ComboBox<String> cboAP1;
  
    @FXML
    private ComboBox<String> cboAP2;  
    
    //Liste avec les unités d'angle de plan pour le combobox.
    public ObservableList<String> listAP=FXCollections.observableArrayList("Degré", "Radian", "Minute d'Arc");
    
    //Tableau qui représente/reprend les valeurs d'angle de plan. Cela veut dire que 1 degré = 0.0174533 radian et 1 degré = 60 minute d'arc.
    private double angleduplan[] = {1.0, 0.0174533, 60};
    
    @FXML
    private ComboBox<String> cboE1;
    
    @FXML
    private ComboBox<String> cboE2;
    
    //Liste avec les unités d'énergie pour le combobox.
    public ObservableList<String> listE=FXCollections.observableArrayList("Joule", "Kilojoule", "Kilocalorie");
    
    //Tableau qui représente/reprend les valeurs d'énergie. Cela veut dire que 1J = 0.001kJ et 1J = 0.000239006kcal.
    private double energie[] = {1.0, 0.001, 0.000239006};
    
    @FXML
    private Button btnEff1;
    
    @FXML
    private Button btnEff2;
    
    @FXML
    private Button btnEff3;
    
    @FXML
    private Button btnQuit1;
    
    @FXML
    private Button btnQuit2;
    
    @FXML
    private Button btnQuit3;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    	//Définit les options dans la ComboBox. 
    	//Dans ce cas, la liste des masses sera dans les ComboBoxs de masse. 
    	cboM1.setItems(listM);
    	cboM2.setItems(listM);
    	//Sélectionne le premier élément de la liste qui apparaîtra par défaut dans les ComboBoxs.
    	cboM1.getSelectionModel().selectFirst();
    	cboM2.getSelectionModel().selectFirst();
    	
    	cboAP1.setItems(listAP);
    	cboAP2.setItems(listAP);
    	cboAP1.getSelectionModel().selectFirst();
    	cboAP2.getSelectionModel().selectFirst();
    	
    	cboE1.setItems(listE);
    	cboE2.setItems(listE);
    	cboE1.getSelectionModel().selectFirst();
    	cboE2.getSelectionModel().selectFirst();
    }
    
    @FXML
    //Cette méthode assure qu'une valeur numérique est saisie dans les TextFields et empêche la saisie des valeurs non numériques qui ne sont pas des nombres décimaux valides.
    private void verifNum(KeyEvent e)
    {
    //Vérifie l'action effectuer par le clavier. 
    	TextField txt = (TextField)e.getSource();
    	
    	txt.textProperty().addListener((observable, oldValue, newValue)->
    	{
    		if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
    		{
    			txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
    		}
    	});
    }
    
    //Cette méthode prende une valeur du tableau en fonction de la sélection dans la Combobox.
    private double setTaux(ComboBox a, double tbl[])
    {
    	//Obtient l'index de l'élément sélectionné. 
    	int item=a.getSelectionModel().getSelectedIndex();
    	//Selectionne la valeur dans le tableau qui correspond avec l'élément sélectionné dans la Combobox.
    	//(i.e. pour masse: 0 = Milligramme = 1.0
    	double val=tbl[item];
    	//Retourne la valeur de l'élément sélectionné.
    	return val;
    }
    
    //Cette méthode permet d'effectuer les calculs de conversion des unités.
    private void convert(ComboBox a, ComboBox b, TextField c, TextField d, double tbl[])
    {
    	//Appelle la méthode "setTaux" sur "ComboBox a" pour determiner la taux de conversion qui doit être appliqué.
    	double from = setTaux(a,tbl);
    	double depart=0;
    	
    	//Vérifie la valeur de "TextField c".
    	//Si la valeur dans "TextField c" est rien, alors la valeur par défaut sera 0.
    	if(c.getText().equals(""))
    		depart=0;
    	else 
    	//Si il y a une valeur dans "TextField c", la valeur sera converti de String en Double et placé sous "depart".
    		depart = Double.parseDouble(c.getText());
    	//Appelle la méthode "setTaux" sur "ComboBox b" pour determiner la taux de conversion qui doit être appliqué.
    	double to = setTaux(b,tbl);
    	//Calcul pour la conversion. 
    	double dest=(to/from)*depart;
    	//Convertit la valeur calculée de Double en String.
    	d.setText(String.valueOf(dest));
    }

    //Cette méthode crée une alerte pop-up quand une valeur non numérique est saisie.
    void AlertNum(TextField a)
    {
    	try {
    		//Convertit la valeur dans le TextField de String en Integer.
    		double b=Double.parseDouble(a.getText());
    	//Attrape l'exception NumberFormatException et crée un alerte d'erreur.
    	} catch (NumberFormatException e) 
    	{
    		//Configuration de l'alerte d'erreur.
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.setHeaderText("Erreur");
    		alert.setTitle("Attention");
    		alert.setContentText("Veuillez entrer une valeur numérique!");
    		alert.show();
    		a.requestFocus();
    	}
    	
    }
    
    @FXML
    //Cette méthode permet l'utilisateur de quitter le convertisseur par la clique d'un bouton.
    void quitter()
    {
    	//Configuration du message d'abandonnement.
    	Alert a=new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("Confirmation");
		a.setTitle("Sortie");
		a.setContentText("Veux tu vraiment quitter?");
		//Bloque l'exécution de l'abandon jusqu'à ce que l'utilisateur appuie sur le bouton "OK".
		Optional<ButtonType> result=a.showAndWait();
		//Lorsque l'utilisateur clique sur le bouton "OK", le programme se ferme. 
		if(result.get()==ButtonType.OK)
			System.exit(0);
    }
    
    @FXML
    //Cette méthode permet l'utilisateur d'effacer toutes les TextFields en cliquant sur un seul bouton.
    private void Clear()
    {
    	txtM1.clear();
    	txtM2.clear();
    	txtAP1.clear();
    	txtAP2.clear();
    	txtE1.clear();	
    	txtE2.clear();
    }
    
    @FXML
    //Conversion des unités de masse.
    private void ConvertM1()
    {
    	convert(cboM1, cboM2, txtM1, txtM2, masse);
    	TextField M1 = txtM1;
    	AlertNum(M1);
    }
    
    @FXML
    private void ConvertM2()
    {
    	convert(cboM2, cboM1, txtM2, txtM1, masse);
    	
    	TextField M2 = txtM2;
    	AlertNum(M2);
    }
    
    @FXML
    //Une méthode distincte afin que la méthode "AlertNum" n'affecte pas les ComboBox.
    private void M()
    {
    	convert(cboM1, cboM2, txtM1, txtM2, masse);
    }
    
    @FXML
    //Conversion des unités d'angle de plan. 
    private void ConvertAP1()
    {
    	convert(cboAP1, cboAP2, txtAP1, txtAP2, angleduplan);
    	
    	TextField AP1 = txtAP1;
    	AlertNum(AP1);
    }
    
    @FXML
    private void ConvertAP2()
    {
    	convert(cboAP2, cboAP1, txtAP2, txtAP1, angleduplan);
    	
    	TextField AP2 = txtAP2;
    	AlertNum(AP2);
    }
    
    @FXML
    //Une méthode distincte afin que la méthode "AlertNum" n'affecte pas les ComboBox.
    private void AP()
    {
    	convert(cboAP1, cboAP2, txtAP1, txtAP2, angleduplan);
    }
    
    @FXML
    //Conversion des unités d'énergie.
    private void ConvertE1()
    {
    	convert(cboE1, cboE2, txtE1, txtE2, energie);
    	
    	TextField E1 = txtE1;
    	AlertNum(E1);
    }
    
    @FXML
    private void ConvertE2()
    {
    	convert(cboE2, cboE1, txtE2, txtE1, energie);
    	
    	TextField E2 = txtE2;
    	AlertNum(E2);
    }
    
    @FXML
    //Une méthode distincte afin que la méthode "AlertNum" n'affecte pas les ComboBox.
    private void E()
    {
    	convert(cboE1, cboE2, txtE1, txtE2, energie);
    }
    

}
