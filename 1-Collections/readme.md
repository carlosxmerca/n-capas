# Collections

## Lists

```java
System.out.println("--------- Lists ---------");

List<Integer> numbers = new ArrayList<>();

numbers.add(1);
numbers.add(2);

System.out.println(numbers);

for (int i = 0; i < numbers.size(); i++) {
    int value = numbers.get(i);
    System.out.println("Valor " + i + " : " + value);
}

for (int value : numbers)
    System.out.println("Valor: " + value);

Iterator<Integer> listIterator = numbers.iterator();
while (listIterator.hasNext()) {
    int value = listIterator.next();
    System.out.println("Valor: " + value);
}

// Stacks
System.out.println("--------- Stacks ---------");
Stack<Integer> numberStack = new Stack<>();

numberStack.push(1);
numberStack.push(6);

while (!numberStack.empty())
    System.out.println(numberStack.pop());
```

## Sets

```java
public class Person {
	public String name;
	public int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " - Age: " + age;
	}
}

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
```

## Maps

```java
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
```

## Streams

```java
// Streams
System.out.println("--------- Streams ---------");
List<Integer> numbers = new ArrayList<>();

Collections.addAll(numbers, 1, 2, 3, 4, 5, 6, 7, 8, 9);

System.out.println(numbers);

System.out.println("--------- For each ---------");

numbers.stream()
    .forEach((number) -> {
        System.out.println(number);
    });

System.out.println("--------- Map ---------");

List<Integer> doubles = numbers.stream()
    .map((number) -> number * 2)
    .collect(Collectors.toList());

System.out.println(doubles);
```