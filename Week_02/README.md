学习笔记

## 树的遍历模板代码

```kotlin

class Node<T>(var `val`: T, var left: Node<T>? = null, var right: Node<T>? = null)
/**
 * root - left - right
 */
fun <T> preOrder(root: Node<T>?) {
    if (root == null) {
        return
    }
    
    traversalPath.append(root.`val`)
    preOrder(root.left)
    preOrder(root.right)
}

/**
 * left - root - right
 */
fun inOrder(root: Node?) {
    if (root == null) {
        return
    }
    
    inOrder(root.left)
    traversalPath.append(root.`val`)
    inOrder(root.right)
}

/**
 * left - right - root
 */
fun postOrder() {
    if (root == null) {
        return
    }
    
    postOrder(root.left)
    postOrder(root.right)
    traversalPath.append(root.`val`)
}
``` 

```kotlin
fun backtrack(result, path, select) {
    if (is end) {
        result.add(copy path)
    }
    
    forEach in select { it ->
        path.add(it)
        backtrack(result, path, select)
        path.remove(it)
    }
}
```

## 不够熟悉

DFS, backtrack

