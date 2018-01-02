import java.util.ArrayList;

public class Dict {

	String term;
	int DocFrecuency;
	String PostingListPath;
	int documentTermFrequency;
	ArrayList<Integer> docIds;
    public Dict(){};
	
	public Dict(String term, int DocFrecuency, String PostingListPath){
		this.term = term;
		this.DocFrecuency = DocFrecuency;
		this.PostingListPath = PostingListPath;		
	}
	public Dict(String term, int DocFrecuency, String PostingListPath, int dTF, ArrayList<Integer> docIds) {
		this.term = term;
		this.DocFrecuency = DocFrecuency;
		this.PostingListPath = PostingListPath;
		this.documentTermFrequency = dTF;
		this.docIds=docIds;
	}
}
