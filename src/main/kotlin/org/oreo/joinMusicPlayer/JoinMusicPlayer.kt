package org.oreo.joinMusicPlayer

import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.oreo.joinMusicPlayer.commands.MusicCommand

class JoinMusicPlayer : Listener, JavaPlugin() {

    private val volume = config.getDouble("music-volume")

    private val quotes : MutableList<String> = config.getStringList("quote-list")

    override fun onEnable() {
        server.pluginManager.registerEvents(this,this)

        getCommand("music")!!.setExecutor(MusicCommand())

        saveDefaultConfig()
    }

    @EventHandler
    fun onPlayerJoin(e:PlayerJoinEvent){
        val player = e.player

        val randomQuote : String = quotes.random()

        player.sendMessage("$randomQuote${ChatColor.BOLD}${ChatColor.ITALIC}${ChatColor.GOLD}")

        player.playSound(player,Sound.MUSIC_DISC_STAL, volume.toFloat(), 1f)
    }



}
