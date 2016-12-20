package discrete;
import java.util.HashMap;

/**
 * @author Michael Wehar
 *
 */
public class DataMap extends HashMap<String,Multiset>{
	
	/**
	 * @param str
	 * @param offset
	 */
	public DataMap(String str, int offset) {
		super();
		for(int i=0; i<str.length()-offset; i++){
			String temp = str.substring(i, i+1);
			String tempOffset = str.substring(i+offset, i+offset+1);
			if(!this.containsKey(temp)) this.put(temp,new Multiset());
			this.get(temp).add(tempOffset);
		}
	}

}
