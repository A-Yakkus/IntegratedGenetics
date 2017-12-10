package io.github.ayakkus.integratedgenetics.integration.part;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.cyclops.cyclopscore.datastructure.DimPos;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.api.evaluate.EvaluationException;
import org.cyclops.integrateddynamics.api.part.PartTarget;
import org.cyclops.integrateddynamics.api.part.aspect.IAspectRead;
import org.cyclops.integrateddynamics.api.part.aspect.IAspectRegistry;
import org.cyclops.integrateddynamics.api.part.aspect.property.IAspectProperties;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueObjectTypeItemStack;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueObjectTypeItemStack.ValueItemStack;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeBoolean;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeInteger;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeList;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeList.ValueList;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeString;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypeString.ValueString;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypes;
import org.cyclops.integrateddynamics.core.part.aspect.build.AspectBuilder;
import org.cyclops.integrateddynamics.core.part.aspect.build.IAspectValuePropagator;
import org.cyclops.integrateddynamics.part.aspect.Aspects;
import org.cyclops.integrateddynamics.part.aspect.read.AspectReadBuilders;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IChromosome;
import forestry.core.genetics.ItemGE;
import io.github.ayakkus.integratedgenetics.IntegratedBees;
import net.minecraft.item.ItemStack;

public class AspectsIB {

	public static final IAspectRegistry REGISTRY = IntegratedDynamics._instance.getRegistryManager().getRegistry(IAspectRegistry.class);

	public static void load(){}
	
	public static final class AspectsBees{
		public static final IAspectRead<ValueTypeInteger.ValueInteger, ValueTypeInteger> BEE_AGE =
				Builders.BUILDER_BEE_HOUSING_INTEGER.handle(new IAspectValuePropagator<IBeeHousing, Integer>(){
					@Override
					public Integer getOutput(IBeeHousing input) throws EvaluationException {
						if(input!=null){
							if(input.getBeeInventory().getQueen()!=ItemStack.EMPTY)
								return input.getBeekeepingLogic().getBeeProgressPercent();
						}
						return 0;
					}
				}).handle(AspectReadBuilders.PROP_GET_INTEGER, "beeAge").buildRead();

		public static final IAspectRead<ValueTypeBoolean.ValueBoolean, ValueTypeBoolean> IS_BEE_HOUSING =
				Builders.BUILDER_BEE_HOUSING_BOOLEAN.handle(new IAspectValuePropagator<IBeeHousing, Boolean>(){
					@Override
					public Boolean getOutput(IBeeHousing input) throws EvaluationException {
						return input!=null;
					}
				}).handle(AspectReadBuilders.PROP_GET_BOOLEAN, "isBeeHouse").buildRead();


		public static final IAspectRead<ValueTypeBoolean.ValueBoolean, ValueTypeBoolean> IS_ANALYZED =
				Builders.BUILDER_BEE_HOUSING_BOOLEAN.handle(new IAspectValuePropagator<IBeeHousing, Boolean>(){
					@Override
					public Boolean getOutput(IBeeHousing input) throws EvaluationException {
						if(input!=null){
							if(input.getBeeInventory().getQueen()!=ItemStack.EMPTY){
								IBee bee = BeeManager.beeRoot.getMember(input.getBeeInventory().getQueen());
								if(bee!=null){
									if(bee.isAnalyzed()){
										return true;
									}
								}
							}
						}
						return false;
					}
				}).handle(AspectReadBuilders.PROP_GET_BOOLEAN, "isAnalyzed").buildRead();

		public static final IAspectRead<ValueTypeString.ValueString, ValueTypeString> BEE_TRAIT_SPECIES_PRIMARY=
				Builders.BUILDER_BEE_HOUSING_STRING.handle(new IAspectValuePropagator<IBeeHousing, String>(){
					@Override
					public String getOutput(IBeeHousing input) throws EvaluationException {
						if(input!=null){
							if(input.getBeeInventory().getQueen()!=ItemStack.EMPTY){
								IBee bee = BeeManager.beeRoot.getMember(input.getBeeInventory().getQueen());
								if(bee!=null){
										return bee.getGenome().getPrimary().getAlleleName();
								}
							}
						}
						return "";
					}})
				.handle(AspectReadBuilders.PROP_GET_STRING, "Speciesp").buildRead();


		public static final IAspectRead<ValueTypeString.ValueString, ValueTypeString> BEE_TRAIT_SPECIES_SECONDARY=
				Builders.BUILDER_BEE_HOUSING_STRING.handle(new IAspectValuePropagator<IBeeHousing, String>(){
					@Override
					public String getOutput(IBeeHousing input) throws EvaluationException {
						if(input!=null){
							if(input.getBeeInventory().getQueen()!=ItemStack.EMPTY){
								IBee bee = BeeManager.beeRoot.getMember(input.getBeeInventory().getQueen());
								if(bee!=null){
									if(bee.isAnalyzed()){
										return bee.getGenome().getSecondary().getAlleleName();
									}
								}
							}
						}
						return "";
					}})
				.handle(AspectReadBuilders.PROP_GET_STRING, "Speciess").buildRead();


