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

public class CommandAddCoordinateData extends CommandBase {
    public static String Key = "AdditionalAPICoordinateData";

    @Override
    public String getName()
    {
        return "saa-coordinatedata-add";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/saa-coordinatedata-add <X> <Z> <key> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 2 && args.length != 3 && args.length != 4)
        {
            throw new WrongUsageException("/saa-coordinatedata-add <X> <Z> <key> <value>", new Object[0]);
        }

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) sender;
            if(!args[0].matches("-?\\d+") || !args[1].matches("-?\\d+"))
            {
                player.sendMessage(new TextComponentString("Illegal import !"));
                FMLLog.log.info("Illegal import !");
                return;
            }
            World world = player.getEntityWorld();
            Data data = (Data) world.getPerWorldStorage().getOrLoadData(Data.class, Key);
            if(data == null)
            {
                data = new Data(Key);
                world.getPerWorldStorage().setData(Key, data);
            }
            NBTTagCompound CoordinateNBT = data.getData(args[0] + "," + args[1]);
            if (args.length == 4) {
                CoordinateNBT.setString(args[2], args[3]);
                data.setData(args[0] + "," + args[1], CoordinateNBT);
                player.sendMessage(new TextComponentString("Add CoordinateData {" + args[0] + "," + args[1] + "} = " + args[2] + ":" + args[3]));
            }

            if (args.length == 3) {
                player.sendMessage(new TextComponentString("CoordinateData: " + CoordinateNBT.getTag(args[2])));
                FMLLog.log.info("CoordinateData: " + CoordinateNBT.getTag(args[2]));
            }

            if (args.length == 2) {
                player.sendMessage(new TextComponentString("CoordinateData: " + CoordinateNBT.toString()));
                FMLLog.log.info("CoordinateData: " + CoordinateNBT.toString());
            }

        }
        else {
            sender.sendMessage(new TextComponentString("This command can only be executed by a player."));
        }
    }
}
