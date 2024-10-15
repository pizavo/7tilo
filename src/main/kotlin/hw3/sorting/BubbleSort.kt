package hw3.sorting

class BubbleSort<T : Comparable<T>>: Sort<T>() {
	override fun sort(list: MutableList<T>): Long {
		var j: Int = list.size - 2
		var tmp: T
		var swapped: Boolean = true
		
		while (swapped) {
			swapped = false
			
			for (i in 0..j) {
				complexity++
				
				if (list[i] > list[i + 1]) {
					tmp = list[i]
					list[i] = list[i + 1]
					list[i + 1] = tmp
					swapped = true
				}
			}
			
			j--
		}
		
		return complexity
	}
}