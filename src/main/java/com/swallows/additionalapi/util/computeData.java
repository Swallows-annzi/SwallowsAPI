package com.swallows.additionalapi.util;

import net.minecraft.nbt.NBTTagCompound;

public class computeData {

    public NBTTagCompound upData(NBTTagCompound data1, NBTTagCompound data2) {
        for (String key : data2.getKeySet()) {
            data1.setTag(key, data2.getTag(key));
        }
        return data1;
    }

    public NBTTagCompound addData(NBTTagCompound data1, NBTTagCompound data2) {
        for (String key : data2.getKeySet()) {
            data1.setTag(key, data2.getTag(key));
        }
        return data1;
    }

    public NBTTagCompound subtractData(NBTTagCompound data1, NBTTagCompound data2) {
        for (String key : data2.getKeySet()) {
            if(data1.hasKey(key)) {
                data1.removeTag(key);
            }
        }
        return data1;
    }
}
