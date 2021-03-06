package owmii.powah.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import owmii.lib.client.renderer.tile.AbstractTileRenderer;
import owmii.lib.inventory.Inventory;
import owmii.lib.util.math.V3d;
import owmii.powah.block.energizing.EnergizingOrbTile;
import owmii.powah.client.model.OrbModel;

import java.util.ArrayList;
import java.util.List;

public class EnergizingOrbRenderer extends AbstractTileRenderer<EnergizingOrbTile> {
    public static final OrbModel MODEL = new OrbModel();

    public EnergizingOrbRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(EnergizingOrbTile te, float pt, MatrixStack matrix, IRenderTypeBuffer rtb, Minecraft mc, ClientWorld world, ClientPlayerEntity player, int light, int ov) {
        Inventory inv = te.getInventory();
        if (!inv.isEmpty()) {
            float ticks = (te.ticks + pt) / 200.0F;
            ItemStack output = inv.getStackInSlot(0);
            if (!output.isEmpty()) {
                matrix.push();
                matrix.translate(0.5D, 0.6D, 0.5D);
                matrix.rotate(Vector3f.YP.rotationDegrees(-ticks * 360.0F));
                matrix.scale(0.35F, 0.35F, 0.35F);
                mc.getItemRenderer().renderItem(output, ItemCameraTransforms.TransformType.FIXED, light, ov, matrix, rtb);
                matrix.pop();
            } else {
                List<ItemStack> stacks = new ArrayList<>(inv.getNonEmptyStacks());
                List<V3d> circled = V3d.from(BlockPos.ZERO).circled(stacks.size(), 0.1D);
                for (int i = 0; i < circled.size(); i++) {
                    V3d v3d1 = circled.get(i);
                    ItemStack stack = stacks.get(i);
                    if (!stack.isEmpty()) {
                        matrix.push();
                        if (stacks.size() == 1) {
                            matrix.translate(0.5D, 0.6D, 0.5D);
                        } else {
                            matrix.translate(v3d1.x + 0.5D, v3d1.y + 0.6D, v3d1.z + 0.5D);
                        }
                        matrix.scale(0.35F, 0.35F, 0.35F);
                        matrix.rotate(Vector3f.YP.rotationDegrees(-ticks * 360.0F));
                        mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, light, ov, matrix, rtb);
                        matrix.pop();
                    }
                }
            }
        }

        matrix.push();
        matrix.translate(0.5D, 0.6D, 0.5D);
        matrix.scale(1.8F, 1.8F, 1.8F);
        MODEL.render(te, this, matrix, rtb, light, ov);
        matrix.pop();
    }
}
