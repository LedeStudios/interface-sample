package net.ledestudio.isekailife.api.inventory;

import net.ledestudio.isekailife.api.item.IsekaiItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IsekaiInventory {

    /**
     * 플레이어가 온라인이라면 플레이어가 해당 아이템을 가지고 있는지 확인
     * @param item 보유중인지 파악할 아이템
     * @return 아이템을 보유중이라면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    boolean hasItem(@NotNull IsekaiItem item);

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리에 해당 아이템의 갯수를 반환
     * @param item 개수를 파악할 아이템
     * @return 아이템 개수
     * @since 0.0.0
     */
    int getItemAmount(@NotNull IsekaiItem item);

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리에서 해당 아이템을 제거
     * @param item 제거할 아이템
     * @since 0.0.0
     */
    void removeItem(@NotNull IsekaiItem item);

    void removeItem(@NotNull IsekaiItem item, int amount);

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리의 해당 아이템을 모두 제거
     * @param item 제거할 아이템
     */
    void clearItem(@NotNull IsekaiItem item);

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리에 빈공간이 있는지 확인한다.
     * @return 플레이어 인벤토리에 빈 공간이 존재한다면 true, 그렇지 않다면 false
     * @since 0.0.0
     */
    boolean hasEmptySpace();

    /**
     * 플레이어 인벤토리에 아이템을 추가한다. 플레이어가 오프라인이라면 추가하지 않는다.
     * @param items 인벤토리에 추가할 아이템 Array
     * @since 0.0.0
     */
    void addItem(@NotNull IsekaiItem... items);

    void addItem(@NotNull IsekaiItem item, int amount);

    /**
     * 플레이어가 온라인이라면 플레이어의 인벤토리에 아이템을 추가하고, 아이템을 모두 추가하지 못했다면 우편함으로 전송
     * <p>플레이어가 오프라인이라면 우편함으로 아이템 전송
     * @param items 인벤토리에 추가할 아이템 Array
     * @since 0.0.0
     */
    void addItemOrSendMail(@NotNull IsekaiItem... items);

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리의 모든 아이템을 반환
     * @return 플레이어 인벤토리에 존재하는 아이템 List
     * @since 0.0.0
     */
    @NotNull List<IsekaiItem> getContents();

    /**
     * 플레이어가 온라인이라면 플레이어 인벤토리를 비움
     * @since 0.0.0
     */
    void clear();

    /**
     * 플레이어 우편함을 반환
     * @return 우편함 {@link IsekaiMailBox}
     * @since 0.0.0
     */
    @NotNull IsekaiMailBox getMailBox();

}
