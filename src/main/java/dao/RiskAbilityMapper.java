package dao;

import entity.RiskAbility;

/**
 * Created by p on 2017/9/13.
 */
public interface RiskAbilityMapper {
    void insert(RiskAbility entity);
    void update(RiskAbility entity);
    RiskAbility get(String phone);
}
