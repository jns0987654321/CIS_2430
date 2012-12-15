/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fei Song
 */
public class Die {

    private int maxFaces;
    private int faceValue;

    public Die( int max, int value ) {
        if( max > 0 && value > 0 && value <= max) {
           maxFaces = max;
           faceValue = value;
        } else {
            System.out.println("Invalid max and face values: " + max + " and " + value);
            System.exit(0);
        }
    }

    public Die() {
        maxFaces = 6;
        faceValue = 1;
    }

    public int getMaxFaces() {
        return maxFaces;
    }

    public boolean setMaxFaces(int maxFaces) {
        if( maxFaces > 0) {
          this.maxFaces = maxFaces;
          return true;
        } else {
           System.out.println("Invalid max faces: " + maxFaces);
           return false;
        }
    }

    public int getFaceValue() {
        return faceValue;
    }

    public boolean setFaceValue(int value) {
        if( value > 0 && value <= maxFaces) {
          faceValue = value;
          return true;
        } else {
            System.out.println("Invalid face value: " + value);
            return false;
        }
    }

    public boolean equals(Die other) {
        if( other == null )
            return false;
        else
            return (maxFaces == other.maxFaces &&
                    faceValue == other.faceValue);
    }

    public String toString() {
        return "max and face values are " + maxFaces + " and " +
                faceValue;
    }

    public void roll() {
        int value = (int)(Math.random() * maxFaces) + 1;
        faceValue = value;
    }

    public static void main(String[] args) {
        Die die1 = new Die(8, 1);
        System.out.println("Before rolling: " + die1.toString());
        die1.roll();
        System.out.println("After rolling: " + die1.toString());

    }
}
