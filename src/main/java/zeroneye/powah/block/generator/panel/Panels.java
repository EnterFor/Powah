package zeroneye.powah.block.generator.panel;

import net.minecraft.block.material.Material;
import zeroneye.powah.block.IBlocks;
import zeroneye.powah.block.generator.panel.solar.SolarPanelBlock;

public enum Panels {
    BASIC(Material.ROCK, 3.0F, 1000, 24, 10),
    HARDENED(Material.ROCK, 30.0F, 10000, 40, 30),
    BLAZING(Material.IRON, 30.0F, 50000, 100, 70),
    NIOTIC(Material.IRON, 100.0F, 80000, 400, 200),
    SPIRITED(Material.IRON, 100.0F, 200000, 800, 500);

    public final Material material;
    public final float resistance;
    public final int capacity;
    public final int transfer;
    public final int perTick;

    Panels(Material material, float resistance, int capacity, int transfer, int perTick) {
        this.material = material;
        this.resistance = resistance;
        this.capacity = capacity;
        this.transfer = transfer;
        this.perTick = perTick;
    }

    public SolarPanelBlock get() {
        return IBlocks.SOLAR_PANELS[ordinal()];
    }
}