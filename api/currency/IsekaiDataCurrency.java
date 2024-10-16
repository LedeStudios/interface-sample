package net.ledestudio.isekailife.api.currency;

import net.ledestudio.isekailife.api.db.ConfiguratorException;
import net.ledestudio.isekailife.api.db.data.IsekaiCurrencyConfigurator;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class IsekaiDataCurrency extends IsekaiCurrency {

    private transient Path path;

    protected double value;

    public IsekaiDataCurrency() {
        super("", "");
        this.value = 0;
    }

    protected IsekaiDataCurrency(@NotNull String id, @NotNull String name, @NotNull Path path) {
        super(id, name);
        this.path = path;
    }

    @Override
    public double get() {
        return value;
    }

    @Override
    public boolean has(double amount) {
        return value >= amount;
    }

    @Override
    public void add(double amount) {
        value += amount;
    }

    @Override
    public void remove(double amount) {
        value = Math.max(0, value - amount);
    }

    @Override
    public void set(double amount) {
        value = amount;
    }

    public Path getPath() {
        return path;
    }

    public final void save() {
        IsekaiCurrencyConfigurator configurator = new IsekaiCurrencyConfigurator(String.format(path.toString() + "/%s.json", owner));
        try {
            configurator.save(this);
        } catch (ConfiguratorException e) {
            throw new RuntimeException(e);
        }
    }

    public final void load() {
        IsekaiCurrencyConfigurator configurator = new IsekaiCurrencyConfigurator(String.format(path.toString() + "/%s.json", owner));
        try {
            IsekaiDataCurrency other = configurator.load();
            value = other.value;
        } catch (ConfiguratorException e) {
            throw new RuntimeException(e);
        }
    }

}
