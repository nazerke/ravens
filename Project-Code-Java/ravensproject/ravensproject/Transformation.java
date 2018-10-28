package ravensproject;

public class Transformation {
  private String pair;
  private double similarity;
  private String transformationType;

  public Transformation(String pair, double similarity, String transformationType) {
    this.pair = pair;
    this.similarity = similarity;
    this.transformationType = transformationType;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }

  public double getSimilarity() {
    return similarity;
  }

  public void setSimilarity(double similarity) {
    this.similarity = similarity;
  }

  public String getTransformationType() {
    return transformationType;
  }

  public void setTransformationType(String transformationType) {
    this.transformationType = transformationType;
  }
}
