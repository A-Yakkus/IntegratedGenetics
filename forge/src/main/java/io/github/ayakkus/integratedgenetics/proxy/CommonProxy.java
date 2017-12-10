package io.github.ayakkus.integratedgenetics.proxy;

import org.cyclops.cyclopscore.client.key.IKeyRegistry;
import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.network.PacketHandler;
import org.cyclops.cyclopscore.proxy.CommonProxyComponent;
import org.cyclops.cyclopscore.proxy.ICommonProxy;

import io.github.ayakkus.integratedgenetics.IntegratedBees;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class CommonProxy extends CommonProxyComponent {

	@Override
	public ModBase getMod() {
		return IntegratedBees.instance;
	}

	@Override
	public void registerRenderer(Class<? extends TileEntity> clazz, TileEntitySpecialRenderer renderer) {}

	@Override
	public void registerRenderers() {}

	@Override
	public void registerKeyBindings(IKeyRegistry keyRegistry) {}

	@Override
	public void registerPacketHandlers(PacketHandler packetHandler) {}

	@Override
	public void registerTickHandlers() {}

	@Override
	public void registerEventHooks() {}

}
