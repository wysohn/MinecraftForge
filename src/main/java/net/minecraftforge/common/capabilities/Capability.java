package net.minecraftforge.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by cpw on 28/12/15.
 */
public final class Capability<S extends Capability.IStorage, I extends Capability.IInteraction>
{
    public interface IStorage
    {
        void writeStorage(NBTTagCompound nbt);
        void readStorage(NBTTagCompound nbt);
    }

    public interface IInteraction
    {

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

    public I interaction()
    {
        return this.interaction;
    }

    public S storage()
    {
        return this.storage;
    }
}
