package owmii.powah.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import owmii.lib.item.ItemBase;
import owmii.powah.block.solarpanel.SolarPanelTile;

public class LensOfEnderItem extends ItemBase {
    public LensOfEnderItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, World world, BlockPos pos, PlayerEntity player, Hand hand, Direction side, Vec3d hit) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof SolarPanelTile) {
                SolarPanelTile solar = (SolarPanelTile) tile;
                if (!solar.hasLensOfEnder()) {
                    solar.setHasLensOfEnder(true);
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return super.onItemUseFirst(stack, world, pos, player, hand, side, hit);
    }
}
