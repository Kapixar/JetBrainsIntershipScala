
def foo(x: Int): Int =
	if x == 0 then 1
	else foo(x - 1) * 2

foo(1)
foo(2)
foo(3)
foo(4)
foo(5)

// the function will return 2^x
// this solution will take O(n) time complexity
// and O(n) space complexity

// the function can be optimized to take O(1) space complexity
// by using tail recursion
// (compiler will optimize the tail recursion to sort of a loop)

def bar(x: Int, acc: Int = 1): Int =
	if x == 0 then acc
	else bar(x - 1, acc * 2)

"4"

