package net.ledestudio.isekailife.api.donation;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.UUID;


public class DonationEvent extends Event {

    private final @NotNull UUID player;
    private final @NotNull String sender;
    private final @Range(from = 0, to = Integer.MAX_VALUE) int donation;

    public DonationEvent(@NotNull UUID player, @NotNull String sender, @Range(from = 0, to = Integer.MAX_VALUE) int donation) {
        this.player = player;
        this.sender = sender;
        this.donation = donation;
    }

    public @NotNull UUID getPlayer() {
        return player;
    }

    public @NotNull String getSender() {
        return sender;
    }

    public @Range(from = 0, to = Integer.MAX_VALUE) int getDonation() {
        return donation;
    }

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

}
