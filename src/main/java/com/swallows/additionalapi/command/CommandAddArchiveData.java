package com.swallows.additionalapi.command;

import com.swallows.additionalapi.data.Data;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class CommandAddArchiveData extends CommandBase {
    public static String Key = "AdditionalAPIArchiveData";

    @Override
    public String getName()
    {
        return "saa-archivedata-add";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-archivedata-add <key> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 2)
        {
            throw new WrongUsageException("/saa-archivedata-add <key> <value>", new Object[0]);
        }

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            World world = player.getEntityWorld();
            Data data = (Data) world.getMapStorage().getOrLoadData(Data.class, Key);
            if(data == null)
            {
                data = new Data(Key);
                world.getMapStorage().setData(Key, data);
            }
            NBTTagCompound ArchiveNBT = data.getData("ArchiveData");
            if (args.length == 2) {
                ArchiveNBT.setString(args[0] , args[1]);
                data.setData("ArchiveData", ArchiveNBT);
                player.sendMessage(new TextComponentString("Add ArchiveData " + args[0] + " = " + args[1]));
            }

            if (args.length == 1) {
                player.sendMessage(new TextComponentString("ArchiveData: " + ArchiveNBT.getTag(args[0])));
                FMLLog.log.info("ArchiveData: " + ArchiveNBT.getTag(args[0]));
            }

            if (args.length == 0) {
                player.sendMessage(new TextComponentString("ArchiveData: " + ArchiveNBT.toString()));
                FMLLog.log.info("ArchiveData: " + ArchiveNBT.toString());
            }

        }
        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
