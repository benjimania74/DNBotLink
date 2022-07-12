# DNApiTests Bugs
Voici differents bugs du DreamNetwork API

### Possibilité remplacement commande `service`

> En créant une commande `service` il est possible de remplacer la vrai commande `service`
> 
> Code:
> ```java
> DNClientAPI clientAPI = DNClientAPI.getInstance();
> clientAPI.getCommandReader().getCommands().addCommands(new ICommand("service"));
> ```
> 
> Résultat:
> 
> ![image](https://user-images.githubusercontent.com/48529276/178573353-fb96357c-4251-451f-895c-0e0a2de1b854.png)

### Boucle infini nous empêchant d'utiliser le DN
> Si l'on met un nom de SubCommand (`addSubCommand(String subCommand, SubCommandExecutor sce`) deux fois pour la même commande, une boucle se créer dans le DreamNetwork nous empêchant de l'utiliser
>
> Code:
> ```java
> addSubCommand("start", new SubCommandExecutor() {
>     @Override
>     public boolean onSubCommand(@NonNull String[] strings) {
>           // DO STUFF
>     }
> });
>
> addSubCommand("start", new SubCommandExecutor() {
>     @Override
>     public boolean onSubCommand(@NonNull String[] strings) {
>           // DO STUFF
>     }
> });
> ```
>
> Résulat:
> 
> ![image](https://user-images.githubusercontent.com/48529276/178577723-d2d608a3-2501-4cad-932f-122bb6a90bca.png)

### `getDnClientAPI()` ne fonctionne pas
> Si l'on utilise la fonction `getDnClientAPI()` dans la class principal de notre Addon (qui extends de DreamExtension), on obtient un NullPointerExeption lors du lancement du DN
>
> Code (dans la fonction start):
> ```java
> DNClientAPI clientAPI = getDnClientAPI()
> ```
>
> Résultat:
> 
> ![image](https://user-images.githubusercontent.com/48529276/178592620-3a078066-9e65-4dae-9f6c-a95be40cc275.png)
