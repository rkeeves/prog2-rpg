package hu.unideb.inf.factory;

import hu.unideb.inf.model.Enemy;

public class EnemyCreationService implements EntityFactory {

    @Override
    public Enemy create() {
        return Enemy.builder().build();
    }

}
