package net.minecraftforge.common.capabilities;

import java.util.IdentityHashMap;

import com.google.common.collect.Maps;

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

    public <S extends Capability.IStorage,I extends Capability.IInteraction> Capability<S,I> register(String name, S storageProvider, I interactionProvider)
    {
        String realName = name.intern();
        Capability<S,I> cap = new Capability<S,I>(realName, storageProvider, interactionProvider);
        providers.put(realName, cap);
        return cap;
    }
}
