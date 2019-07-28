package com.pghavin.LUDecomposition;

public class LUDecomposition implements java.io.Serializable {

    private Matrix LU;

    public Matrix getLU() {
        return LU;
    }

    public LUDecomposition setLU(Matrix LU) {
        this.LU = LU;
        return this;
    }

    private int m, n, pivsign;
    private int[] piv;

    public LUDecomposition (Matrix A) {

        LU = A.clone();
        m = A.getRowDimension();
        n = A.getColumnDimension();
        piv = new int[m];
        for (int i = 0; i < m; i++) {
            piv[i] = i;
        }
        pivsign = 1;
        Double[] LUrowi= new Double[n];
        Double[] LUcolj = new Double[m];

        for (int j = 0; j < n; j++) {

            for (int i = 0; i < m; i++) {
                LUcolj[i] = LU.getValue(i,j);
            }

            for (int i = 0; i < m; i++) {

                for (int t = 0; t < n; t++) {
                    LUrowi[t]=LU.getValue(i,t);
                }

                int kmax = Math.min(i,j);
                double s = 0.0;
                for (int k = 0; k < kmax; k++) {
                    s += LUrowi[k]*LUcolj[k];
                }

                LUrowi[j] = LUcolj[i] -= s;

                for (int t = 0; t < n; t++) {
                    LU.setValue(LUrowi[t],i,t);
                }
            }

            int p = j;
            for (int i = j+1; i < m; i++) {
                if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; k++) {
                    double t = LU.getValue(p, k);
                    LU.setValue(LU.getValue(j, k), p, k);
                    LU.setValue(t, j, k);
                }
                int k = piv[p]; piv[p] = piv[j]; piv[j] = k;
                pivsign = -pivsign;
            }

            if (j < m & LU.getValue(j,j) != 0.0) {
                for (int i = j+1; i < m; i++) {
                    LU.setValue(LU.getValue(i,j)/LU.getValue(j,j),i,j);
                }
            }

        }
    }

    public Matrix getL () {
        Matrix X = new Matrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < j) {
                    X.addValue(LU.getValue(j,i),j,i);
                } else if (i == j) {
                    X.addValue(1.0,j,i);
                }
            }
        }
        return X;
    }

    public Matrix getU () {
        Matrix X = new Matrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    X.addValue(LU.getValue(j,i),j,i);
                }
            }
        }
        return X;
    }

    public int[] getPivot () {
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            p[i] = piv[i];
        }
        return p;
    }

}