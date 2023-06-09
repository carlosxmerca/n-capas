package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {
		
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
	}

}
