package com.camunda.test;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

public class ProcessJUnitTest {

  @Rule
  public ProcessEngineRule engine = new ProcessEngineRule();

  @Test
  @Deployment(resources = {"process.bpmn"})
  public void testHappyPath() {

    ProcessInstance pi = runtimeService().startProcessInstanceByKey("MyProcess");
    assertThat(pi).isStarted().isWaitingAt("ATask");
    complete(task());
    assertThat(pi).isEnded();
  }
}
