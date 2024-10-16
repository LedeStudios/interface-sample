package net.ledestudio.isekailife.api.npc;

import net.ledestudio.isekailife.api.player.IsekaiPlayer;
import org.jetbrains.annotations.NotNull;

public interface IsekaiNPC {

    /**
     * 특정 클래스를 상속받아 인스턴스를 생성
     * @return {@link IsekaiNPC}
     * @since 0.0.0
     */
    static @NotNull IsekaiNPC of(@NotNull String id, @NotNull String name, @NotNull String role) {
        return new IsekaiNpcImpl(id, name, role);
    }

    /**
     * NPC 의 ID 를 반환
     * @return {@link String}
     * @since 0.0.0
     */
    @NotNull String getId();

    /**
     * NPC 의 이름을 반환
     * @return {@link String}
     * @since 0.0.0
     */
    @NotNull String getName();

    /**
     * NPC 의 타입을 반환
     * @return {@link String}
     * @since 0.0.0
     * @see IsekaiPlayer.Role
     */
    @NotNull String getRole();

}

