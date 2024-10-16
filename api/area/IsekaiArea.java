package net.ledestudio.isekailife.api.area;

import net.ledestudio.isekailife.api.player.IsekaiPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface IsekaiArea {

    /**
     * 지역 인스턴스 생성
     * @param id 지역 ID
     * @param name 지역 이름
     * @param boundingBox 지역 범위
     * @return IsekaiArea
     */
    static @NotNull IsekaiArea of(@NotNull String id, @NotNull String name, @NotNull IsekaiBoundingBox boundingBox) {
        return new IsekaiAreaImpl(id, name, null, boundingBox);
    }

    /**
     * 지역 인스턴스 생성
     * @param id 지역 ID
     * @param name 지역 이름
     * @param owner 지역 주인
     * @param boundingBox 지역 범위
     * @return IsekaiArea
     */
    static @NotNull IsekaiArea of(@NotNull String id, @NotNull String name, @Nullable UUID owner, @NotNull IsekaiBoundingBox boundingBox) {
        return new IsekaiAreaImpl(id, name, owner, boundingBox);
    }

    /**
     * 지역의 IsekaiBoundingBox를 반환
     *
     * @return 지역의 IsekaiBoundingBox
     * @since 0.0.0
     */
    @NotNull IsekaiBoundingBox getBoundingBox();

    /**
     * 지역의 ID를 반환
     *
     * @return 지역의 ID
     * @since 0.0.0
     */
    @NotNull String getId();

    /**
     * 지역의 이름을 반환
     *
     * @return 지역의 이름
     * @since 0.0.0
     */
    @NotNull String getName();

    /**
     * 지역의 소유자 플레이어를 반환
     *
     * @return 지역의 소유자
     * @since 0.0.0
     */
    @NotNull Optional<IsekaiPlayer> getOwner();
}

