package node;

// This class implements the communication protocol
public class Action {
	private String message;
	private boolean isListener;
	private boolean isSpeaker;

	public Action () {
		String message = new String();
		boolean isListener = true;
		boolean isSpeaker = false;
	}

	public String sendMensage() {
		this.isSpeaker = false;
		this.isListener = true;
		return message;
	}

	public void reciveMensage(String message) {
		this.isSpeaker = true;
		this.isListener = false;
		this.message = message;
	}

	public boolean isSpeaking() {
		return this.isSpeaker;
	}

	public boolean isListenning() {
		return this.isListener;
	}
}