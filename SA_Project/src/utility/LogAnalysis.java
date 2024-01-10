package utility;

import java.util.List;
import java.util.ArrayList;

public class LogAnalysis {
    private List<String> logs = new ArrayList<>();

    public void analyzeLog(String log) {
        System.out.println("Analyzing log: " + log);
        logs.add(log);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void logEvent(String event) {
        System.out.println("Log Analysis Event: " + event);
        analyzeLog(event);  // 分析并记录事件
    }
}
