package net.ledestudio.isekailife.api.donation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface IsekaiDonation {

    /**
     * 플레이어 ID를 반환
     * @return 플레이어 ID
     */
    @NotNull UUID getPlayer();

    /**
     * 플레이어의 데이터를 해당 치지직 아이디랑 연동
     * @param id 치지직 아이디
     * @since 0.0.0
     */
    void setChzzkAccount(@NotNull String id);

    /**
     * 플레이어의 연동된 치지직 아이디를 반환
     * @return 치지직 아이디
     * @since 0.0.0
     */
    @NotNull String getChzzkAccount();

    /**
     * 플레이어의 데이터를 아프리카 tv 아이디와 연동
     * @param id 아프리카 tv 아이디
     * @since 0.0.0
     */
    void setAfreecaTvAccount(@NotNull String id);

    /**
     * 플레이어의 연동된 아프리카 tv 아이디를 반환
     * @return 아프리카 tv 아이디
     * @since 0.0.0
     */
    @NotNull String getAfreecaTvAccount();

    /**
     * 플레이어의 데이터를 투네이션과 API KEY 와 연동
     * @param id 투네이션 API KEY
     * @since 0.0.0
     */
    void setToonationAccount(@NotNull String id);

    /**
     * 플레이어의 연동된 투네이션 API KEY 를 반환
     * @return 투네이션 API KEY
     * @since 0.0.0
     */
    @NotNull String getToonationAccount();

    /**
     * 치지직 도네이션 크롤러 반환
     * @return Crawler
     * @since 0.0.0
     */
    @Nullable Crawler getChzzkCrawler();

    /**
     * 아프리카 tv 도네이션 크롤러 반환
     * @return Crawler
     * @since 0.0.0
     */
    @Nullable Crawler getAfreecaCrawler();

    /**
     * 투네이션 도네이션 크롤러 반환
     * @return Crawler
     * @since 0.0.0
     */
    @Nullable Crawler getToonationCrawler();

    /**
     * 방송 크롤러 상태 업데이트
     */
    void updateCrawler();

    /**
     * 방송 크롤러 종료
     */
    void stopCrawler();

    /**
     * 후원된 금액을 반환
     * @return 후원 합산 금액
     * @since 0.0.0
     */
    int getAmount();

    /**
     * 후원 금액 설정
     * @param amount 후원 금액
     * @since 0.0.0
     */
    void setAmount(int amount);

    /**
     * 데이터를 데이터베이스에서 로드
     */
    void load();

    /**
     * 데이터를 데이터베이스에 저장
     */
    void save();

}
