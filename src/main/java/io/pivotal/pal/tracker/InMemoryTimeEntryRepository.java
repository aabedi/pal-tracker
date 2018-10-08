package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> repo = new ArrayList<TimeEntry>();
    private long entryId = 1;

    @Override
    public TimeEntry create(TimeEntry inputEntry) {
        inputEntry.setId(entryId++);
        repo.add(inputEntry);
        return inputEntry;
    }

    public TimeEntry find(long l) {
        for (TimeEntry entry : repo) {
            if (entry.getId().equals(l)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        return repo;
    }

    public TimeEntry update(long id, TimeEntry newEntry) {
        for (TimeEntry entry : repo) {
            if (entry.getId() == id) {
                newEntry.setId(id);
                repo.remove(entry);
                repo.add(newEntry);
                return repo.get(repo.size() - 1);
            }
        }
        return null;
    }

    public TimeEntry delete(long timeEntryId) {
        if (find(timeEntryId) != null) {
            repo.removeIf(element -> element.getId().equals(timeEntryId));
            return new TimeEntry();
        } else {
            return null;
        }
    }
}
