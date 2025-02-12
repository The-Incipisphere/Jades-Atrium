package io.thedogofchaos.jadesatrium.organic;

import lombok.Getter;
import lombok.NonNull;

/**
 * Enum representing crop texture sets.
 * Provides predefined, constant values to prevent invalid or inconsistent texture references.
 */
public enum CropTextures {

    /**
     * Debug textures.
     * Crops generated with this texture set during data generation will display their growth stage numbers
     * (from 0 through 7) as part of their visible model, useful for debugging and testing purposes.
     */
    DEBUG("debug");

    @Getter
    private final String textureSetName;

    /**
     * Constructs a crop texture enum value.
     * The provided texture set name must be valid for use as a part of a {@link net.minecraft.resources.ResourceLocation}.
     *
     * @param textureSetName The name of the texture set.
     *                       This <b>MUST</b> adhere to the constraints for paths in a ResourceLocation.
     * @see net.minecraft.resources.ResourceLocation#isAllowedInResourceLocation(char)
     */
    CropTextures(@NonNull String textureSetName) {
        this.textureSetName = textureSetName;
    }
}
