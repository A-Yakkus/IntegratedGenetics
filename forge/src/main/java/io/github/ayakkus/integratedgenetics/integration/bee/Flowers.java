package io.github.ayakkus.integratedgenetics.integration.bee;

import java.util.Locale;

import forestry.apiculture.flowers.FlowerProvider;
import forestry.core.genetics.alleles.IAlleleValue;

public enum Flowers implements IAlleleValue<FlowerProvider>{

	MENRIL(ForestryRegistry.FLOWER_MENRIL, false);

	private final FlowerProvider provider;
	private final boolean dominance;
	
	Flowers(String flowerType, boolean dominant){
		String lwc = this.toString().toLowerCase(Locale.ENGLISH);
		this.provider = new FlowerProvider(flowerType, "for.flowers."+lwc);
		this.dominance=dominant;
	}
	
	@Override
	public boolean isDominant() {
		return dominance;
	}

	@Override
	public FlowerProvider getValue() {
		return provider;
	}

}
