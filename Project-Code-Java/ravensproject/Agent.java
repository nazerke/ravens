package ravensproject;

// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this file.
 *
 * You may also create and submit new files in addition to modifying this file.
 *
 * Make sure your file retains methods with the signatures: public Agent() public char
 * Solve(RavensProblem problem)
 *
 * These methods will be necessary for the project's main method to run.
 */
public class Agent {

  /**
   * The default constructor for your Agent. Make sure to execute any processing necessary before
   * your Agent starts solving problems here.
   *
   * Do not add any variables to this signature; they will not be used by main().
   */
  String HORIZONTAL = "HORIZONTAL";
  String VERTICAL = "VERTICAL";
  List<String> transformationTypes = new ArrayList<>();
  private double figureDPRs[] = new double[8];

  public Agent() {
    transformationTypes.add("rotate90");
    transformationTypes.add("rotate180");
    transformationTypes.add("rotate270");
    transformationTypes.add("identity");
    transformationTypes.add("flip");
    transformationTypes.add("mirror");
    transformationTypes.add("rotate90flip");
    transformationTypes.add("rotate270flip");
  }

  /**
   * The primary method for solving incoming Raven's Progressive Matrices. For each problem, your
   * Agent's Solve() method will be called. At the conclusion of Solve(), your Agent should return
   * an int representing its answer to the question: 1, 2, 3, 4, 5, or 6. Strings of these ints are
   * also the Names of the individual RavensFigures, obtained through RavensFigure.getName(). Return
   * a negative number to skip a problem.
   *
   * Make sure to return your answer *as an integer* at the end of Solve(). Returning your answer as
   * a string may cause your program to crash.
   *
   * @param problem the RavensProblem your agent should solve
   * @return your Agent's answer to this problem
   */
  public int Solve(RavensProblem problem) {
    if (problem.getProblemType().equals("2x2")) {
      return -1;
    }
    boolean[][] map = null;
    RavensFigure figureA = problem.getFigures().get("A");
    RavensFigure figureB = problem.getFigures().get("B");
    RavensFigure figureC = problem.getFigures().get("C");
    RavensFigure figureD = problem.getFigures().get("D");
    RavensFigure figureE = problem.getFigures().get("E");
    RavensFigure figureF = problem.getFigures().get("F");
    RavensFigure figureG = problem.getFigures().get("G");
    RavensFigure figureH = problem.getFigures().get("H");
    RavensFigure figure4 = problem.getFigures().get("2");

  //  double ipr = getIPR(figureD, figureF);
   // findAnswerByIprRatio(figureG,ipr, problem.getFigures());
  //  double figureBDpr = getDPR(figureB);
   // double figureCDpr = getDPR(figureC);
     /* double dprRatioDiagonal = getDPR(figureA)/getDPR(figureE);
      List<RavensFigure> answers = findAnswersByDprRatio(figureE, dprRatioDiagonal,
          problem.getFigures() );*/
     List<RavensFigure> answers = null;
    double dprRatioHorizontal = getDPR(figureB)/ getDPR(figureC);
    Map<Double,List<RavensFigure>> answersHorizontal = findAnswersByDprRatio(figureH,
        dprRatioHorizontal,
        problem.getFigures());
    double dprRatioVertical = getDPR(figureD)/ getDPR(figureG);
    Map<Double,List<RavensFigure>> answersVertical = findAnswersByDprRatio(figureF,
        dprRatioVertical,
        problem.getFigures());
    double dprRatioDiagonal = getDPR(figureA)/ getDPR(figureE);
    Map<Double,List<RavensFigure>> answersDiagonal = findAnswersByDprRatio(figureE,
        dprRatioDiagonal,
        problem.getFigures());
    double diagonalSimilarity = (double)answersDiagonal.keySet().toArray()[0];
    double horizontalSimilarity = (double)answersHorizontal.keySet().toArray()[0];
    double verticalSimilarity = (double)answersVertical.keySet().toArray()[0];

    if(diagonalSimilarity<=horizontalSimilarity&&diagonalSimilarity<=verticalSimilarity){
      answers = answersDiagonal.get(diagonalSimilarity);
    }
    else if(horizontalSimilarity<=diagonalSimilarity&&horizontalSimilarity<=verticalSimilarity){
      answers = answersHorizontal.get(horizontalSimilarity);
    }
    else if(verticalSimilarity<=horizontalSimilarity&&verticalSimilarity<=diagonalSimilarity){
      answers = answersVertical.get(verticalSimilarity);
    }
 /*   File file1 = new File("rotated180_fig_A.png");
    File file2 = new File(figureG.getVisual());
    File file3 = new File(figureE.getVisual());

    try {
      BufferedImage figureImage = ImageIO.read(file1);
      BufferedImage figureImage2 = ImageIO.read(file2);
      BufferedImage figureImage3 = ImageIO.read(file3);
      double similarity = getIPR(figureImage, figureImage2);
      double similarity2 = getIPR(figureImage, figureImage3);
      double diff = similarity - similarity2;

    }
      catch(IOException e ){

      }
    if (answers.size() > 10) {
      List<Transformation> transformations = affineTransformRow(answers, figureC, figureE
          , figureG);
      if(transformations.size() == 1){
        Transformation transformationToApply = transformations.get(0);
        if(transformationToApply.getPair().equals("AB")){
          applyTransformation(transformationToApply,figureH, problem.getFigures());
        }
        else if(transformationToApply.getPair().equals("AC")){
          applyTransformation(transformationToApply,figureG, problem.getFigures());
        }

      }
      else{
        //todo
      }
        
    }*/

     /* double dprRatioVertical = getDPR(figureD)/getDPR(figureG);
      int answer2 = findAnswerByDprRatio(figureF, dprRatioVertical, problem.getFigures());*/

     /* double iprRatioHorizontal = getIPR(figureB,figureC);
    int answer = findAnswerByIprRatio(figureH, iprRatioHorizontal, problem.getFigures() );*/
/*      File file = new File(figureC.getVisual());
      System.out.println(file.getAbsolutePath());
      try {
        BufferedImage figureImage = ImageIO.read(file);
        *//*BufferedImage rotatedImage = rotate(90,figureImage);
        drawImageToFile(rotatedImage);*//*
        BufferedImage flippedImage = flip(VERTICAL,figureImage);
        drawImageToFile(flippedImage,"flipped");

      } catch (IOException e) {
        e.printStackTrace();
      }*/

/*        //answer pics
      RavensFigure figure1 =  problem.getFigures().get("1");
      RavensFigure figure2 =  problem.getFigures().get("2");
      RavensFigure figure3 =  problem.getFigures().get("3");
      RavensFigure figure4 =  problem.getFigures().get("4");
      RavensFigure figure5 =  problem.getFigures().get("5");
      RavensFigure figure6 =  problem.getFigures().get("6");
      RavensFigure figure7 =  problem.getFigures().get("7");
      RavensFigure figure8 =  problem.getFigures().get("8");*/

   /*  double dprA = getDPR(figureA);
      double dprB = getDPR(figureB);
      double dprC = getDPR(figureC);
      double dprD = getDPR(figureD);
      double dprE = getDPR(figureE);
      double dprF = getDPR(figureF);
      double dprG = getDPR(figureG);
      double dprH = getDPR(figureH);

      double horizontalMeasurements[] = new double[8];

      for(int i = 1; i <= 8; i++){
        RavensFigure answerFigure = problem.getFigures().get(Integer.toString(i));
        double answerFigureDPR = getDPR(answerFigure);
        double alphaHorizontal = dprH/answerFigureDPR;
        double measurement = (dprA/dprB - alphaHorizontal)+(dprB/dprC - alphaHorizontal)+
            (dprD/dprE - alphaHorizontal)+(dprE/dprF - alphaHorizontal)+
            (dprG/dprH - alphaHorizontal);
        horizontalMeasurements[i-1] = measurement;
      }
      int lowestDPR = getLowestDPRIndex(horizontalMeasurements);*/
    int name = answers.get(0) == null ? -1 : Integer.parseInt(answers.get(0).getName());
    return name;
  }

