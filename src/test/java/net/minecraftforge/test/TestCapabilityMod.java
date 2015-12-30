package net.minecraftforge.test;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by cpw on 28/12/15.
 */
@Mod(modid="forge.testcapmod",version="1.0")
public class TestCapabilityMod
{

    private Capability testCapability;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent evt)
    {
        testCapability = CapabilityManager.INSTANCE.register("forge.testcapmod.BasicCapability", new StorageTest(), new InteractionTest());

    }

    public void onInteract()
    {
        InteractionTest test = CapabilityManager.INSTANCE.findInteraction("forge.testcapmod.BasicCapability");
        test.interact("World");
    }
    public class StorageTest implements Capability.IStorage
    {
        @Override
        public void writeStorage(NBTTagCompound nbt)
        {

        }

        @Override
        public void readStorage(NBTTagCompound nbt)
        {

        }
    }

    public class InteractionTest implements Capability.IInteraction
    {
        public String interact(String words)
        {
            return "Hello " + words;
        }
    }
}


