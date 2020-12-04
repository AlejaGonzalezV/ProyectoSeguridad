package controller;
import java.net.URL;

import java.util.ResourceBundle;

import connection.ConnectionChat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * Clase encargada de controlar la vista del chat
 */

public class ControllerChat implements Initializable{

    @FXML
    private Button BtSend;

    @FXML
    private TextArea TxtChat;

    @FXML
    private TextField TxtMessage;
    
    @FXML
    public Label nameLbl;
    
    private Parent parent;

	private Stage stage;
	
	/*
	 * Atributo que representa el nombre del usuario de la aplicación dentro del chat
	 */
	public String user;
	
	/*
	 * Atributo que representa la conexión por parte del usuario con el servidor
	 */
	private ConnectionChat chat;
	
	public void init(Parent parent) {
		this.parent = parent;

		for (int i = 0; i < parent.getChildrenUnmodifiable().size(); i++) {
			Node node = parent.getChildrenUnmodifiable().get(i);

			if (node != null && node.getId() != null) {
				if (node.getId().equals("nameLbl")) {
					nameLbl = (Label) node;
				}
			}
		}
		
	}
	
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	public Parent getParent() {
		return parent;
	}

	public Stage getStage() {
		return stage;
	}
	
	@FXML
	void BtSend(ActionEvent event) {
		chat(TxtMessage.getText());
		TxtMessage.setText("");

	}
	
	/*
	 * Este método se encarga de enviar el mensaje al hilo encargado de la comunicación con el servidor
	 * @Param: String data que almacena el mensaje escrito por el usuario
	 */
	public void chat(String data) {
		chat.write(nameLbl.getText() + ": " + data);

	}
	
	/*
	 * Este método se encarga de instanciar y ejecutar el hilo encargado de la comunicación entre el cliente y el servidor
	 */
	public void conectarChat() throws Exception {
		chat = new ConnectionChat(this);
		chat.start();
		System.out.println("Hilo chat");
		System.out.println(chat.connected());
		
	}
	
	public void append(String data) {
		TxtChat.appendText(data + System.lineSeparator());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			conectarChat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
