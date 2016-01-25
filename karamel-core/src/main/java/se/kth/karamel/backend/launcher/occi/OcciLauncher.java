package se.kth.karamel.backend.launcher.occi;

import org.jclouds.rest.AuthorizationException;
import se.kth.karamel.backend.launcher.Launcher;
import se.kth.karamel.backend.running.model.ClusterRuntime;
import se.kth.karamel.backend.running.model.MachineRuntime;
import se.kth.karamel.common.clusterdef.json.JsonCluster;
import se.kth.karamel.common.exception.InvalidOcciCredentialsException;
import se.kth.karamel.common.exception.KaramelException;
import se.kth.karamel.common.util.Confs;
import se.kth.karamel.common.util.OcciCredentials;
import se.kth.karamel.common.util.SshKeyPair;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;


/**
 * Created by Mamut on 2016-1-19.
 */
public final class OcciLauncher extends Launcher{
    
  private static final Logger logger = Logger.getLogger(OcciLauncher.class);
  public final OcciContext context;
  private static boolean TESTING = true;  
  private final SshKeyPair sshKeyPair;
  
  private Set<String> keys = new HashSet<>();

  public OcciLauncher(OcciContext occiContext, SshKeyPair sshKeyPair) throws KaramelException {
    if (occiContext == null) {
      throw new KaramelException("Register your valid credentials first :-| ");
    } else if (sshKeyPair == null) {
      throw new KaramelException("Choose your ssh keypair first :-| ");
    } else {
      this.context = occiContext;
      this.sshKeyPair = sshKeyPair;
      //Mtd todo update context
      /*logger.info(String.format("Account-Name='%s'", novaContext.getNovaCredentials().getAccountName()));
      logger.info(String.format("Public-key='%s'", sshKeyPair.getPublicKeyPath()));
      logger.info(String.format("Private-key='%s'", sshKeyPair.getPrivateKeyPath()));*/
    }
  }

  public static OcciContext validateCredentials(OcciCredentials occiCredentials)
          throws InvalidOcciCredentialsException {
        /*try {
            HTTPAuthentication authentication = new VOMSAuthentication(occiCredentials.getUserCertificatePath());
            //if custom certificates are needed
            authentication.setCAPath(occiCredentials.getSystemCertDir());
            Client client = new HTTPClient(URI.create("https://localhost:1234"), authentication);

            List<URI> list = client.list();
            }
        } catch (CommunicationException ex) {
            throw new RuntimeException(ex);
        }*/
      
      
    try {
      //logger.debug("Whatever xxxxxxxxxxxxxxxxxxxxxxx");
      logger.warn("Whatever xxxxxxxxxxxxxxxxxxxxxxx");
      OcciContext context = new OcciContext(occiCredentials);
      /*SecurityGroupApi securityGroupApi = context.getSecurityGroupApi();
      securityGroupApi.list();*/
      
      
      
      
      return context;
    } catch (AuthorizationException e) {
      throw new InvalidOcciCredentialsException("Invalid creds" , e);
    }
  }

  public static OcciCredentials readCredentials(Confs confs) {
    /*String accountId = confs.getProperty(NovaSetting.NOVA_ACCOUNT_ID_KEY.getParameter());
    String accessKey = confs.getProperty(NovaSetting.NOVA_ACCESSKEY_KEY.getParameter());
    String endpoint = confs.getProperty(NovaSetting.NOVA_ACCOUNT_ENDPOINT.getParameter());
    String novaRegion = confs.getProperty(NovaSetting.NOVA_REGION.getParameter());*/
    OcciCredentials occiCredentials = null;
    /*if (accountId != null && !accountId.isEmpty() && accessKey != null && !accessKey.isEmpty()
            && endpoint != null && !endpoint.isEmpty() && novaRegion != null && !novaRegion.isEmpty()) {
      occiCredentials = new OcciCredentials();
      novaCredentials.setAccountName(accountId);
      novaCredentials.setAccountPass(accessKey);
      novaCredentials.setEndpoint(endpoint);
      novaCredentials.setRegion(novaRegion);
    }*/
    return occiCredentials;
  }
  
  @Override
  public void cleanup(JsonCluster definition, ClusterRuntime runtime) throws KaramelException {
   /* runtime.resolveFailures();
    List<GroupRuntime> groups = runtime.getGroups();
    Set<String> allNovaVms = new HashSet<>();
    Set<String> allNovaVmsIds = new HashSet<>();
    Map<String, String> groupRegion = new HashMap<>();
    for (GroupRuntime group : groups) {
      group.getCluster().resolveFailures();
      Provider provider = UserClusterDataExtractor.getGroupProvider(definition, group.getName());
      if (provider instanceof Nova) {
        for (MachineRuntime machine : group.getMachines()) {
          if (machine.getVmId() != null) {
            allNovaVmsIds.add(machine.getVmId());
          }
        }
        JsonGroup jg = UserClusterDataExtractor.findGroup(definition, group.getName());
        List<String> vmNames = NovaSetting.NOVA_UNIQUE_VM_NAMES(group.getCluster().getName(), group.getName(),
                jg.getSize());
        allNovaVms.addAll(vmNames);
        groupRegion.put(group.getName(), novaContext.getNovaCredentials().getRegion());
      }
    }
    cleanup(definition.getName(), allNovaVmsIds, allNovaVms, groupRegion);*/
  }

  @Override
  public String forkGroup(JsonCluster definition, ClusterRuntime runtime, String name) throws KaramelException {
    /*JsonGroup jg = UserClusterDataExtractor.findGroup(definition,name);
    Provider provider = UserClusterDataExtractor.getGroupProvider(definition,name);
    Nova nova = (Nova) provider;
    Set<String> ports = new HashSet<>();
    ports.addAll(Settings.EC2_DEFAULT_PORTS);
    String groupId = createSecurityGroup(definition.getName(), jg.getName(), nova, ports);
    return groupId;*/
    return "";
  }

  @Override
  public List<MachineRuntime> forkMachines(JsonCluster definition, ClusterRuntime runtime, String name)
          throws KaramelException {
    /*Nova nova = (Nova) UserClusterDataExtractor.getGroupProvider(definition,name);
    JsonGroup definedGroup = UserClusterDataExtractor.findGroup(definition, name);
    GroupRuntime groupRuntime = UserClusterDataExtractor.findGroup(runtime,name);
    Set<String> groupIds = new HashSet<>();
    groupIds.add(groupRuntime.getId());

    String keypairName = NovaSetting.NOVA_KEYPAIR_NAME(runtime.getName(), novaContext.getNovaCredentials().getRegion());
    if(!keys.contains(keypairName)){
      uploadSshPublicKey(keypairName,nova,true);
      keys.add(keypairName);
    }
    return requestNodes(keypairName,groupRuntime,groupIds,Integer.valueOf(definedGroup.getSize()),nova);*/
    return null;
  }

}