javac -d build/classes/java/main/org/example src/main/java/org/example/*.java
jar --create --file build/libs/HeapSort.jar --main-class=org.example.HeapSort -C build/classes/java/main/org/example .
javadoc -d build/docs/javadoc src/main/java/org/example/*.java
java -jar build/libs/HeapSort.jar