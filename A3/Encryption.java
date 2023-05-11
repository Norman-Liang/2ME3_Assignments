import java.util.function.BiFunction;

public class Encryption {
	
	private BiFunction <String, Integer, String> encryptScheme = (x,y) -> x;
	private BiFunction <String, Integer, String> decryptScheme = (x,y) -> x;

	public Encryption() {
	}
	
	public void changeEncrypt(BiFunction<String, Integer, String> e) {
		this.encryptScheme = e;
	}
	
	public void changeDecrypt(BiFunction<String, Integer, String> d) {
		this.decryptScheme = d;
	}

	public BiFunction<String, Integer, String> getEncrypt() {
		return this.encryptScheme;
	}
	
	public BiFunction<String, Integer, String> getDecrypt() {
		return this.decryptScheme;
	}
	
	public String encrypt(String m, int k) {
		return encryptScheme.apply(m,k);
	}
	
	public String decrypt(String m, int k) {
		return decryptScheme.apply(m,k);
	}
}
