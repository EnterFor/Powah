package owmii.powah.config.energy;

import net.minecraftforge.common.ForgeConfigSpec;
import owmii.powah.block.endercell.EnderCells;

public class EnderCellConfig {
    public final ForgeConfigSpec.ConfigValue[] channels = new ForgeConfigSpec.ConfigValue[EnderCells.values().length];
    public final ForgeConfigSpec.ConfigValue[] transfers = new ForgeConfigSpec.ConfigValue[EnderCells.values().length];

    public EnderCellConfig(ForgeConfigSpec.Builder builder) {
        EnderCells[] values = EnderCells.values();
        for (int i = 0; i < values.length; i++) {
            EnderCells cell = values[i];
            String name = cell.name().toLowerCase();
            String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
            builder.push(cap + " Ender Cell");
            this.channels[i] = builder.comment("Range: min = 1, max = 16").define("channels", "" + cell.channels);
            this.transfers[i] = builder.comment("Range: min = 0, max = " + Integer.MAX_VALUE).define("transfer", "" + cell.transfer);
            builder.pop();
        }
    }
}
