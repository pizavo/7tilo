package hw3.sorting

class MergeSort<T: Comparable<T>>: Sort<T>() {
	var result: List<T> = listOf<T>()
	
	override fun sort(list: MutableList<T>): Long {
		result = _sort(list)
		
		return complexity
	}
	
	private fun _sort(list: MutableList<T>, startIndex: Int = 0, endIndex: Int = list.size-1): MutableList<T> {
		if (list.size <= 1) {
			return list
		}
		
		val middleIndex = (startIndex + endIndex) / 2
		val left = list.slice(startIndex..middleIndex).toMutableList()
		val right = list.slice(middleIndex + 1..endIndex).toMutableList()
		
		return merge(_sort(left), _sort(right))
	}
	
	private fun merge(leftArray: MutableList<T>, rightArray: MutableList<T>): MutableList<T> {
		val mergedArray = MutableList<T?>(leftArray.size + rightArray.size) { null as? T }
		var leftIndex = 0
		var rightIndex = 0
		var mergedIndex = 0
		
		while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
			complexity++
			
			if (leftArray[leftIndex] <= rightArray[rightIndex]) {
				mergedArray[mergedIndex] = leftArray[leftIndex]
				leftIndex++
			} else {
				mergedArray[mergedIndex] = rightArray[rightIndex]
				rightIndex++
			}
			
			mergedIndex++
		}
		
		while (leftIndex < leftArray.size) {
			mergedArray[mergedIndex] = leftArray[leftIndex]
			leftIndex++
			mergedIndex++
		}
		
		while (rightIndex < rightArray.size) {
			mergedArray[mergedIndex] = rightArray[rightIndex]
			rightIndex++
			mergedIndex++
		}
		
		return mergedArray.map { it!! }.toMutableList()
	}
}