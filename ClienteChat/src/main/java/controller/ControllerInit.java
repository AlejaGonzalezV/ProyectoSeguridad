package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerInit {

    @FXML
    private Button BtConnect;

    @FXML
    private TextField TxtName;
    
    private Stage stage;
    
    private AnchorPane root;
    
    private ControllerChat chat;
    
    public AnchorPane getRoot() {
    		return root;
    	}
    
    public Stage get() {
		
		Stage stageAct = (Stage) BtConnect.getScene().getWindow();
		return stageAct;
		
	}
    
    public void show() {
		this.stage.show();

	}
    
    public void setPlayButtonListener(EventHandler<ActionEvent> handler) {
		// TODO Auto-generated method stub
		BtConnect.setOnAction(handler);
		
	}
    
    @FXML
    void BtConnect(ActionEvent event) {
    	
    	try {
			showChat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public void showChat() throws IOException {
    	
		FXMLLoader loader = new FXMLLoader();
		try {

			AnchorPane pane = loader.load(getClass().getResourceAsStream("../view/chat.fxml"));
			chat = new ControllerChat();
			String name = TxtName.getText();
			chat.init(pane);
			chat.nameLbl.setText(name);
			//chat.user = TxtName.getText();
			//chat.setUser();
			Stage stage = new Stage();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			chat.setStage(stage);
			stage.setTitle("Chat");
			stage.show();
			Stage stageAct = (Stage) get();
			stageAct.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
