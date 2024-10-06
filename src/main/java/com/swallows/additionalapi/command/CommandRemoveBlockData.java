package com.swallows.additionalapi.command;

import com.swallows.additionalapi.data.Data;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class CommandRemoveBlockData extends CommandBase {

    @Override
    public String getName()
    {
        return "saa-blockdata-remove";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-blockdata-remove <X> <Y> <Z> <key>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 3 && args.length != 4)
        {
            throw new WrongUsageException("/saa-blockdata-remove <X> <Y> <Z> <key>", new Object[0]);
        }

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            if(!args[0].matches("-?\\d+") || !args[1].matches("-?\\d+") || !args[2].matches("-?\\d+"))
            {
                player.sendMessage(new TextComponentString("Illegal import !"));
                FMLLog.log.info("Illegal import !");
                return;
            }
            World world = player.getEntityWorld();
            Data data = (Data) world.getPerWorldStorage().getOrLoadData(Data.class, CommandAddBlockData.Key);
            if(data == null)
            {
                data = new Data(CommandAddBlockData.Key);
                world.getPerWorldStorage().setData(CommandAddBlockData.Key, data);
            }

            if (args.length == 4) {
                data.removeData(args[0] + "," + args[1] + "," + args[2], args[3]);
                player.sendMessage(new TextComponentString("Remove BlockData: " + "{" + args[0] + "," + args[1] + "," + args[2] + "}: " + args[3]));
                FMLLog.log.info("Remove BlockData: " + "{" + args[0] + "," + args[1] + "," + args[2] + "}: " + args[3]);
            }

            if (args.length == 3) {
                data.removeData(args[0] + "," + args[1] + "," + args[2]);
                player.sendMessage(new TextComponentString("Remove {" + args[0] + "," + args[1] + "," + args[2] + "} All BlockData."));
                FMLLog.log.info("Remove {" + args[0] + "," + args[1] + "," + args[2] + "} All BlockData.");
            }
        }

        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
