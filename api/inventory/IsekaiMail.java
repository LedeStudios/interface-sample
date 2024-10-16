package net.ledestudio.isekailife.api.inventory;

import net.ledestudio.isekailife.api.item.IsekaiItem;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface IsekaiMail {

    /**
     * IsekaiItem 을 사용하여 인스턴스를 생성
     * @return IsekaiMail
     * @see IsekaiItem
     * @since 0.0.0
     */
    static @NotNull IsekaiMail of(@NotNull String id, @NotNull IsekaiItem item) {
        return new IsekaiMailImpl(id, item);
    }

    static @NotNull IsekaiMail of(@NotNull IsekaiItem item) {
        return new IsekaiMailImpl(UUID.randomUUID().toString(), item);
    }

    static @NotNull IsekaiMail of(@NotNull String id, @NotNull IsekaiItem item, @NotNull ZonedDateTime date) {
        return new IsekaiMailImpl(id, item, date);
    }

    /**
     * 우편의 아이템을 반환
     * @return IsekaiItem
     * @see IsekaiItem
     * @since 0.0.0
     */
    @NotNull IsekaiItem getItem();

    /**
     * 우편의 아이템 아이디를 반환
     * @return String
     * @since 0.0.0
     */
    @NotNull String getId();

    /**
     * 우편의 생성 시각을 반환
     * @return ZonedDateTime
     * @since 0.0.0
     */
    @NotNull ZonedDateTime getDate();
}