  private void applyTransformation(Transformation transformationToApply, RavensFigure figureH, HashMap<String, RavensFigure> figures) {
  }

  private List<Transformation> affineTransformRow(List<RavensFigure> answers, RavensFigure figureA,
      RavensFigure figureB, RavensFigure figureC) {
    File figAFile = new File(figureA.getVisual());
    File figBFile = new File(figureB.getVisual());
    File figCFile = new File(figureC.getVisual());
    BufferedImage figA = null;
    BufferedImage figB = null;
    BufferedImage figC = null;
    List<Transformation> transformations = new ArrayList<>();
    try {
      figA = ImageIO.read(figAFile);
      figB = ImageIO.read(figBFile);
      figC = ImageIO.read(figCFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    double similarity = 0.0;
    BufferedImage transformedImage = null;
    for (String type : transformationTypes) {
      switch (type) {
        case "identity":
          similarity = getIPR(figA, figB);
          if(similarity == 1){
            transformations.add(new Transformation("AB", similarity, type));
            break;
          }
          similarity = getIPR(figA, figC);
          if(similarity == 1){
            transformations.add(new Transformation("AC", similarity, type));
            break;
          }
        case "flip":
          transformedImage = flip(HORIZONTAL, figA);
          drawImageToFile(transformedImage, "flipped_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "mirror":
          transformedImage = flip(VERTICAL, figA);
          drawImageToFile(transformedImage, "mirrored_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "rotate90":
          transformedImage = rotate(90, figA);
          drawImageToFile(transformedImage, "rotated90_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "rotate180":
          transformedImage = rotate(180, figA);
          drawImageToFile(transformedImage, "rotated180_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "rotate270":
          transformedImage = rotate(270, figA);
          drawImageToFile(transformedImage, "rotated270_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "rotate90flip":
          transformedImage = rotate(90, figA);
          transformedImage = flip(HORIZONTAL, transformedImage);
          drawImageToFile(transformedImage, "rotated90_flipped_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
        case "rotate270flip":
          transformedImage = rotate(270, figA);
          transformedImage = flip(HORIZONTAL, transformedImage);
          drawImageToFile(transformedImage, "rotated270_flipped_fig_A");
          similarity = getIPR(transformedImage, figB);
          transformations.add(new Transformation("AB", similarity, type));
          similarity = getIPR(transformedImage, figC);
          transformations.add(new Transformation("AC", similarity, type));
          break;
      }
    }

    Map<Double, List<Transformation>> similarityByTransformations =
             transformations.stream()
        .collect(Collectors.groupingBy(Transformation::getSimilarity));
    Optional<Entry<Double,List<Transformation>>> optionalFirstEntry =
        similarityByTransformations.entrySet().stream().findFirst();
    if(optionalFirstEntry != null){
      return (List<Transformation>) optionalFirstEntry.get();
    }
    return transformations;
  }

  private Map<Double, List<RavensFigure>> findAnswersByDprRatio(RavensFigure figureH,
      double dprRatioHorizontal,
      HashMap<String, RavensFigure> figures) {
    RavensFigure figure1 = figures.get("1");
    List<RavensFigure> answerOptions = new ArrayList<>();
    Map<Double, List<RavensFigure>> answerOptionsBySimilarityValue = new HashMap<>();
    double figureHdpr = getDPR(figureH);
    double similarityToCompare = Math.abs(dprRatioHorizontal - (figureHdpr / getDPR(figure1)));
    int possibleAnswer = 1;
    for (int i = 2; i <= 8; i++) {
      RavensFigure figureToAnalyse = figures.get(Integer.toString(i));
      double similarity = dprRatioHorizontal - figureHdpr / getDPR(figureToAnalyse);
      similarity = Math.abs(similarity);
      System.out.println("similarity" + similarity);
      if (Math.abs(similarity) < similarityToCompare) {
        similarityToCompare = similarity;
        possibleAnswer = i;
      } else if (Math.abs(similarity) == similarityToCompare) {
        if (answerOptions.isEmpty()) {
          answerOptions.add(figures.get(Integer.toString(possibleAnswer)));
        }
        List<RavensFigure> existingFiguresBySimilarity =
            answerOptionsBySimilarityValue.get(similarity);
        RavensFigure answerFigure = figures.get(Integer.toString(i));
        if(existingFiguresBySimilarity==null){
          answerOptions.add(answerFigure);
          answerOptionsBySimilarityValue.put(similarity,answerOptions);
        }
        else{
          answerOptionsBySimilarityValue.get(similarity).add(answerFigure);
        }
      }
     /* if(Math.abs(similarity) == similarityToCompare){
        similarityToCompare = similarity;
        possibleAnswer = i;
      }*/
    }
    if (answerOptions.isEmpty()) {
      answerOptions.add(figures.get(Integer.toString(possibleAnswer)));
      answerOptionsBySimilarityValue.put(similarityToCompare, answerOptions);
    }
    return answerOptionsBySimilarityValue;
  }

  private int getLowestDPRIndex(double[] horizontalMeasurements) {
    int lowestIndex = 0;
    for (int i = 0; i < horizontalMeasurements.length - 1; i++) {
      if (Double.compare(horizontalMeasurements[i + 1], horizontalMeasurements[lowestIndex])
          == -1) {
        lowestIndex = i + 1;
      }
    }
    return lowestIndex;
  }

  private BufferedImage rotate(int angle, BufferedImage image) {
    AffineTransform transform = new AffineTransform();
    transform.rotate(Math.toRadians(angle), image.getWidth() / 2, image.getHeight() / 2);
    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
    image = op.filter(image, null);
    return image;
  }

  private BufferedImage flip(String direction, BufferedImage image) {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage flippedImage = new BufferedImage(height, width,
        BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (direction.equals(HORIZONTAL)) {
          flippedImage.setRGB(x, (height - 1) - y, image.getRGB(x, y));
        } else if (direction.equals(VERTICAL)) {
          flippedImage.setRGB((width - 1) - x, y, image.getRGB(x, y));
        }
      }
    }
    return flippedImage;
  }

  private void drawImageToFile(BufferedImage image, String filename) {
    File outputfile = new File(filename + ".png");
    try {
      ImageIO.write(image, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean[][] getRGBValues(BufferedImage figureImage) {
    boolean map[][] = new boolean[figureImage.getHeight()][figureImage.getWidth()];

    for (int x = 0; x < figureImage.getWidth(); x++) {
      for (int y = 0; y < figureImage.getHeight(); y++) {
        int thisPixel = figureImage.getRGB(x, y);
        map[x][y] = (thisPixel != -1);
      }
    }
    return map;
  }

  private double getDPR(RavensFigure figure) {
    BufferedImage figureImage = null;
    try {
      File file = new File(figure.getVisual());
      System.out.println(file.getAbsolutePath());
      figureImage = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    boolean map[][] = getRGBValues(figureImage);
    double total = map[0].length * map.length;
    double dark = 0;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if (map[x][y]) {
          dark++;
        }
      }
    }
    return dark / total;
  }

  /*  private List<Double> getSimilarity(RavensFigure figure1,RavensFigure figure2) {
      getIntersection(figure1,figure2);
    }*/
  private double getIPR(RavensFigure figure1, RavensFigure figure2) {
    BufferedImage figureImg1 = null;
    BufferedImage figureImg2 = null;
    try {
      File file = new File(figure1.getVisual());
      figureImg1 = ImageIO.read(file);
      File file2 = new File(figure2.getVisual());
      figureImg2 = ImageIO.read(file2);
    } catch (IOException e) {
      e.printStackTrace();
    }
    boolean mapImg1[][] = getRGBValues(figureImg1);
    boolean mapImg2[][] = getRGBValues(figureImg2);
    double total = mapImg1[0].length * mapImg1.length + mapImg2[0].length * mapImg2.length;
    double common = 0;
    for (int x = 0; x < mapImg1.length; x++) {
      for (int y = 0; y < mapImg1[x].length; y++) {
        if (mapImg1[x][y] && mapImg2[x][y]) {
          common++;
        }
      }
    }
    return common / total;
  }

  private double getIPR(BufferedImage img1, BufferedImage img2) {
    boolean mapImg1[][] = getRGBValues(img1);
    boolean mapImg2[][] = getRGBValues(img2);
    double total = mapImg1[0].length * mapImg1.length + mapImg2[0].length * mapImg2.length;
    double common = 0;
    for (int x = 0; x < mapImg1.length; x++) {
      for (int y = 0; y < mapImg1[x].length; y++) {
        if (mapImg1[x][y] && mapImg2[x][y]) {
          common++;
        }
      }
    }
    return common / total;
  }

  private int findAnswerByIprRatio(RavensFigure figureH, double iprRatio,
      HashMap<String, RavensFigure> figures) {
    double similarityToCompare = Math.abs(iprRatio - getIPR(figureH, figures.get("1")));
    int possibleAnswer = -1;
    for (int i = 2; i <= 8; i++) {
      RavensFigure figureToAnalyse = figures.get(Integer.toString(i));
      double iprTemp = getIPR(figureH, figureToAnalyse);
      double similarity = Math.abs(iprRatio - iprTemp);
      System.out.println("similarity" + similarity);
      if (Math.abs(similarity) < similarityToCompare) {
        similarityToCompare = similarity;
        possibleAnswer = i;
      }
     /* if(Math.abs(similarity) == similarityToCompare){
        similarityToCompare = similarity;
        possibleAnswer = i;
      }*/
    }
    return possibleAnswer;
  }

  private int findAnswerByIprRatio(RavensFigure figureH, double iprRatio,
      List<RavensFigure> figures) {
    // double similarityToCompare= Math.abs(iprRatio - getIPR(figureH, figures.get("1")));
    int possibleAnswer = -1;
   /* for(int i=2; i<=8; i++){
      RavensFigure figureToAnalyse = figures.get(Integer.toString(i));
      double iprTemp = getIPR(figureH, figureToAnalyse);
      double similarity= Math.abs(iprRatio - iprTemp);
      System.out.println("similarity"+similarity);
      if(Math.abs(similarity) < similarityToCompare){
        similarityToCompare = similarity;
        possibleAnswer = i;
      }
     *//* if(Math.abs(similarity) == similarityToCompare){
        similarityToCompare = similarity;
        possibleAnswer = i;
      }*//*
    }*/
    return possibleAnswer;
  }

}

