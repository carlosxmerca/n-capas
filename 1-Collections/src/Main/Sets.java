package Main;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class ageComparator implements Comparator<Person>
{
    public int compare(Person a, Person b)
    {
        return a.age - b.age;
    }
}

public class Sets {

	public static void main(String[] args) {
		// Hash sets
		System.out.println("--------- HashSets ---------");
		Integer[] numbers01 = new Integer[] {1, 5, 98, 5, 76, 4, 3, 100, 987, 264, 32, 7, 1, 5, 98, 5, 76, 4};
		// Integer[] numbers02 = new Integer[] {987, 264, 32, 7, 1, 5, 98, 5, 76, 4, 1, 5, 98, 5, 76, 4, 3, 100, };
		
		Set<Integer> set01 = new HashSet<>();
		Collections.addAll(set01, numbers01);
		
		System.out.println(set01);
		
		Set<Integer> set02 = new TreeSet<>();
		// Collections.addAll(set02, numbers02);
		set02.addAll(set01);
		
		System.out.println(set02);
		
		// Object sorting -> equals
		System.out.println("--------- Sorting Objects ---------");
		
		Person carlos = new Person("Carlos", 22);
		Person sofia = new Person("Sofia", 21);
		Person valeria = new Person("Valeria", 18);
		
		TreeSet<Person> personSet = new TreeSet<Person>(new ageComparator());
		personSet.add(sofia);
		personSet.add(carlos);
		personSet.add(valeria);
		
		System.out.println(personSet);
		
	}

}
