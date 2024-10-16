package net.ledestudio.isekailife.api;

import net.ledestudio.isekailife.api.area.IsekaiAreaManager;
import net.ledestudio.isekailife.api.db.IsekaiGson;
import net.ledestudio.isekailife.api.npc.IsekaiNpcManager;
import net.ledestudio.isekailife.api.player.IsekaiPlayer;
import net.ledestudio.isekailife.api.server.IsekaiServer;
import net.ledestudio.isekailife.api.server.IsekaiServerImpl;
import net.ledestudio.isekailife.box.IsekaiBoxManager;
import net.ledestudio.isekailife.secondproduce.recipe.IsekaiRecipeManager;
import net.ledestudio.isekailife.secondproduce.seconditem.IsekaiSecondItemManager;
import net.ledestudio.isekailife.store.shop.IsekaiShopManager;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;

public class Isekai {

    private static final @NotNull IsekaiServer server = new IsekaiServerImpl();

    /**
     * UUID를 기반으로 플레이어 인스턴스 반환
     * @param uuid 플레이어 ID
     * @return IsekaiPlayer
     * @since 0.0.0
     * @see <a href="https://ko.namemc.com/profile/DoublePointer">UUID 확인 사이트</a>에서 플레이어 닉네임으로 UUID 조회 가능
     * @see IsekaiPlayer
     */
    public static @NotNull IsekaiPlayer getPlayer(@NotNull UUID uuid) {
        return server.getPlayer(uuid);
    }

    /**
     * Json 기반의 데이터 를 저장 및 로드
     * @return IsekaiGson
     * @since 0.0.0
     * @see IsekaiGson
     */
    public static @NotNull IsekaiGson getIsekaiGson() {
        return server.getIsekaiGson();
    }

    /**
     * 지역 관리 매니저 반환
     * @return IsekaiAreaManager
     * @since 0.0.0
     */
    public static @NotNull IsekaiAreaManager getAreaManager() {
        return server.getIsekaiAreaManager();
    }

    /**
     * NPC 관리 매니저 반환
     * @return IsekaiNpcManager
     * @since 0.0.0
     */
    public static @NotNull IsekaiNpcManager getNpcManager() {
        return server.getIsekaiNpcManager();
    }

    public static @NotNull IsekaiShopManager getShopManager() {
        return server.getShopManager();
    }

    public static @NotNull IsekaiRecipeManager getRecipeManager() {
        return server.getRecipeManager();
    }

    public static @NotNull IsekaiSecondItemManager getSecondItemManager() {
        return server.getSecondItemManager();
    }

    /**
     * 박스 매니저 반환
     * @return IsekaiBoxManager
     */
    public static @NotNull IsekaiBoxManager getBoxManger() {
        return server.getIsekaiBoxManager();
    }

    /**
     * 로거 반환
     * @return 로거
     * @since 0.0.0
     */
    public static @NotNull Logger getLogger() {
        return Logger.getLogger("Isekai");
    }

    /**
     * 데이터 폴더 경로 반환
     * @return Path
     */
    public static @NotNull Path getDataPath() {
        return Path.of("./isekai/data");
    }

    /**
     * 콘피그 폴더 경로 반환
     * @return Path
     */
    public static @NotNull Path getConfigPath() {
        return Path.of("./isekai/config");
    }

}
