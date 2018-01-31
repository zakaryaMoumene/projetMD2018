javac -d bin/ -cp src src/angryBirds/*.java
echo "Compilation terminée ... !!"
java -cp bin angryBirds/AngryBirds
echo "Fin du jeu"
