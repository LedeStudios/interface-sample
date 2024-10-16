package net.ledestudio.isekailife.api.currency;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class IsekaiCurrency {

    private transient final @NotNull String id;
    private transient final @NotNull String name;
    protected transient UUID owner;

    protected IsekaiCurrency(@NotNull String id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 재화의 고유한 ID를 반환
     * @return 재화 ID
     */
    public final @NotNull String getId() {
        return id;
    }

    /**
     * 재화의 이름을 반환
     * @return 재화 이름
     */
    public final @NotNull String getName() {
        return name;
    }

    /**
     * 해당 재화를 소유한 오너 설정
     * @param owner 재화 오너
     */
    public final void setOwner(@NotNull UUID owner) {
        this.owner = owner;
    }

    /**
     * 해당 재화를 소유한 오너를 반환
     * @return 재화 오너
     */
    public final @NotNull UUID getOwner() {
        return owner;
    }

    /**
     * 플레이어의 재화를 반환
     * @return 보유중인 재화
     * @since 0.0.0
     */
    public abstract double get();

    /**
     * 플레이어가 파라메터 만큼의 재화를 보유중인지 확인
     * @param amount 확인할 재화의 양
     * @return 재화를 보유중이라면 true, 그렇지 않아면 false
     * @since 0.0.0
     */
    public abstract boolean has(double amount);

    /**
     * 플레이어에게 파라메터 만큼의 재화를 지급
     * @param amount 지급할 재화
     * @since 0.0.0
     */
    public abstract void add(double amount);

    /**
     * 플레이어로부터 파라메터 만큼의 재화를 제거
     * @param amount 제거할 재화
     * @since 0.0.0
     */
    public abstract void remove(double amount);

    /**
     * 플레이어의 재화를 파라메터의 값으로 설정
     * @param amount 재화 값
     * @since 0.0.0
     */
    public abstract void set(double amount);
}
