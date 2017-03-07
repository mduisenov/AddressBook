package com.taxsee;

final class Modules {
  static Object[] list(DemoApp app) {
    return new Object[] {
        new DemoappModule(app),
        new InternalReleaseDemoAppModule()
    };
  }

  private Modules() {
    // No instances.
  }
}
