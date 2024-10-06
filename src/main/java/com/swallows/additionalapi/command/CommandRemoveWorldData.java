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

public class CommandRemoveWorldData extends CommandBase {

    @Override
    public String getName()
    {
        return "saa-worlddata-remove";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-worlddata-remove <key>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1)
        {
            throw new WrongUsageException("/saa-worlddata-remove <key>", new Object[0]);
        }

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            World world = player.getEntityWorld();
            Data data = (Data) world.getPerWorldStorage().getOrLoadData(Data.class, CommandAddWorldData.Key);
            if(data == null)
            {
                data = new Data(CommandAddWorldData.Key);
                world.getPerWorldStorage().setData(CommandAddWorldData.Key, data);
            }

            if (args.length == 1) {
                data.removeData("WorldData", args[0]);
                player.sendMessage(new TextComponentString("Remove WorldData: " + args[0]));
                FMLLog.log.info("Remove WorldData: " + args[0]);
            }

            if (args.length == 0) {
                data.removeData("WorldData");
                player.sendMessage(new TextComponentString("Remove All WorldData."));
                FMLLog.log.info("Remove All WorldData.");
            }
        }

        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
