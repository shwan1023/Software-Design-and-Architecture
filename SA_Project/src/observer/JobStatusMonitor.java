package observer;

public class JobStatusMonitor implements JobObserver {
    @Override
    public void update(Job job) {
        System.out.println("Job status updated: " + job.getJobDetails());
    }
}
