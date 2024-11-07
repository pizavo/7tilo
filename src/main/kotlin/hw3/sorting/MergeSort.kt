package hw3.sorting

class MergeSort<T : Comparable<T>> : Sort<T>() {
    override fun sort(list: List<T>): Sort<T> {
        result = _sort(list.toMutableList())
        
        return this
    }
    
    private fun _sort(list: MutableList<T>, start: Int = 0, end: Int = list.size - 1): MutableList<T> {
        if (start >= end) return list
        
        var mid = (start + end) / 2
        
        _sort(list, start, mid)
        _sort(list, mid + 1, end)
        
        var i = start
        var j = mid + 1
        
        while (i <= mid && j <= end) {
            complexity++
            
            if (list[i] <= list[j]) {
                i++
            } else {
                val temp = list[j]
                for (k in j downTo i + 1) {
                    list[k] = list[k - 1]
                }
                list[i] = temp
                i++
                mid++
                j++
            }
        }
        
        return list
    }
}
