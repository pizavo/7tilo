package hw3.sorting

class BubbleSort<T : Comparable<T>>: Sort<T>() {
	override fun sort(list: List<T>): Sort<T> {
		val mutableList = list.toMutableList()
		
		var j: Int = mutableList.size - 2
		var tmp: T
		var swapped = true
		
		while (swapped) {
			swapped = false
			
			for (i in 0..j) {
				complexity++
				
				if (mutableList[i] > mutableList[i + 1]) {
					tmp = mutableList[i]
					mutableList[i] = mutableList[i + 1]
					mutableList[i + 1] = tmp
					swapped = true
				}
			}
			
			j--
		}
		
		result = mutableList
		
		return this
	}
}
