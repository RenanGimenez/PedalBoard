package application;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiInputReceiver implements Receiver {

	public Info info;
	private int count;

	public MidiInputReceiver(Info info) {
		this.info = info;
		this.count = 0;
	}
	
	@Override
	public void send(MidiMessage msg, long timeStamp) {
		
		if(msg.getMessage()[0] == -2 || msg.getMessage()[0] == -8) return;
		System.out.println("midi received number "+count++);
System.out.println(info.getName());
	    byte[] aMsg = msg.getMessage();
	    // take the MidiMessage msg and store it in a byte array

		// msg.getLength() returns the length of the message in bytes
	    for(int i=0;i<msg.getLength();i++){
	        System.out.println(aMsg[i]);
	        // aMsg[0] is something, velocity maybe? Not 100% sure.
	        // aMsg[1] is the note value as an int. This is the important one.
	        // aMsg[2] is pressed or not (0/100), it sends 100 when they key goes down,  
	        // and 0 when the key is back up again. With a better keyboard it could maybe
	        // send continuous values between then for how quickly it's pressed? 
	        // I'm only using VMPK for testing on the go, so it's either 
	        // clicked or not.
	        
	       try {
				msg = new ShortMessage(ShortMessage.NOTE_ON, aMsg[1]-5, aMsg[2]);
				Info[] infos = MidiSystem.getMidiDeviceInfo();
				 try {
					Receiver r = MidiSystem.getMidiDevice(infos[4]).getReceiver();
					r.send(msg, timeStamp);
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    System.out.println();
		/*try {
			MidiSystem.getReceiver().send(sm, timeStamp);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Override
	public void close() {}
}

