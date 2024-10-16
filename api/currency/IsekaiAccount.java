package net.ledestudio.isekailife.api.currency;

import org.jetbrains.annotations.NotNull;

public interface IsekaiAccount {

    /**
     * IsekaiCurrency를 반환
     * @param type Currency 타입
     * @return Currency 인스턴스
     * @since 0.0.0
     */
    @NotNull IsekaiCurrency getCurrency(Class<? extends IsekaiCurrency> type);

    /**
     * 재화 데이터를 저장
     * @since 0.0.0
     */
    void save();

}
