
val tree1: Tree = Tree(List(Tree(1), Tree(List(Tree(List()), Tree(2))), Tree(3)))

tree1.toString

tree1.toString == "(1 (2) 3)"

List() == Nil

"(1 (2 3 4) 5)".matches("^([a-zA-Z0-9]|\\(.*\\))( ([a-zA-Z0-9]|\\(.*\\)))*$")

Tree("(1 (2 3 4) 5)")
Tree("(1 (23 4) 5)").toString

//tree1.parseString("(1 (2 3 4) 5)").toString