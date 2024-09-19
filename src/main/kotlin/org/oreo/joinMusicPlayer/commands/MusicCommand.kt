package org.oreo.joinMusicPlayer.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class MusicCommand : CommandExecutor , TabCompleter{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (!sender.isOp) {
            sender.sendMessage("You don't have permission to use this command!" + ChatColor.RED)
            return true
        }

        val sound = Sound.MUSIC_DISC_STAL

        if (args.isNullOrEmpty()) {
            // No player specified, play sound to all online players
            Bukkit.getOnlinePlayers().forEach { player ->
                player.playSound(player.location, sound, 1.0f, 1.0f)
            }
            sender.sendMessage("Played sound to all players." + ChatColor.DARK_BLUE)
        } else {
            // Player specified, play sound to that player
            val targetPlayer = Bukkit.getPlayer(args[0])
            if (targetPlayer != null) {
                targetPlayer.playSound(targetPlayer.location, sound, 1.0f, 1.0f)
                sender.sendMessage("Played sound to ${targetPlayer.name}." + ChatColor.DARK_BLUE)
            } else {
                sender.sendMessage("Player not found!" + ChatColor.RED)
            }
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>?
    ): MutableList<String>? {
        return if (args?.size == 1) {
            val playerNames = mutableListOf<String>()
            Bukkit.getOnlinePlayers().forEach { player -> playerNames.add(player.name) }
            playerNames
        } else {
            null
        }
    }
}