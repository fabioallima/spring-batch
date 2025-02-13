package com.example.SpringBatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@StepScope
public class PrintHelloTasklet implements Tasklet {

    @Value("${name}")
    private String name;

    private static int executionCount = 1;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello, " + name + "! Execution " + executionCount++);

        return RepeatStatus.FINISHED;
    }
}
