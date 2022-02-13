package dev.satyrn.wolfarmor.util;

import net.minecraft.world.Difficulty;

/**
 * Options for processing hunger depletion and damage for tamed wolves.
 *
 * @author Isabel Maskrey
 * @since 4.0.0
 */
public enum WolfHungerSettings {
    /**
     * Wolf hunger is dependent on the world difficulty. This is the default option.
     * <p> <ul>
     * <li>{@link Difficulty#PEACEFUL}: Corresponds to {@link WolfHungerSettings#HEAL_ONLY}.</li>
     * <li>{@link Difficulty#EASY}: Corresponds to {@link WolfHungerSettings#DAMAGE_TO_HALF}.</li>
     * <li>{@link Difficulty#NORMAL} corresponds to {@link WolfHungerSettings#DAMAGE_TO_ABSOLUTE}.</li>
     * <li>{@link Difficulty#HARD} corresponds to {@link WolfHungerSettings#DAMAGE_TO_DEATH}.</li>
     * </ul>
     */
    DIFFICULTY_DEPENDANT,
    /**
     * Hunger is only used to heal a wolf.
     * <p>
     * A low-hunger wolf will not be damaged by hunger, and hunger does not deplete unless the wolf is
     *  currently healing.
     * This matches how player hunger works when world difficulty is set to {@link Difficulty#PEACEFUL}.
     */
    HEAL_ONLY,
    /**
     * Hunger can damage a wolf until they are at half-health.
     * <p>
     * Additionally, hunger depletes at a reduced rate.
     * This matches how player hunger works when the world difficulty is set to {@link Difficulty#EASY}.
     */
    DAMAGE_TO_HALF,
    /**
     * Hunger can damage a wolf until the wolf is at a half-heart.
     * <p>
     * This matches how player hunger works when the world difficulty is set to {@link Difficulty#NORMAL}.
     */
    DAMAGE_TO_ABSOLUTE,
    /**
     * Hunger can damage a wolf until they die from starvation.
     * <p>
     * Additionally, hunger depletes at an increased rate.
     * This matches how player hunger works when the world difficulty is set to {@link Difficulty#HARD}.
     */
    DAMAGE_TO_DEATH,
    /**
     * Hunger is not processed for wolves. Instead, they are healed by food directly.
     */
    DISABLED
}
