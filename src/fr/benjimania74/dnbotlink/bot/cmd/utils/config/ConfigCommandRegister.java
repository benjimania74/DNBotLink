package fr.benjimania74.dnbotlink.bot.cmd.utils.config;

import fr.benjimania74.dnbotlink.bot.cmd.utils.ConfigCmd;

public class ConfigCommandRegister {
    public ConfigCommandRegister(){
        ConfigCmd.cmd.put("prefix", new PrefixConfigCmd("prefix", "Change the bot Prefix"));
        ConfigCmd.cmd.put("activity", new ActivityConfigCmd("activity", "Change the bot Activity"));
        ConfigCmd.cmd.put("status", new StatusConfigCmd("status", "Change the bot Status"));
        ConfigCmd.cmd.put("permrole", new PermRoleConfigCmd("permrole", "Change the permission role to use the bot"));
    }
}
