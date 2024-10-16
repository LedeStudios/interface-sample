package net.ledestudio.isekailife.api.item;


import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IsekaiItem {

    /**
     * ItemStack을 사용하여 IsekaiItem 인스턴스를 생성
     * @param item Minecraft ItemStack 객체
     * @return IsekaiItem 인스턴스
     * @since 0.0.0
     */
    static @NotNull IsekaiItem of(@NotNull ItemStack item) {
        return new IsekaiItemImpl(item);
    }

    /**
     * ItemsAdder ID를 사용하여 IsekaiItem 인스턴스를 생성
     * @param id ItemsAdder에 등록된 아이템의 ID
     * @return IsekaiItem 인스턴스
     * @since 0.0.0
     */
    static @NotNull IsekaiItem of(@NotNull String id) {
        return new IsekaiItemImpl(id);
    }

    /**
     * Material을 사용하여 IsekaiItem 인스턴스를 생성
     * @param type Minecraft Material 타입
     * @return IsekaiItem 인스턴스
     * @since 0.0.0
     */
    static @NotNull IsekaiItem of(@NotNull Material type) {
        return new IsekaiItemImpl(new ItemStack(type));
    }

    /**
     * 아이템 데이터 전체를 사용하여 인스턴스를 생성
     * @param id 아이템 아이디
     * @param item 아이템
     * @return IsekaiItem 인스턴스
     * @since 0.0.0
     */
    static @NotNull IsekaiItem of(@NotNull String id, @NotNull ItemStack item) {
        return new IsekaiItemImpl(id, item);
    }

    /**
     * 아이템이 ItemsAdder에 등록되어 있는지 확인
     * @return ID가 ItemsAdder에 등록되어 있다면 true, 그렇지 않으면 false
     * @since 0.0.0
     */
    boolean isRegistered();

    /**
     * ItemsAdder에 등록된 아이템의 ID가 존재한다면 ID를 반환
     * @return 아이템 ID
     * @since 0.0.0
     */
    @NotNull Optional<String> getId();

    /**
     * 아이템에 특정 태그를 설정
     * @param key 태그의 NamespacedKey
     * @param type 태그의 데이터 타입
     * @param value 태그의 값
     * @since 0.0.0
     */
    <T, V> void setTag(@NotNull NamespacedKey key, @NotNull PersistentDataType<T, V> type, @NotNull V value);

    /**
     * 아이템에 설정된 특정 태그 값을 반환
     * @param key 태그의 NamespacedKey
     * @param type 태그의 데이터 타입
     * @return 태그의 값
     * @since 0.0.0
     */
    <T, V> V getTag(@NotNull NamespacedKey key, @NotNull PersistentDataType<T, V> type);

    /**
     * 아이템이 특정 태그를 가지고 있는지 확인
     * @param key 태그의 NamespacedKey
     * @return 태그가 존재한다면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    boolean hasTag(@NotNull NamespacedKey key);

    /**
     * 아이템이 특정 타입의 태그를 갖고 있는지 확인
     * @param key 태그의 NamespacedKey
     * @param type 태그의 데이터 타입
     * @return 태그가 존재한다면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    <T, V> boolean hasTag(@NotNull NamespacedKey key, @NotNull PersistentDataType<T, V> type);

    /**
     * 아이템의 이름을 설정
     * @param name 설정할 아이템의 이름
     * @since 0.0.0
     */
    void setName(@NotNull Component name);

    /**
     * 아이템의 이름을 반환
     * @return 아이템 이름
     * @since 0.0.0
     */
    @NotNull Component getName();

    /**
     * 아이템의 설명을 설정
     * @param lore 설정할 아이템의 설명 List
     * @since 0.0.0
     */
    void setLore(@NotNull List<Component> lore);

    /**
     * 아이템의 설명을 반환
     * @return 아이템 설명 List
     * @since 0.0.0
     */
    @NotNull List<Component> getLore();

    /**
     * 아이템에 특정 인첸트를 부여
     * @param enchantment 설정할 인챈트
     * @since 0.0.0
     */
    void setEnchantment(@NotNull IsekaiEnchantment enchantment);

    /**
     * 아이템의 특정 인첸트를 제거
     * @param enchantment 제거할 인챈트
     * @since 0.0.0
     */
    void removeEnchantment(@NotNull IsekaiEnchantment enchantment);

    /**
     * 아이템이 특정 인첸트를 가지고 있는지 확인
     * @param enchantment 확인할 인챈트
     * @return 인챈트 존재 여부
     * @since 0.0.0
     */
    boolean hasEnchantment(@NotNull IsekaiEnchantment enchantment);

    /**
     * 아이템에 부여된 인첸트들을 반환
     * @return 아이템의 인챈트 목록 Set
     * @since 0.0.0
     */
    @NotNull Set<IsekaiEnchantment> getEnchantments();

    /**
     * 아이템에 부여된 모든 인첸트를 제거
     * @since 0.0.0
     */
    void clearEnchantments();

    /**
     * 아이템의 내구도를 설정
     * @param durability 설정할 내구도 값
     * @since 0.0.0
     */
    void setDurability(@Range(from = 0, to = Integer.MAX_VALUE) int durability);

    /**
     * 아이템이 부서지지 않게 설정
     * @param unbreakable 부서지지 않게 설정 여부
     * @since 0.0.0
     */
    void setUnbreakable(boolean unbreakable);

    /**
     * 아이템의 내구도를 반환
     * @return 현재 아이템의 내구도가 존재한다면 그 값을 반환, 그렇지 않다면 -1을 반환
     * @since 0.0.0
     */
    @Range(from = -1, to = Integer.MAX_VALUE) int getDurability();

    /**
     * 아이템의 개수를 설정
     * @param amount 아이템 개수
     * @since 0.0.0
     */
    void setAmount(@Range(from = 0, to = Integer.MAX_VALUE) int amount);

    /**
     * 아이템에 플래그를 설정
     * @param flags 설정할 아이템 플래그 Array
     * @since 0.0.0
     */
    void setFlags(@NotNull ItemFlag... flags);

    /**
     * 아이템이 해당 플래그를 가지고 있는지 확인
     * @param flag 확인할 아이템 플래그
     * @return 플래그를 보유하고 있다면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    boolean hasFlag(@NotNull ItemFlag flag);

    /**
     * 아이템의 플래그들을 반환
     * @return 아이템의 플래그 목록 Set
     * @since 0.0.0
     */
    Set<ItemFlag> getFlags();

    /**
     * 아이템의 해당 플래그를 제거
     * @param flags 제거할 아이템 플래그들
     * @since 0.0.0
     */
    void removeFlags(@NotNull ItemFlag... flags);

    /**
     * 아이템에 설정된 플래그 들을 제거
     * @since 0.0.0
     */
    void clearFlags();

    /**
     * ItemStack을 생성하여 반환
     * @return 생성 ItemStack 객체
     * @since 0.0.0
     */
    @NotNull ItemStack toItemStack();

    /**
     * IsekaiItem 객체를 복제
     * @return 복제된 IsekaiItem 인스턴스
     * @since 0.0.0
     */
    @NotNull IsekaiItem copy();

}