package com.r4l.snipers_mini_game.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class ModelFRF2 extends Model {
    private final ModelPart root;

    public ModelFRF2(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
        
        // NeoForge 1.21.1 supports native scaling, removing the need for manual GL11 hacks in render()!
        this.root.getChild("Shape25").xScale = 0.99f;
        this.root.getChild("Shape28").xScale = 0.99f;
        this.root.getChild("Shape37").xScale = 1.1f;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("Shape40", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 40.0F), PartPose.offset(2.0F, -21.0F, 4.0F));
        root.addOrReplaceChild("Shape20", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(3.0F, 11.4F, 44.4F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape33", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F), PartPose.offset(-1.0F, 14.1F, 98.1F));
        root.addOrReplaceChild("Shape16", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -1.0F, 0.0F, 7.0F, 7.0F, 30.0F), PartPose.offsetAndRotation(0.5F, 11.0F, 4.0F, -0.05235988F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape44", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 7.0F, 7.0F, 39.0F), PartPose.offset(0.5F, -32.0F, -1.0F));
        root.addOrReplaceChild("Shape43", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 6.0F), PartPose.offset(3.5F, -25.0F, 27.0F));
        root.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 48.0F), PartPose.offset(1.0F, -16.0F, -16.0F));
        root.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F), PartPose.offset(3.5F, -20.0F, -44.0F));
        root.addOrReplaceChild("Shape39", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -4.0F, 10.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 10.9F, 150.2F, -0.7807508F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape50", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 5.0F), PartPose.offset(-1.0F, -24.5F, 49.0F));
        root.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -14.0F, -52.0F, 7.0F, 13.0F, 52.0F), PartPose.offsetAndRotation(0.5F, 2.7F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 18.0F), PartPose.offset(0.0F, -17.0F, 32.0F));
        root.addOrReplaceChild("Shape23", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 44.84F));
        root.addOrReplaceChild("Shape51", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 1.0F, 8.0F, 5.0F), PartPose.offset(-1.0F, -32.5F, 49.0F));
        root.addOrReplaceChild("Shape18", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 8.0F, 41.5F, -0.34906584F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape25", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 36.0F), PartPose.offset(-1.0F, -2.0F, 67.0F));
        root.addOrReplaceChild("Shape42", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 6.0F), PartPose.offset(3.5F, -25.0F, 1.0F));
        root.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 5.0F, 19.0F), PartPose.offset(0.0F, -6.0F, -39.0F));
        root.addOrReplaceChild("Shape46", CubeListBuilder.create().texOffs(0, 100).addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F), PartPose.offset(1.75F, -33.5F, 18.0F));
        root.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 19.0F), PartPose.offset(1.0F, -15.5F, -129.0F));
        root.addOrReplaceChild("Shape19", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 35.0F));
        root.addOrReplaceChild("Shape28", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 12.0F), PartPose.offsetAndRotation(-1.0F, -2.0F, 92.0F, 0.6981317F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape29", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 22.0F, 46.0F), PartPose.offset(-1.0F, -9.7F, 101.2F));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 12.0F), PartPose.offset(0.0F, -17.0F, 0.0F));
        root.addOrReplaceChild("Shape41", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 45.0F), PartPose.offset(3.0F, -23.0F, 1.0F));
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 15.0F, 50.0F), PartPose.offsetAndRotation(0.0F, -13.4F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape37", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 13.0F, 27.0F), PartPose.offset(-1.75F, -10.0F, 110.5F));
        root.addOrReplaceChild("Shape52", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 1.0F, 8.0F, 5.0F), PartPose.offset(8.0F, -32.5F, 49.0F));
        root.addOrReplaceChild("Shape31", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 9.0F, 21.0F, 13.0F), PartPose.offsetAndRotation(-0.5F, 11.0F, 46.0F, 0.34906584F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape30", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 9.0F, 6.0F, 13.0F), PartPose.offset(-0.5F, 5.0F, 46.0F));
        root.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F), PartPose.offset(3.5F, -19.5F, -109.0F));
        root.addOrReplaceChild("Shape36", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, -5.0F, -1.0F, 10.0F, 5.0F, 45.0F), PartPose.offsetAndRotation(-1.0F, 13.1F, 102.4F, -0.08726646F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape38", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 4.0F), PartPose.offset(-1.0F, 10.9F, 146.2F));
        root.addOrReplaceChild("Shape48", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 9.0F, 9.0F, 2.0F), PartPose.offset(-0.5F, -33.0F, 47.0F));
        root.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 15.0F, 13.0F), PartPose.offset(0.0F, -16.8F, -52.0F));
        root.addOrReplaceChild("Shape49", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 5.0F), PartPose.offset(-1.0F, -33.5F, 49.0F));
        root.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 7.0F, 7.0F, 90.0F), PartPose.offset(0.5F, -16.0F, -110.0F));
        root.addOrReplaceChild("Shape45", CubeListBuilder.create().texOffs(0, 200).addBox(0.0F, 0.0F, 0.0F, 10.0F, 10.0F, 9.0F), PartPose.offset(-1.0F, -33.5F, 38.0F));
        root.addOrReplaceChild("Shape26", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 6.0F, 2.0F), PartPose.offset(1.0F, -15.0F, 50.0F));
        root.addOrReplaceChild("Shape34", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -5.0F, 0.0F, 10.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 15.1F, 99.0F, 0.5235988F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape17", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(3.0F, 4.0F, 40.5F));
        root.addOrReplaceChild("Shape32", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -8.0F, 0.0F, 10.0F, 8.0F, 28.0F), PartPose.offsetAndRotation(-1.0F, 5.0F, 72.0F, -0.37175512F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, -2.0F, -52.0F, 8.0F, 2.0F, 52.0F), PartPose.offsetAndRotation(0.0F, 2.8F, 0.0F, -0.08726646F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 8.0F, 17.0F, 4.0F), PartPose.offset(0.0F, -16.5F, -20.0F));
        root.addOrReplaceChild("Shape21", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 8.0F), PartPose.offset(3.0F, 10.4F, 36.4F));
        root.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 71.0F), PartPose.offset(2.5F, -19.0F, -110.0F));
        root.addOrReplaceChild("Shape15", CubeListBuilder.create().texOffs(350, 0).addBox(0.0F, 0.0F, 0.0F, 6.0F, 12.0F, 28.0F), PartPose.offset(1.0F, 1.7F, 5.0F));
        root.addOrReplaceChild("Shape27", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 12.0F), PartPose.offsetAndRotation(-1.0F, -6.0F, 67.0F, -0.34906584F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape35", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 8.0F), PartPose.offset(-1.0F, 5.0F, 94.0F));
        root.addOrReplaceChild("Shape47", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 5.0F), PartPose.offset(7.5F, -31.0F, 18.0F));
        root.addOrReplaceChild("Shape22", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 10.0F, 35.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Shape24", CubeListBuilder.create().texOffs(0, 400).addBox(0.0F, 0.0F, 0.0F, 10.0F, 11.0F, 32.0F), PartPose.offset(-1.0F, -6.0F, 35.0F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}