package connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

import controller.ControllerChat;

public class ConnectionChat extends Thread{
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private ControllerChat chat;
	private int privateKey;
	private BigInteger finalKey;
	private String finalk;
	
	public ConnectionChat(ControllerChat chat) throws Exception {
		this.chat = chat;
		socket = new Socket("localhost", 5600);
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		BigInteger comun = new BigInteger(in.readLine());
		privateKey = (int) (Math.random() * (8 - 6)) + 6;
		BigInteger key = comun.pow(privateKey);
		out.println(key);
		
		BigInteger friendKey = new BigInteger(in.readLine());
		finalKey = friendKey.pow(privateKey);
		System.out.println(finalKey.toString());
		
		finalk = finalKey.toString();
		if(finalk.length() > 32) {
			finalk = finalKey.toString().substring(0, 32);
		}		

	}
	/**
	 * @author Angie, Alejandra, Melqui, Miguel
	 * @Method Método encargado de encriptar los mensajes entre la persona A y B
	 * @Param: the Msg es el mensaje a encriptar
	 * @Return: String mensaje encriptado con AES
	 */
	public String encriptar(String theMsg) {
		
		byte[] theCph;
		try {
			SecretKeySpec ks = new SecretKeySpec(finalk.getBytes(), "AES");
	        Cipher cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cf.init(Cipher.ENCRYPT_MODE,ks);
			theCph = cf.doFinal(theMsg.getBytes());
			return new String(encodeBase64(theCph));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		return null;
		
	}
	
	/**
	 * @author Angie, Alejandra, Melqui, Miguel
	 * @Method Método encargado de desencriptar los mensajes entre la persona A y B
	 * @Param: the Msg es el mensaje a desencriptar
	 * @Return: String mensaje desencriptar
	 */
	public String desencriptar(String theMsg) {
		
		byte[] theCph;
		try {
			SecretKeySpec ks = new SecretKeySpec(finalk.getBytes(), "AES");
	        Cipher cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cf.init(Cipher.DECRYPT_MODE,ks);
			theCph = cf.doFinal(decodeBase64(theMsg));
			return new String(theCph);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		return null;
		
	}
	
	/**
	 * @author Angie, Alejandra, Melqui, Miguel
	 * @Method Método encargado de informar si el socket está conectado o no
	 * @Return: booelan si está conectado el socket
	 */
	public boolean connected() {
		return socket.isConnected();
	}
	
	 
	/**
	 * @author Angie, Alejandra, Melqui, Miguel
	 * @Method hilo encargado estar escuchando y recibiendo los mensajes entre la persona A y B
	 */
	public synchronized void run() {

		while (socket.isConnected()) {
			
			String data = "";
			try {
				while (in.ready()) {
					data = in.readLine();
					chat.append(desencriptar(data));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	
	/**
	 * @author Angie, Alejandra, Melqui, Miguel
	 * @Method Método encargado de enviar el mensaje
	 */
	public void write(String message) {
		try {
			out.println(encriptar(message));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
