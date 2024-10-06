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

public class CommandAddWorldData extends CommandBase {

    public static String Key = "AdditionalAPIWorldData";

    @Override
    public String getName()
    {
        return "saa-worlddata-add";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-worlddata-add <key> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 2)
        {
            throw new WrongUsageException("/saa-worlddata-add <key> <value>", new Object[0]);
        }

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            World world = player.getEntityWorld();
            Data data = (Data) world.getPerWorldStorage().getOrLoadData(Data.class, Key);
            if(data == null)
            {
                data = new Data(Key);
                world.getPerWorldStorage().setData(Key, data);
            }
            NBTTagCompound WorldNBT = data.getData("WorldData");
            if (args.length == 2) {
                WorldNBT.setString(args[0] , args[1]);
                data.setData("WorldData", WorldNBT);
                player.sendMessage(new TextComponentString("Add WorldData " + args[0] + " = " + args[1]));
            }

            if (args.length == 1) {
                player.sendMessage(new TextComponentString("WorldData: " + WorldNBT.getTag(args[0])));
                FMLLog.log.info("WorldData: " + WorldNBT.getTag(args[0]));
            }

            if (args.length == 0) {
                player.sendMessage(new TextComponentString("WorldData: " + WorldNBT.toString()));
                FMLLog.log.info("WorldData: " + WorldNBT.toString());
            }

        }
        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
