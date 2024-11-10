# Tree implementation in Scala for JetBrains Intership

Tree class that represents a tree with nested IDs and trees.

**EBNF schema:**
- TREE = NODE | ID
- NODE = ‘(‘ TREE* ‘)’
- ID = [a-zA-Z0-9]+

Tree is implemented recursively.

**Constructors:**
- Default - creates empty tree:
- String - parses string to tree, incorrect string throws IllegalArgumentException
- List of Trees | Char - creates tree with given argument 

**Methods:**
- getNode - return tree node
- toString - returns the string of the tree the method was called on
- toSting tree - returns the string of given tree
- parseString string - returns tree from given string, incorrect string throws IllegalArgumentException
- equals otherTree - compares the tree it was called on and given tree
- replace tree, searchTree, replacement - swaps every search sub tree in given tree with replacement
