package codoadvento2022;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Monkey {
	private final int id;
	private Queue<Long> items = new LinkedList<>();

	private int moduloNumber;
	private int monkeyTrue;
	private int monkeyFalse;
	private String operator = "";
	private int operatorValue;

	private int numberOfChecks = 0;

	public Monkey(String id) {
		this.id = Integer.parseInt(id);
	}

	public void addModuloValue(String modulo) {
		this.moduloNumber = Integer.parseInt(modulo);
	}

	public void addTrueValue(String monkeyTrue) {
		this.monkeyTrue = Integer.parseInt(monkeyTrue);
	}

	public void addFalseValue(String monkeyFalse) {
		this.monkeyFalse = Integer.parseInt(monkeyFalse);
	}

	public void addOperatorChar(String operatorChar) {
		this.operator = operatorChar;
	}

	public void addOperatorValue(String operatorValue) {
		this.operatorValue = Integer.parseInt(operatorValue);
	}

	public void doRotation(Map<Integer, Monkey> apes, String part) {

		while (!items.isEmpty()) {
			long item = this.items.remove();
			if (operator.equals("*")) {
				item *= operatorValue;
			} else if (operator.equals("+")) {
				item += operatorValue;
			} else if (operator.equals("^")) {
				item *= item;
			}

			numberOfChecks++;
			if (part.equals("one")) {
				item = Math.floorDiv(item, 3);
			}
			if (part.equals("two")) {
				item %= 9699690;
			}

			if (item % moduloNumber == 0) {
				apes.get(monkeyTrue).addItem(item);
			} else {
				apes.get(monkeyFalse).addItem(item);
			}
		}
	}

	public void addItem(long item) {
		this.items.add(item);
	}

	public void addItem(String item) {
		this.items.add(Long.parseLong(item));
	}

	public Integer getChecks() {
		return this.numberOfChecks;
	}

	public Integer getId() {
		return this.id;
	}
}
