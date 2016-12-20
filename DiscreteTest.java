package discrete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sound.sampled.LineListener;

/**
 * @author Michael Wehar
 *
 */
public class DiscreteTest {

	public static void main(String[] args) {
		playTest1();
		System.out.println();
		
		playTest2();
		System.out.println();

		playTest3();
		System.out.println();
	}
	
	public static void playTest1(){
		String clip = "112233221122331122441";
		String extended = testCase(3,50,clip);
		String song = clip+extended;
		
		System.out.println("Sound Clip 1: ");
		System.out.println(clip);
		// System.out.println(song);
		
		play(song);
	}
	
	public static void playTest2(){
		String clip = "3141592653589793238";
		String extended = testCase(6,50,clip);
		String song = clip+extended;
		
		System.out.println("Sound Clip 2: ");
		System.out.println(clip);
		// System.out.println(song);
		
		play(song);
	}
	
	public static void playTest3(){
		String clip = "3304" + "3314" + "3304" + "3214" + "3304" + "3314" + "3304" + "3214";
		String extended = testCase(25,50,clip);
		String song = clip+extended;
		
		System.out.println("Sound Clip 3: ");
		System.out.println(clip);
		// System.out.println(song);
		
		play(song);
	}
	
	/* Actually plays the string as if it were a sequence of notes */
	public static void play(String song){
		Sound.play(song.split("(?!^)"));
		System.out.println();
	}
	
	/* Given the string str, it builds an extended string */
	public static String testCase(int num, int limit, String str){
		ArrayList<DataMap> dm = new ArrayList<>();
		for(int i=1; i<=num; i++){
			dm.add(new DataMap(str,i));
		}
		
		String extendedStr = str;
		String extendedPart = "";
		for(int i=0; i<limit; i++){
			String temp = avg(getNext(extendedStr,dm));
			extendedStr = extendedStr + temp;
			extendedPart = extendedPart + temp;
		}
		
		return extendedPart;
	}
	
	public static Multiset getNext(String str, ArrayList<DataMap> dm){
		int length = str.length();
		Multiset ms = new Multiset();
		
		for(int i=1; i<=dm.size(); i++){
			String tempStr = str.substring(length-i,length-i+1);
			Multiset tempMs = dm.get(i-1).get(tempStr);
			if(tempMs == null) tempMs = new Multiset();
			ms = combine(ms,tempMs);
		}
		
		return ms;
	}
	
	public static Multiset combine(Multiset a, Multiset b){
		Map<String,Integer> map1 = a.getMap();
		Map<String,Integer> map2 = b.getMap();
		Map<String,Integer> map3 = new HashMap<String,Integer>();
		
		Iterator<String> it = map1.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Integer value = map1.get(key);
			map3.put(key,value);
		}
		
		it = map2.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Integer value = map2.get(key);
			
			if(!map3.containsKey(key)) map3.put(key,value);
			else{
				Integer temp = map3.get(key) + value;
				map3.put(key,temp);
			}
		}
		
		return new Multiset(map3);
	}
	
	public static String max(Multiset ms){
		String maxKey = "None";
		int maxValue = 0;
		
		Iterator<String> it = ms.getMap().keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(ms.get(key) > maxValue){
				maxKey = key;
				maxValue = ms.get(key);
			}
		}
		
		return maxKey;
	}
	
	public static String avg(Multiset ms){
		ArrayList<String> list = new ArrayList<>();
		String max = max(ms);
		int maxValue = ms.get(max);
		int nearMaxValue = maxValue;
		
		// System.out.println(maxValue + ", " + nearMaxValue);
		
		Iterator<String> it = ms.getMap().keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(ms.get(key) >= nearMaxValue){
				list.add(key);
			}
		}
		
		// System.out.println(ms.getMap());
		int value = (int)(Math.random()*list.size());
		
		return list.get(value);
	}

}
