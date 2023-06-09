package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MainClass {
	
	public static void main(String[] args)   
	{   
		// Lists
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
		
	}

}
