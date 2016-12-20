package discrete;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Wehar
 *
 */
public class Multiset {
	
	private Map<String,Integer> m;
	
	public Multiset(){
		m = new HashMap<>();
	}
	
	public Multiset(Map<String,Integer> m){
		this.m = m;
	}
	
	public void add(String str){
		if(!m.containsKey(str)){
			m.put(str,1);
		}
		else{
			int i = m.get(str);
			m.put(str,i+1);
		}
	}
	
	public int get(String str){
		if(m.containsKey(str)) return m.get(str);
		else return 0;
	}
	
	public Map<String,Integer> getMap(){ return m; }
	
}
