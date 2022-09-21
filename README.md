# MeneliaAPI (Minecraft Plugin)

Date of project: 29-09-2019


# MeneliaAPI doc

Ici vous avez un aperçu des fonctions disponibles sur l'API

### getGroup(Player player)
Cette fonction permet de recevoir le group qui a la plus grande priorité.

_**return**_ `String`

> _**Emplacement**_: `MeneliaAPI.getInstance()`


### sendToServer(Player player, String targetServer)
Cette fonction permet de déplacer un joueur vers un autre serveur.

Serveur disponibles: `hub`, `games`, `tots`, `beta`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### SendPluginMessage(Player player, String server, String channel, String subChannel, boolean useArgs, String[] args)
Cette fonction permet d'envoyer un message spécifique  au BungeeCord

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getOtherServerOnlinePlayers(String ip, int port)
Cette fonction permet de récuperer un nombre de joueurs d'un autre serveur.

_**return**_ `int`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getOtherServerMaxPlayers(String ip, int port)
Cette fonction permet de récuperer un nombre maximum de joueurs d'un autre serveur.

_**return**_ `int`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getOtherServerMotd(String ip, int port)
Cette fonction permet de récuperer le motd d'un autre serveur.

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getNoPermissionMessage(Player player)
Cette fonction permet de récuperer le message global quand les joueurs n'ont pas une permission (dans la bonne langue).

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getLang(Player player, String fr, String en)
Cette fonction permet de récupérer le message entrée dans le bonne lange

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### colorize(String string)
Cette fonction permet de traduire les message en couleur

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getDateFromDate(Date date)
Cette fonction permet de retourner la date en texte

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getDateFromDate(Date date, String betweenDate)
Cette fonction permet de retourner la date en texte (avec option de pouvoir changer les symboles entre les dates ex. `-`)

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getHourFromDate(Date date, boolean includeSeconds)
Cette fonction permet de retourner l'heure en texte

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getHourFromDate(Date date, String betweenHour, boolean includeSeconds)
Cette fonction permet de retourner l'heure en texte (avec option de pouvoir changer les symboles entre les heures ex. `|`)

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getAccounts()
Cette fonction permet de retourner la liste des comptes 

_**return**_ `ArrayList<Accounts>`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


### getServerList()
Cette fonction permet de

_**return**_ `ArrayList<String>`

> _**Emplacement**_:`MeneliaAPI.getInstance()`


# Introduction sur les variables MySQL

Il y a des variables prédéfinis qui permetent de pouvoir connetre les noms des champs dans la database MySQL

Les variables pour les tables commençent par `Table_<nom de la table>` et le reste est en rapport a ce que vous rechercher (ex. `Table_Accounts`).

Pour les variables il y a `Field_<Nom de la table>_<nom du champ>` (ex. `Field_Accounts_ID`)

Toutes ces variables sont accessibles depuis cet emplacement:
> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`

ex. `MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_ID`

### ADD_INT(String table, String fieldWhere, String valueWhere, String field, int value)
Cette fonction permet d'ajouter a une valeur a un champ dans MySQL

Exemple:

```java
// ajouter 10 d'argent au joueur avec l'uuid 'uuid'

ADD_INT(MeneliaAPI.getInstance().MySQLLibrary.Table_Accounts, MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_UUID, uuid, MeneliaAPI.getInstance().MySQLLibrary.Field_Accounts_MONEY_BASE, 10);
```

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`


### REMOVE_INT(String table, String fieldWhere, String valueWhere, String field, int value)
Cette fonction permet de retirer a une valeur a un champ dans MySQL

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`


### UPDATE_DATA(String uuid, String table, String field, String value)
Cette fonction permet de mettre a jour une valeur dans MySQL

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`


### UPDATE_DATA(String table, String fieldWhere, String valueWhere, String field, String value)
Cette fonction permet de mettre a jour une valeur dans MySQL

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`


### EXIST_DATA(String table, String field, String value)
Cette fonction permet de vérifier si une valeur existe dans MySQL

_**return**_ `boolean`

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`

### DELETE_DATA(String table, String fieldWhere, String valueWhere)
Cette fonction permet de supprimer une donnée dans le MySQL

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`

### GET_DATA_String(String table, String fieldWhere, String valueWhere, String field)
Cette fonction permet de récupérer un valeur en texte depuis MySQL

_**return**_ `String`

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`

### GET_DATA_Int(String table, String fieldWhere, String valueWhere, String field)
Cette fonction permet de récupérer un valeur en chiffre depuis MySQL

_**return**_ `int`

> _**Emplacement**_:`MeneliaAPI.getInstance().MySQLLibrary`