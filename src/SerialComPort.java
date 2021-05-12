import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.*;

public class SerialComPort {

	private SerialPort comPort;
	private ArrayList<String> incommingData = new ArrayList<String>();
	
	SerialComPort(SerialPort activePort){
		
		comPort = activePort;
		
	}
	
	void openConnection() {
		
		if (comPort.openPort()) {
			JOptionPane.showMessageDialog(null, "Succesfully opened port. ","Status",JOptionPane.PLAIN_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: Could not open port. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void closeConnection() {
		
		if (comPort.closePort()) {
			JOptionPane.showMessageDialog(null, "Succesfully closed port. ","Status",JOptionPane.PLAIN_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: Could not close port. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void sendMessage(ArrayList<String>  buffer)  {
	
		openConnection();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		
		for (int i = 0; i < buffer.size(); i++) {
			try {
				comPort.getOutputStream().write(Integer.parseInt(buffer.get(i)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		closeConnection();
	}
	
	ArrayList<String> recieveMessage() {
	
		openConnection();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		
		
			Scanner data  = new Scanner(comPort.getInputStream());
			while(data.hasNextLine()) {
				
				incommingData.add(data.nextLine().toString());
			}
		
		closeConnection();
	
		return incommingData;
	}
	
}
