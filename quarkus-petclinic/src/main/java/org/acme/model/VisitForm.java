package org.acme.model;

import java.time.LocalDate;
import javax.ws.rs.FormParam;

public class VisitForm {

    public @FormParam("date") LocalDate date;
    public @FormParam("description") String description;

    public Visits addVisit() {
        Visits newVisit = new Visits();
        newVisit.date = date;
        newVisit.description = description;
        return newVisit;
    }

}
