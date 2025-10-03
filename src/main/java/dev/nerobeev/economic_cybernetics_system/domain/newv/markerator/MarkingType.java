package dev.nerobeev.economic_cybernetics_system.domain.newv.markerator;


public enum MarkingType {
  MATERIAL("MAT"),
  COMPONENT("CMP"),
  PRODUCT("PRD");

  private final String prefix;

  MarkingType(String prefix) {
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }
}
