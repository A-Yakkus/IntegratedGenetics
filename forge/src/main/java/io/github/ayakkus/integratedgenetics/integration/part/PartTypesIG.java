package io.github.ayakkus.integratedgenetics.integration.part;

import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.api.part.IPartTypeRegistry;

import io.github.ayakkus.integratedgenetics.integration.part.parts.PartBeeReader;
import io.github.ayakkus.integratedgenetics.integration.part.parts.PartInventoryBeeReader;

public class PartTypesIG {

public static final IPartTypeRegistry REGISTRY = IntegratedDynamics._instance.getRegistryManager().getRegistry(IPartTypeRegistry.class);
	
	public static final PartBeeReader BEE_READER = REGISTRY.register(new PartBeeReader("bee_reader"));
	public static final PartInventoryBeeReader Inventory_BEE_READER = REGISTRY.register(new PartInventoryBeeReader("i_bee_reader"));
	//public static final PartTreeReader TREE_READER = REGISTRY.register(new PartTreeReader("tree_reader"));
	
	
}
