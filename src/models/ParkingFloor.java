package models;

import java.util.List;

public class ParkingFloor extends BaseModel {

    private int floorNumber;

    private List<ParkingFloor> parkingSlots;

    private List<VehicleTypes> allowedVehicleTypes;

    private ParkingLotStatus parkingLotStatus;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingFloor> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingFloor> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public List<VehicleTypes> getAllowedVehicleTypes() {
        return allowedVehicleTypes;
    }

    public void setAllowedVehicleTypes(List<VehicleTypes> allowedVehicleTypes) {
        this.allowedVehicleTypes = allowedVehicleTypes;
    }
}
