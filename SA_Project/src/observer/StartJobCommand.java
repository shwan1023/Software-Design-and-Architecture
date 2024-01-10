package observer;

public class StartJobCommand implements JobCommand {
    @Override
    public void execute(Job job) {
        System.out.println("Starting job: " + job.getJobDetails());
    }
}
