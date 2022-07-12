# DNApiTests Bugs
Voici differents bugs du DreamNetwork API

### Possibilité remplacement commande "service"

> En créant une commande "service" il est possible de remplacer la vrai commande "service"
> 
> ```java
> DNClientAPI clientAPI = DNClientAPI.getInstance();
> clientAPI.getCommandReader().getCommands().addCommands(new Commande("service"));
> ```
> 
> ![image](https://user-images.githubusercontent.com/48529276/178573353-fb96357c-4251-451f-895c-0e0a2de1b854.png)
