import java.util.Collections

import java.util.*

fun main(){
    solution(Arrays("i", "drink", "water"), )
}
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var answer: String = "Yes"
        val q1 = LinkedList<String>()
        val q2 = LinkedList<String>()

        cards1.forEach{ q1.offer(it) }
        cards2.forEach{ q2.offer(it) }

        for (s in goal){
            if (q1.isNotEmpty() && q1.peek().equals(s)) q1.poll()
            else if (q2.isNotEmpty() && q2.peek().equals(s)) q2.poll()
            else{
                answer = "No"
                break
            }
        }

        return answer
    }
class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var idx1 = 0
        var idx2 = 0
        goal.forEach {
            if (idx1 < cards1.size && it == cards1[idx1]) idx1++
            else if (idx2 < cards2.size && it == cards2[idx2]) idx2++
            else return "No"
        }
        return "Yes"
    }
}