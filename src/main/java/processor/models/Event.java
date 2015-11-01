package processor.models;

public class Event {
    private int eventType;
    private String eventCreated;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


    public String getEventCreated() {
        return eventCreated;
    }

    public void setEvent_created(String eventCreated) {
        this.eventCreated = eventCreated;
    }
}
