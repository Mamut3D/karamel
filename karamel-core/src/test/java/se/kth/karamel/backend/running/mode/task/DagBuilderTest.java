/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.karamel.backend.running.mode.task;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import se.kth.karamel.backend.ClusterDefinitionService;
import se.kth.karamel.backend.converter.ChefJsonGenerator;
import se.kth.karamel.backend.dag.Dag;
import se.kth.karamel.backend.machines.TaskSubmitter;
import se.kth.karamel.backend.running.model.ClusterRuntime;
import se.kth.karamel.backend.running.model.tasks.Task;
import se.kth.karamel.backend.running.model.tasks.DagBuilder;
import se.kth.karamel.common.clusterdef.json.JsonCluster;
import se.kth.karamel.common.util.Settings;
import se.kth.karamel.common.exception.KaramelException;
import se.kth.karamel.backend.mocking.MockingUtil;
import se.kth.karamel.common.stats.ClusterStats;

/**
 *
 * @author kamal
 */
public class DagBuilderTest {

  @Test
  public void testHubshubDag() throws IOException, KaramelException {
    TaskSubmitter dummyTaskSubmitter = new TaskSubmitter() {

      @Override
      public void submitTask(Task task) throws KaramelException {
        System.out.println(task.uniqueId());
        task.succeed();
      }

      @Override
      public void prepareToStart(Task task) throws KaramelException {
      }
    };

    Settings.CB_CLASSPATH_MODE = true;
    String ymlString = Resources.toString(Resources.getResource("se/kth/karamel/client/model/test-definitions/hopsworks.yml"), Charsets.UTF_8);
    JsonCluster definition = ClusterDefinitionService.yamlToJsonObject(ymlString);
    ClusterRuntime dummyRuntime = MockingUtil.dummyRuntime(definition);
    Map<String, JsonObject> chefJsons = ChefJsonGenerator.generateClusterChefJsons(definition, dummyRuntime);
    ClusterStats clusterStats = new ClusterStats();
    Dag dag = DagBuilder.getInstallationDag(definition, dummyRuntime, clusterStats, dummyTaskSubmitter, chefJsons);
    dag.validate();
    System.out.println(dag.print());
//    dag.start();
  }

  @Test
  public void testFlinkDag() throws IOException, KaramelException {
    TaskSubmitter dummyTaskSubmitter = new TaskSubmitter() {

      @Override
      public void submitTask(Task task) throws KaramelException {
        System.out.println(task.uniqueId());
        task.succeed();
      }

      @Override
      public void prepareToStart(Task task) throws KaramelException {
      }
    };

    Settings.CB_CLASSPATH_MODE = true;
    String ymlString = Resources.toString(Resources.getResource("se/kth/karamel/client/model/test-definitions/flink.yml"), Charsets.UTF_8);
    JsonCluster definition = ClusterDefinitionService.yamlToJsonObject(ymlString);
    ClusterRuntime dummyRuntime = MockingUtil.dummyRuntime(definition);
    Map<String, JsonObject> chefJsons = ChefJsonGenerator.generateClusterChefJsons(definition, dummyRuntime);
    ClusterStats clusterStats = new ClusterStats();
    Dag dag = DagBuilder.getInstallationDag(definition, dummyRuntime, clusterStats, dummyTaskSubmitter, chefJsons);
    dag.validate();
    System.out.println(dag.print());

    Assert.assertTrue(dag.isRoot("prepare storages on namenodes1"));
//    Assert.assertTrue(dag.hasDependency("prepare storages on namenodes1", "apt-get essentials on namenodes1"));
    Assert.assertTrue(dag.hasDependency("apt-get essentials on namenodes1", "install berkshelf on namenodes1"));
    Assert.assertTrue(dag.hasDependency("install berkshelf on namenodes1", "make solo.rb on namenodes1"));
    Assert.assertTrue(dag.hasDependency("make solo.rb on namenodes1", "clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on namenodes1"));
    Assert.assertTrue(dag.hasDependency("clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on namenodes1", "flink::install on namenodes1"));
    Assert.assertTrue(dag.hasDependency("flink::install on namenodes1", "flink::jobmanager on namenodes1"));
    Assert.assertTrue(dag.hasDependency("flink::install on namenodes1", "flink::wordcount on namenodes1"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on namenodes1", "hadoop::nn on namenodes1"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on namenodes1", "flink::install on namenodes1"));

    Assert.assertTrue(dag.isRoot("prepare storages on datanodes1"));
    Assert.assertTrue(dag.hasDependency("prepare storages on datanodes1", "apt-get essentials on datanodes1"));
    Assert.assertTrue(dag.hasDependency("install berkshelf on datanodes1", "make solo.rb on datanodes1"));
    Assert.assertTrue(dag.hasDependency("make solo.rb on datanodes1", "clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on datanodes1"));
    Assert.assertTrue(dag.hasDependency("clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on datanodes1", "flink::install on datanodes1"));
    Assert.assertTrue(dag.hasDependency("flink::install on datanodes1", "flink::taskmanager on datanodes1"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on datanodes1", "hadoop::dn on datanodes1"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on datanodes1", "flink::install on datanodes1"));

    Assert.assertTrue(dag.isRoot("prepare storages on datanodes2"));
    Assert.assertTrue(dag.hasDependency("prepare storages on datanodes2", "apt-get essentials on datanodes2"));
    Assert.assertTrue(dag.hasDependency("install berkshelf on datanodes2", "make solo.rb on datanodes2"));
    Assert.assertTrue(dag.hasDependency("make solo.rb on datanodes2", "clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on datanodes2"));
    Assert.assertTrue(dag.hasDependency("clone and vendor https://github.com/testorg/testrepo/tree/master/cookbooks/flink-chef on datanodes2", "flink::install on datanodes2"));
    Assert.assertTrue(dag.hasDependency("flink::install on datanodes2", "flink::taskmanager on datanodes2"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on datanodes2", "hadoop::dn on datanodes2"));
    Assert.assertTrue(dag.hasDependency("hadoop::install on datanodes2", "flink::install on datanodes2"));
  }
}
