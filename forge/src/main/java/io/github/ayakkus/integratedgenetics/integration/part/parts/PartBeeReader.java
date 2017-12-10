package io.github.ayakkus.integratedgenetics.integration.part.parts;

import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.api.part.aspect.IAspect;
import org.cyclops.integrateddynamics.api.part.read.IPartTypeReader;
import org.cyclops.integrateddynamics.client.gui.GuiPartReader;
import org.cyclops.integrateddynamics.core.part.aspect.AspectRegistry;
import org.cyclops.integrateddynamics.core.part.read.PartStateReaderBase;
import org.cyclops.integrateddynamics.core.part.read.PartTypeReadBase;
import org.cyclops.integrateddynamics.inventory.container.ContainerPartReader;

import com.google.common.collect.Lists;

import io.github.ayakkus.integratedgenetics.IntegratedBees;
import io.github.ayakkus.integratedgenetics.integration.part.AspectsIB;
import io.github.ayakkus.integratedgenetics.integration.part.AspectsIB.AspectsBees;
import io.github.ayakkus.integratedgenetics.integration.part.AspectsIB.AspectsGenetics;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;

public class PartBeeReader extends PartTypeReadBase<PartBeeReader, PartStateReaderBase<PartBeeReader>>{

	public PartBeeReader(String name) {
		super(name);
		AspectRegistry.getInstance().register(this, Lists.<IAspect>newArrayList(
				AspectsIB.AspectsBees.IS_BEE_HOUSING,
				AspectsIB.AspectsBees.BEE_AGE,
				AspectsIB.AspectsBees.IS_ANALYZED,
				AspectsIB.AspectsBees.IS_HYBRID,
				AspectsIB.AspectsBees.BEE_TRAIT_SPECIES_PRIMARY,
				AspectsIB.AspectsBees.BEE_TRAIT_SPECIES_SECONDARY,
				AspectsIB.AspectsGenetics.GENETICS_TRAIT
				));
	}

	@Override
	protected PartStateReaderBase<PartBeeReader> constructDefaultState() {
		return new PartStateReaderBase<PartBeeReader>();
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
