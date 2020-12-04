package main;

import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
	Esta clase es el servidor del programa. 
*/
public class Server {
	
	public static int KEY = 1223;
	private ServerSocket serverSocketChat;
	private ArrayList<ConnectionChatSer> chats;
	private int contador;
	
	public Server() {
		chats = new ArrayList<ConnectionChatSer>();

	}
	
	public void processChat(String data) {
		
		for(int i = 0; i < chats.size(); i++) {
			chats.get(i).write(data);
		}		
	}
	/**
		Este método sirve para enviar la clave  entre los clientes. 
		@param key este parametro es la clave que se va a enviar
		@param index este parametro es un índice del cliente.
	*/
	public void sendKey(BigInteger key, int index) {
		
		if(index == 0) {
			chats.get(1).write(key.toString());
		}else if(index == 1) {
			chats.get(0).write(key.toString());
		}
	}
	/**
		Este método es el mas importante de todos, debido a que permite inciar las 
		conexiones con los clientes. Sin este método no funcionaría el chat. 
	*/
	public void init() {
		try {
			 
			serverSocketChat = new ServerSocket(5600, 2);
			System.out.println("SOY EL SERVIDOR");
			 
		while (true) {
				
			if(contador<2) {
				Socket socketCh = serverSocketChat.accept();
				ConnectionChatSer chat = new ConnectionChatSer(this, socketCh, KEY, contador);
				chats.add(chat);
				chat.start();
				contador++;
				if(contador == 2) {
					sendKey(chats.get(0).getKey(),0);
					sendKey(chats.get(1).getKey(),1);
				}
			
			}
				
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.init();
		System.out.println("Inicializar todo");
	}
	
}
