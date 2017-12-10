package io.github.ayakkus.integratedgenetics.integration.part.parts;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.cyclops.cyclopscore.datastructure.DimPos;
import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.init.IInitListener.Step;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.api.network.INetwork;
import org.cyclops.integrateddynamics.api.network.INetworkElement;
import org.cyclops.integrateddynamics.api.network.IPartNetwork;
import org.cyclops.integrateddynamics.api.network.event.INetworkEvent;
import org.cyclops.integrateddynamics.api.part.IPartContainer;
import org.cyclops.integrateddynamics.api.part.IPartState;
import org.cyclops.integrateddynamics.api.part.IPartType;
import org.cyclops.integrateddynamics.api.part.PartRenderPosition;
import org.cyclops.integrateddynamics.api.part.PartTarget;
import org.cyclops.integrateddynamics.api.part.aspect.IAspect;
import org.cyclops.integrateddynamics.api.part.read.IPartTypeReader;
import org.cyclops.integrateddynamics.client.gui.GuiPartReader;
import org.cyclops.integrateddynamics.core.part.aspect.AspectRegistry;
import org.cyclops.integrateddynamics.core.part.read.PartStateReaderBase;
import org.cyclops.integrateddynamics.core.part.read.PartTypeReadBase;
import org.cyclops.integrateddynamics.inventory.container.ContainerPartReader;

import com.google.common.collect.Lists;

import io.github.ayakkus.integratedgenetics.IntegratedBees;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PartTreeReader extends PartTypeReadBase<PartTreeReader, PartStateReaderBase<PartTreeReader>>{

	public PartTreeReader(String name) {
		super(name);
		AspectRegistry.getInstance().register(this, Lists.<IAspect>newArrayList(
				
				
				));
	}

	@Override
	protected PartStateReaderBase<PartTreeReader> constructDefaultState() {
		return new PartStateReaderBase<PartTreeReader>();
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
