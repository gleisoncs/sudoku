package de.affinitas.sudoku.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private static AtomicLong counter = new AtomicLong(12345678);
	
	public static Long getUniqueLongId() {
		return counter.incrementAndGet();
	}
}
