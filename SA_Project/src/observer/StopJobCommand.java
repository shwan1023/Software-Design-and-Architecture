package observer;

public class StopJobCommand implements JobCommand {
    @Override
    public void execute(Job job) {
        System.out.println("Stopping job: " + job.getJobDetails());
    }
}
