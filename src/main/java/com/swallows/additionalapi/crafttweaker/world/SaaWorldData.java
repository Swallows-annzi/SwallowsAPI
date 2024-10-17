package com.swallows.additionalapi.crafttweaker.world;

import com.swallows.additionalapi.data.ArchiveData;
import com.swallows.additionalapi.util.getData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.data.IData;
import crafttweaker.mc1120.data.NBTConverter;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.additionalapi.WorldData")
@SuppressWarnings("unused")
public class SaaWorldData {

    @ZenMethod
    public  static void setArchiveData (String name, String key, IData iData) {
        ArchiveData archivedata = getData.getArchiveData(name);
        archivedata.setArchiveData(key, (NBTTagCompound) NBTConverter.from(iData));
    }

    @ZenMethod
    public static void upArchiveData (String name, String key, IData iData) {
        ArchiveData archivedata = getData.getArchiveData(name);
        archivedata.upArchiveData(key, (NBTTagCompound) NBTConverter.from(iData));
    }

    @ZenMethod
    public  static IData getArchiveData (String name, String key) {
        ArchiveData archivedata = getData.getArchiveData(name);
        return NBTConverter.from(archivedata.getArchiveData(key), false);
    }

    @ZenMethod
    public  static String getArchiveStringData (String name, String key) {
        ArchiveData archivedata = getData.getArchiveData(name);
        return NBTConverter.from(archivedata.getArchiveData(key), false).toString();
    }

    @ZenMethod
    public static void removeArchiveData(String name, String key) {
        ArchiveData archivedata = getData.getArchiveData(name);
        if(archivedata.isArchiveData(key))
            archivedata.removeArchiveData(key);
    }

    @ZenMethod
    public  static boolean isArchiveData (String name, String key) {
        ArchiveData archivedata = getData.getArchiveData(name);
        return archivedata.isArchiveData(key);
    }
}
