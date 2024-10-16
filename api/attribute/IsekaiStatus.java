package net.ledestudio.isekailife.api.attribute;

import org.jetbrains.annotations.NotNull;

public interface IsekaiStatus {

    /**
     * IsekaiAttribute 를 반환
     * @param attribute
     * @return {@link IsekaiAttribute}
     * @since 0.0.0
     */
    @NotNull IsekaiAttribute getAttribute(@NotNull Class<? extends IsekaiAttribute> attribute);

    /**
     * 스텟 데이터를 저장
     * @since 0.0.0
     */
    void save();
}
