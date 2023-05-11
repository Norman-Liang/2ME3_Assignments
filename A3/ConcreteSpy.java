import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ConcreteSpy implements Spy{


	private BiFunction <String, Integer, String> encryptScheme;
	private BiFunction <String, Integer, String> decryptScheme;
	private int key;
	private boolean alive;
	private List <String> receivedMessages = new ArrayList <String> ();
	
	public ConcreteSpy(BiFunction <String, Integer, String> e, BiFunction <String, Integer, String> d, int k) {
		encryptScheme = e;
		decryptScheme = d;
		key = k;
		alive = true;
	}
	
	@Override
	public void send(String m) {
		encrypt(m,key);
	}

	@Override
	public void receive(String m) {
		receivedMessages.add(decrypt(m,key));		
	}

	@Override
	public void dies() {
		alive = false;
	}

	@Override
	public void update(BiFunction<String, Integer, String> e, BiFunction<String, Integer, String> d, int k) {
		encryptScheme = e;
		decryptScheme = d;
		key = k;
	}
	
	@Override
	public String encrypt(String m, int k) {
		return encryptScheme.apply(m,k);
	}
	
	@Override
	public String decrypt(String m, int k) {
		return decryptScheme.apply(m,k);
	}

}
