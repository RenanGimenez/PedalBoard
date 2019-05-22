package application;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

public class MidiHandler {
	static MidiDevice.Info[] infos;
	public static final void main(String[] args) throws InterruptedException {	
		 
		infos = MidiSystem.getMidiDeviceInfo();
		System.out.println("info.length " + infos.length);
		tentonsDesTrucs();
		//auCasOu();
		
		
	}

	private static void auCasOu() {
		MidiDevice device;
		for (int i = 0; i < infos.length; i++) {
			try {
				
				device = MidiSystem.getMidiDevice(infos[i]);
				//does the device have any transmitters?
				//if it does, add it to the device list
				System.out.println("\ninfo "+(i+1)+":");
				System.out.println("Name: "+device.getDeviceInfo().getName());
				System.out.println("Version: "+device.getDeviceInfo().getVersion());
				System.out.println("Vendor: "+device.getDeviceInfo().getVendor());
				System.out.println("Description: "+device.getDeviceInfo().getDescription());
				//get all transmitters
				List<Transmitter> transmitters = device.getTransmitters();
				List<Receiver> receivers = device.getReceivers();

/*
				System.out.println("----- TRANSMITTERS-----("+transmitters.size()+")");
				try {
					for(int j = 0; j < transmitters.size(); ++j) {
						System.out.println("Transmitter "+(j+1)+":");
						System.out.println(transmitters.get(j).toString());
						System.out.println();
					}
					System.out.println(device.getTransmitter());
				} catch(MidiUnavailableException e) {}
				try {
					System.out.println("----- RECEIVERS-----("+receivers.size()+")");
					for(int j = 0; j < receivers.size(); ++j) {
						System.out.println("receiver "+(j+1)+":");
						System.out.println(receivers.get(j).toString());
						System.out.println();
					}
					device.open();
					MidiMessage msg = new ShortMessage(ShortMessage.NOTE_ON, 92, 92);
					MidiMessage msg2 = new ShortMessage(ShortMessage.NOTE_ON, 80, 92);
					MidiMessage msg3 = new ShortMessage(ShortMessage.NOTE_ON, 84, 92);
					device.getReceiver().send(msg2 , -1);
					device.getReceiver().send(msg3 , -1);
					device.getReceiver().send(msg , -1);
					Transmitter t = device.getTransmitter();
					System.out.println(t);
					//device.close();
				} catch(MidiUnavailableException | InvalidMidiDataException | IllegalStateException e) {}
				System.out.println();
				//and for each transmitter
				*/
				for(int j = 0; j<transmitters.size();j++) {
					//create a new receiver
					transmitters.get(j).setReceiver(
							//using my own MidiInputReceiver
							new MidiInputReceiver(device.getDeviceInfo()));
				}

				Transmitter trans = device.getTransmitter();
				//Receiver r = trans.getReceiver();
				trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo()));

				//open each device
				device.open();
				//if code gets this far without throwing an exception
				//print a success message
				System.out.println(device.getDeviceInfo()+" Was Opened");

				 
			} catch (MidiUnavailableException e) {e.printStackTrace();}
		}
	}

	private static void tentonsDesTrucs() {
		try {
			MidiDevice input = MidiSystem.getMidiDevice(infos[1]);
			MidiDevice output = MidiSystem.getMidiDevice(infos[4]);
			input.open();
			output.open();
			input.getTransmitter().setReceiver(new FakeReceiver(output.getReceiver()));
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
