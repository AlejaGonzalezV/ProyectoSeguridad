package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;

public class ConnectionChatSer extends Thread{
	
	private int index;
	private Server server;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private BigInteger keyp;

	public ConnectionChatSer(Server server, Socket socket, int key, int index) throws Exception {

		this.server = server;
		this.socket = socket;
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println(key);
		keyp = new BigInteger(in.readLine());

	}
	
	public BigInteger getKey() {
		return keyp;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public synchronized void run() {
		
		
		while (socket.isConnected()) {

			String data = "";
			try {
				while (in.ready()) {
					data = in.readLine();
					System.out.println(data);
					server.processChat(data);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
	}
	
	public void write(String message) {
		out.println(message);
	}

}
