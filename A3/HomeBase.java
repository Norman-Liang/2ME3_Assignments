import java.util.function.BiFunction;
import java.util.*;

public class HomeBase {
	Encryption encrypt = new Encryption();
	
	private BiFunction <String, Integer, String> encryptScheme = encrypt.getEncrypt();;
	private BiFunction <String, Integer, String> decryptScheme = encrypt.getDecrypt();;
	private int key = 0;
	private List <FieldBase> fieldBases = new ArrayList <FieldBase> ();
	private List <String> receivedMessages = new ArrayList <String> ();
	
	public HomeBase() {
	}
	
	public void send(String m) {
		encrypt(m, key);
	}
	
	public void receive(String m) { 
		receivedMessages.add(decrypt(m, key));
	}
	
	public void newFieldBase(FieldBase base) {
		fieldBases.add(base);
	}
	
	public void removeFieldBase(FieldBase base) {
		fieldBases.remove(base);
	}
	
	public int getKey() {
		return key;
	}

	private void changeEncryption(BiFunction <String, Integer, String> e, BiFunction <String, Integer, String> d, int k) {
		encrypt.changeEncrypt(e);
		encrypt.changeDecrypt(d);
		encryptScheme = encrypt.getEncrypt();
		decryptScheme = encrypt.getDecrypt();		
		key = k;
		
		for (FieldBase base:fieldBases) {
			base.update(e, d, k);
			for (Spy spy:base.getSpies()) {
				spy.update(e, d, k);
			}
		}
	}
	
	public String encrypt(String m, int k) {
		return encryptScheme.apply(m,k);
	}
	
	public String decrypt(String m, int k) {
		return decryptScheme.apply(m,k);
	}
}
