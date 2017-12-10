package io.github.ayakkus.integratedgenetics;

import java.util.Map;

import org.cyclops.cyclopscore.datastructure.DimPos;
import org.cyclops.cyclopscore.helper.TileHelpers;
import org.cyclops.cyclopscore.infobook.IInfoBookRegistry;
import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.init.RecipeHandler;
import org.cyclops.cyclopscore.proxy.ICommonProxy;
import org.cyclops.cyclopscore.recipe.custom.component.DummyPropertiesComponent;
import org.cyclops.cyclopscore.recipe.custom.component.IngredientAndFluidStackRecipeComponent;
import org.cyclops.cyclopscore.recipe.custom.component.IngredientRecipeComponent;
import org.cyclops.cyclopscore.recipe.xml.IRecipeConditionHandler;
import org.cyclops.cyclopscore.recipe.xml.IRecipeTypeHandler;
import org.cyclops.integrateddynamics.ExtendedRecipeHandler;
import org.cyclops.integrateddynamics.IntegratedDynamics;
import org.cyclops.integrateddynamics.block.BlockSqueezer;
import org.cyclops.integrateddynamics.block.BlockSqueezerConfig;
import org.cyclops.integrateddynamics.core.part.aspect.AspectRegistry;
import org.cyclops.integrateddynamics.core.recipe.xml.DryingBasinRecipeTypeHandler;
import org.cyclops.integrateddynamics.core.recipe.xml.SqueezerRecipeTypeHandler;
import org.cyclops.integrateddynamics.infobook.OnTheDynamicsOfIntegrationBook;

import forestry.api.apiculture.IBeeHousing;
import io.github.ayakkus.integratedgenetics.integration.bee.ForestryRegistry;
import io.github.ayakkus.integratedgenetics.integration.part.AspectsIB;
import io.github.ayakkus.integratedgenetics.integration.part.PartTypesIG;
import io.github.ayakkus.integratedgenetics.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=References.MOD_ID, name=References.MOD_NAME, version=References.VERSION,
dependencies="required-after:forestry@[5.5.0.154,);required-after:integrateddynamics@[0.8.2-669,);")
public class IntegratedBees extends ModBase {

	public IntegratedBees() {
		super(References.MOD_ID, References.MOD_NAME);
	}

	@Instance(References.MOD_ID)
	public static IntegratedBees instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		AspectsIB.load();
		PartTypesIG p = new PartTypesIG();
		super.preInit(event);
	}
	
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		ForestryRegistry.register();
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		IntegratedDynamics._instance.getRegistryManager().getRegistry(IInfoBookRegistry.class)
		.registerSection(OnTheDynamicsOfIntegrationBook.getInstance(), "info_book.integrateddynamics.manual",
				"/assets/" + References.MOD_ID + "/info/bee.xml");
	}
	
	@Override
	protected RecipeHandler constructRecipeHandler() {
		RecipeHandler r = new RecipeHandler(this,"shaped.xml","squeezer_ib.xml" )
				{
			/*@Override
            protected void registerHandlers(Map<String, IRecipeTypeHandler> recipeTypeHandlers, Map<String, IRecipeConditionHandler> recipeConditionHandlers) {
                super.registerHandlers(recipeTypeHandlers, recipeConditionHandlers);
                recipeTypeHandlers.put("integrateddynamics:squeezer", new SqueezerRecipeTypeHandler());//IntegratedDynamics._instance.getRecipeHandler().getRecipeTypeHandlers().get("integrateddynamics:squeezer"));
                
            }*/
				};
		
		 return r;
		}

	@Override
	public CreativeTabs constructDefaultCreativeTab() {
		return IntegratedDynamics._instance.getDefaultCreativeTab();
	}

	@Override
	public ICommonProxy getProxy() {
		return new CommonProxy();
	}
	
	public static IBeeHousing getBeeHousing(DimPos dimPos) {
		return TileHelpers.getSafeTile(dimPos, IBeeHousing.class);
	}
	
}
