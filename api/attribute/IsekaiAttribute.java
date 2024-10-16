package net.ledestudio.isekailife.api.attribute;

import net.ledestudio.isekailife.api.db.ConfiguratorException;
import net.ledestudio.isekailife.api.db.data.IsekaiAttributeConfigurator;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;

import java.nio.file.Path;
import java.util.UUID;

public class IsekaiAttribute {

    private String id;
    private String name;
    private transient Path path;
    protected transient UUID owner;

    private int level;
    private double exp;

    private transient IsekaiAttributeRequiredExp requiredExpHolder;

    public IsekaiAttribute() {
        id = "test";
        name = "테스트";
        level = 0;
        exp = 0;
    }

    protected IsekaiAttribute(@NotNull String id, @NotNull String name, @NotNull Path path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public void setRequiredExpHolder(@NotNull IsekaiAttributeRequiredExp requiredExpHolder) {
        this.requiredExpHolder = requiredExpHolder;
    }

    /**
     * 해당 어트리뷰트를 보유한 오너를 설정한다
     * @param owner 오너 UUID
     */
    public final void setOwner(@NotNull UUID owner) {
        this.owner = owner;
    }

    /**
     * 해당 어트리뷰트를 보유한 오너를 반환한다
     * @return 오너 UUID
     */
    public final @NotNull UUID getOwner() {
        return owner;
    }

    /**
     * 어트리뷰트 ID 반환
     * @return 어트리뷰트 ID
     */
    public final @NotNull String getId() {
        return id;
    }

    /**
     * 어트리뷰트 이름 반환
     * @return 어트리뷰트 이름
     */
    public final @NotNull String getName() {
        return name;
    }

    /**
     * 플레이어의 어트리뷰트 경험치를 설정
     * @param exp 경험치
     * @since 0.0.0
     */
    public final void setExp(double exp) {
        this.exp = Math.clamp(exp, 0.0, Double.MAX_VALUE);
        processLevelUp();
    }

    /**
     * 어트리뷰트 레벨을 설정
     * @param level 레벨
     * @since 0.0.0
     */
    public final void setLevel(int level) {
        this.level = Math.clamp(level, 0, Integer.MAX_VALUE);
    }

    /**
     * 어트리뷰트 경험치를 반환
     * @return 경험치
     * @since 0.0.0
     */
    public final double getExp() {
        return exp;
    }

    /**
     * 어트리뷰트 레벨을 반환
     * @return 레벨
     * @since 0.0.0
     */
    public final int getLevel() {
        return level;
    }

    /**
     * 어트리뷰트 경험치를 추가
     * @param exp 경험치
     * @since 0.0.0
     */
    public final void addExp(double exp) {
        this.exp += Math.clamp(exp, 0.0, Double.MAX_VALUE);
        processLevelUp();
    }

    /**
     * 어트리뷰트 레벨을 추가
     * @param level 레벨
     * @since 0.0.0
     */
    public final void addLevel(int level) {
        this.level += Math.clamp(level, 0, Integer.MAX_VALUE);
    }

    /**
     * 어트리뷰트 경험치를 제거
     * @param exp 경험치
     * @since 0.0.0
     */
    public final void removeExp(double exp) {
        this.exp -= Math.clamp(exp, 0.0, Double.MAX_VALUE);
        this.exp = Math.max(0.0, this.exp);
    }

    /**
     * 어트리뷰트 레벨을 제거
     * @param level 레벨
     * @since 0.0.0
     */
    public final void removeLevel(int level) {
        this.level -= Math.clamp(level, 0, Integer.MAX_VALUE);
        this.level = Math.max(this.level, 0);
    }

    /**
     * 플레이어가 다음 어트리뷰트 레벨업 까지 필요한 경험치 량을 반환
     * <p>
     * 파라메터로 1 을 입력한다면 1 -> 2 레벨업에 필요한 경험치를 반환
     * </p>
     * @param level 레벨
     * @since 0.0.0
     */
    public final double getExpRequired(int level) {
        return requiredExpHolder.getRequiredExp(level);
    }

    /**
     * 플레이어의 어트리뷰트 데이터를 저장
     * @since 0.0.0
     */
    public final void save() {
        IsekaiAttributeConfigurator configurator = new IsekaiAttributeConfigurator(String.format(path + "/%s.json", owner));
        try {
            configurator.save(this);
        } catch (ConfiguratorException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 플레이어 어트리뷰트 데이터 로드
     */
    public final void load() {
        IsekaiAttributeConfigurator configurator = new IsekaiAttributeConfigurator(String.format(path + "/%s.json", owner));
        try {
            IsekaiAttribute other = configurator.load();
            level = other.level;
            exp = other.exp;
        } catch (ConfiguratorException e) {
            throw new RuntimeException(e);
        }
    }

    private void processLevelUp() {
        double requiredExp = requiredExpHolder.getRequiredExp(level);
        if (exp >= requiredExp) {
            level++;
            exp -= requiredExp;
            processLevelUp();
        }
    }

}
