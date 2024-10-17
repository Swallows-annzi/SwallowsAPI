package com.swallows.additionalapi.util;

import com.swallows.additionalapi.data.ArchiveData;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class getData {

    public  static ArchiveData getArchiveData(String name) {
        World world = DimensionManager.getWorld(0);
        ArchiveData archivedata = null;
        if (world.getMapStorage() != null) {
            archivedata = (ArchiveData) world.getMapStorage().getOrLoadData(ArchiveData.class, name);
            if(archivedata == null)
            {
                archivedata = new ArchiveData(name);
                world.getMapStorage().setData(name, archivedata);
            }
        }
        return archivedata;
    }
}
