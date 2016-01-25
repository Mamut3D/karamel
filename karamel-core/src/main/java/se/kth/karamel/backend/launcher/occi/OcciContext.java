package se.kth.karamel.backend.launcher.occi;

import se.kth.karamel.common.util.OcciCredentials;


/**
 * Occi Context
 * Created by Mamut on 2015-05-16.
 */
public class OcciContext {
  private final OcciCredentials occiCredentials;
  /*private final ComputeService computeService;
  private final NovaApi novaApi;
  private final SecurityGroupApi securityGroupApi;
  private final KeyPairApi keyPairApi;*/

  public OcciContext(OcciCredentials credentials) {
    this.occiCredentials = credentials;
    /*ComputeServiceContext context = builder.credentials(credentials.getAccountName(),credentials.getAccountPass())
            .endpoint(credentials.getEndpoint())
            .buildView(ComputeServiceContext.class);
    this.computeService = context.getComputeService();
    this.novaApi = computeService.getContext().unwrapApi(NovaApi.class);
    this.securityGroupApi = novaApi.getSecurityGroupApi(credentials.getRegion()).get();
    this.keyPairApi = novaApi.getKeyPairApi(credentials.getRegion()).get();*/
  }

  public OcciCredentials getOcciCredentials() {
    return occiCredentials;
  }

  /*public ComputeService getComputeService() {
    return computeService;
  }

  public NovaApi getNovaApi() {
    return novaApi;
  }

  public SecurityGroupApi getSecurityGroupApi() {
    return securityGroupApi;
  }

  public KeyPairApi getKeyPairApi() {
    return keyPairApi;
  }*/

}
