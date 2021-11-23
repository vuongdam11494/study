package com.thpt.user.woker;

import org.elasticsearch.action.DocWriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thpt.user.config.WorkerConfig;

@Component
public class ElsWorkerPool {

    private ElsWorker[] workers;
    private int numberOfWorker;
    private int currentWorker = 0;

    public void initWorkers(WorkerConfig config) {
        this.numberOfWorker = config.getWorkerNum();
        workers = new ElsWorker[numberOfWorker];
        for (int i = 0; i < numberOfWorker; i++) {
            workers[i] = new ElsWorker(config.getWaitQueueTime());
            workers[i].start();
        }
    }

    public void push(DocWriteRequest<?> request) {
        workers[currentWorker].push(request);
        currentWorker = (currentWorker + 1) % numberOfWorker;
    }
}
