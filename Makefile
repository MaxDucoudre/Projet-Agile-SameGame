### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

Main.class : Main.java Menu.class Modele.class 
	${JC} ${JCFLAGS} Main.java

Menu.class : Menu.java Jeu.class Parametres.class JPanelImage.class AutoRepaint.class MenuListener.class Modele.class 
	${JC} ${JCFLAGS} Menu.java

MenuListener.class : MenuListener.java 
	${JC} ${JCFLAGS} MenuListener.java

Jeu.class : Jeu.java Grille.class GridFile.class Fin.class GrilleListener.class JPanelImage.class AutoRepaint.class Modele.class 
	${JC} ${JCFLAGS} Jeu.java

Fin.class : Fin.java Grille.class Menu.class FinListener.class JPanelImage.class AutoRepaint.class Modele.class 
	${JC} ${JCFLAGS} Fin.java

FinListener.class : FinListener.java 
	${JC} ${JCFLAGS} FinListener.java

Grille.class : Grille.java GrilleListener.class 
	${JC} ${JCFLAGS} Grille.java

GrilleListener.class : GrilleListener.java
	${JC} ${JCFLAGS} GrilleListener.java

Parametres.class : Parametres.java GridFile.class ParamListener.class
	${JC} ${JCFLAGS} Parametres.java

ParamListener.class : ParamListener.java
	${JC} ${JCFLAGS} ParamListener.java	

GridFile.class : GridFile.java
	${JC} ${JCFLAGS} GridFile.java	

AutoRepaint.class : AutoRepaint.java
	${JC} ${JCFLAGS} AutoRepaint.java	

JPanelImage.class : JPanelImage.java
	${JC} ${JCFLAGS} JPanelImage.java	

Modele.class : Modele.java
	${JC} ${JCFLAGS} Modele.java	




### REGLES OPTIONNELLES ###

run : Main.class
	${JVM} ${JVMFLAGS} Main

clean :
	rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###