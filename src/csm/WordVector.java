package csm;

/**
 * Created by casey on 11/25/2015.
 */
public class WordVector {
    public int termId;
    public int docId;
    public int termFreq;
    public String term;

    public WordVector(){

    }
    public WordVector(int termId, int docId, int termFreq){
        this.termFreq = termFreq;
        this.termId = termId;
        this.docId = docId;
    }

    public WordVector(int termId, int docId, int termFreq, String term){
        this.termFreq = termFreq;
        this.termId = termId;
        this.docId = docId;
        this.term = term;
    }

    public int getDocId() {
        return docId;
    }

    public int getTermFreq() {
        return termFreq;
    }

    public int getTermId() {
        return termId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public void setTermFreq(int termFreq) {
        this.termFreq = termFreq;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String toString(){
        return  this.termId + " " + this.docId + " " + this.termFreq;
    }
}
