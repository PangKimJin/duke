import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    public String type;
    public String name;
    public String status;
    public String date;
    public LocalDate processedDate;
    public String fileDate;
    public boolean hasProcessedDate = false;
    public int priority = 0;

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.status = "[✗]";
    }

    public void setDone() {
        this.status = "[✓]";
    }

    public int getStatusBinary() {
        if (this.status == "[✓]") {
            return 1;
        } else {
            return 0;
        }

    }

    public String getIcon() {
        if (this.type.equals("todo")) {
            return "[T]";
        } else if (this.type.equals("deadline")) {
            return "[D]";
        } else {                  // event
            return "[E]";
        }
    }

    /**
     * Processes the user input date into a localdate format.
     *
     * @return String This returns the formatted form of the localdate.
     */

    public String getProcessedDate() {
        String dateFormatted = "";
        this.date.split("\\s");
        for (int i = 1; i < this.date.split("\\s").length; i++) {
            if (i == 1) {
                dateFormatted += this.date.split("\\s")[i];
            } else {
                dateFormatted += " " + this.date.split("\\s")[i];
            }
        }
        LocalDate ld = LocalDate.parse(dateFormatted);
        this.processedDate = ld;
        this.hasProcessedDate = true;
        this.fileDate = ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return this.fileDate;
    }

    public String addProcessedDate(String dateFormatted) {
        //System.out.println(dateFormatted);
        int month = 0;
        int day = Integer.parseInt(dateFormatted.split("\\s")[1]);
        int year = Integer.parseInt(dateFormatted.split("\\s")[2]);
        if (dateFormatted.split("\\s")[0].equals("Jan")) {
            month = 1;
        } else if (dateFormatted.split("\\s")[0].equals("Feb")) {
            month = 2;
        } else if (dateFormatted.split("\\s")[0].equals("Mar")) {
            month = 3;
        } else if (dateFormatted.split("\\s")[0].equals("Apr")) {
            month = 4;
        } else if (dateFormatted.split("\\s")[0].equals("May")) {
            month = 5;
        } else if (dateFormatted.split("\\s")[0].equals("Jun")) {
            month = 6;
        } else if (dateFormatted.split("\\s")[0].equals("Jul")) {
            month = 7;
        } else if (dateFormatted.split("\\s")[0].equals("Aug")) {
            month = 8;
        } else if (dateFormatted.split("\\s")[0].equals("Sep")) {
            month = 9;
        } else if (dateFormatted.split("\\s")[0].equals("Oct")) {
            month = 10;
        } else if (dateFormatted.split("\\s")[0].equals("Nov")) {
            month = 11;
        } else {
            month = 12;
        }
        this.processedDate = LocalDate.of(year, month, day);
        this.hasProcessedDate = true;
        this.fileDate = this.processedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return this.fileDate;
    }

    public String getDescription() {
        if (this.type.equals("todo")) {
            return this.name;
        } else if (this.type.equals("deadline")) {
//            String dateFormatted = "";
//            this.date.split("\\s");
//            for (int i = 1; i < this.date.split("\\s").length; i++) {
//                if (i == 1) {
//                    dateFormatted += this.date.split("\\s")[i];
//                }
//                else {
//                    dateFormatted += " " + this.date.split("\\s")[i];
//                }
//            }
//            dateFormatted = "(by: " + dateFormatted + ")";
//            return this.name + dateFormatted;
            if (this.hasProcessedDate == false) {
                return this.name + " (by: " + this.getProcessedDate() + ")";
            } else {
                return this.name + " (by: " + this.fileDate + ")";
            }
        } else {                  // event
            if (this.hasProcessedDate == false) {
                return this.name + " (at: " + this.getProcessedDate() + ")";
            } else {
                return this.name + " (at: " + this.fileDate + ")";
            }
        }
    }

    public void setPriority(int priorityLevel) {
        this.priority = priorityLevel;
    }
    public void addDate(String date) {
        this.date = date;
    }
}
