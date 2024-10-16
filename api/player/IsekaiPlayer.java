package net.ledestudio.isekailife.api.player;

import net.kyori.adventure.text.Component;
import net.ledestudio.isekailife.api.area.IsekaiArea;
import net.ledestudio.isekailife.api.attribute.IsekaiStatus;
import net.ledestudio.isekailife.api.currency.IsekaiAccount;
import net.ledestudio.isekailife.api.donation.IsekaiDonation;
import net.ledestudio.isekailife.api.inventory.IsekaiInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface IsekaiPlayer {

    /**
     * 플레이어의 UUID 를 반환
     * @return 플레이어 UUID
     * @since 0.0.0
     */
    @NotNull UUID getUniqueId();

    /**
     * 오프라인 플레이어를 반환
     * @return OfflinePlayer
     * @since 0.0.0
     */
    @NotNull OfflinePlayer getOfflinePlayer();

    /**
     * 온라인 플레이어를 반환
     * @return 플레이어가 온라인이라면 Player 반환, 그렇지 않다면 null
     * @since 0.0.0
     */
    @NotNull Optional<Player> getPlayer();

    /**
     * 플레이어에게 설정된 역할을 반환
     * @return 플레이어 역할 {@link Role}
     * @since 0.0.0
     * @see Role
     */
    @NotNull Role getRole();

    /**
     * 플레이어의 역할을 설정
     * @param role 플레이어 역할 {@link Role}
     * @since 0.0.0
     * @see Role
     */
    void setRole(@NotNull Role role);

    /**
     * 플레이어의 이름을 반환
     * @return 플레이어 이름
     * @since 0.0.0
     */
    @NotNull Component getName();

    /**
     * 플레이어의 이름을 설정
     * @param name 플레이어 이름 {@link Component}
     * @since 0.0.0
     */
    void setName(@NotNull Component name);

    @NotNull String getPrefix();

    void setPrefix(@NotNull String prefix);

    @NotNull String getPrefixColor();

    void setPrefixColor(@NotNull String color);

    @NotNull Component getColoredPrefix();

    /**
     * 특정 이펙트(버프) 를 적용
     * @param effect 적용할 포션 이펙트 {@link PotionEffect}
     * @since 0.0.0
     */
    void applyEffect(@NotNull PotionEffect effect);

    /**
     * 특정 이펙트(버프) 를 가지고있는지 반환
     * @param effect 적용된 포션 이펙트 {@link PotionEffect}
     * @since 0.0.0
     */
     boolean hasEffect(@NotNull PotionEffectType effect);

    /**
     * 적용된 특정 이펙트(버프) 를 제거
     * @param effect 제거할 포션 이펙트 {@link PotionEffectType}
     * @since 0.0.0
     */
    void removeEffect(@NotNull PotionEffectType effect);

    /**
     * 적용된 이펙트(버프) 를 모두 제거
     * @since 0.0.0
     */
    void clearEffect();

    /**
     * 플레이어가 접속했던 기록이 있는지 확인
     * @return 플레이어가 이전에 접속한 기록이 있다면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    boolean hasPlayedBefore();

    /**
     * 플레이어의 접속 기록을 설정
     * @param state true - 플레이어 접속 기록 추가, false - 플레이어 접속 기록 제거
     * @since 0.0.0
     */
    void setPlayedBefore(boolean state);

    /**
     * 플레이어가 온라인이라면 플레이어의 인벤토리를 가져온다
     * @return 온라인 플레이어의 인벤토리 {@link IsekaiPlayer}
     * @since 0.0.0
     */
    @NotNull IsekaiInventory getInventory();

    /**
     * 플레이어의 재화를 보환하는 어카운트를 반환
     * @return 플레이어 재화 어카운트 {@link IsekaiAccount}
     * @since 0.0.0
     */
    @NotNull IsekaiAccount getAccount();

    /**
     * 플레이어의 스테이터스를 반환
     * @return 플레이어 스테이터스 {@link IsekaiStatus}
     * @since 0.0.0
     */
    @NotNull IsekaiStatus getStatus();

    /**
     * 플레이어가 받은 후원 기록을 반환
     * @return 플레이어 후원 기록 {@link IsekaiDonation}
     * @since 0.0.0
     */
    @NotNull IsekaiDonation getDonation();

    /**
     * 플레이어가 위치한 지역이 존재한다면, 그 지역을 반환
     * @return 플레이어가 위치한 지역 {@link IsekaiAccount}
     * @since 0.0.0
     */
    @NotNull Optional<IsekaiArea> getArea();

    /**
     * 플레이어의 상태 정보를 저장한다
     */
    void save();

    /**
     * 플레이어의 상태 정보를 로드한다
     */
    void load();

    /**
     * 로드한 플레이어의 상태 정보를 플레이어에게 반영한다
     */
    void update();

    /**
     * 플레이어의 역할을 나타낸다
     * @since 0.0.0
     */
    enum Role {
        /**
         * 일반 유저
         * @since 0.0.0
         */
        USER("유저"),

        /**
         * 관리자
         * @since 0.0.0
         */
        MODERATOR("관리자"),

        /**
         * 관전자
         * @since 0.0.0
         */
        OBSERVER("관전자"),

        /**
         * 신관
         * @since 0.0.0
         */
        PRIEST("신관"),

        /**
         * 대신관
         * @since 0.0.0
         */
        HIGH_PRIEST("대신관");

        private final @NotNull String display;

        Role(@NotNull String display) {
            this.display = display;
        }

        /**
         * 플레이어 역할의 디스플레이 이름을 반환
         * @return 역할 이름
         * @since 0.0.0
         */
        public @NotNull String getDisplay() {
            return display;
        }
    }

}
