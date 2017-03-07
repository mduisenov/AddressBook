package com.taxsee;

final class Modules {
  static Object[] list(DemoApp app) {
    return new Object[] {
        new DemoAppModule(app),
        new DebugDemoAppModule()
    };
  }

  private Modules() {
    // No instances.
  }
}
