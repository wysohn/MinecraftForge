package net.minecraftforge.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Created by cpw on 28/12/15.
 */
public final class Capability<S extends Capability.IStorage, I extends Capability.IInteraction<?>>
{
    public interface IStorage
    {
        // For writing to the tile entity during save
        NBTTagCompound getNBTForWriting(Capability<?,?> capability, EnumFacing facing, TileEntity tile);
        // For reading from tile entity during load
        void handleNBTRead(Capability<?, ?> cap, EnumFacing facing, TileEntity tileEntity, NBTTagCompound nbtTagCompound);
        // For interactions to read from tile entity
        NBTTagCompound getNBTFromTile(Capability<?,?> capability, EnumFacing facing, TileEntity tile);
    }

    public interface IInteraction<T>
    {
        // Get a handler for the supplied tile entity
        public T forTileEntity(Capability<?,?> capability, EnumFacing facing, TileEntity tile);
    }

    private final String name;
    private final S storage;
    private final I interaction;
    Capability(String name, S storageProvider, I interactionProvider)
    {
        this.name = name;
        this.storage = storageProvider;
        this.interaction = interactionProvider;
    }

    public String name() { return name; }

    public I interaction()
    {
        return this.interaction;
    }

    public S storage()
    {
        return this.storage;
    }
}
