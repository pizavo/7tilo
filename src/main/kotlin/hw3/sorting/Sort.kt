package hw3.sorting

abstract class Sort<T : Comparable<T>> {
	var complexity: Long = 0
		protected set
	
	abstract fun sort(list: MutableList<T>): Long
}