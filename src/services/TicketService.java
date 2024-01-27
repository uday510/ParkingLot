package services;

import exceptions.GateNotFoundException;
import factories.SlotAssingmentStrategyFactory;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.RandomSlotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }
    public Ticket issueTicket(
            String vehicleNumber,
            String vehicleOwnerName,
            VehicleTypes vehicleType,
            Long gateId) throws GateNotFoundException {

        // create ticket here

        // 1. create ticket
        // 2. assign a spot
        // 3. return ticket

        Ticket newTicket = new Ticket();
        newTicket.setEntryTime(new Date());

        // Setting the Gate
        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if (gateOptional.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate gate = gateOptional.get();
        newTicket.setGeneratedAt(gate);
        newTicket.setGeneratedBy(gate.getCurrentOperator());

        // Setting the vehicle
        Vehicle savedVehicle;
        Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByNumber(vehicleNumber);

        if (vehicleOptional.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);
            vehicle.setVehicleType(vehicleType);

            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        } else {
            savedVehicle = vehicleOptional.get();
        }

        newTicket.setVehicle(savedVehicle);

        // Assign the slot
        SlotAssignmentStrategyType slotAssignmentStrategyType = parkingLotRepository
                .getParkingLotByGate(gate)
                .getSlotAssignmentStrategyType();

        ParkingSlot parkingSlot = SlotAssingmentStrategyFactory
                .getSlotForType(slotAssignmentStrategyType)
                .getSlot(gate, vehicleType);

        newTicket.setParkingSlot(parkingSlot);

        // store in DB.
        newTicket = ticketRepository.saveTicket(newTicket);
        newTicket.setNumber("TICKET - " + newTicket.getId());

        return newTicket;
    }
}
