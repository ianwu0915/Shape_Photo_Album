package photoalbum.model;

public enum ShapeType {
  RECTANGLE("model.rectangle"),
  OVAL("model.oval");

  private final String type;
  ShapeType(String type) {
    this.type = type;
  }
  public String getType() {
    return type;
  }
}
