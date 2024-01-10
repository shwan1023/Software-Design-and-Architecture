package pool;

// 计算节点池接口
public interface ComputeNodePool {
    void processData(String data);
    void interactWithBatchService(String jobDetails);
    void interactWithKeyVault(String secretKey);
    void interactWithStorage(String storageData);
}
