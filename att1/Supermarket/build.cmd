javac -encoding UTF-8 --module-path lib --add-modules=ALL-MODULE-PATH -sourcepath ./src -d bin src/edu/csf/oop/java/supermarket/Game.java
echo main-class: edu.csf.oop.java.supermarket.Game>manifest.mf
jar -cmf manifest.mf src/edu/csf/oop/java/supermarket/supermarket.jar -C bin .