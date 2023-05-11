import java.util.function.BiFunction;

public interface Spy {
	public void send(String m);
	public void receive(String m);
	public void dies();
	public void update(BiFunction <String, Integer, String> e, BiFunction <String, Integer, String> d, int k);
	public String encrypt(String m, int k);
	public String decrypt(String m, int k);
}

