package io.github.ayakkus.integratedgenetics.integration.bee;

import java.util.Locale;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.IBranchDefinition;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;

public enum EnumBeeBranches implements IBranchDefinition {

	MENRIL("menris"){
		@Override
		protected IAllele[] setDefaults()
		{
			IAllele[] alleles = new IAllele[EnumBeeChromosome.values().length];
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.SPEED, EnumAllele.Speed.SLOWER);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.LIFESPAN, EnumAllele.Lifespan.SHORTENED);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.NEVER_SLEEPS, true);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.DOWN_3);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.TOLERATES_RAIN, false);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.CAVE_DWELLING, false);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.FLOWER_PROVIDER, ForestryRegistry.FLOWER_TYPE_MENRIL);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.FLOWERING, EnumAllele.Flowering.SLOW);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.TERRITORY, EnumAllele.Territory.AVERAGE);
			AlleleHelper.getInstance().set(alleles, EnumBeeChromosome.EFFECT, AlleleEffects.effectNone);
			return alleles;
		}
	};
		
	private final IClassification branch;
	
	EnumBeeBranches(String scientific){
		branch = BeeManager.beeFactory.createBranch(this.name().toLowerCase(Locale.ENGLISH), scientific);
	}
	
	@Override
	public IAllele[] getTemplate() {
		if(this.setDefaults()!=null){
			return this.setDefaults();
		}
		else return null;
	}

	@Override
	public IClassification getBranch() {
		return branch;
	}
	
	protected IAllele[] setDefaults(){return null;}

}
