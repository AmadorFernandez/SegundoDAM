package com.pspro;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Class that encapsulates the counters.
 *  The AtomictInteger type variable has been chosen to avoid the race condition, 
 *  which could be due to several threads working on the same memory area.
 * */
class SafeCount {
	
	public final static AtomicInteger totalVocals = new AtomicInteger(0);
	public static int[] countVocals = new int[5];
	

}
