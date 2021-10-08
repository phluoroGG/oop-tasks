javac -sourcepath ./src -d bin src/edu/csf/oop/java/supermarket/Main.java
echo main-class: edu.csf.oop.java.supermarket.Main>manifest.mf
jar -cmf manifest.mf supermarket.jar -C bin .