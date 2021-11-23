package com.thpt.user.woker;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ElsWorker {

    public final ArrayBlockingQueue<DocWriteRequest> queue = new ArrayBlockingQueue<>(200);
    private BulkRequest bulkRequest = new BulkRequest();
    private static int WAIT_QUEUE_TIME = 10;
    
    @Autowired
    private RestHighLevelClient client;
    
    public ElsWorker(int waitQueueTime) {
    	this.WAIT_QUEUE_TIME = waitQueueTime;
    }
    
    public void start() {
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    DocWriteRequest<?> request = null;
                    try {
                        request = queue.poll(WAIT_QUEUE_TIME,TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                    }
                    addToBulk(request);
                    bulkRequest(request);
                }
            }
        }.start();
        
    }
    
    private void bulkRequest(DocWriteRequest<?> request) {
        if(request == null && bulkRequest.numberOfActions() > 0) {
            try {
                client.bulk(bulkRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
            }
        }
        bulkRequest = new BulkRequest();
    }
    
    private void addToBulk(DocWriteRequest<?> request) {
        if(request != null) {
            bulkRequest.add(request);
        }
    }
    
    public void push(DocWriteRequest<?> indexRequest) {
        queue.add(indexRequest);
    }
}
