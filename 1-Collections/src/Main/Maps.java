package Main;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Maps {

	public static void main(String[] args) {
		
		// Maps
		System.out.println("--------- Maps ---------");
		// Map<Character, String> map = new HashMap<>();
		Map<Character, String> map = new TreeMap<>();
		
		map.put('a', "Valor 1");
		map.put('b', "Valor 2");
		map.put('t', "Valor 3");
		map.put('w', "Valor 4");
		map.put('f', "Valor 5");
		map.put('z', "Valor 7");
		
		System.out.println(map);
		
		System.out.println("Valor a: " + map.get('a'));
		
		Iterator<Character> keysIterator = map.keySet().iterator();
		while (keysIterator.hasNext()) {
			Character key = keysIterator.next();
			System.out.println("Key: " + key + " V: " + map.get(key));
		}
	}

}
