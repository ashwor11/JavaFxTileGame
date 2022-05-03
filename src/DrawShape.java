import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class DrawShape {


    public static Arc drawCurvedPipe(String status, int xCoordinate, int yCoordinate, boolean isEnter1ReallyEnter) {

        double centerOfTileXCorrdinateInCenterPane = (125.0/2 + 50 + xCoordinate * 130); //50 is the inset from left
        double centerOfTileYCorrdinateInCenterPane = (125.0/2 + yCoordinate * 130); //No inset from top

        Arc shape = new Arc();

        if (status.equals("00")) {

            if (yCoordinate == 1 || yCoordinate == 2 || yCoordinate == 3) {

                if (xCoordinate == 0) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 0, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 270, 90);
                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 0, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 270, 90);

                }

            } else {

                if (xCoordinate == 0) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2, 125.0 / 2, 0, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2, 125.0 / 2, 270, 90);

                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 0, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 270, 90);

                }
            }


        }


        if (status.equals("01")) {

            if (yCoordinate == 1 || yCoordinate == 2 || yCoordinate == 3) {

                if (xCoordinate == 3) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 180, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 270, -90);
                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 180, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 270, -90);

                }

            } else {

                if (xCoordinate == 3) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2, 125.0 / 2, 180, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2, 125.0 / 2, 270, -90);

                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, 180, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, 270, -90);

                }
            }

        }


        if (status.equals("10")) {

            if (yCoordinate == 0 || yCoordinate == 1 || yCoordinate == 2) {

                if (xCoordinate == 0) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, -270, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 90, -90);
                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, -270, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 90, -90);

                }

            } else {

                if (xCoordinate == 0) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane + 125.0 / 2, 125.0 / 2, 125.0 / 2, -270, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane + 125.0 / 2, 125.0 / 2, 125.0 / 2, 90, -90);

                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane + 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, -270, -90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane - 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, 90, -90);

                }
            }

        }


        if (status.equals("11")) {

            if (yCoordinate == 0 || yCoordinate == 1 || yCoordinate == 2) {

                if (xCoordinate == 3) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 90, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane + 125 + (125.0 / 2 + 5.0 / 2), 125.0 / 2, 125.0 / 2 + 5.0 / 2, 180, -90);
                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 90, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + (125.0 / 2 + 5.0 / 2), centerOfTileYCorrdinateInCenterPane + 125 - (125.0 / 2 + 5.0 / 2), 125.0 / 2 + 5.0 / 2, 125.0 / 2 + 5.0 / 2, 180, -90);

                }

            } else {

                if (xCoordinate == 3) {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2, centerOfTileYCorrdinateInCenterPane + 125.0 / 2, 125.0 / 2, 125.0 / 2, 90, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane - 125.0 / 2, centerOfTileYCorrdinateInCenterPane + 125 + 125.0 / 2, 125.0 / 2, 125.0 / 2, 180, -90);

                } else {

                    if (isEnter1ReallyEnter)
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane + 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, 90, 90);
                    else
                        shape = new Arc(centerOfTileXCorrdinateInCenterPane + 125.0 / 2 + 5.0 / 2, centerOfTileYCorrdinateInCenterPane + 125 - 125.0 / 2, 125.0 / 2 + 5.0 / 2, 125.0 / 2, 180, -90); //180 90 dı 270 -90 denenebilir

                }
            }

        }

        shape.setStrokeWidth(5);
    return shape;
    }

    public static Line drawEnd(String status, int xCoordinate, int yCoordinate, int enterCordinate) {

        Line shape = new Line();

        double centerOfTileXCorrdinateInCenterPane = (125.0/2 + 50 + xCoordinate * 130); //50 is the inset from left
        double centerOfTileYCorrdinateInCenterPane = (125.0/2 + yCoordinate * 130); //No inset from top

        if (status.equals("Vertical")) {
            if (enterCordinate == 1) {

                if (yCoordinate == 0)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - 125.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);

            }

            else if(enterCordinate == 3) {

                if (yCoordinate == 3)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);

            }

        }


        if (status.equals("Horizontal")) {
            if (enterCordinate == 2) {

                if (xCoordinate == 3)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane + 125.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);

            }

            else if (enterCordinate == 4) {

                if(xCoordinate == 0)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane - 125.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane);

            }

        }

        shape.setStrokeWidth(5);
        return shape;
    }

    
    public static Line drawPipe(String status, int xCoordinate, int yCoordinate, boolean isEnter1ReallyEnter) {

        Line shape = new Line();

        double centerOfTileXCorrdinateInCenterPane = (125.0/2 + 50 + xCoordinate * 130); //50 is the inset from left
        double centerOfTileYCorrdinateInCenterPane = (125.0/2 + yCoordinate * 130); //No inset from top

        if (status.equals("Vertical")) {

            if (yCoordinate == 1 || yCoordinate == 2) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2));

            }

            else if (yCoordinate == 0) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2), centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2));

            }

            else if (yCoordinate == 3) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2));

            }

        }




        if (status.equals("Horizontal")) {

            if (xCoordinate == 1 || xCoordinate == 2) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane);

            }

            else if (xCoordinate == 0) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane - 125.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane - 125.0/2, centerOfTileYCorrdinateInCenterPane);

            }

            else if (xCoordinate == 3) {

                if (isEnter1ReallyEnter) //Line başlangıç noktası animasyon için önemli, bu nedenle ona göre ayarlıyoruz
                    shape = new Line(centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane + 125.0/2, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane + 125.0/2, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane);

            }

        }
        shape.setStrokeWidth(5);
        return shape;
    }


    public static Line drawStarter(String status, int xCoordinate, int yCoordinate, int exitCoordinate) {

        Line shape = new Line();

        //Yörünge parçası çizimi
        double centerOfTileXCorrdinateInCenterPane = (125.0/2 + 50 + xCoordinate * 130); //50 is the inset from left
        double centerOfTileYCorrdinateInCenterPane = (125.0/2 + yCoordinate * 130); //No inset from top

        if (status.equals("Vertical")) {
            if (exitCoordinate == 1) {

                if (yCoordinate == 0)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - 125.0/2);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane - (125.0/2 + 5.0/2));

            }

            else if(exitCoordinate == 3) {

                if (yCoordinate == 3)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane + 125.0/2 + 5.0/2);

            }

        }


        if (status.equals("Horizontal")) {
            if (exitCoordinate == 2) {

                if (xCoordinate == 3)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane + 125.0/2, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane + 125.0/2 + 5.0/2, centerOfTileYCorrdinateInCenterPane);

            }

            else if (exitCoordinate == 4) {

                if(xCoordinate == 0)
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane - 125.0/2, centerOfTileYCorrdinateInCenterPane);
                else
                    shape = new Line(centerOfTileXCorrdinateInCenterPane, centerOfTileYCorrdinateInCenterPane, centerOfTileXCorrdinateInCenterPane - (125.0/2 + 5.0/2), centerOfTileYCorrdinateInCenterPane);

            }

        }
        shape.setStrokeWidth(5);
        return shape;
    }








}
