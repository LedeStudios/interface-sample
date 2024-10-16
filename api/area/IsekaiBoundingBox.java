package net.ledestudio.isekailife.api.area;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * 바운딩 박스의 인터페이스입니다. 바운딩 박스는 두 위치를 기준으로 영역을 정의합니다.
 */
public interface IsekaiBoundingBox {

    /**
     * 원점 Location을 받아 size x size x size 크기의 바운딩 박스를 생성합니다.
     * <p>입력한 원점을 기준으로 size / 2 크기만큼 이동한 좌표 2개를 선택하여 바운딩 박스를 생성합니다.
     * <p>size / 2 의 소숫점은 버립니다.
     *
     * @param location 중심 위치
     * @return 생성된 IsekaiBoundingBox 객체
     * @since 0.0.0
     */
    static @NotNull IsekaiBoundingBox of(@NotNull Location location, @Range(from = 0, to = Integer.MAX_VALUE) int size) {
        return new IsekaiBoundingBoxImpl(location, size);
    }

    /**
     * 두 개의 좌표를 입력받아 바운딩 박스를 생성합니다.
     * @param first 첫번째 좌표
     * @param second 두번째 좌표
     * @return IsekaiBoundingBox 인스턴스
     * @since 0.0.0
     */
    static @NotNull IsekaiBoundingBox of(@NotNull Location first, @NotNull Location second) {
        return new IsekaiBoundingBoxImpl(first, second);
    }

    /**
     * 바운딩 박스의 첫 번째 위치를 반환합니다.
     *
     * @return 첫 번째 위치
     * @since 0.0.0
     */
    @NotNull Location getFirstLocation();

    /**
     * 바운딩 박스의 두 번째 위치를 반환합니다.
     *
     * @return 두 번째 위치
     * @since 0.0.0
     */
    @NotNull Location getSecondLocation();

    /**
     * 특정 위치가 바운딩 박스 내에 있는지 확인합니다.
     *
     * @param location 확인할 위치
     * @return 바운딩 박스 내에 있으면 true, 그렇지 않으면 false
     * @since 0.0.0
     */
    boolean contains(@NotNull Location location);
}
