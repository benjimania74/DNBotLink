# DNApiTests Bugs
Voici differents bugs du DreamNetwork API

### Possibilité remplacement commande "service"

> En créant une commande "service" il est possible de remplacer la vrai commande "service"
> 
> Code:
> ```java
> DNClientAPI clientAPI = DNClientAPI.getInstance();
> clientAPI.getCommandReader().getCommands().addCommands(new Commande("service"));
> ```
> 
> Résultat:
> ![image](https://user-images.githubusercontent.com/48529276/178573353-fb96357c-4251-451f-895c-0e0a2de1b854.png)

### Boucle infini nous empêchant d'utiliser le DN
> Si l'on met un nom de SubCommand deux fois pour la même commande, une boucle se créer dans le DreamNetwork nous empêchant de l'utiliser
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
> ![image](https://user-images.githubusercontent.com/48529276/178577723-d2d608a3-2501-4cad-932f-122bb6a90bca.png)
