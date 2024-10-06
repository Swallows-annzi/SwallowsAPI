package com.swallows.additionalapi.data;

import com.swallows.additionalapi.Tags;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class Data extends WorldSavedData {

    public static String Key = Tags.MOD_ID;

    private NBTTagCompound nbt = new NBTTagCompound();

    public Data() {
        super(Key);
    }

    public Data(String name) {
        super(name);
        Key = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.nbt = nbt.getCompoundTag(Key);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(Key, this.nbt);
        return compound;
    }

    public NBTTagCompound getData(String key) {
        return nbt.getCompoundTag(key);
    }

    public void setData(String key, NBTTagCompound data) {
        nbt.setTag(key, data);
        markDirty();
    }

    public void removeData(String key) {
        nbt.removeTag(key);
        markDirty();
    }

    public void removeData(String key1, String key2) {
        nbt.getCompoundTag(key1).removeTag(key2);
        markDirty();
    }
}
