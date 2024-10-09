package net.irene.amorcito.entity.client;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.irene.amorcito.entity.animations.ModAnimationDefinitions;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PiruModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "piru"), "main");
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

		PartDefinition LeftFrontLeg = piru.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create().texOffs(16, 60).addBox(0.5F, 0.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 57).addBox(0.5F, 2.0F, 9.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 60).addBox(0.5F, 4.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -16.0F));

		PartDefinition Tail = piru.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(30, 37).addBox(-1.0F, -11.0F, 7.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 40).addBox(0.0F, -10.0F, 8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 43).addBox(0.0F, -9.0F, 9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 61).addBox(0.0F, -8.0F, 10.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 61).addBox(0.0F, -9.0F, 11.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 61).addBox(0.0F, -10.0F, 12.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 62).addBox(1.0F, -11.0F, 13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 62).addBox(2.0F, -12.0F, 14.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightFrontLeg = piru.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create().texOffs(58, 27).addBox(-0.5F, 0.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.5F, 2.0F, 9.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 58).addBox(-0.5F, 4.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, -16.0F));

		PartDefinition RightBackLeg = piru.addOrReplaceChild("RightBackLeg", CubeListBuilder.create().texOffs(58, 23).addBox(-0.5F, 0.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 56).addBox(-0.5F, 2.0F, 9.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 56).addBox(-0.5F, 4.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, -5.0F));

		PartDefinition Body = piru.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, 2.0F, 5.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(38, 48).addBox(-5.0F, -3.0F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-4.0F, -8.0F, -2.0F, 3.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(56, 34).addBox(-4.0F, -6.0F, 14.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, -8.0F));

		PartDefinition LeftBodyFat = Body.addOrReplaceChild("LeftBodyFat", CubeListBuilder.create().texOffs(34, 0).addBox(2.0F, -5.0F, 8.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(20, 37).addBox(2.0F, -5.0F, 4.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 34).addBox(1.0F, -4.0F, 8.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(56, 42).addBox(1.0F, -4.0F, 5.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -1.0F, -5.0F));

		PartDefinition RightBodyFat = Body.addOrReplaceChild("RightBodyFat", CubeListBuilder.create().texOffs(36, 17).addBox(-2.0F, -5.0F, 8.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(56, 6).addBox(-2.0F, -5.0F, 4.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 37).addBox(-1.0F, -4.0F, 8.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(44, 56).addBox(-1.0F, -4.0F, 5.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, -5.0F));

		PartDefinition LeftBackLeg = piru.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create().texOffs(58, 19).addBox(0.5F, 0.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 51).addBox(0.5F, 2.0F, 9.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 46).addBox(0.5F, 4.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -5.0F));

		PartDefinition Neck = piru.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 51).addBox(-5.0F, -2.0F, 10.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 56).addBox(-5.0F, -4.0F, 12.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 56).addBox(-5.0F, -2.0F, 9.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-5.0F, -4.0F, 8.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -9.0F, -20.0F));

		PartDefinition Face = piru.addOrReplaceChild("Face", CubeListBuilder.create().texOffs(10, 62).addBox(2.0F, -8.0F, 14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 62).addBox(-2.0F, -8.0F, 14.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 48).addBox(-2.0F, -6.0F, 11.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(58, 31).addBox(-1.0F, -4.0F, 10.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 62).addBox(0.0F, -3.0F, 9.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 16).addBox(-1.0F, -6.5F, 12.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -5.0F, 12.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(1, 0).addBox(3.0F, -5.0F, 12.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 1).addBox(-3.0F, -2.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 1).addBox(3.0F, -2.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -3.0F, 14.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, -3.0F, 14.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(22, 60).addBox(-2.0F, -5.0F, 15.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 49).addBox(-2.0F, -2.0F, 11.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 49).addBox(-2.0F, -2.0F, 12.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, -25.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.walk, limbSwing, limbSwingAmount, 2, 0.25f);
		this.animate(((PiruEntity) entity).idleAnimationState, ModAnimationDefinitions.idle, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float AgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -10.0f, 10.0f);
		pHeadPitch = Mth.clamp(pHeadPitch, -10.0f, 25.0f);

		this.Face.yRot = pNetHeadYaw * ((float)Math.PI/180);
		this.Face.xRot = pHeadPitch * ((float)Math.PI/180);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int pColor) {
		piru.render(poseStack, vertexConsumer, packedLight, packedOverlay, pColor);
	}

	@Override
	public ModelPart root() {
		return piru;
	}
}
