package fr.cactuscata.shematicspaster.commands.other;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Schemall implements CommandExecutor {

	private final Plugin plugin;
	private int amount;

	public Schemall(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Ne peut d'être executé par un joueur !");
			return true;
		}

		Player player = (Player) sender;

		File folder = new File("D:/Adam/Minecraft/Servers/Serveur 1.8.8/plugins/WorldEdit/schematics/");
		File[] listOfFiles = folder.listFiles();

		Location location = new Location(Bukkit.getWorlds().get(0), -2890, 10, -2270);
		int i = Integer.parseInt(args[0]);
		int j = 0;
		boolean c = false;

		for (File file : listOfFiles) {

			boolean b = false;

			if (j < i) {
				j++;
				b = true;
			}

			System.out.println(b);

			if (b) {
				continue;
			} else if (!c) {
				c = true;
				for (int l = 0; l > j; l++) {
					if (location.getX() > 950) {
						location.setX(-2890);
						location.setZ(location.getZ() + 80);
					}

					location.setX(location.getX() + 80);
				}
			}

			this.amount++;
			Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {

				@Override
				public void run() {

					if (location.getX() > 950) {
						location.setX(-2890);
						location.setZ(location.getZ() + 80);
					}

					location.setX(location.getX() + 80);

					player.teleport(location);

					Bukkit.getServer().dispatchCommand(player,
							"/schem load " + file.getName().replace(".shematics", ""));
					Bukkit.getServer().dispatchCommand(player, "/paste");

				}
			}, this.amount * 200L);

		}
		return true;

	}

}
