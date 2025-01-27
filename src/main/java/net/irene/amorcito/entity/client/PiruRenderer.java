package net.irene.amorcito.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class PiruRenderer extends MobRenderer<PiruEntity, PiruModel<PiruEntity>> {
    public PiruRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PiruModel<>(pContext.bakeLayer(PiruModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PiruEntity pEntity) {
        return new ResourceLocation(Amorcito.MODID, "textures/entity/piru.png");
    }



    @Override
    public void render(PiruEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        this.model.setupAnim(pEntity, pEntity.getLimbSwing(), pEntity.getLimbSwingAmount(), pEntity.tickCount, pEntity.getYRot(), pEntity.getXRot());
    }
}
