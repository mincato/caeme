package com.caeme.activityreport.core.comparators;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator<K,V extends Comparable<V>> implements Comparator<K> {

	Map<K,V> map;

	public ValueComparator(Map<K,V> map) {
		this.map = map;
	}

	@Override
	public int compare(K o1, K o2) { 
		return map.get(o1).compareTo(map.get(o2));
	}

}
