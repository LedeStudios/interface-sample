package net.ledestudio.isekailife.api.server;

import net.ledestudio.isekailife.api.area.IsekaiAreaManager;
import net.ledestudio.isekailife.api.db.IsekaiGson;
import net.ledestudio.isekailife.api.npc.IsekaiNpcManager;
import net.ledestudio.isekailife.api.player.IsekaiPlayer;
import net.ledestudio.isekailife.box.IsekaiBoxManager;
import net.ledestudio.isekailife.secondproduce.recipe.IsekaiRecipeManager;
import net.ledestudio.isekailife.secondproduce.seconditem.IsekaiSecondItemManager;
import net.ledestudio.isekailife.store.shop.IsekaiShopManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface IsekaiServer {

    /**
     * UUID를 기반으로 플레이어를 생성하여 반환
     * @param uuid 플레이어 ID
     * @return IsekaiPlayer
     * @since 0.0.0
     * @see <a href="https://ko.namemc.com/profile/DoublePointer">UUID 확인 사이트</a>에서 플레이어 닉네임으로 UUID 조회 가능
     * @see IsekaiPlayer
     */
    @NotNull IsekaiPlayer getPlayer(@NotNull UUID uuid);

    /**
     * Json 기반의 데이터 를 저장 및 로드
     * @return IsekaiGson
     * @since 0.0.0
     * @see IsekaiGson
     */
    @NotNull IsekaiGson getIsekaiGson();

    /**
     * 지역 관리 매니저 반환
     * @return 지역 관리 매니저
     * @since 0.0.0
     */
    @NotNull IsekaiAreaManager getIsekaiAreaManager();

    /**
     * 박스 매니저 반환
     * @return 박스 매니저
     * @since 0.0.0
     */
    @NotNull IsekaiBoxManager getIsekaiBoxManager();

    /**
     * NPC 관리 매니저 반환
     * @return NPC 관리 매니저
     * @since 0.0.0
     */
    @NotNull IsekaiNpcManager getIsekaiNpcManager();

    @NotNull IsekaiShopManager getShopManager();

    @NotNull IsekaiRecipeManager getRecipeManager();

    @NotNull IsekaiSecondItemManager getSecondItemManager();

}
