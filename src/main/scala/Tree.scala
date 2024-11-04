import scala.util.matching.Regex
// Tree class that represents a tree with nested IDs and trees.
// EBNF schema:
// TREE = NODE | ID
// NODE = ‘(‘ TREE* ‘)’
// ID = [a-zA-Z0-9]+

// TASK #1:
class Tree(node : List[Tree] | Char) {
	// starting node of a tree is either a list of trees or an integer

	// Default constructor
	def this() =
		this(List())

	// Constructor that takes a string and parses it to a tree
	// if the string is invalid, it will create an tree with an empty list
	def this(string: String) =
		this(
			try Tree().parseString(string).getNode
			catch case e => {
				print(e)
				List()
			}
		)


	// getter for starting node
	def getNode: List[Tree] | Char = node

	// Printing the tree
	override def toString: String =
		node match
			case id: Char => id.toString
			case list: List[Tree] => list.mkString("(", " ", ")")

    // TASK #3: Tree to string
	def toString(tree: Tree): String = tree.toString

	// TASK #4: Parse a string to a tree, throws an exception if the string is invalid
	def parseString(string : String): Tree = {
		string.charAt(0) match
			case '(' =>
				if (string.charAt(string.length - 1) != ')')
					throw new IllegalArgumentException("Invalid parenthesis")

				val listString = string.substring(1, string.length - 1)

//				if (listString.matches("^([a-zA-Z0-9]|\\(.*\\))( ([a-zA-Z0-9]|\\(.*\\)))*$"))
//					throw new IllegalArgumentException("Invalid list inside")
				def getListFromString(string: String): List[String] = {
					if(string.isEmpty) return List()
					var list = List[String]()
					var count = 0
					var start = 0
					for (i <- 0 until string.length) {
						string.charAt(i) match {
							case '(' => count += 1
							case ')' => count -= 1
							case ' ' if count == 0 =>
								list = list :+ string.substring(start, i)
								start = i + 1
							case _ =>
						}
					}
					list :+ string.substring(start)
				}

				val list = getListFromString(listString)
				if (list.isEmpty)
					Tree(List())
				else
					val treeList = list.map(parseString)
					Tree(treeList)

			case id: Char if id.isLetter | id.isDigit => Tree(id)
			case _ =>
				throw new IllegalArgumentException("Invalid ID")
	}

	// TASK #2: Compare two trees
	def equals(other: Tree): Boolean = {
		def listEquals(list1: List[Tree], list2: List[Tree]): Boolean = {
			list1 match
				case Nil => list2.isEmpty
				case head1 :: tail1 => list2 match
					case head2 :: tail2 => head1.equals(head2) && listEquals(tail1, tail2)
					case Nil => false
		}
		node match
			case id: Char => other.getNode match
				case i: Char => id == i
				case _ => false
			case list: List[Tree] =>
				other.getNode match
					case l: List[Tree] =>
						if (list.length != l.length)
							false
						else
							listEquals(list, l)
					case _ => false
	}

	// TASK #5: returns a copy of tree with all occurrences of searchTree replaced with replacement
	// example:
	// tree = (a () (c ()) b)
	// search = ()
	// replacement = (d b)
	// replace(tree, search, replacement) = (a (d b) (c ()) b)
	def replace(tree: Tree, searchTree: Tree, replacement: Tree): Tree = {
		def replaceTree(tree: Tree): Tree = {           // arguments complement function
			if (tree.equals(searchTree)) replacement    // replace the tree if found
			else                                        // else find the tree in the node
				tree.getNode match
					case _: Char => tree          // if the node is an ID, finish the search in this branch
					case list: List[Tree] =>        // if the node is a list of trees, search in each tree
						val newList = list.map(replaceTree)
						Tree(newList)
		}
		replaceTree(tree)
	}
}
