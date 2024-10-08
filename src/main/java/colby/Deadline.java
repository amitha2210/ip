package colby;

import java.util.Objects;


/**
 * Class that represents deadline tasks
 * description refers to what the task is about
 * end is the deadline time/date for the task
 */
public class Deadline extends Task {
    private final String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }


    /**
     * Returns a string depiction of the task with the type shown as "[D]", followed by whether the
     * task is marked as done or not, the description of the task, and the deadline
     * @return string of the deadline task with its details
     */
    @Override
    public String toString() {
        try {
            String formattedEnd = formattedEnd = changeDateTime(end);
            if (formattedEnd.contains("Wrong")) {
                throw new ColbyException("Please input the date in the YYYY/MM/DD " +
                        "format and time in HH-MM format so that I can help you save them!\"");
            }
            return "[D]" + super.toString() + " (by: " + formattedEnd + ")";
        } catch (ColbyException e) {
            String message = e.getMessage();
            return message;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deadline deadline = (Deadline) o;
        return Objects.equals(description, deadline.description) &&
                Objects.equals(end, deadline.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, end);
    }
}
