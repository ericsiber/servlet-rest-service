# servlet-rest-service

Proposition de correction / illustration du TP au long cours réalisé en session 3.

Chaque étape a sa propre branche, intégrant les étapes précédentes :

- [master](https://github.com/simplon-promo-pe-2/servlet-rest-service) (découverte / prise en main)
- [crud](https://github.com/simplon-promo-pe-2/servlet-rest-service/tree/crud)
- [session](https://github.com/simplon-promo-pe-2/servlet-rest-service/tree/session)
- [json](https://github.com/simplon-promo-pe-2/servlet-rest-service/tree/json)
- [tomcat](https://github.com/simplon-promo-pe-2/servlet-rest-service/tree/tomcat)

La base de données est configurée pour fonctionner en mode serveur grâce à l'exécution de l'outil *h2.sh* / *h2.bat* situé dans le dossier db (démarrage d'une base de données autonome). L'url d'accès à la base de données doit être revue pour coïncider avec l'environnement local, propriété *javax.persistence.jdbc.url* située dans le fichier *persistence.xml*

Possibilité de fonctionner en mode embarqué pour la base de données en configurant la propriété *javax.persistence.jdbc.url* située dans le fichier *persistence.xml* à une valeur de type *jdbc:h2:file:CheminAbsoluVersDossier/servlet-rest-service/db/data/referentiel*
