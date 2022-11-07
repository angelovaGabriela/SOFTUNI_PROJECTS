package com.softuni.battleships.services;

import com.softuni.battleships.models.Ship;
import com.softuni.battleships.models.dtos.StartBattleDTO;
import com.softuni.battleships.repositories.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {
    private final ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }


    public void attack(StartBattleDTO attackData) {
        Optional<Ship> attackerOption = this.shipRepository.findById((long) attackData.getAttackerId());
        Optional<Ship> defenderOption = this.shipRepository.findById((long) attackData.getDefenderId());

        if (attackerOption.isEmpty() || defenderOption.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOption.get();
        Ship defender = defenderOption.get();

        long newDefenderHealth = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            this.shipRepository.save(defender);
        }


    }
}
