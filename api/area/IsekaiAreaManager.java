package net.ledestudio.isekailife.api.area;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface IsekaiAreaManager {

    /**
     * 데이터베이스에서 지역 목록을 로드합니다.
     */
    void load();

    /**
     * ID를 기반으로 등록된 지역이 있는지 확인 후 지역을 반환합니다.
     * @param id 지역 ID
     * @return IsekaiArea
     */
    @NotNull Optional<IsekaiArea> getAreaById(@NotNull String id);

    /**
     * 특정 좌표에 지역이 존재한다면 반환합니다.
     * @param location 지역이 존재하는지 확인할 좌표
     * @return IsekaiArea
     */
    @NotNull Set<IsekaiArea> getAreasByLocation(@NotNull Location location);

}