		public static final IAspectRead<ValueTypeBoolean.ValueBoolean, ValueTypeBoolean> IS_HYBRID=
				Builders.BUILDER_BEE_HOUSING_BOOLEAN.handle(new IAspectValuePropagator<IBeeHousing, Boolean>(){
					@Override
					public Boolean getOutput(IBeeHousing input) throws EvaluationException {
						if(input!=null){
							if(input.getBeeInventory().getQueen()!=ItemStack.EMPTY){
								IBee bee = BeeManager.beeRoot.getMember(input.getBeeInventory().getQueen());
								if(bee.isAnalyzed()){
									if(bee.getGenome().getPrimary()!=bee.getGenome().getSecondary()){
										return true;
									}
								}
							}
						}
						return false;
					}

				}).handle(AspectReadBuilders.PROP_GET_BOOLEAN, "ishybrid").buildRead();

	}
	 
	public static final class AspectsGenetics{

		public static final IAspectRead<ValueTypeList.ValueList, ValueTypeList> GENETICS_TRAIT=
				Builders.BUILDER_GENE_TRAITS_LIST.withProperties(AspectReadBuilders.Inventory.PROPERTIES).buildRead();

	}

	public static final class Propagators{

		public static final IAspectValuePropagator<ItemStack, ValueList> PROP_TRAITS = 
				new IAspectValuePropagator<ItemStack, ValueList>(){
			@Override
			public ValueList getOutput(ItemStack input) throws EvaluationException {
				IBee bee = BeeManager.beeRoot.getMember(input);
				if(bee==null || !bee.isAnalyzed()){
					return ValueTypeList.ValueList.ofList(ValueTypes.STRING, new ArrayList<ValueString>(0));
				}
				else{
					IChromosome[] chromo = bee.getGenome().getChromosomes();
					List<ValueString> alleles = new ArrayList<ValueString>(chromo.length*2);
					for(int i = 0; i<chromo.length;i++){
						alleles.add(ValueString.of(chromo[i].getActiveAllele().getAlleleName()));
					}
					for(int i = 0; i<chromo.length;i++){
						alleles.add(ValueString.of(chromo[i].getInactiveAllele().getAlleleName()));
					}
					return ValueTypeList.ValueList.ofList(ValueTypes.STRING, alleles);
				}
			}

		};

		public static final IAspectValuePropagator<Pair<PartTarget, IAspectProperties>, IBeeHousing> PROP_GET_BEE_HOUSING=
				new IAspectValuePropagator<Pair<PartTarget, IAspectProperties>, IBeeHousing>()
		{
			@Override
			public IBeeHousing getOutput(Pair<PartTarget, IAspectProperties> input) throws EvaluationException {
				DimPos dimPos = input.getLeft().getTarget().getPos();
				return IntegratedBees.getBeeHousing(dimPos);
			}
		};


	}

	public static final class Builders{
		public static final AspectBuilder<ValueTypeBoolean.ValueBoolean, ValueTypeBoolean, IBeeHousing> BUILDER_BEE_HOUSING_BOOLEAN=
				AspectReadBuilders.BUILDER_BOOLEAN.byMod(IntegratedBees.instance).appendKind("bee").handle(Propagators.PROP_GET_BEE_HOUSING, "beeHousing");
		public static final AspectBuilder<ValueTypeInteger.ValueInteger, ValueTypeInteger, IBeeHousing> BUILDER_BEE_HOUSING_INTEGER=
				AspectReadBuilders.BUILDER_INTEGER.byMod(IntegratedBees.instance).appendKind("bee").handle(Propagators.PROP_GET_BEE_HOUSING, "beeHousing");
		public static final AspectBuilder<ValueString, ValueTypeString, IBeeHousing> BUILDER_BEE_HOUSING_STRING=
				AspectReadBuilders.BUILDER_STRING.byMod(IntegratedBees.instance).appendKind("bee").handle(Propagators.PROP_GET_BEE_HOUSING, "beeHousing");
		public static final AspectBuilder<ValueTypeList.ValueList, ValueTypeList, IBeeHousing> BUILDER_BEE_HOUSING_LIST=
				AspectReadBuilders.BUILDER_LIST.byMod(IntegratedBees.instance).appendKind("bee").handle(Propagators.PROP_GET_BEE_HOUSING,"beeHousing");
		public static final AspectBuilder<ValueList, ValueTypeList, ValueList> BUILDER_GENE_TRAITS_LIST=
				AspectReadBuilders.BUILDER_LIST.byMod(IntegratedBees.instance).appendKind("gene").handle(AspectReadBuilders.Inventory.PROP_GET_SLOT).handle(Propagators.PROP_TRAITS,"traits");
	
	}


}
