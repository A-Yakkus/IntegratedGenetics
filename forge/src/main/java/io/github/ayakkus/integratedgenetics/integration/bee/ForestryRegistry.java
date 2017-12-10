package io.github.ayakkus.integratedgenetics.integration.bee;

import org.cyclops.integrateddynamics.block.BlockMenrilLog;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.FlowerManager;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.flowers.FlowerRegistry;
import io.github.ayakkus.integratedgenetics.References;

public class ForestryRegistry {
	public static IAlleleFlowers FLOWER_TYPE_MENRIL;
	public static final String FLOWER_MENRIL = "menril";
	public static final String FLOWER_TYPE = "flowers";
	
	public static void register(){
		registerFlower();
		registerGenes();
		EnumBeeTypes.initBees();
		//RecipeManagers.carpenterManager.
	}
	
	public static void registerFlower(){
		FlowerRegistry flower = (FlowerRegistry) FlowerManager.flowerRegistry;
		flower.registerAcceptableFlower(BlockMenrilLog.getInstance(), FLOWER_MENRIL);
	}
	
	public static void registerGenes(){
		FLOWER_TYPE_MENRIL = AlleleManager.alleleFactory.createFlowers
				(References.MOD_ID, FLOWER_TYPE, FLOWER_MENRIL, Flowers.MENRIL.getValue(), Flowers.MENRIL.isDominant(), EnumBeeChromosome.FLOWER_PROVIDER);
	}
}
