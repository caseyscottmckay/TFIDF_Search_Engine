package csm;

/**
 * Created by casey on 11/29/2015.
 */
public class Vocab {
    public String term;
    public int termId;

    public Vocab(){

    }
    public Vocab(String term){
        this.term = term;
    }
    public Vocab(String term, int termId){
        this.term=term;
        this.termId=termId;
    }

    public String toString(){
        return this.term + " " + " " +this.termId;
    }

    public int getTermId() {
        return termId;
    }

    public String getTerm() {
        return term;
    }
}
