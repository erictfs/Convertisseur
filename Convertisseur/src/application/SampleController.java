package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;


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
    
    public ObservableList<String> listM=FXCollections.observableArrayList("Milligramme", "Gramme", "Kilogramme");
    
    private double masse[] = {1.0, 0.001, 0.000001};

    @FXML
    private ComboBox<String> cboAP1;
  
    @FXML
    private ComboBox<String> cboAP2;
    
    public ObservableList<String> listAP=FXCollections.observableArrayList("Degré", "Radian", "Minute d'Arc");
    
    private double angleduplan[] = {1.0, 0.0174533, 60};
    
    @FXML
    private ComboBox<String> cboE1;
    
    @FXML
    private ComboBox<String> cboE2;
    
    public ObservableList<String> listE=FXCollections.observableArrayList("Joule", "Kilojoule", "Kilocaorie");
    
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
    	cboM1.setItems(listM);
    	cboM2.setItems(listM);
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
    private void verifNum(KeyEvent e)
    {
    	TextField txt = (TextField)e.getSource();
    	
    	txt.textProperty().addListener((observable, oldValue, newValue)->
    	{
    		if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
    		{
    			txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
    		}
    	
    	});
    
    }
    
    private double setTaux(ComboBox a, double tbl[])
    {
    	int item=a.getSelectionModel().getSelectedIndex();
    	double val=tbl[item];
    	return val;
    }
    
    private void convert(ComboBox a, ComboBox b, TextField c, TextField d, double tbl[])
    {
    	double from = setTaux(a,tbl);
    	double depart=0;
    	
    	if(c.getText().equals(""))
    		depart=0;
    	else 
    		depart = Double.parseDouble(c.getText());
    	double to = setTaux(b,tbl);
    	double dest=(to/from)*depart;
    	d.setText(String.valueOf(dest));
    }
    
    void checkNum(TextField a)
    {
    	try {
    		int b=Integer.parseInt(a.getText());
    	} catch (NumberFormatException e) 
    	{
    		Alert alert=new Alert(AlertType.ERROR);
    		alert.setHeaderText("Erreur");
    		alert.setTitle("Attention");
    		alert.setContentText("Entrer numerique seulement");
    		alert.show();
    		a.requestFocus();
    	}
    }
    
    @FXML
    private void ConvertM1()
    {
    	convert(cboM1, cboM2, txtM1, txtM2, masse);
    }
    
    @FXML
    private void ConvertM2()
    {
    	convert(cboM2, cboM1, txtM2, txtM1, masse);
    }
    
    @FXML
    private void ConvertAP1()
    {
    	convert(cboAP1, cboAP2, txtAP1, txtAP2, angleduplan);
    }
    
    @FXML
    private void ConvertAP2()
    {
    	convert(cboAP1, cboAP2, txtAP1, txtAP2, angleduplan);
    }
    
    @FXML
    private void ConvertE1()
    {
    	convert(cboE1, cboE2, txtE1, txtE2, energie);
    }
    
    @FXML
    private void ConvertE2()
    {
    	convert(cboE1, cboE2, txtE1, txtE2, energie);
    }
    
  

}
