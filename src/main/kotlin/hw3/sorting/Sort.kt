package hw3.sorting

abstract class Sort<T : Comparable<T>> {
	var result: List<T> = listOf<T>()
		protected set
	var complexity: Long = 0
		protected set
	
	abstract fun sort(list: List<T>): Sort<T>
}
