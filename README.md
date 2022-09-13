# Haskell-like functional utils for Java
* tuples
* lists
* sets
* trees
* and more

## Sample use
Code:
```java
System.out.println (
    StringList.split (' ', "a quick brown fox jumped over the lazy dog")
    .mapToPair (s -> Pair.of (s.length (), s))
    .sortBy (Pair.LexicographicalComparator.natural ())
    .groupByKey (Pair :: get1)
    .mapToString (group -> group.head ().get1 () + ": " + group.mapToString (Pair :: get2).join (", "))
    .unlines ()
);
```
Output:
```
1: a
3: dog, fox, the
4: lazy, over
5: brown, quick
6: jumped
```
