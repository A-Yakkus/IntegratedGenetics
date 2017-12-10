package io.github.ayakkus.integratedgenetics.proxy;

import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.proxy.ClientProxyComponent;
import org.cyclops.cyclopscore.proxy.CommonProxyComponent;

import io.github.ayakkus.integratedgenetics.IntegratedBees;

public class ClientProxy extends ClientProxyComponent {

	public ClientProxy() {
		super(new CommonProxy());
	}

	@Override
	public ModBase getMod() {
		return IntegratedBees.instance;
	}

}
