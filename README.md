# Minecraft Server Plugin
An experiment in Minecraft server plugin creation.

This plugin uses the [Bukkit API](https://bukkit.fandom.com/wiki/Plugin_Tutorial_(Eclipse)) and must be deployed on a server running Bukkit (or Bukkit-compatible server such as [Spigot](https://www.spigotmc.org/) or [Paper](https://papermc.io/)).

Tested on Spigot 1.17.

# Commands

|Command|Functionality|
|---|---|
|/lava&#8209;feet&nbsp;[player]|Toggles "lava feet" on and off. Optionally provide a player name to apply the effect to another player (of omitted, it will apply to the issuing player)<br/><br/>_The "lava feet" effect causes blocks under the player to turn to lava after the player steps off of them. The blocks will return to their original material after a period of time_|
