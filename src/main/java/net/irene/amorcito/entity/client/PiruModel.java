package net.irene.amorcito.entity.client;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import org.jline.utils.Log;
import org.joml.Vector3f;

public class PiruModel<T extends PiruEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Amorcito.MODID, "pirumodel"), "main");
	private final ModelPart piru;
	private final ModelPart LeftFrontLeg;
	private final ModelPart Tail;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart Body;
	private final ModelPart LeftBodyFat;
	private final ModelPart RightBodyFat;
	private final ModelPart LeftBackLeg;
	private final ModelPart Neck;
	private final ModelPart Face;

	public PiruModel(ModelPart root) {
		this.piru = root.getChild("piru");
		this.LeftFrontLeg = this.piru.getChild("LeftFrontLeg");
		this.Tail = this.piru.getChild("Tail");
		this.RightFrontLeg = this.piru.getChild("RightFrontLeg");
		this.RightBackLeg = this.piru.getChild("RightBackLeg");
		this.Body = this.piru.getChild("Body");
		this.LeftBodyFat = this.Body.getChild("LeftBodyFat");
		this.RightBodyFat = this.Body.getChild("RightBodyFat");
		this.LeftBackLeg = this.piru.getChild("LeftBackLeg");
		this.Neck = this.piru.getChild("Neck");
		this.Face = this.piru.getChild("Face");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition piru = partdefinition.addOrReplaceChild("piru", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LeftFrontLeg = piru.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create().texOffs(16, 60).addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 57).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 60).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, -6.5F));

		PartDefinition Tail = piru.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(30, 37).addBox(-2.1875F, -1.6875F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 40).addBox(-1.1875F, -0.6875F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 43).addBox(-1.1875F, 0.3125F, -2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 61).addBox(-1.1875F, 1.3125F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 61).addBox(-1.1875F, 0.3125F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 61).addBox(-1.1875F, -0.6875F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 62).addBox(-0.1875F, -1.6875F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(62, 62).addBox(0.8125F, -2.6875F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1875F, -9.3125F, 11.0F));

		PartDefinition RightFrontLeg = piru.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create().texOffs(58, 27).addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 57).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 58).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, -6.5F));

		PartDefinition RightBackLeg = piru.addOrReplaceChild("RightBackLeg", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 56).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 56).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, 4.5F));

		PartDefinition Body = piru.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.625F, -5.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(38, 48).addBox(-2.5F, 0.375F, -9.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-1.5F, -4.625F, -9.0F, 3.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(56, 34).addBox(-1.5F, -2.625F, 7.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -9.375F, -1.0F));

		PartDefinition LeftBodyFat = Body.addOrReplaceChild("LeftBodyFat", CubeListBuilder.create().texOffs(34, 0).addBox(0.0F, -3.25F, -1.5F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(20, 37).addBox(0.0F, -3.25F, -5.5F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(36, 34).addBox(-1.0F, -2.25F, -1.5F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(56, 42).addBox(-1.0F, -2.25F, -4.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.625F, -2.5F));

		PartDefinition RightBodyFat = Body.addOrReplaceChild("RightBodyFat", CubeListBuilder.create().texOffs(36, 17).addBox(-1.0F, -3.25F, -1.5F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(56, 6).addBox(-1.0F, -3.25F, -5.5F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 37).addBox(0.0F, -2.25F, -1.5F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(44, 56).addBox(0.0F, -2.25F, -4.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.625F, -2.5F));

		PartDefinition LeftBackLeg = piru.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create().texOffs(58, 19).addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(56, 51).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(30, 46).addBox(-1.0F, 1.0F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 4.5F));

		PartDefinition Neck = piru.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 51).addBox(-2.5F, -0.25F, -1.125F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(18, 56).addBox(-2.5F, -2.25F, 0.875F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(32, 56).addBox(-2.5F, -0.25F, -2.125F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 0).addBox(-2.5F, -2.25F, -3.125F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -10.75F, -8.875F));

		PartDefinition Face = piru.addOrReplaceChild("Face", CubeListBuilder.create().texOffs(10, 62).addBox(1.5F, -4.7333F, 0.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 62).addBox(-2.5F, -4.7333F, 0.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(20, 48).addBox(-2.5F, -2.7333F, -2.2F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(58, 31).addBox(-1.5F, -0.7333F, -3.2F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 62).addBox(-0.5F, 0.2667F, -3.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 16).addBox(-1.5F, -3.2333F, -1.2F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, -1.7333F, -1.2F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 0).addBox(2.5F, -1.7333F, -1.2F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(2, 1).addBox(-3.5F, 1.2667F, -0.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(2, 1).addBox(2.5F, 1.2667F, -0.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.5F, 0.2667F, 0.8F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.5F, 0.2667F, 0.8F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 60).addBox(-2.5F, -1.7333F, 1.8F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 49).addBox(-2.5F, 1.2667F, -2.2F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 49).addBox(-2.5F, 1.2667F, -1.2F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -14.2667F, -11.8F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	@Override
	public void setupAnim(PiruEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		//if (entity.getPose() == Pose.STANDING) {
		this.animateWalk(PiruAnimation.WALK, limbSwing * 3f, limbSwingAmount * 4f, 0.6f, 2f);
		this.animate(entity.idleAnimationState, PiruAnimation.IDLE, ageInTicks, 1f);
		this.animate(entity.unBreadAnimationState, PiruAnimation.BREADMODE2, ageInTicks, 1f);
		//} else if (entity.getPose() == Pose.SITTING) {
		this.animate(entity.breadAnimationState, PiruAnimation.BREADMODE, ageInTicks, 1f);
		//}
	}

	@Override
	protected void animateWalk(AnimationDefinition pAnimationDefinition, float pLimbSwing, float pLimbSwingAmount, float pMaxAnimationSpeed, float pAnimationScaleFactor) {
		long i = (long)(pLimbSwing * 50.0F * pMaxAnimationSpeed);
		float f = Math.min(pLimbSwingAmount * pAnimationScaleFactor, 3.0F);
		KeyframeAnimations.animate(this, pAnimationDefinition, i, f, ANIMATION_VECTOR_CACHE);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float AgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -50.0f, 35.0f);
		pHeadPitch = Mth.clamp(pHeadPitch, -35f, 50.0f);

		this.Face.yRot = pNetHeadYaw * (float) (Math.PI / 180.0);
		this.Face.xRot = pHeadPitch * (float) (Math.PI / 180.0);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		piru.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return piru;
	}
}
