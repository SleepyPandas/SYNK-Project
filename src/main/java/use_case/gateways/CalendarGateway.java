package use_case.gateways;

// CRUD GET (read), PUT (create and update), POST (create - if we don't have `id` or `uuid`), and DELETE (delete)

import entities.Completable;
import entities.Task;

public interface CalendarGateway {


//    public void getInstance();
//    public void createEvent();
//    public void updateEvent();
//    public void deleteEvent();
//    public void fetchEventsForDateRange();


    String createEvent(String userId, Task task);

    boolean updateEvent(String userId, String eventID, Task updatedTask);

    boolean deleteEvent(String userId, String eventID);

    String getCalendarById(String userID);

    String getCalendarByUsername(String username);

    String getCalendarByEmail(String email);
}