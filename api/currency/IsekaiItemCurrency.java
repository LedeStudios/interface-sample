package net.ledestudio.isekailife.api.currency;

import net.ledestudio.isekailife.api.Isekai;
import net.ledestudio.isekailife.api.inventory.IsekaiInventory;
import net.ledestudio.isekailife.api.item.IsekaiItem;
import org.jetbrains.annotations.NotNull;

public class IsekaiItemCurrency extends IsekaiCurrency {

    private final transient IsekaiItem item;

    protected IsekaiItemCurrency(@NotNull String id, @NotNull String name, @NotNull IsekaiItem item) {
        super(id, name);
        this.item = item.copy();
    }

    @Override
    public double get() {
        return Isekai.getPlayer(owner).getInventory().getItemAmount(item);
    }

    @Override
    public boolean has(double amount) {
        return Isekai.getPlayer(owner).getInventory().getItemAmount(item) >= amount;
    }

    @Override
    public void add(double amount) {
        item.setAmount((int) amount);
        Isekai.getPlayer(owner).getInventory().addItem(item);
    }

    @Override
    public void remove(double amount) {
        item.setAmount((int) amount);
        Isekai.getPlayer(owner).getInventory().removeItem(item);
    }

    @Override
    public void set(double amount) {
        item.setAmount((int) amount);

        IsekaiInventory inv = Isekai.getPlayer(owner).getInventory();
        inv.clearItem(item);
        inv.addItem(item);
    }
}
