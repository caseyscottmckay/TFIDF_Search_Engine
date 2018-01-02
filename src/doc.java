/**
 * Created by casey on 12/6/2015.
 */
public class doc {
    public int docId;
    public int termDocFreq;
    public doc(){

    }
    public doc(int docId, int termDocFreq){
        this.docId=docId;
        this.termDocFreq=termDocFreq;

    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public void setTermDocFreq(int termDocFreq) {
        this.termDocFreq = termDocFreq;
    }
}
