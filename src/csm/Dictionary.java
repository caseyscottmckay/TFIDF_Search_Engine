package csm;

/**
 * Created by casey on 11/25/2015.
 */
public class Dictionary {
    public String term;
    public int termId;
    public int termFrequency;
    public int docId;
    public int DocFrecuency;
    public String PostingListPath;
    WordVector wv;


    public Dictionary(){

    }
    public Dictionary(int termId, int docFreq, String postingListPath){
        this.term = term;
        this.termId=termId;
        this.termFrequency=termFrequency;
        this.PostingListPath = postingListPath;
        this.wv = wv;
    }
    public Dictionary(int termId, String postingListPath){
        this.term = term;
        this.termId=termId;
        this.termFrequency=termFrequency;
        this.PostingListPath = postingListPath;
    }



}


