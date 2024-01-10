import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

enum JobAction {
    START, STOP
}

class Job {
    private String jobDetails;

    public Job(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public String getJobDetails() {
        return jobDetails;
    }
}

class BatchService {
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

    public void addJobCommand(JobAction action, JobCommand command) {
        jobCommands.put(action, command);
    }
}

interface JobCommand {
    void execute(Job job);
}

class StartJobCommand implements JobCommand {
    @Override
    public void execute(Job job) {
        System.out.println("Starting job: " + job.getJobDetails());
    }
}

class StopJobCommand implements JobCommand {
    @Override
    public void execute(Job job) {
        System.out.println("Stopping job: " + job.getJobDetails());
    }
}

interface JobObserver {
    void update(Job job);
}

class JobStatusMonitor implements JobObserver {
    @Override
    public void update(Job job) {
        System.out.println("Job status updated: " + job.getJobDetails());
    }
}

public class Main4 {
    public static void main(String[] args) {
        BatchService batchService = BatchService.getInstance();

        batchService.addJobCommand(JobAction.START, new StartJobCommand());
        batchService.addJobCommand(JobAction.STOP, new StopJobCommand());

        batchService.registerObserver(new JobStatusMonitor());

        Job job1 = new Job("Data Processing");
        batchService.executeJobAction(job1, JobAction.START);
        batchService.executeJobAction(job1, JobAction.STOP);
    }
}
