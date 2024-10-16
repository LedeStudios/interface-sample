package net.ledestudio.isekailife.api.npc;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface IsekaiNpcManager {

    @NotNull Optional<IsekaiNPC> getById(@NotNull String id);

    @NotNull Optional<IsekaiNPC> getByName(@NotNull String name);

    @NotNull Optional<IsekaiNPC> getByRole(@NotNull String role);

    void load();

}
