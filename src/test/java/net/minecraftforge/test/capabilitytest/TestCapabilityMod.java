package net.minecraftforge.test.capabilitytest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
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

    @CapabilityInject("net.minecraftforge.test.capabilitytest.ExampleCapability")
    private Capability<ExampleCapability.ExampleCapStorage, ExampleCapability.ExampleCapInteraction> testCapability;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent evt)
    {
        testCapability = CapabilityManager.INSTANCE.register(ExampleCapability.class.getName(), new ExampleCapability.ExampleCapStorage(), new ExampleCapability.ExampleCapInteraction());
    }

    public void onInteract(World w)
    {
        TileEntity te = w.getTileEntity(BlockPos.ORIGIN);
        if (te.hasInteractionCapability(EnumFacing.DOWN, testCapability)) {
            ExampleCapability.ExampleCapInteraction interaction = w.getTileEntity(BlockPos.ORIGIN).getInteraction(EnumFacing.DOWN, testCapability);
            interaction.doSomething("hello");
        }
    }
}


