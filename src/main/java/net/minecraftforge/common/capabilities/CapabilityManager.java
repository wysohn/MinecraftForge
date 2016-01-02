package net.minecraftforge.common.capabilities;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import com.google.common.collect.Maps;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.apache.logging.log4j.Level;

/**
 * Created by cpw on 28/12/15.
 */
public enum CapabilityManager
{
    INSTANCE;

    private IdentityHashMap<String, Capability<?,?>> providers = Maps.newIdentityHashMap();

    public <S extends Capability.IInteraction> S findInteraction(String name)
    {
        String lookup = name.intern();
        return (S)providers.get(lookup).interaction();
    }

    public <S extends Capability.IStorage,I extends Capability.IInteraction<?>> Capability<S,I> register(String name, S storageProvider, I interactionProvider)
    {
        String realName = name.intern();
        Capability<S,I> cap = new Capability<S,I>(realName, storageProvider, interactionProvider);
        providers.put(realName, cap);
        return cap;
    }

    public void injectCapabilities(ASMDataTable data)
    {
        for (ASMDataTable.ASMData entry : data.getAll(CapabilityInject.class.getName())) {
            String capabilityName = (String)entry.getAnnotationInfo().get("value");
            String targetClass = entry.getClassName();
            String fieldName = entry.getObjectName();
            Capability<?,?> cap = providers.get(capabilityName.intern());
            try
            {
                Field f = Class.forName(targetClass).getDeclaredField(fieldName);
                f.set(null, cap);
            } catch (Exception e)
            {
                FMLLog.log(Level.WARN, e, "Unable to inject capability %s at %s.%s", capabilityName, targetClass, fieldName);
            }
        }
    }
}
