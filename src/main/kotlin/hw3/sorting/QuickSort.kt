package hw3.sorting

class QuickSort<T : Comparable<T>>: Sort<T>() {
	override fun sort(list: MutableList<T>): Long {
		_sort(list)
		
		return complexity
	}
	
	private fun _sort(list: MutableList<T>, low: Int = 0, high: Int = list.size - 1) {
		if (low < high) {
			val pivot = partition(list, low, high)
			
			_sort(list, low, pivot)
			_sort(list, pivot + 1, high)
		}
	}
	
	private fun partition(list: MutableList<T>, low: Int, high: Int): Int {
		val pivot = list[low]
		var start = low - 1
		var end = high + 1
		
		while (true) {
			do {
				start++
				complexity++
			} while (list[start] < pivot)
			
			do {
				end--
				complexity++
			} while (list[end] > pivot)
			
			if (start >= end) {
				complexity++
				return end
			}
			
			val temp = list[start]
			
			list[start] = list[end]
			list[end] = temp
		}
	}
}