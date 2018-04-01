JFLAGS = -g
JC = javac
J = java -cp "/export/documents/mariadb-client.jar:."
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

build:
	javac *.java

test:
	$(J) Main

clean:
	$(RM) *.class