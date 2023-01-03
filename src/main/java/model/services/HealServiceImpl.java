package model.services;

import model.Character;
import model.abilities.HealService;

public class HealServiceImpl implements HealService {
    @Override
    public void heal(Character healer, Character healed) {
        if((healer.equals(healed) || areAllies(healed)) && healed.isAlive()) {
            healed.setHealth(healed.getHealth() + healer.getHeal());
            if(healed.getHealth() > 1000) {
                healed.setHealth(1000);
            }
        }
    }
}
