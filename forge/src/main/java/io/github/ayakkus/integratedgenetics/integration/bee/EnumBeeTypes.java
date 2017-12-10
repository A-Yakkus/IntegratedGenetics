package io.github.ayakkus.integratedgenetics.integration.bee;

import java.awt.Color;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang3.text.WordUtils;
import org.cyclops.integrateddynamics.item.ItemCrystalizedChorusChunkConfig;
import org.cyclops.integrateddynamics.item.ItemCrystalizedMenrilChunkConfig;
import org.cyclops.integrateddynamics.item.ItemMenrilBerriesConfig;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IAlleleBeeSpeciesBuilder;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.BeeVariation;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.IBranchDefinition;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import io.github.ayakkus.integratedgenetics.References;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public enum EnumBeeTypes implements IBeeDefinition{

	MENRIL(EnumBeeBranches.MENRIL, "menris", false, new Color(0x675643), new Color(0x58697C)){
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies){
			beeSpecies.addProduct(new ItemStack(ItemCrystalizedMenrilChunkConfig._instance.getItemInstance()),0.2F);
			beeSpecies.addSpecialty(new ItemStack(ItemMenrilBerriesConfig._instance.getItemInstance()), 0.01F);
		}
		
		@Override
		protected void registerMutations(){
			BeeManager.beeMutationFactory.createMutation(BeeDefinition.NOBLE.getGenome().getPrimary(),
					BeeDefinition.MODEST.getGenome().getPrimary(), getTemplate(), 20);
		}
		
	},
	CHORUS(EnumBeeBranches.MENRIL, "chorus", false, new Color(0x7A4A7A), new Color(0x380838)){
		protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies){
			beeSpecies.addSpecialty(new ItemStack(ItemCrystalizedChorusChunkConfig._instance.getItemInstance()),0.05F);
			beeSpecies.addProduct(new ItemStack(ItemCrystalizedMenrilChunkConfig._instance.getItemInstance()),0.2F);
		}
		
		@Override
		protected void setAlleles(IAllele[] template) {
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_5);
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.LIFESPAN, EnumAllele.Lifespan.LONGEST);
			AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, AlleleEffects.effectMisanthrope);
		}

		
		@Override
		protected void registerMutations(){
			BeeManager.beeMutationFactory.createMutation(EnumBeeTypes.MENRIL.getGenome().getPrimary(),
					BeeDefinition.ENDED.getGenome().getPrimary(), getTemplate(), 1)
			.requireResource(Blocks.END_STONE.getDefaultState())
			.restrictTemperature(EnumTemperature.ICY, EnumTemperature.COLD)
			.requireNight();
		}
	}
	;
	private IBranchDefinition branch;
	private IAlleleBeeSpecies species;
	private IAllele[] template;
	private IBeeGenome genome;

	EnumBeeTypes(IBranchDefinition  branch, String binomial, boolean dominant, Color primary, Color secondary){
		String lcw = this.toString().toLowerCase(Locale.ENGLISH);
		String species = "species"+WordUtils.capitalize(lcw);
		String id = References.MOD_ID+"."+species;
		String description = References.MOD_ID+".desc."+species;
		String name = "for.bees.species."+lcw;
		this.branch=branch;
		IAlleleBeeSpeciesBuilder builder = BeeManager.beeFactory.createSpecies(id, dominant, "Yakkus", name, description, branch.getBranch(), binomial, primary.getRGB(), secondary.getRGB());
		if(isSecret()){
			builder.setIsSecret();
		}
		this.setSpeciesProperties(builder);
		this.species = builder.build();
	}
		
	protected boolean isSecret() {
		return false;
	}

	protected void setSpeciesProperties(IAlleleBeeSpeciesBuilder beeSpecies){
		
	}
	
	protected void setAlleles(IAllele[] alleles){
		
	}
	
	public static void initBees(){
		for(EnumBeeTypes bee : values()){
			bee.init();
			bee.registerMutations();
		}
	}

	protected void registerMutations() {
		
	}
	
	void init() {
		template = branch.getTemplate();
		AlleleHelper.getInstance().set(template, EnumBeeChromosome.SPECIES, species);
		setAlleles(template);
		genome = BeeManager.beeRoot.templateAsGenome(template);
		BeeManager.beeRoot.registerTemplate(template);
	}
	
	@Override
	public IBeeGenome getGenome() {
		return genome;
	}

	@Override
	public IBee getIndividual() {
		return new Bee(genome);
	}

	@Override
	public ItemStack getMemberStack(EnumBeeType beeType) {
		return BeeManager.beeRoot.getMemberStack(getIndividual(), beeType);
	}

	@Override
	public IAllele[] getTemplate() {
		return Arrays.copyOf(template, template.length);
	}
	
	public final IBeeDefinition getRainResist()
	{
		return new BeeVariation.RainResist(this);
	}

}
