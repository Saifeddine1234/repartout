package com.example.rparetout;

import com.google.firebase.database.Exclude;

public class DemandeClass {
        String T1 ,T0, T2 ,T3,T4,T5,T6,T7,T8,autre ,T9,T10,T11 , T12 , T13 ,T14 ,key, T15 ,T16;

    public DemandeClass() {
    }
    public DemandeClass(String t0,String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8,String autre , String t9, String t10, String t11) {
       T0 = t0;
        T1 = t1;
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        this.autre = autre;
        T7 = t7;
        T8 = t8;
        T9 = t9;
        T10 = t10;
        T11 = t11;
    }
    public DemandeClass(String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8,String autre , String t9, String t10, String t11) {
        T1 = t1;
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        this.autre = autre;
        T7 = t7;
        T8 = t8;
        T9 = t9;
        T10 = t10;
        T11 = t11;
    }
    public DemandeClass(String t0,String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, String autre , String t9, String t10, String t11 ,String t12 ,String t13 , String t14 , String t15,String t16) {
      T0 = t0;
        T1 = t1;
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        T7 = t7;
        T8 = t8;
        this.autre = autre ;
        T9 = t9;
        T10 = t10;
        T11 = t11;
        T12 = t12;
        T13 = t13;
        T14 = t14;
        T15 = t15;
        T16 = t16;
    }

    public String getT1() {
        return T1;
    }

    public void setT1(String t1) {
        T1 = t1;
    }

    public String getT12() {
        return T12;
    }

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public void setT12(String t12) {
        T12 = t12;
    }

    public String getT2() {
        return T2;
    }

    public void setT2(String t2) {
        T2 = t2;
    }

    public String getT3() {
        return T3;
    }

    public void setT3(String t3) {
        T3 = t3;
    }

    public String getT4() {
        return T4;
    }

    public void setT4(String t4) {
        T4 = t4;
    }

    public String getT5() {
        return T5;
    }

    public String getT16() {
        return T16;
    }

    public void setT16(String t16) {
        T16 = t16;
    }

    public void setT5(String t5) {
        T5 = t5;
    }

    public String getT6() {
        return T6;
    }

    public String getT13() {
        return T13;
    }

    public void setT13(String t13) {
        T13 = t13;
    }

    public String getT14() {
        return T14;
    }

    public void setT14(String t14) {
        T14 = t14;
    }

    public String getT15() {
        return T15;
    }

    public void setT15(String t15) {
        T15 = t15;
    }

    public void setT6(String t6) {
        T6 = t6;
    }

    public String getT7() {
        return T7;
    }

    public void setT7(String t7) {
        T7 = t7;
    }

    public String getT8() {
        return T8;
    }

    public void setT8(String t8) {
        T8 = t8;
    }

    public String getT9() {
        return T9;
    }

    public void setT9(String t9) {
        T9 = t9;
    }

    public String getT10() {
        return T10;
    }

    public void setT10(String t10) {
        T10 = t10;
    }

    public String getT11() {
        return T11;
    }

    public void setT11(String t11) {
        T11 = t11;
    }
    public String getT0() {
        return T0;
    }

    public void setT0(String t0) {
        T0 = t0;
    }
    @Exclude
    public String getKey() {
        return key;
    }
@Exclude
    public void setKey(String key) {
        this.key = key;
    }
}

