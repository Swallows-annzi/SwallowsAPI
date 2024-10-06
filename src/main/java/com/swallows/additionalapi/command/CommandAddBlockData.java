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

public class CommandAddBlockData extends CommandBase {
    public static String Key = "AdditionalAPIBlockData";

    @Override
    public String getName()
    {
        return "saa-blockdata-add";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-blockdata-add <X> <Y> <Z> <key> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 3 && args.length != 4 && args.length != 5)
        {
            throw new WrongUsageException("/saa-blockdata-add <X> <Y> <Z> <key> <value>", new Object[0]);
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
            if(Integer.parseInt(args[1]) < 0)
            {
                player.sendMessage(new TextComponentString("Y needs to be less than 0"));
                FMLLog.log.info("Y needs to be less than 0");
                return;
            }
            World world = player.getEntityWorld();
            Data data = (Data) world.getPerWorldStorage().getOrLoadData(Data.class, Key);
            if(data == null)
            {
                data = new Data(Key);
                world.getPerWorldStorage().setData(Key, data);
            }
            NBTTagCompound BlockNBT = data.getData(args[0] + "," + args[1] + "," + args[2]);
            if (args.length == 5) {
                BlockNBT.setString(args[3], args[4]);
                data.setData(args[0] + "," + args[1] + "," + args[2], BlockNBT);
                player.sendMessage(new TextComponentString("Add BlockData {" + args[0] + "," + args[1] + "," + args[2] + "} = " + args[3] + ":" + args[4]));
            }

            if (args.length == 4) {
                player.sendMessage(new TextComponentString("BlockData: " + BlockNBT.getTag(args[3])));
                FMLLog.log.info("BlockData: " + BlockNBT.getTag(args[3]));
            }

            if (args.length == 3) {
                player.sendMessage(new TextComponentString("BlockData: " + BlockNBT.toString()));
                FMLLog.log.info("BlockData: " + BlockNBT.toString());
            }

        }
        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
