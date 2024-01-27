package factories;

import models.SlotAssignmentStrategyType;
import strategies.RandomSlotAssignmentStrategy;
import strategies.SlotAssignmentStrategy;

public class SlotAssingmentStrategyFactory {
    public static RandomSlotAssignmentStrategy getSlotForType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
        return new RandomSlotAssignmentStrategy();
    }
}
