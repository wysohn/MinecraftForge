package net.minecraftforge.test.capabilitytest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by cpw on 01/01/16.
 */
public class ExampleCapability
{
    public static class ExampleCapStorage implements Capability.IStorage
    {
        @Override
        public NBTTagCompound getNBTForWriting(Capability<?, ?> capability, EnumFacing facing, TileEntity tile)
        {
            return null;
        }

        @Override
        public void handleNBTRead(Capability<?, ?> cap, EnumFacing facing, TileEntity tileEntity, NBTTagCompound nbtTagCompound)
        {

        }

        @Override
        public NBTTagCompound getNBTFromTile(Capability<?, ?> capability, EnumFacing facing, TileEntity tile)
        {
            return null;
        }
    }

    public static class ExampleCapInteraction implements Capability.IInteraction<ExampleCapInteraction>
    {
        public void doSomething(String testy)
        {

        }

        @Override
        public ExampleCapInteraction forTileEntity(Capability<?,?> capability, EnumFacing facing, TileEntity tile)
        {
            NBTTagCompound mydata = capability.storage().getNBTFromTile(capability, facing, tile);
            return this;
        }
    }
}
