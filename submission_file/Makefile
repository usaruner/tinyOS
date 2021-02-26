build:	src/*.java
	@echo ----- creating a jar file with java -----
	javac --module-path resources/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.base,javafx.graphics src/*.java
	mv src/*.class .
	jar cfmv 141OS.jar src/MANIFEST.MF  *.class resources/javafx-sdk-15.0.1/lib/*jar