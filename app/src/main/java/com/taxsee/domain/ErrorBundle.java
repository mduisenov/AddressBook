package com.taxsee.domain;

public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
