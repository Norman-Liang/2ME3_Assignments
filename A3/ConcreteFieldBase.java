import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ConcreteFieldBase implements FieldBase {


	private BiFunction <String, Integer, String> encryptScheme;
	private BiFunction <String, Integer, String> decryptScheme;
	private int key;
	private List<Spy> spyList = new ArrayList <Spy> ();
	private List <String> receivedMessages = new ArrayList <String> ();
	
	public ConcreteFieldBase(BiFunction <String, Integer, String> e, BiFunction <String, Integer, String> d, int k) {
		encryptScheme = e;
		decryptScheme = d;
		key = k;
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
	public void newSpy(Spy spy) {
		spyList.add(spy);
	}

	@Override
	public void removeSpy(Spy spy) {
		spyList.remove(spy);
	}

	@Override
	public List<Spy> getSpies() {
		return spyList;
	}

	@Override
	public void unregister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reregister() {
		// TODO Auto-generated method stub
		
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
