package net.ledestudio.isekailife.api.inventory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IsekaiMailBox {

    /**
     * 우편함에 있는 해당 메일을 수령
     * @param index 수령할 메일 번호
     * @since 0.0.0
     */
    void receiveMail(int index);

    /**
     * 우편함에 있는 모든 메일을 수령
     * @since 0.0.0
     */
    void receiveMails();

    /**
     * 우편함에 마지막에 새로운 우편을 추가
     * @param mail 추가할 우편
     * @since 0.0.0
     */
    void addLast(@NotNull IsekaiMail mail);

    /**
     * 우편함에 처음에 새로운 우편을 추가
     * @param mail 추가할 우편
     * @since 0.0.0
     */
    void addFirst(@NotNull IsekaiMail mail);

    /**
     * 해당 우편을 반환
     * @param index 우편 번호
     * @return 인덱스에 해당하는 우편
     * @see IsekaiMail
     * @since 0.0.0
     */
    @NotNull IsekaiMail getMail(int index);

    /**
     * 우편함의 모든 메일을 반환
     * @return 우편함의 모든 메일
     * @see IsekaiMail
     * @since 0.0.0
     */
    @NotNull List<IsekaiMail> getMails();

    /**
     * 우편함 저장
     * @since 0.0.0
     */
    void save();

    /**
     * 우편함 로드
     * @since 0.0.0
     */
    void load();

}
