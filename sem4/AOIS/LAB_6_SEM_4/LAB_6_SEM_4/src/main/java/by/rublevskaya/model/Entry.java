package by.rublevskaya.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Entry {
    private String id;
    private String data;
    private boolean deleted;

    public Entry(String id, String data) {
        this.id = id;
        this.data = data;
        this.deleted = false;
    }
}