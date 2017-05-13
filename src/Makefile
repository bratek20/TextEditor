JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        texteditor/TextEditor.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) texteditor/*.class
