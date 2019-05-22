package application;
import javax.sound.midi.*;

import java.util.*;
import java.io.*;
import java.awt.event.*;

public class FakeReceiver implements Receiver {

	private Receiver output;

	public FakeReceiver(Receiver output) {
		this.output = output;
	}





	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MidiMessage message, long timeStamp) {
		byte[] msg = message.getMessage();
		System.out.println("Message reçu : "+msg);
		//msg[1] += 12;
		try {
			System.out.println("Note jouée : "+getNoteJouée(msg[1]));
			if(msg[1]%12 == 0 || msg[1]%12 == 2 || msg[1]%12 == 5 || msg[1]%12 == 7) {
			output.send(new ShortMessage(msg[0],msg[1],msg[2]), -1);
			output.send(new ShortMessage(msg[0],msg[1]-12,msg[2]), -1);
			output.send(new ShortMessage(msg[0],msg[1]-24,msg[2]), -1);
			} else {
				output.send(new ShortMessage(msg[0],msg[1],msg[2]), -1);
				output.send(new ShortMessage(msg[0],msg[1]-12,msg[2]), -1);
				output.send(new ShortMessage(msg[0],msg[1]-24,msg[2]), -1);
			}
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





	private String getNoteJouée(byte b) {
		String[] range = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		return range[b%12] + Integer.toString(b/12 - 1);

	}
}
