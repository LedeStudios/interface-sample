package net.ledestudio.isekailife.api.item;

import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public interface IsekaiEnchantment {

    /**
     * Bukkit Enchantment 객체를 반환
     * @return Enchantment
     */
    @NotNull
    Enchantment enchantment();

    /**
     * 인첸트 레벨을 반환
     * @return 인첸트 레벨
     */
    int level();

    /**
     * 두 인첸트를 비교
     * @param other 비교할 인첸트
     * @return 동일 여부
     */
    boolean equals(Object other);

    /**
     * 인첸트의 해시코드를 반환
     * @return 해시코드
     */
    int hashCode();
}