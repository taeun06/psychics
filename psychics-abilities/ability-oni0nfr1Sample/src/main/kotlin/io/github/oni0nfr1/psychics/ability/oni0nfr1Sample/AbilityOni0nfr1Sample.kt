package io.github.oni0nfr1.psychics.ability.oni0nfr1Sample

import io.github.monun.psychics.Ability
import io.github.monun.psychics.AbilityConcept
import io.github.monun.tap.event.EntityProvider
import io.github.monun.tap.event.TargetEntity
import io.github.monun.psychics.Psychics
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component

@Suppress("unused")
class AbilityOni0nfr1Sample : Ability<AbilityConcept>() {
    override fun onEnable() {
        psychic.registerEvents(SampleListener())
        psychic.registerEvents(Oni0nfr1Listener())
        psychic.runTaskTimer(MyAbilityTask(), 0L, 20L)
    }
}

class SampleListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.LEFT_CLICK_AIR) {
            event.player.sendMessage("능력 사용자가 왼손을 휘둘렀다.")
        }
    }
}

class Oni0nfr1Listener : Listener {
    @EventHandler
    @TargetEntity(KillerProvider::class)
    fun onPlayerKill(event: EntityDeathEvent) {
        event.drops.add(ItemStack(Material.STONE))
    }
    // 능력 사용자가 Entity를 죽일때 드랍 아이템에 돌을 추가
}

class KillerProvider : EntityProvider<EntityDeathEvent> {
    override fun getFrom(event: EntityDeathEvent): Entity? {
        return event.entity.killer
    }
}

class MyAbilityTask : Runnable {
    override fun run() {
        val taskMsg = Component.text("태스크 호출")
        Psychics.plugin.server.broadcast(taskMsg)
    }
}