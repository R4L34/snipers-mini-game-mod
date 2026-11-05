package com.r4l.snipers_mini_game.weapon.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class ModelFRF2<T extends Entity> extends EntityModel<T> {
    private final ModelPart root;

    public ModelFRF2(ModelPart root) {
        this.root = root;
        
        // Replicate the GL11.glScaled properties from the original 1.7.10 render() method.
        // Modern ModelParts natively handle scaling after translation.
        this.root.getChild("shape25").xScale = 0.99f;
        this.root.getChild("shape28").xScale = 0.99f;
        this.root.getChild("shape37").xScale = 1.1f;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("shape40", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 40.0F), PartPose.offset(2.0F, -21.0F, 4.0F));
        partdefinition.addOrReplaceChild("shape20", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(3.0F, 11.4F, 44.4F, 0.7853982F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape33", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F), PartPose.offset(-1.0F, 14.1F, 98.1F));
        partdefinition.addOrReplaceChild("shape16", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -1.0F, 0.0F, 7.0F, 7.0F, 30.0F), PartPose.offsetAndRotation(0.5F, 11.0F, 4.0F, -0.05235988F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape44", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 7.0F, 7.0F, 39.0F), PartPose.offset(0.5F, -32.0F, -1.0F));
        partdefinition.addOrReplaceChild("shape43", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 6.0F), PartPose.offset(3.5F, -25.0F, 27.0F));
        partdefinition.addOrReplaceChild("shape8", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 48.0F), PartPose.offset(1.0F, -16.0F, -16.0F));
        partdefinition.addOrReplaceChild("shape12", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F), PartPose.offset(3.5F, -20.0F, -44.0F));
        partdefinition.addOrReplaceChild("shape39", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -4.0F, 10.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 10.9F, 150.2F, -0.7807508F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape50", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 5.0F), PartPose.offset(-1.0F, -24.5F, 49.0F));
        partdefinition.addOrReplaceChild("shape6", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -14.0F, -52.0F, 7.0F, 13.0F, 52.0F), PartPose.offsetAndRotation(0.5F, 2.7F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 18.0F), PartPose.offset(0.0F, -17.0F, 32.0F));
        partdefinition.addOrReplaceChild("shape23", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 44.84F));
        partdefinition.addOrReplaceChild("shape51", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 1.0F, 8.0F, 5.0F), PartPose.offset(-1.0F, -32.5F, 49.0F));
        partdefinition.addOrReplaceChild("shape18", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 8.0F, 41.5F, -0.34906584F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape25", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 36.0F), PartPose.offset(-1.0F, -2.0F, 67.0F));
        partdefinition.addOrReplaceChild("shape42", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 6.0F), PartPose.offset(3.5F, -25.0F, 1.0F));
        partdefinition.addOrReplaceChild("shape9", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 5.0F, 19.0F), PartPose.offset(0.0F, -6.0F, -39.0F));
        partdefinition.addOrReplaceChild("shape46", CubeListBuilder.create().texOffs(0, 100).addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F), PartPose.offset(1.75F, -33.5F, 18.0F));
        partdefinition.addOrReplaceChild("shape14", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 19.0F), PartPose.offset(1.0F, -15.5F, -129.0F));
        partdefinition.addOrReplaceChild("shape19", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 35.0F));
        partdefinition.addOrReplaceChild("shape28", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 12.0F), PartPose.offsetAndRotation(-1.0F, -2.0F, 92.0F, 0.6981317F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape29", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 22.0F, 46.0F), PartPose.offset(-1.0F, -9.7F, 101.2F));
        partdefinition.addOrReplaceChild("shape3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 12.0F), PartPose.offset(0.0F, -17.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape41", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 45.0F), PartPose.offset(3.0F, -23.0F, 1.0F));
        partdefinition.addOrReplaceChild("shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 15.0F, 50.0F), PartPose.offsetAndRotation(0.0F, -13.4F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape37", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 13.0F, 27.0F), PartPose.offset(-1.75F, -10.0F, 110.5F));
        partdefinition.addOrReplaceChild("shape52", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 1.0F, 8.0F, 5.0F), PartPose.offset(8.0F, -32.5F, 49.0F));
        partdefinition.addOrReplaceChild("shape31", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 9.0F, 21.0F, 13.0F), PartPose.offsetAndRotation(-0.5F, 11.0F, 46.0F, 0.34906584F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape30", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 9.0F, 6.0F, 13.0F), PartPose.offset(-0.5F, 5.0F, 46.0F));
        partdefinition.addOrReplaceChild("shape13", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F), PartPose.offset(3.5F, -19.5F, -109.0F));
        partdefinition.addOrReplaceChild("shape36", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, -5.0F, -1.0F, 10.0F, 5.0F, 45.0F), PartPose.offsetAndRotation(-1.0F, 13.1F, 102.4F, -0.08726646F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape38", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 4.0F), PartPose.offset(-1.0F, 10.9F, 146.2F));
        partdefinition.addOrReplaceChild("shape48", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 9.0F, 9.0F, 2.0F), PartPose.offset(-0.5F, -33.0F, 47.0F));
        partdefinition.addOrReplaceChild("shape10", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 15.0F, 13.0F), PartPose.offset(0.0F, -16.8F, -52.0F));
        partdefinition.addOrReplaceChild("shape49", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 5.0F), PartPose.offset(-1.0F, -33.5F, 49.0F));
        partdefinition.addOrReplaceChild("shape2", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 7.0F, 7.0F, 90.0F), PartPose.offset(0.5F, -16.0F, -110.0F));
        partdefinition.addOrReplaceChild("shape45", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 10.0F, 9.0F), PartPose.offset(-1.0F, -33.5F, 38.0F));
        partdefinition.addOrReplaceChild("shape26", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 2.0F), PartPose.offset(1.0F, -15.0F, 50.0F));
        partdefinition.addOrReplaceChild("shape34", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -5.0F, 0.0F, 10.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 15.1F, 99.0F, 0.5235988F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape17", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 40.5F));
        partdefinition.addOrReplaceChild("shape32", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -8.0F, 0.0F, 10.0F, 8.0F, 28.0F), PartPose.offsetAndRotation(-1.0F, 5.0F, 72.0F, -0.37175512F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape5", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -2.0F, -52.0F, 8.0F, 2.0F, 52.0F), PartPose.offsetAndRotation(0.0F, 2.8F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape7", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 17.0F, 4.0F), PartPose.offset(0.0F, -16.5F, -20.0F));
        partdefinition.addOrReplaceChild("shape21", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 8.0F), PartPose.offset(3.0F, 10.4F, 36.4F));
        partdefinition.addOrReplaceChild("shape11", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 71.0F), PartPose.offset(2.5F, -19.0F, -110.0F));
        partdefinition.addOrReplaceChild("shape15", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 12.0F, 28.0F), PartPose.offset(1.0F, 1.7F, 5.0F));
        partdefinition.addOrReplaceChild("shape27", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 12.0F), PartPose.offsetAndRotation(-1.0F, -6.0F, 67.0F, -0.34906584F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape35", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 8.0F), PartPose.offset(-1.0F, 5.0F, 94.0F));
        partdefinition.addOrReplaceChild("shape47", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 5.0F), PartPose.offset(7.5F, -31.0F, 18.0F));
        partdefinition.addOrReplaceChild("shape22", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 10.0F, 35.0F, 0.7853982F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("shape24", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 11.0F, 32.0F), PartPose.offset(-1.0F, -6.0F, 35.0F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Animation code for rotating the weapon (if handled directly by the model in a hand renderer) goes here.
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        // The root component cascades rendering down to all parts. 
        // Notice that the x-scaling for the three unique parts is executed seamlessly because we assigned `.xScale` values during class initialization.
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}