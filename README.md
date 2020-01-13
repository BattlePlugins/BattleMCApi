# BattleMcAPI
An API abstraction layer for Minecraft plugins. The idea of this project is to offer an abstraction layer and easy to understand API for those wanting to support more than just one server software for their plugins. 

Instead of maintaining two or three separate codebases for cross-compatibility or working with Gradle/Maven modules, BattleMcAPI should be able to eliminate those modules or separate projects at the minimum. 

While (as of now) it doesn't completely eliminate the need for using the actual API's, BattleMcAPI should allow you to consildate those modules/extra projects into one and make it a whole lot easier to maintain. With this being said, the only caveat is that you have to completely update the whole project to use BattleMcAPI. We tried to make this as easy as possible by writing BattleMcAPI in a way that is familiar with most all developers.

# NOTE: This API as-is is still being developed and not ready for production use yet. It's quite volatile as of now.

## Server Softwares Currently Supported
- [Bukkit/Spigot](http://spigotmc.org)
- [Sponge](https://www.spongepowered.org/)
- [Nukkit](https://nukkitx.com)

## Plugins Using This Project
- [BattleTracker](https://github.com/BattlePlugins/BattleTracker)
- [BattleArena](https://github.com/BattlePlugins/BattleArena-4.0)

## API Documentation
All of the API classes within this project contain documentation explaining what they do. If you're familiar with Bukkit, Sponge or Nukkit already, converting over or using this API should not be far too difficult. More documentation on the [wiki](https://github.com/BattlePlugins/BattleMcAPI/wiki) will be coming soon.

Links
------------
Live Chat on Discord:
* [BattlePlugins Discord](https://discord.gg/tMVPVJf): Join our Discord server to get support, talk about dev stuff, or just say hi!

