package co.eltrut.differentiate.core.util;

import net.fabricmc.loader.api.FabricLoader;

public class CompactUtil {
	public static boolean areModsLoaded(String ...mods) {
		if (mods == null) return true;
		for (String mod : mods) {
			if (mod.startsWith("!") && FabricLoader.getInstance().isModLoaded(mod)) {
				return false;
			} else if (!FabricLoader.getInstance().isModLoaded(mod)) {
				return false;
			}
		}
		return true;
	}
	
	public static class Mods {
		// Core
		public static final String MINECRAFT = "minecraft";
		public static final String FABRIC_API = "fabric";
		
		// Aurora
		public static final String ABUNDANCE = "abundance";
		public static final String BAYOU_BLUES = "bayou_blues";
		public static final String BETTER_BADLANDS = "better_badlands";
		public static final String ENHANCED_MUSHROOMS = "enhanced_mushrooms";
		public static final String FRUITFUL	= "fruitful";
		public static final String REFORESTED = "reforested";
		
		// Evoslab
		public static final String ASSEMBLY = "assembly";
		public static final String GLUTTONY = "gluttony";
		
		// Eltrut & Co.
		public static final String ADDENDUM = "addendum";
		public static final String DIFFERENTIATE = "differentiate";
		public static final String FLAMBOYANT = "flamboyant";
		public static final String LEPTON = "lepton";
		
		// Other
		public static final String CREATE = "create";
		public static final String CRUMBS = "crumbs";
		public static final String FARMERS_DELIGHT = "farmersdelight";
		public static final String INFERNAL_EXPANSION = "infernalexp";
		public static final String MORE_RESPAWN_ANCHORS = "morerespawnanchors";
	}
}