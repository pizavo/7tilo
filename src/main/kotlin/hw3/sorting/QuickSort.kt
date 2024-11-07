package hw3.sorting

class QuickSort<T : Comparable<T>> : Sort<T>() {
    override fun sort(list: List<T>): Sort<T> {
        result = _sort(list.toMutableList())
        
        return this
    }
    
    private fun _sort(list: MutableList<T>, low: Int = 0, high: Int = list.size - 1): List<T> {
        var start = low
        var end = high
        val pivot = list[(low + high) / 2]
        
        while (start <= end) {
            while (list[start] < pivot) {
                complexity++
                start++
            }
            complexity++
            
            while (list[end] > pivot) {
                complexity++
                end--
            }
            complexity++
            
            if (start <= end) {
                val temp = list[start]
                list[start] = list[end]
                list[end] = temp
                start++
                end--
            }
        }
        
        if (low < end) {
            _sort(list, low, end)
        }
        
        if (start < high) {
            _sort(list, start, high)
        }
        
        return list
    }
}
