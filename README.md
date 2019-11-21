# Ce projet a été créé pour le projet 3 de la formation Java d'OpenClassrooms

## Comment compiler l'application ?
Pour gérer les dépendances et compiler l'application j'ai utilisé gradle. 
De ce fait, afin de compiler l'application, il vous faudra dans un premier temps télécharger le projet.
Ensuite, vous devrez ouvrir une fenêtre console dans le dossier racine du projet, et écrire `gradlew build` ou `./gradlew build` si vous êtes sur PowerShell.
Je pense qu'il faut au préalabale avoir ouvert le projet avec un IDE, mais je n'en suis pas parfaitement sûr.
Une fois cette commande executée, deux fichiers JAR se seront créés dans le dossier `build/libs/`.
Ouvrez une fenêtre de ligne de commande dans ce dossier, et exécutez la commande `java -jar java -jar escape-game-code-1.0-SNAPSHOT-all.jar <chemin d'accès du dossier gamedir>`.
Un dossier gamedir par défaut, avec la configuration par défaut de l'application et du logger est fourni.
