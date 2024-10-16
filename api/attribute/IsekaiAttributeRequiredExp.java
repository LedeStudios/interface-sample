package net.ledestudio.isekailife.api.attribute;

import org.jetbrains.annotations.Range;

public interface IsekaiAttributeRequiredExp {

    double getRequiredExp(@Range(from = 0, to = Long.MAX_VALUE) int level);

}
