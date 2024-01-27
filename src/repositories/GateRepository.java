package repositories;

import models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private Map<Long, Gate> gates = new TreeMap<>();
    public Optional<Gate> findGateById(long gateId) {
        // db.execute(`Select * From gates where id = gateId`);

        if (gates.containsKey(gateId)) {
            return Optional.of(gates.get(gateId));
        }
        return Optional.empty();
    }
}
