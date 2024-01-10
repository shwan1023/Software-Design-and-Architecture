package utility;

import bridge.NetworkConnectionInterface;

public class PC {
    private NetworkConnectionInterface connection;

    public PC(NetworkConnectionInterface connection) {
        this.connection = connection;
    }

    // 连接到网络并记录日志
    public void connectToNetwork(AzureMonitor monitor, LogAnalysis logAnalysis) {
        connection.executeConnection();
        monitor.logTraffic("连接到网络");
        logAnalysis.analyzeLog("PC执行了网络连接");
    }


}
