package ravensproject;

// Uncomment these lines to access image processing.
//import java.awt.Image;
//import java.io.File;
//import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     * 
     * Do not add any variables to this signature; they will not be used by
     * main().
     * 
     */
    String HORIZONTAL = "HORIZONTAL";
    String VERTICAL = "VERTICAL";
    private double figureDPRs[] = new double[8];
    public Agent() {
        
    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return an int representing its
     * answer to the question: 1, 2, 3, 4, 5, or 6. Strings of these ints 
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName(). Return a negative number to skip a problem.
     * 
     * Make sure to return your answer *as an integer* at the end of Solve().
     * Returning your answer as a string may cause your program to crash.
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public int Solve(RavensProblem problem) {
      if(problem.getProblemType().equals("2x2"))
        return -1;
      boolean[][] map = null;
        RavensFigure figureA =  problem.getFigures().get("A");
        RavensFigure figureB =  problem.getFigures().get("B");
        RavensFigure figureC =  problem.getFigures().get("C");
        RavensFigure figureD =  problem.getFigures().get("D");
        RavensFigure figureE =  problem.getFigures().get("E");
        RavensFigure figureF =  problem.getFigures().get("F");
        RavensFigure figureG =  problem.getFigures().get("G");
        RavensFigure figureH =  problem.getFigures().get("H");
        RavensFigure figure4 =  problem.getFigures().get("2");

        double figureBDpr = getDPR(figureB);
        double figureCDpr = getDPR(figureC);
 /*     double dprRatioDiagonal = getDPR(figureA)/getDPR(figureE);
      List<RavensFigure> answers = findAnswersByDprRatio(figureE, dprRatioDiagonal,
          problem.getFigures() );*/
     double dprRatioHorizontal = figureBDpr/figureCDpr;
        List<RavensFigure> answers = findAnswersByDprRatio(figureH, dprRatioHorizontal,
            problem.getFigures() );

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

 /*     double dprA = getDPR(figureA);
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
      int lowestDPR = getLowestDPRIndex(horizontalMeasurements);
      return lowestDPR;*/
 int name = answers.get(0) == null ? -1:Integer.parseInt(answers.get(0).getName());
 return name;
    }

  private List<RavensFigure> findAnswersByDprRatio(RavensFigure figureH, double dprRatioHorizontal,
      HashMap<String, RavensFigure> figures) {
    RavensFigure figure1 =  figures.get("1");
    List<RavensFigure> answerOptions = new ArrayList<>();
    double figureHdpr = getDPR(figureH);
    double similarityToCompare= Math.abs(dprRatioHorizontal - (figureHdpr/getDPR(figure1)));
    int possibleAnswer = -1;
    for(int i=2; i<=8; i++){
      RavensFigure figureToAnalyse = figures.get(Integer.toString(i));
      double similarity = dprRatioHorizontal - figureHdpr/getDPR(figureToAnalyse);
          similarity = Math.abs(similarity);
      System.out.println("similarity"+similarity);
        if(Math.abs(similarity) < similarityToCompare){
          similarityToCompare = similarity;
          possibleAnswer = i;
        }
      else if(Math.abs(similarity) == similarityToCompare){
        if(answerOptions.isEmpty()){
          answerOptions.add(figures.get(Integer.toString(possibleAnswer)));
        }
        answerOptions.add(figures.get(Integer.toString(i)));
      }
     /* if(Math.abs(similarity) == similarityToCompare){
        similarityToCompare = similarity;
        possibleAnswer = i;
      }*/
    }
    if(answerOptions.isEmpty()){
      answerOptions.add(figures.get(Integer.toString(possibleAnswer)));
    }
    return answerOptions;
    }

  private int getLowestDPRIndex(double[] horizontalMeasurements) {
      int lowestIndex = 0;
      for(int i = 0; i< horizontalMeasurements.length-1; i++){
        if(Double.compare(horizontalMeasurements[i+1],horizontalMeasurements[lowestIndex]) == -1)
          lowestIndex = i+1;
      }
      return lowestIndex;
  }
  private BufferedImage rotate(int angle, BufferedImage image){
    AffineTransform transform = new AffineTransform();
    transform.rotate(Math.toRadians(angle), image.getWidth()/2, image.getHeight()/2);
    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
    image = op.filter(image, null);
    return image;
  }
  private BufferedImage flip(String direction, BufferedImage image){
    int height = image.getHeight();
    int width = image.getWidth();
      BufferedImage flippedImage = new BufferedImage(height,width,
          BufferedImage.TYPE_INT_RGB);

      for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++) {
            if(direction.equals(HORIZONTAL)){
              flippedImage.setRGB(x, (height-1)-y, image.getRGB(x,y));
            }
            else if(direction.equals(VERTICAL)){
                flippedImage.setRGB((width-1)-x, y, image.getRGB(x,y));
            }
        }
      }
    return flippedImage;
  }
  private void drawImageToFile(BufferedImage image, String filename){
    File outputfile = new File(filename+".png");
    try {
      ImageIO.write(image, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private boolean[][] getRGBValues(RavensFigure figure) {
      boolean map[][] = null;
    try{
    File file = new File(figure.getVisual());
    System.out.println(file.getAbsolutePath());
    BufferedImage figureImage = ImageIO.read(file);
      map = new boolean[figureImage.getHeight()][figureImage.getWidth()];

    for (int x = 0; x < figureImage.getWidth(); x++) {
      for (int y = 0; y < figureImage.getHeight(); y++) {
        int thisPixel = figureImage.getRGB(x, y);
        map[x][y] = (thisPixel != -1);
      }
    }
  } catch (IOException e) {
    e.printStackTrace();
  }

      for (int x = 0; x < map.length; x++) {
        for (int y = 0; y < map[x].length; y++) {
    //  System.out.print(Boolean.toString(map[x][y]) + ",");
        }

    System.out.println("");
  }
  return map;
  }

  private double getDPR(RavensFigure figure){
    boolean map[][] = getRGBValues(figure);
    double total = map[0].length*map.length;
      double dark = 0;
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if(map[x][y]){
          dark++;
        }
      }
    }
    return dark/total;
  }
/*  private List<Double> getSimilarity(RavensFigure figure1,RavensFigure figure2) {
    getIntersection(figure1,figure2);
  }*/
  private double getIPR(RavensFigure figure1,RavensFigure figure2){
      boolean mapImg1[][] = getRGBValues(figure1);
      boolean mapImg2[][] = getRGBValues(figure2);
    double total = mapImg1[0].length*mapImg1.length+mapImg2[0].length*mapImg2.length;
      double common = 0;
    for (int x = 0; x < mapImg1.length; x++) {
      for (int y = 0; y < mapImg1[x].length; y++) {
        if(mapImg1[x][y] && mapImg2[x][y]){
          common++;
        }
      }
    }
    return common/total;
  }

  private int findAnswerByIprRatio(RavensFigure figureH, double iprRatio,
      HashMap<String, RavensFigure> figures) {
    double similarityToCompare= Math.abs(iprRatio - getIPR(figureH, figures.get("1")));
    int possibleAnswer = -1;
    for(int i=2; i<=8; i++){
      RavensFigure figureToAnalyse = figures.get(Integer.toString(i));
      double iprTemp = getIPR(figureH, figureToAnalyse);
      double similarity= Math.abs(iprRatio - iprTemp);
      System.out.println("similarity"+similarity);
      if(Math.abs(similarity) < similarityToCompare){
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

