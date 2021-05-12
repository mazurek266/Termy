import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class SerialCommunicationSettings implements ActionListener{
	
	JFrame settingsWindow = new JFrame();
	JComboBox baudRateBox;
	JComboBox dataBitsBox;
	JComboBox parityBox;
	JComboBox stopBitsBox;
	JComboBox flowControlBox;
	JLabel baudRateLabel;
	JLabel dataBitsLabel;
	JLabel parityLabel;
	JLabel stopBitsLabel;
	JLabel flowControlLabel;
	JButton updateSettingsButton;
	
	SettingsListener toMainWindow;
	
	Integer [] standardBaudRates = {110, 150, 300, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200};
	Integer [] dataBits = {4, 5, 6, 7, 8};
	String  [] parity = {"NO_PARITY", "ODD", "EVEN", "MARK_PARITY", "SPACE_PARITY" };
	String [] stopBits = {"ONE_STOP_BIT", "ONE_POINT_FIVE_STOP_BITS", "TWO_STOP_BITS" };
	String [] flowControl = {"Xon / Xoff", "Hardware", "None"}  ;
	
	SerialCommunicationSettings(SettingsListener serialSettings){
		
		
		toMainWindow = serialSettings;
		
		baudRateBox = new JComboBox(standardBaudRates);
		baudRateBox.setBounds(180, 20, 200, 40);
		baudRateBox.setFocusable(false);
		baudRateBox.addActionListener(this);
		
		dataBitsBox = new JComboBox(dataBits);
		dataBitsBox.setBounds(180, 70, 200, 40);
		dataBitsBox.setFocusable(false);
		dataBitsBox.addActionListener(this);
		
		parityBox = new JComboBox(parity);
		parityBox.setBounds(180, 120, 200, 40);
		parityBox.setFocusable(false);
		parityBox.addActionListener(this);
		
		stopBitsBox = new JComboBox(stopBits);
		stopBitsBox.setBounds(180, 170, 200, 40);
		stopBitsBox.setFocusable(false);
		stopBitsBox.addActionListener(this);
		
		flowControlBox = new JComboBox(flowControl);
		flowControlBox.setBounds(180, 220, 200, 40);
		flowControlBox.setFocusable(false);
		flowControlBox.addActionListener(this);
		
		updateSettingsButton = new JButton("Update settings");
		updateSettingsButton.setBounds(110, 300, 200, 40);
		updateSettingsButton.setFocusable(false);
		updateSettingsButton.addActionListener(this);
		
		baudRateLabel = new JLabel();
		baudRateLabel.setText("Baud rate: ");
		baudRateLabel.setBounds(40, 20, 100, 40);
		
		dataBitsLabel = new JLabel();
		dataBitsLabel.setText("Data bits: ");
		dataBitsLabel.setBounds(40, 70, 100, 40);
		
		parityLabel = new JLabel();
		parityLabel.setText("Parity: ");
		parityLabel.setBounds(40, 120, 100, 40);
		
		stopBitsLabel = new JLabel();
		stopBitsLabel.setText("Stop bits: ");
		stopBitsLabel.setBounds(40, 170, 100, 40);

		flowControlLabel= new JLabel();
		flowControlLabel.setText("Flow control: ");
		flowControlLabel.setBounds(40, 220, 100, 40);
		
		settingsWindow.add(baudRateBox);
		settingsWindow.add(dataBitsBox);
		settingsWindow.add(parityBox);
		settingsWindow.add(stopBitsBox);
		settingsWindow.add(flowControlBox);
		settingsWindow.add(updateSettingsButton);
		settingsWindow.add(baudRateLabel);
		settingsWindow.add(dataBitsLabel);
		settingsWindow.add(parityLabel);
		settingsWindow.add(stopBitsLabel);
		settingsWindow.add(flowControlLabel);
		
		settingsWindow.setTitle("Settings");
		settingsWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		settingsWindow.setSize(420, 400);
		settingsWindow.setLayout(null);
		settingsWindow.setResizable(false);
		settingsWindow.setVisible(true);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == updateSettingsButton) {
			
			
			toMainWindow.setSerialSettings((Integer)baudRateBox.getSelectedItem(),
										   (Integer)dataBitsBox.getSelectedItem(),
										   (Integer)stopBitsBox.getSelectedIndex(),
										   (Integer)parityBox.getSelectedIndex());
		}
		
	}

	
	
}
