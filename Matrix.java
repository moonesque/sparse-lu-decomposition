package com.pghavin.LUDecomposition;

import java.util.ArrayList;

public class Matrix {

    public int exC =0;
    public ArrayList<Double> values = new ArrayList<>();
    public ArrayList<Integer> row = new ArrayList<>();
    public ArrayList<Integer> sC = new ArrayList<>();

    public Matrix() {
        sC.add(0);
    }

    private Matrix(int exC, ArrayList<Double> values, ArrayList<Integer> row, ArrayList<Integer> sC) {
        this.exC = exC;
        this.values = values;
        this.row = row;
        this.sC = sC;
    }

    @Override
    protected Matrix clone()  {
        return new Matrix(Integer.valueOf(exC)
                , (ArrayList<Double>)values.clone()
                ,(ArrayList<Integer>)row.clone()
                , (ArrayList<Integer>)sC.clone());
    }

    public void addValue(double value, int row, int column){
        if (value==0) return;
        if(exC !=column){
            sC.add(values.size());
            exC++;
        }
        values.add(value);
        this.row.add(row);
    }

    public void setValue(double value,int row,int column){

        int t = (column != sC.size()-1) ? sC.get(column + 1) : this.row.size();
        boolean flag = true;

        if(value!=0) {
            if (t == sC.get(column) && t != this.row.size()) {
                t++;
                flag=false;
            }else if(t == sC.get(column) && t == this.row.size()){
                values.add(t,value);
                this.row.add(t,row);
            }
            for (int i = sC.get(column); i < t; i++) {
                if (this.row.get(i) == row) {
                    values.set(i, value);
                    break;
                } else if (this.row.get(i) > row) {
                    values.add(i, value);
                    this.row.add(i, row);
                    for (int j = column + 1; j < sC.size(); j++) {
                        sC.set(j, sC.get(j) + 1);
                    }
                    break;
                }else if (i == t - 1 && flag) {
                    values.add(t, value);
                    this.row.add(t, row);
                    for (int j = column + 1; j < sC.size(); j++) {
                        sC.set(j, sC.get(j) + 1);
                    }
                    break;
                }else if (i == t - 1 && !flag) {
                    values.add(i, value);
                    this.row.add(i, row);
                    for (int j = column + 1; j < sC.size(); j++) {
                        sC.set(j, sC.get(j) + 1);
                    }
                    break;
                }
            }

        }else{
            for (int i = sC.get(column); i < t; i++) {
                if (this.row.get(i) == row) {
                    values.remove(i);
                    this.row.remove(i);
                    for (int j = column + 1; j < sC.size(); j++) {
                        sC.set(j, sC.get(j) - 1);
                    }
                    break;
                }
            }
        }
    }

    public double getValue(int row,int column){
        int t=  (column != sC.size()-1) ? sC.get(column+1) : this.row.size();

            for (int i = sC.get(column); i < t; i++) {
                if (this.row.get(i) == row) {
                    return values.get(i);
                }
            }

        return 0;
    }

    public int getRowDimension() {
        int Max=0;
        for (int i = 0; i < row.size(); i++) {
            if(row.get(i)>Max){
                Max=row.get(i);
            }
        }
        return ++Max;
    }

    public int getColumnDimension() {
        return sC.size();
    }

    public Double[] getRow(int i){
        ArrayList<Double> doubles = new ArrayList<>();
        for (int j = 0; j < row.size(); j++) {
            if (row.get(j)==i){
                doubles.add(values.get(j));
            }
        }
        return doubles.toArray(new Double[0]);
    }

    public Double[] getColumn(int j){
        ArrayList<Double> doubles = new ArrayList<>();


        return doubles.toArray(new Double[0]);
    }

    public void E1(int row1,int row2){
        if (row1 != row2) {
            for (int k = 0; k < getColumnDimension(); k++) {
                double t = getValue(row1, k);
                setValue(getValue(row2, k), row1, k);
                setValue(t, row2, k);
            }
        }
    }

    public void E2(int row,double x){
        for (int i = 0; i < this.row.size(); i++) {
            if(this.row.get(i)==row){
                values.set(i, values.get(i)*x);
            }
        }
    }

    public void E3EditValue(double value,int row,int column){

        for (int i = sC.get(column); i < sC.get(column+1); i++) {
            if(this.row.get(i)==row){
                values.set(i,value+ values.get(i));
            }else if(this.row.get(i)>row){
                values.add(i,value);
                this.row.add(i,row);
                for (int j = column+1; j < sC.size(); j++) {
                    sC.set(j, sC.get(j)+1);
                }
                break;
            }
        }

    }

    public void E3(int row1,int row2,double x){

        for (int i = 0; i < row.size(); i++) {
            if(row.get(i)==row2){
                int j=0;
                while (sC.get(j)<=i){
                    j++;
                }
                E3EditValue(x* values.get(i),row1,j-1);
            }
        }

    }

    public Matrix getMatrix (int[] r, int j0, int j1) {
        Matrix X = new Matrix();
        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = j0; j <= j1; j++) {
                    X.addValue(this.getValue(r[i],j),i,j-j0);
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Submatrix indices");
        }
        return X;
    }

    @Override
    public String toString() {

        StringBuilder s= new StringBuilder();
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = 0; j < getColumnDimension(); j++) {
                s.append(getValue(i, j)).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
