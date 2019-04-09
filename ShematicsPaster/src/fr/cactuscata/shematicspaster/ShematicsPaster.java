package fr.cactuscata.shematicspaster;

import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import fr.cactuscata.shematicspaster.commands.other.PasterCmd;

public final class ShematicsPaster extends JavaPlugin {
	
	private static WorldEditPlugin wePLugin;


	@Override
	public void onEnable() {
		
		wePLugin = (WorldEditPlugin) super.getServer().getPluginManager().getPlugin("WorldEdit");

		//super.getCommand("schemall").setExecutor(new Schemall(this));
		super.getCommand("paster").setExecutor(new PasterCmd());

	}
	
	public static WorldEditPlugin getWorldEditPlugin() {
		return wePLugin;
	}

	
}
