package com.pghavin.LUDecomposition;

public class Main {
    public static void main(String[] args) {
        
        Matrix M = new Matrix();

        M.addValue(1,0,0);
        M.addValue(0,1,0);
        M.addValue(0,2,0);
        M.addValue(0,3,0);
        M.addValue(0,4,0);
        M.addValue(2,5,0);
        M.addValue(0,6,0);
        M.addValue(0,7,0);

        M.addValue(0,0,1);
        M.addValue(3,1,1);
        M.addValue(0,2,1);
        M.addValue(0,3,1);
        M.addValue(0,4,1);
        M.addValue(0,5,1);
        M.addValue(0,6,1);
        M.addValue(4,7,1);

        M.addValue(5,0,2);
        M.addValue(0,1,2);
        M.addValue(0,2,2);
        M.addValue(6,3,2);
        M.addValue(0,4,2);
        M.addValue(0,5,2);
        M.addValue(0,6,2);
        M.addValue(0,7,2);

        M.addValue(0,0,3);
        M.addValue(0,1,3);
        M.addValue(0,2,3);
        M.addValue(0,3,3);
        M.addValue(7,4,3);
        M.addValue(0,5,3);
        M.addValue(8,6,3);
        M.addValue(0,7,3);

        M.addValue(0,0,4);
        M.addValue(0,1,4);
        M.addValue(9,2,4);
        M.addValue(0,3,4);
        M.addValue(0,4,4);
        M.addValue(0,5,4);
        M.addValue(0,6,4);
        M.addValue(0,7,4);

        M.addValue(0,0,5);
        M.addValue(0,1,5);
        M.addValue(0,2,5);
        M.addValue(0,3,5);
        M.addValue(0,4,5);
        M.addValue(10,5,5);
        M.addValue(0,6,5);
        M.addValue(0,7,5);

        M.addValue(0,0,6);
        M.addValue(11,1,6);
        M.addValue(0,2,6);
        M.addValue(0,3,6);
        M.addValue(0,4,6);
        M.addValue(0,5,6);
        M.addValue(0,6,6);
        M.addValue(0,7,6);

        M.addValue(0,0,7);
        M.addValue(0,1,7);
        M.addValue(0,2,7);
        M.addValue(0,3,7);
        M.addValue(0,4,7);
        M.addValue(0,5,7);
        M.addValue(0,6,7);
        M.addValue(12,7,7);

        System.out.println();
        System.out.println("RowDimension : ");
        System.out.println(M.getRowDimension());
        System.out.println();
        System.out.println("ColumnDimension : ");
        System.out.println(M.getColumnDimension());
        System.out.println();

        System.out.println("M : @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(M);

        LUDecomposition luDecomposition = new LUDecomposition(M);

        System.out.println("L : @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(luDecomposition.getL());
        System.out.println("U : @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(luDecomposition.getU());
        System.out.println("LU: @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(luDecomposition.getLU());

    }
}
