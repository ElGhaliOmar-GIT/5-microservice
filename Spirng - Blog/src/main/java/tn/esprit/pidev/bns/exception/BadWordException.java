package tn.esprit.pidev.bns.exception.siwarbacc;



public  class BadWordException extends RuntimeException{
        public BadWordException(String word) {
            super("The comment contains the inappropriate word: " + word);
        }
    }
