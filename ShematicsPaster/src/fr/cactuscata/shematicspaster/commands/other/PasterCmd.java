package fr.cactuscata.shematicspaster.commands.other;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import fr.cactuscata.shematicspaster.ShematicsPaster;

@SuppressWarnings("deprecation")
public class PasterCmd implements CommandExecutor {

	private int xMax = 600, y = 30, zMax = 1600, xMin = -3500, zMin = -2500, x = xMin, z = zMin;
	// private final List<Runnable> actions = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		File folder = new File(ShematicsPaster.getWorldEditPlugin().getDataFolder() + File.separator + "schematics");
		World world = Bukkit.getWorlds().get(0);
		EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world),
				WorldEdit.getInstance().getConfiguration().maxChangeLimit);

		new Thread(() -> {

			for (File secondFolder : folder.listFiles()) {
				for (File eventType : secondFolder.listFiles()) {
					for (File event : eventType.listFiles()) {
						System.out.println(event + ": (x:" + x + "y:" + y + " z:" + z + ")");
						try {

							CuboidClipboard clipBoard = SchematicFormat.getFormat(event).load(event);
							clipBoard.paste(es, new Vector(x, y, z), true);
							// Creeper creeper = (Creeper) world.spawnCreature(
							// new Location(world, x + (clipBoard.getWidth() / 2), y - 10, z + 3),
							// EntityType.CREEPER);
							// creeper.setCustomName(
							// secondFolder.getName() + "/" + eventType.getName() + "/" + event.getName());
							// Entity entity = ((CraftEntity) creeper).getHandle();
							// NBTTagCompound nbt = entity.getNBTTag() == null ? new NBTTagCompound() :
							// entity.getNBTTag();
							// entity.c(nbt);
							// nbt.setInt("NoAI", 1);
							// entity.f(nbt);
							x += clipBoard.getWidth() + 10;
							if (zMax < clipBoard.getLength())
								zMax = clipBoard.getLength();

						} catch (MaxChangedBlocksException | DataException | IOException e) {
							e.printStackTrace();
						}

						if (xMax < x) {
							x = xMin;
							z += zMax + 10;
						}

					}
				}
			}
		}).start();

		return true;
	}

}
