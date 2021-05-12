import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.fazecast.jSerialComm.*;

public class MainWindow implements ActionListener, SettingsListener{

	JFrame mainWindow = new JFrame();
	
	JButton getPortsButton = new JButton("List ports");
	JButton settingsButton = new JButton("Settings");
	JButton sendButton = new JButton("Send");
	JButton recieveButton = new JButton("Recieve");
	
	JTextArea sendDataTextArea = new JTextArea();
	
	JComboBox portListBox = new JComboBox();
	
	SerialPort availableSerialPorts [] = SerialPort.getCommPorts();
	SerialPort communicationPort;
	
	private ArrayList<String> dataToSend = new ArrayList<String>();
	
	MainWindow(){

		
		sendDataTextArea.setBounds(20, 20, 400, 550);
 
		portListBox.setBounds(450, 50, 150, 30);
		portListBox.addActionListener(this);
		
		getPortsButton.setBounds(500, 100, 100, 40);
		getPortsButton.setFocusable(false);
		getPortsButton.addActionListener(this);
		
		settingsButton.setBounds(500, 150, 100, 40);
		settingsButton.setFocusable(false);
		settingsButton.addActionListener(this);
		
		sendButton.setBounds(500, 200, 100, 40);
		sendButton.setFocusable(false);
		sendButton.addActionListener(this);
		
		recieveButton.setBounds(500, 250, 100, 40);
		recieveButton.setFocusable(false);
		recieveButton.addActionListener(this);
		
		mainWindow.add(getPortsButton);
		mainWindow.add(sendDataTextArea);
		mainWindow.add(portListBox);
		mainWindow.add(settingsButton);
		mainWindow.add(sendButton);
		mainWindow.add(recieveButton);
		
		mainWindow.setTitle("Termy");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(640, 640);
		mainWindow.setLayout(null);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		if(e.getSource()== settingsButton) {
			
			//Opens settings window
			SerialCommunicationSettings serialSettings = new SerialCommunicationSettings(this);
		}
		
		if (e.getSource()== getPortsButton) {
			
			for (int i = 0; i < availableSerialPorts.length; i++) {		
				//List all available ports in portListBox
				portListBox.addItem(availableSerialPorts[i].getDescriptivePortName());
			}
		}
		
		if (e.getSource()== sendButton) {
			
			SerialComPort comPort = new SerialComPort(communicationPort);
			dataToSend.add(sendDataTextArea.getText());
			comPort.sendMessage(dataToSend);
		}
		
		if (e.getSource()== recieveButton) {
			
			SerialComPort comPort = new SerialComPort(communicationPort);
			sendDataTextArea.setText(comPort.recieveMessage().toString());
		}
		
		if (e.getSource()== portListBox) {
			
			//Sets the communication port descriptor from the available ports listed in the portListBox	
			communicationPort = availableSerialPorts[portListBox.getSelectedIndex()];
		}
		
	}

	//Listener for communication parameters set in settings window
	@Override
	public void setSerialSettings(int baud, int dataBits, int stopBits, int parity) {
		// TODO Auto-generated method stub
		communicationPort.setComPortParameters(baud, dataBits, stopBits, parity);	
	}
	
}
