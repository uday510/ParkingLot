package controllers;
import dto.IssueTicketRequestDTO;
import dto.IssueTicketResponseDTO;
import models.ResponseType;
import models.Ticket;
import services.TicketService;

public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO) throws Exception {
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();

        try {
            Ticket ticket = ticketService.issueTicket(requestDTO.getVehicleNumber(), requestDTO.getVehicleOwnerName(), requestDTO.getVehicleType(), requestDTO.getGateId());
            response.setTicket(ticket);
            response.setResponseStatus(ResponseType.SUCCESS);
        } catch (Exception e) {
            response.setResponseStatus(ResponseType.FAILURE);
            throw new Exception(e);
        }
        return response;
    }
}

/*
 * 1. Needs to interact with client.
 * 2. Validations on the input.
 * 3. Call the service and provide it with the inputs.
 * 4. Receive the output from the service and send it over to the client in proper form.
 */
