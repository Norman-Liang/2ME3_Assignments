import java.util.function.BiFunction;
import java.util.*;

public interface FieldBase {
	public void send(String m);
	public void receive(String m);
	public void newSpy(Spy spy);
	public void removeSpy(Spy spy);
	public List<Spy> getSpies();
	public void unregister();
	public void reregister();
	public void update(BiFunction <String, Integer, String> e, BiFunction <String, Integer, String> d, int k);
	public String encrypt(String m, int k);
	public String decrypt(String m, int k);
}
