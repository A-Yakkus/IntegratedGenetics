package io.github.ayakkus.integratedgenetics.integration.part.parts;

import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.api.part.aspect.IAspect;
import org.cyclops.integrateddynamics.api.part.read.IPartTypeReader;
import org.cyclops.integrateddynamics.client.gui.GuiPartReader;
import org.cyclops.integrateddynamics.core.part.PartTypeRegistry;
import org.cyclops.integrateddynamics.core.part.PartTypes;
import org.cyclops.integrateddynamics.core.part.aspect.AspectRegistry;
import org.cyclops.integrateddynamics.core.part.read.PartStateReaderBase;
import org.cyclops.integrateddynamics.core.part.read.PartTypeReadBase;
import org.cyclops.integrateddynamics.inventory.container.ContainerPartReader;

import com.google.common.collect.Lists;

import io.github.ayakkus.integratedgenetics.IntegratedBees;
import io.github.ayakkus.integratedgenetics.integration.part.PartTypesIG;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import scala.actors.threadpool.Arrays;

public class PartInventoryBeeReader extends PartTypeReadBase<PartInventoryBeeReader, PartStateReaderBase<PartInventoryBeeReader>>{

	public PartInventoryBeeReader(String name) {
		super(name);
		AspectRegistry.getInstance().register(this, Lists.<IAspect>newArrayList(
				AspectRegistry.getInstance().getAspects(PartTypes.INVENTORY_READER))); 
		AspectRegistry.getInstance().register(this, Lists.<IAspect>newArrayList(AspectRegistry.getInstance().getAspects(PartTypesIG.BEE_READER)));
		
	}

	@Override
	protected PartStateReaderBase<PartInventoryBeeReader> constructDefaultState() {
		return new PartStateReaderBase<PartInventoryBeeReader>();
	}

	@Override
	protected ModBase getMod() {
		return IntegratedBees.instance;
	}

	@Override
	public ModBase getModGui() {
		return IntegratedDynamics._instance;
	}

	@Override
	public Class getPartTypeClass() {
		return IPartTypeReader.class;
	}

	@Override
	protected boolean hasGui() {
		return true;
	}

	@Override
	public Class<? extends Container> getContainer() {
		return ContainerPartReader.class;
	}

	@Override
	public Class<? extends GuiScreen> getGui() {
		return GuiPartReader.class;
	}



}
