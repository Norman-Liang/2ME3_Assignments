import java.util.*;

public class Mystery1 {
	
	HashMap <String, String> S = new HashMap <String, String> ();
	
	public Mystery1() {
		
		}
	
	public void f1(String s1, String s2) {
		if (!s1.equals(s2)) S.put(s1, s2);
	}
	
	public int f2(String s) {
		int count = 0;
		for (String i : S.values()) {
			System.out.println(i);
			if (i.equals(s)) count++;
		}
		return count;
	}
	
	public boolean f3(String s1, String s2) {
		return (S.get(s2) != null && S.get(s2).equals(s1));
	}

	public boolean P(String[] s) {
		for (int i = 0; i < s.length; i++) {
			if (!S.get(s[i]).equals(s[i+1])) return false;
		}
		return true;
	}
}
