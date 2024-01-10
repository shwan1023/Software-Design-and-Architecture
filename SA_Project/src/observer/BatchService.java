package observer;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BatchService {
    private static BatchService instance;
    private Map<JobAction, JobCommand> jobCommands;
    private List<JobObserver> observers;

    private BatchService() {
        jobCommands = new HashMap<>();
        observers = new ArrayList<>();
    }

    public static BatchService getInstance() {
        if (instance == null) {
            instance = new BatchService();
        }
        return instance;
    }

    public void executeJobAction(Job job, JobAction action) {
        if (jobCommands.containsKey(action)) {
            jobCommands.get(action).execute(job);
            notifyObservers(job);
        }
    }

    public void registerObserver(JobObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Job job) {
        for (JobObserver observer : observers) {
            observer.update(job);
        }
    }

    public void logEvent(String event) {
        System.out.println("Batch Service Log: " + event);
    }

    public void addJobCommand(JobAction action, JobCommand command) {
        jobCommands.put(action, command);
    }
}
