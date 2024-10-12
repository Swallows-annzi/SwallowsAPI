package com.swallows.additionalapi.crafttweaker.world;

import com.swallows.additionalapi.data.Data;
import crafttweaker.annotations.ZenRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.additionalapi.WorldData")
@SuppressWarnings("unused")
public class SaaWorldData {
    @ZenMethod
    public  static void setArchiveDataInt (String Key, int Value) {
        World world = DimensionManager.getWorld(0);
        Data data = (Data) world.getMapStorage().getOrLoadData(Data.class, "AdditionalAPIArchiveData");
        if(data == null)
        {
            data = new Data("AdditionalAPIArchiveData");
            world.getMapStorage().setData("AdditionalAPIArchiveData", data);
        }
        NBTTagCompound ArchiveNBT = data.getData("ArchiveData");
        ArchiveNBT.setInteger(Key, Value);
        data.setData("ArchiveData", ArchiveNBT);
    }
}
