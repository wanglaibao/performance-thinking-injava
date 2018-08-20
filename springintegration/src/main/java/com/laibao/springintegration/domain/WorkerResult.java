package com.laibao.springintegration.domain;

/**
 * @author laibao wang
 * @date 2018-08-21
 * @version 1.0
 */
public class WorkerResult {
    private final int id;

    private final boolean success;

    public WorkerResult(int id, boolean success) {
        this.id = id;
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * 静态工厂方法
     * @param id
     * @param success
     * @return WorkerResult
     */
    public static WorkerResult of(int id, boolean success) {
        return new WorkerResult(id, success);
    }
}
