package com.ctoutweb.aet.core.provider;

public class CoreFactoryProvider {
  private CoreFactoryProvider() {
    throw new IllegalStateException("Le classe InstanceLoader ne peut pas être instanciée");
  }
  private static class CoreFactoryHolder {
    private static final CoreFactory CORE_FACTORY = new CoreFactory();
  }

  public static CoreFactory getCoreFactory() {
    return CoreFactoryHolder.CORE_FACTORY;
  }
}
